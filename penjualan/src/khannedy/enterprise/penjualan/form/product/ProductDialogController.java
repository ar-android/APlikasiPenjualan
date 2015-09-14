/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import khannedy.enterprise.penjualan.entity.Product;

/**
 *
 * @author echo
 */
public class ProductDialogController extends ProductDialogHelper implements ActionListener {

    private ProductDialog productDialog;

    private ProductUnitDialog productUnitDialog;

    public ProductDialogController(ProductDialog productDialog, ProductUnitDialog productUnitDialog) {
	this.productDialog = productDialog;
	this.productUnitDialog = productUnitDialog;
    }

    // <editor-fold defaultstate="collapsed" desc="Action Controller">
    public void actionPerformed(ActionEvent e) {
	JButton button = (JButton) e.getSource();
	if (productDialog.getCrudPanel().getButtonTambah() == button) {
	    addController();
	} else if (productDialog.getCrudPanel().getButtonUbah() == button) {
	    editController();
	} else if (productDialog.getCrudPanel().getButtonHapus() == button) {
	    deleteController();
	} else if (productDialog.getCrudPanel().getButtonSegarkan() == button) {
	    refreshController();
	}
    }// </editor-fold>

    public void addController() {
	productUnitDialog.setLocationRelativeTo(productDialog);
	Product product = productUnitDialog.createProduct();
	if (product != null) {
	    TableModelProduct model = productDialog.getTableModelProduct();
	    model.addProduct(product);
	}
    }

    public void editController() {
	productUnitDialog.setLocationRelativeTo(productDialog);
	int selected = productDialog.getTableBarang().getSelectedRow();
	if (selected != -1) {
	    TableModelProduct model = productDialog.getTableModelProduct();
	    Product product = model.getProduct(selected);
	    Product result = productUnitDialog.changeProduct(product);
	    if (result != null) {
		model.setProduct(selected, result);
	    }
	}
    }

    public void deleteController() {
	int selected = productDialog.getTableBarang().getSelectedRow();
	if (selected != -1) {
	    if (confirm(productDialog, "Hapus Barang", "Anda yakin akan menghapus barang?")) {
		TableModelProduct model = productDialog.getTableModelProduct();
		Product product = model.getProduct(selected);
		EntityManager manager = createManager();
		try {
		    begin(manager);
		    manager.remove(manager.merge(product));
		    commit(manager);
		} catch (Throwable t) {
		    trace(t);
		    rollback(manager);
		} finally {
		    close(manager);
		}
	    }
	}
    }

    public void refreshController() {
	TableModelProduct model = productDialog.getTableModelProduct();
	EntityManager manager = createManager();
	try {
	    begin(manager);
	    List<Product> list = manager.createQuery("select a from Product a order by a.id").getResultList();
	    model.setData(list);
	    commit(manager);
	} catch (Throwable t) {
	    trace(t);
	    rollback(manager);
	} finally {
	    close(manager);
	}
    }
}
