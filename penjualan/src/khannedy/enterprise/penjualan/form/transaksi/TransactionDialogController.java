/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.transaksi;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import khannedy.enterprise.penjualan.entity.DetailTransaction;
import khannedy.enterprise.penjualan.entity.Transaction;

/**
 *
 * @author echo
 */
public class TransactionDialogController extends TransactionDialogHelper implements ListSelectionListener {

    private TransactionDialog transactionDialog;

    public TransactionDialogController(TransactionDialog transactionDialog) {
	this.transactionDialog = transactionDialog;
    }

    // <editor-fold defaultstate="collapsed" desc="Table Controller">
    public void valueChanged(ListSelectionEvent e) {
	selectedTransactionController();
    }// </editor-fold>

    public void selectedTransactionController() {
	int selected = transactionDialog.getTableTransaksi().getSelectedRow();
	if (selected != -1) {
	    TableModelTransaction model = transactionDialog.getTableModelTransaction();
	    Set<DetailTransaction> details = model.getDetail(selected);
	    TableModelDetailTransaction detailModel = transactionDialog.getTableModelDetailTransaction();
	    detailModel.setData(details);
	}
    }

    public void refreshController() {
	EntityManager manager = createManager();
	try {
	    begin(manager);
	    List<Transaction> list = manager.createQuery("select a from Transaction a order by a.id").getResultList();
	    TableModelTransaction model = transactionDialog.getTableModelTransaction();
	    model.setData(list);
	    commit(manager);
	} catch (Throwable t) {
	    t.printStackTrace();
	    rollback(manager);
	} finally {
	    close(manager);
	}
    }
}
