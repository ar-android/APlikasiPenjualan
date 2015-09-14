/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan;

import javax.persistence.EntityManager;
import khannedy.enterprise.penjualan.form.Application;
import khannedy.enterprise.penjualan.util.PersistenceContext;

/**
 *
 * @author echo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	PersistenceContext.getEntityManagerFactory();
	EntityManager manager = PersistenceContext.createEntityManager();
	PersistenceContext.closeEntityManager(manager);

	java.awt.EventQueue.invokeLater(new Runnable() {

	    public void run() {
		new Application().setVisible(true);
	    }
	});
    }
}
