/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author echo
 */
public final class PersistenceContext {

    private static final EntityManagerFactory entityManagerFactory;

    static {
	entityManagerFactory = Persistence.createEntityManagerFactory("penjualan-pu");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
	return entityManagerFactory;
    }

    public static EntityManager createEntityManager() {
	return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManager(EntityManager manager) {
	if (manager != null) {
	    if (manager.isOpen()) {
		manager.close();
	    }
	}
    }

    public static void destroy() {
	getEntityManagerFactory().close();
    }
}
