/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CategoryUnitDialog.java
 *
 * Created on Mar 13, 2010, 1:42:55 AM
 */
package khannedy.enterprise.penjualan.form.category;

import javax.persistence.EntityManager;
import javax.swing.JDialog;
import khannedy.enterprise.penjualan.entity.Category;
import khannedy.enterprise.penjualan.util.PersistenceContext;

/**
 *
 * @author echo
 */
public class CategoryUnitDialog extends javax.swing.JDialog {

    private Category category;

    /** Creates new form CategoryUnitDialog */
    public CategoryUnitDialog(JDialog parent) {
	super(parent, true);
	initComponents();
    }

    public Category createCategory() {
	category = null;
	buttonTambah.setText("Tambah");
	textNama.setText("");
	setTitle("Tambah Categori");

	setVisible(true);
	return category;
    }

    public Category changeCategory(Category category) {
	this.category = category;
	buttonTambah.setText("Ubah");
	textNama.setText(category.getName());
	setTitle("Ubah Kategori");

	setVisible(true);
	return this.category;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textNama = new javax.swing.JTextField();
        buttonBatal = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Judul");

        jLabel1.setText("Nama Kategori :");

        buttonBatal.setText("Batal");
        buttonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBatalActionPerformed(evt);
            }
        });

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNama, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonTambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBatal)
                    .addComponent(buttonTambah))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBatalActionPerformed
	// TODO add your handling code here:
	category = null;
	setVisible(false);
    }//GEN-LAST:event_buttonBatalActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
	// TODO add your handling code here:
	if (buttonTambah.getText().equalsIgnoreCase("tambah")) {
	    EntityManager manager = PersistenceContext.createEntityManager();
	    try {
		manager.getTransaction().begin();
		Category c = new Category();
		c.setName(textNama.getText());
		manager.persist(c);
		category = c;
		manager.getTransaction().commit();
	    } catch (Throwable t) {
		t.printStackTrace();
		manager.getTransaction().rollback();
	    } finally {
		manager.close();
	    }
	} else if (buttonTambah.getText().equalsIgnoreCase("ubah")) {
	    EntityManager manager = PersistenceContext.createEntityManager();
	    try {
		manager.getTransaction().begin();
		Category c = category;
		c.setName(textNama.getText());
		category = manager.merge(c);
		manager.getTransaction().commit();
	    } catch (Throwable t) {
		t.printStackTrace();
		manager.getTransaction().rollback();
	    } finally {
		manager.close();
	    }
	}
	setVisible(false);
    }//GEN-LAST:event_buttonTambahActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBatal;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField textNama;
    // End of variables declaration//GEN-END:variables
}
