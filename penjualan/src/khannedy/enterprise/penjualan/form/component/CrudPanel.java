/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CrudPanel.java
 *
 * Created on Mar 13, 2010, 1:19:36 AM
 */
package khannedy.enterprise.penjualan.form.component;

import javax.swing.JButton;

/**
 *
 * @author echo
 */
public class CrudPanel extends javax.swing.JPanel {

    /** Creates new form CrudPanel */
    public CrudPanel() {
	initComponents();
    }

    public JButton getButtonHapus() {
	return buttonHapus;
    }

    public JButton getButtonSegarkan() {
	return buttonSegarkan;
    }

    public JButton getButtonTambah() {
	return buttonTambah;
    }

    public JButton getButtonUbah() {
	return buttonUbah;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonTambah = new javax.swing.JButton();
        buttonUbah = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonSegarkan = new javax.swing.JButton();

        buttonTambah.setText("Tambah");
        add(buttonTambah);

        buttonUbah.setText("Ubah");
        add(buttonUbah);

        buttonHapus.setText("Hapus");
        add(buttonHapus);

        buttonSegarkan.setText("Segarkan");
        add(buttonSegarkan);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSegarkan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JButton buttonUbah;
    // End of variables declaration//GEN-END:variables
}
