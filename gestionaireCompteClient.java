/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import entities.clientBancaire;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
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
public class gestionaireCompteClient {

    @PersistenceContext(unitName = "TP3_Banque_Michaud_Mamboundou-ejbPU")
    private EntityManager em;
    
     public List<CompteBancaire> findAllBankAccountById(int idClient) {
        Query q = em.createQuery("select c from CompteBancaire c where c.id="+idClient);/*eq a select **/
        return q.getResultList();//renvoie la liste des resultats
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
    public List<CompteBancaire> getCompesById(int id2) {
         clientBancaire c=em.find(clientBancaire.class,id2);
        return c.getListeComptes();
    }
}
