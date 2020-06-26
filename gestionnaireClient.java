/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.clientBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author n.mamboundou
 */
@Stateless
@LocalBean
public class gestionnaireClient {

    @PersistenceContext(unitName = "TP3_Banque_Michaud_Mamboundou-ejbPU")
    private EntityManager em;
    
    
    
   
        public void creerUtilisateur(clientBancaire client){
         persist(client);
    }
    
    public void creerUtilisateur(){
        creerUtilisateur(new clientBancaire("MAMBOUNDOU", "Narcisse", "Wakanda", "admin", "06409197"));
        creerUtilisateur(new clientBancaire("MICHAUD", "Antoine", "Bidart", "cruz@nar", "06409197"));
        creerUtilisateur(new clientBancaire("MOPOLO", "Gabriel", "Nice", "cruz@nar", "06409197"));
        creerUtilisateur(new clientBancaire("LESAS", "Anne-Marie", "Nice", "lesas@nice.fr", "06409197"));
        creerUtilisateur(new clientBancaire("CAMBERABERO", "Antoine", "Biarriz", "antoine@nar", "06409197"));
        creerUtilisateur(new clientBancaire("DELAPORTE", "Lucia", "Pau", "lucia@nar", "06409197"));
        creerUtilisateur(new clientBancaire("YOLO", "Brice", "Cean", "brun@nar", "06409197"));
        creerUtilisateur(new clientBancaire("BERNADINE", "Joseph", "Bordeaux", "brun@nar", "06409197"));
        creerUtilisateur(new clientBancaire("PATOU", "Jr", "Nice", "brun@nar", "06409197"));
        creerUtilisateur(new clientBancaire("ROUMANET", "Cedric", "Nice", "brun@nar", "06409197"));
        creerUtilisateur(new clientBancaire("ROCHA DA SILVA", "Marcelo", "Porto", "marcelo@nar", "06409197"));
    }
    
     public void creerUtilisateurByInfo(String nom, String prenom,String ville, String mail, String numero){
        creerUtilisateur(new clientBancaire( nom, prenom, ville, mail, numero));
        
    }
    public List<clientBancaire> findAllBankAccountClient() {
        Query q = em.createQuery("select c from clientBancaire c ");/*eq a select **/
        return q.getResultList();//renvoie la liste des resultats
    }
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int count(){
        Query q = em.createQuery("select count(c) from clientBancaire c ");/*eq a select **/
        Number n = (Number)q.getSingleResult();
        return n.intValue();
    }
    
    
    //les comptes du client connecté
    public List<CompteBancaire> getComptesByID(int id2) {
         clientBancaire client=em.find(clientBancaire.class,id2);
        return client.listeComptes();
    }
    
  /* public List<clientBancaire> getInfoClientById(int id2) {
         clientBancaire client=em.find(clientBancaire.class,id2);
         
        return client.;
    }*/    
    
     
    public clientBancaire login(String mail, String motDePasse) {
        Query q = em.createQuery("Select client from clientBancaire client where client.mail = :mail");
        System.out.println("test identification "+mail+" moy de pasdse "+motDePasse);
        q.setParameter("mail", mail);
        try {
            clientBancaire client = (clientBancaire) q.getSingleResult();
            if (client == null) {
                return client;
            }
            if (!client.getMotDePasse().equals(motDePasse)) {
                return null;
            } else {
                return client;
            }

        } catch (Exception e) {
               return null; 
        }

    }
    
    public String getNomProprietaireById(int id2) {
         clientBancaire client=em.find(clientBancaire.class,id2);
        return client.getNomProprietaire();
    }
    
    public void addNewCompteByID(int id2, String str) {
         clientBancaire client=em.find(clientBancaire.class,id2);
         client.clientBancaireByType(str);
    }

     public void persist(Object object) {
        em.persist(object);
    }
     
}
