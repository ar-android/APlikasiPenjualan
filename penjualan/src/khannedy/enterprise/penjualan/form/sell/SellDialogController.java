/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.sell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import khannedy.enterprise.penjualan.entity.DetailTransaction;
import khannedy.enterprise.penjualan.entity.Product;
import khannedy.enterprise.penjualan.entity.Transaction;

/**
 *
 * @author echo
 */
public class SellDialogController extends SellDialogHelper implements ActionListener {

    private SellDialog sellDialog;

    private DecimalFormat format = new DecimalFormat("#,##0.00");

    public SellDialogController(SellDialog sellDialog) {
	this.sellDialog = sellDialog;
	format.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(new Locale("in", "ID")));
    }

    // <editor-fold defaultstate="collapsed" desc="Action Controller">
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == sellDialog.getButtonJual()) {
	    sellController();
	} else if (e.getSource() == sellDialog.getButtonTambah()) {
	    addController();
	} else if (e.getSource() == sellDialog.getButtonHapus()) {
	    deleteController();
	}
    }// </editor-fold>

    public void addController() {
	Long id = null;
	Integer quantity = null;

	try {
	    id = Long.parseLong(sellDialog.getTextKodeBarang().getText());
	} catch (NumberFormatException e) {
	    warning(sellDialog, "Kesalahan", "Id barang harus nomber");
	    return;
	}

	try {
	    quantity = Integer.parseInt(sellDialog.getTextBanyak().getText());
	} catch (NumberFormatException e) {
	    warning(sellDialog, "Kesalahan", "Banyak barang harus nomor");
	    return;
	}

	EntityManager manager = createManager();
	try {
	    begin(manager);
	    Product product = manager.find(Product.class, id);
	    if (product == null) {
		warning(sellDialog, "Kesalahan", "Barang dengan kode-" + id + " tidak ditemukan");
	    } else {
		sellDialog.getTableModelDetailTransaksi().addDetail(product, quantity);
	    }
	    commit(manager);
	} catch (Throwable t) {
	    t.printStackTrace();
	    rollback(manager);
	} finally {
	    close(manager);
	}

	resetController();
	refreshController();
    }

    public void sellController() {

	String name = sellDialog.getTextNamaPembeli().getText();
	if (name.trim().isEmpty()) {
	    warning(sellDialog, "Kesalahan", "Nama pembeli belum dimasukkan");
	    return;
	}

	List<DetailTransaction> list = sellDialog.getTableModelDetailTransaksi().getList();

	if (list.size() < 1) {
	    warning(sellDialog, "Kesalahan", "Belum ada barang yang akan dibeli");
	    return;
	}

	Transaction transaction = new Transaction();
	for (DetailTransaction detail : list) {
	    transaction.addDetail(detail);
	}

	EntityManager manager = createManager();
	try {
	    begin(manager);
	    transaction.getTotal();
	    transaction.setName(name);
	    manager.persist(transaction);
	    commit(manager);
	    sellDialog.dispose();
	} catch (Throwable t) {
	    t.printStackTrace();
	    rollback(manager);
	} finally {
	    close(manager);
	}
    }

    public void resetController() {
	sellDialog.getTextKodeBarang().setText("");
	sellDialog.getTextBanyak().setText("1");
	sellDialog.getTextKodeBarang().requestFocusInWindow();
    }

    public void deleteController() {
	int selected = sellDialog.getTabelTransaksi().getSelectedRow();
	if (selected != -1) {
	    TableModelDetailTransaksi model = sellDialog.getTableModelDetailTransaksi();
	    model.removeDetail(selected);
	}
	refreshController();
    }

    public void refreshController() {
	TableModelDetailTransaksi model = sellDialog.getTableModelDetailTransaksi();
	Double data = 0.0D;
	for (DetailTransaction detail : model.getList()) {
	    data += detail.getTotal();
	}
	sellDialog.getLabelTotal().setText("Rp. " + format.format(data));
    }
}
