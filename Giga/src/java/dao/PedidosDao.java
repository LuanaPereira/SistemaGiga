
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Pedidos;
import util.JpaUtil;

public class PedidosDao {
    public boolean inserir(Pedidos ped) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(ped);
        tx.commit();
        manager.close();
        return true;
    }
}
