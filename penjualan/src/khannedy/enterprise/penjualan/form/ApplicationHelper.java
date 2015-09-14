/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form;

import java.awt.Component;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import khannedy.enterprise.penjualan.util.PersistenceContext;

/**
 *
 * @author echo
 */
public class ApplicationHelper {

    public EntityManager createManager() {
	return PersistenceContext.createEntityManager();
    }

    public void close(EntityManager manager) {
	if (manager.isOpen()) {
	    manager.close();
	}
    }

    public void commit(EntityManager manager) {
	if (manager.getTransaction().isActive()) {
	    if (!manager.getTransaction().getRollbackOnly()) {
		manager.getTransaction().commit();
	    }
	}
    }

    public void rollback(EntityManager manager) {
	if (manager.getTransaction().isActive()) {
	    manager.getTransaction().rollback();
	}
    }

    public void begin(EntityManager manager) {
	if (!manager.getTransaction().isActive()) {
	    manager.getTransaction().begin();
	}
    }

    public boolean confirm(Component parent, String title, String message) {
	return JOptionPane.OK_OPTION
		== JOptionPane.showConfirmDialog(parent,
		message, title, JOptionPane.OK_CANCEL_OPTION);
    }

    public void alert(Component parent, String title, String message) {
	JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void warning(Component parent, String title, String message) {
	JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }
}
