/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProductDialog.java
 *
 * Created on Mar 13, 2010, 10:07:47 PM
 */
package khannedy.enterprise.penjualan.form.product;

import javax.swing.JTable;
import khannedy.enterprise.penjualan.form.Application;
import khannedy.enterprise.penjualan.form.Dialogable;
import khannedy.enterprise.penjualan.form.component.CrudPanel;

/**
 *
 * @author echo
 */
public class ProductDialog extends javax.swing.JDialog implements Dialogable {

    private TableModelProduct tableModelProduct;

    private ProductUnitDialog productUnitDialog;

    private ProductDialogController controller;

    /** Creates new form ProductDialog */
    public ProductDialog(Application parent) {
	super(parent, true);
	initComponents();

	tableModelProduct = new TableModelProduct();
	tableBarang.setModel(tableModelProduct);

	productUnitDialog = new ProductUnitDialog(this);

	controller = new ProductDialogController(this, productUnitDialog);

	crudPanel.getButtonHapus().addActionListener(controller);
	crudPanel.getButtonSegarkan().addActionListener(controller);
	crudPanel.getButtonTambah().addActionListener(controller);
	crudPanel.getButtonUbah().addActionListener(controller);
    }

    public JTable getTableBarang() {
	return tableBarang;
    }

    public TableModelProduct getTableModelProduct() {
	return tableModelProduct;
    }

    public CrudPanel getCrudPanel() {
	return crudPanel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableBarang = new javax.swing.JTable();
        crudPanel = new khannedy.enterprise.penjualan.form.component.CrudPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Barang");

        jScrollPane1.setViewportView(tableBarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(crudPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-640)/2, (screenSize.height-480)/2, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private khannedy.enterprise.penjualan.form.component.CrudPanel crudPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBarang;
    // End of variables declaration//GEN-END:variables

    public void dialog() {
	controller.refreshController();
	setVisible(true);
    }
}
