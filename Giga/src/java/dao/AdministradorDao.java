
package dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import modelo.Administrador;
import util.JpaUtil;

public class AdministradorDao implements Serializable { 
    
    public boolean inserir(Administrador adm) { 
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(adm);
        tx.commit();
        manager.close();
        return true;
    } 
    public Administrador autenticar(Administrador adm){ 
        Administrador temp = null; 
        EntityManager manager = JpaUtil.getEntityManager();
        TypedQuery<Administrador> query = manager.createQuery("SELECT a FROM Administrador a WHERE a.login = :login AND a.senha = :senha", Administrador.class); 
        query.setParameter("login", adm.getLogin());
        query.setParameter("senha", adm.getSenha());
        try{
            temp = query.getSingleResult(); 
        }
        catch(Exception e){ 
            
        }     //aqui poderia haver um tratamento de exceção mais decente
        finally{
            manager.close();
        }        
        return temp;
    }

}