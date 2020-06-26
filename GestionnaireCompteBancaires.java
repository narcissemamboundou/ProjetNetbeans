/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.OperationBancaire;
import entities.clientBancaire;
import static entities.clientBancaire_.id;
import static java.lang.System.in;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.SortOrder;

/**
 *
 * @author n.mamboundou
 */
@Stateless
@LocalBean
public class GestionnaireCompteBancaires {

    @PersistenceContext(unitName = "TP3_Banque_Michaud_Mamboundou-ejbPU")
    //@PersistenceContext
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   public void creerCompte(CompteBancaire c) {
        //insert dans la base de compte
        persist(c);
    }
    
    public void creerComptesTest() {
        System.out.print("*************** on entre dans creer compte ***********************");
       //creerCompte(new CompteBancaire("MAMBOUNDOU NARCISSE", 9150000,"wak"));
       /// creerCompte(new CompteBancaire("MICHAUD ANTOINE", 950000));
        //creerCompte(new CompteBancaire("DELAPORTE LUCIA", 20000));


        /*for (int i=0; i<20; i++){
            CompteBancaire cpt= new CompteBancaire("WAKANDAIS"+i, (int)(15000*Math.random()));
            creerCompte(cpt);
        }*/
        
        System.out.print("*************** comptes de test créés ***********************");
        
        //creerCompte(new CompteBancaire("SALIM DEUX-FOIS", 100000));
        System.out.print("*************** SALIM CREER ***********************");
    }
     
    public List<CompteBancaire> findAllBankAccount() {
        Query q = em.createQuery("select c from CompteBancaire c ");/*eq a select **/
        return q.getResultList();//renvoie la liste des resultats
    }
    
    
    //comptes des client connecter
    public List<CompteBancaire> findAllAccountByIdClientConnected(int idClientConnected) {
        Query q = em.createQuery("select c from CompteBancaire c where c.id=:idClientConnected");/*eq a select **/
        q.setParameter("idClientConnected", idClientConnected+1);
        return q.getResultList();//renvoie la liste des resultats
    }
   
 
   public String getUtilisateur(){   
        clientBancaire client = em.find(clientBancaire.class, id);
        return(client.getNom());
    };
    public List<CompteBancaire> findRange(int start, int nombreMax,String colname, String sort) {
        Query q = em.createQuery("select c from CompteBancaire c ");/*eq a select **/
        q.setFirstResult(start);
        q.setMaxResults(nombreMax);
        return q.getResultList();//renvoie la liste des resultats
    }
    public int count(){
        Query q = em.createQuery("select count(c) from CompteBancaire c ");/*eq a select **/
        Number n = (Number)q.getSingleResult();
        return n.intValue();
    }
   
    public void deposer(int id, double montant){
        
        System.out.println("**fonction desposer() gestion compte bancaire*");
       // CompteBancaire cb= new CompteBancaire(id, montant);
        CompteBancaire c = em.find(CompteBancaire.class, id);//connecter a la base tant qu'on est dans cet ejb
        //tant que em exist toute modification de ces attribue est repertorié dans la base 
        c.deposerArgent(montant);
        //si je veux modifier des donnée dans la base, cela doit se faire dans le jbd 
        //la ligne sera update 
    }
    
     public void retirer(int id, double montant){
        
        System.out.println("**fonction retirer() gestion compte bancaire*");
       // CompteBancaire cb= new CompteBancaire(id, montant);
        CompteBancaire c = em.find(CompteBancaire.class, id);//connecter a la base tant qu'on est dans cet ejb
        //tant que em exist toute modification de ces attribue est repertorié dans la base 
        c.retirerArgent(montant);
        //si je veux modifier des donnée dans la base, cela doit se faire dans le jbd 
        //la ligne sera update 
    }
     
     
     public void transferer(int idEmeteur,int idRecepteur, double montant){
        
        System.out.println("**fonction transferer() gestion compte bancaire*");
        CompteBancaire c_emeteur = em.find(CompteBancaire.class, idEmeteur);//connecter a la base tant qu'on est dans cet ejb
        //tant que em exist toute modification de ces attribue est repertorié dans la base 
        c_emeteur.retirerArgent(montant);
        //si je veux modifier des donnée dans la base, cela doit se faire dans le jbd 
        //la ligne sera update 
        CompteBancaire c_recepteur = em.find(CompteBancaire.class, idRecepteur);
        c_recepteur.deposerArgent(montant);
    }
    
     public void suprimerCompte(CompteBancaire cb) {
        //si le compte bancaire n'est pas connecter on ne peux pas le sup 
        //on va donc d'abord le reconnecter
        CompteBancaire cConnecte= em.merge(cb);
        em.remove(cConnecte);
        
    }
     
    public void persist(Object object) {
        em.persist(object);
    }



    public List<OperationBancaire> getOperations(int id2) {
         CompteBancaire c=em.find(CompteBancaire.class,id2);
        return c.getListeOperations();
    }
   public String getNomProprietaire(int id2) {
         CompteBancaire c=em.find(CompteBancaire.class,id2);
 
        return c.getNomProprietaire();
    }
   
   
    
}
