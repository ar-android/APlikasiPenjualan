/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import khannedy.enterprise.penjualan.form.category.CategoryDialog;
import khannedy.enterprise.penjualan.form.sell.SellDialog;
import khannedy.enterprise.penjualan.form.product.ProductDialog;
import khannedy.enterprise.penjualan.form.transaksi.TransactionDialog;

/**
 *
 * @author echo
 */
public class ApplicationController extends ApplicationHelper implements ActionListener {

    private Application application;

    public ApplicationController(Application application) {
	this.application = application;
    }

    // <editor-fold defaultstate="collapsed" desc="Action Controller">
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == application.getButtonMasterBarang()) {
	    productController();
	} else if (e.getSource() == application.getButtonMasterKategori()) {
	    categoryController();
	} else if (e.getSource() == application.getButtonMasterTransaksi()) {
	    transactionController();
	} else if (e.getSource() == application.getButtonJualBarang()) {
	    sellController();
	}
    }// </editor-fold>

    public void productController() {
	ProductDialog dialog = new ProductDialog(application);
	dialog.setLocationRelativeTo(application);
	dialog.dialog();
    }

    public void categoryController() {
	CategoryDialog dialog = new CategoryDialog(application);
	dialog.setLocationRelativeTo(application);
	dialog.dialog();
    }

    public void transactionController() {
	TransactionDialog dialog = new TransactionDialog(application);
	dialog.setLocationRelativeTo(application);
	dialog.dialog();
    }

    public void sellController() {
	SellDialog sellDialog = new SellDialog(application);
	sellDialog.setLocationRelativeTo(application);
	sellDialog.dialog();
    }
}
