/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.sell;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import khannedy.enterprise.penjualan.entity.DetailTransaction;
import khannedy.enterprise.penjualan.entity.Product;

/**
 *
 * @author echo
 */
public class TableModelDetailTransaksi extends AbstractTableModel {

    private List<DetailTransaction> list = new ArrayList<DetailTransaction>();

    public List<DetailTransaction> getList() {
	return list;
    }

    public void addDetail(Product product, Integer quantity) {
	DetailTransaction detail = getDetailByProduct(product);
	if (detail == null) {
	    detail = new DetailTransaction(product, quantity);
	    list.add(detail);
	    fireTableRowsInserted(list.size() - 1, list.size() - 1);
	} else {
	    detail.setQuantity(detail.getQuantity() + quantity);
	    setDetail(detail);
	}
    }

    public void setDetail(DetailTransaction detailTransaction) {
	for (int i = 0; i < list.size(); i++) {
	    DetailTransaction detail = list.get(i);
	    if (detail.getProduct().getId() == detailTransaction.getProduct().getId()) {
		list.set(i, detailTransaction);
		fireTableRowsUpdated(i, i);
	    }
	}
    }

    public DetailTransaction getDetailByProduct(Product product) {
	for (DetailTransaction detail : list) {
	    if (detail.getProduct().equals(product)) {
		return detail;
	    }
	}
	return null;
    }

    public void removeDetail(int index){
	list.remove(index);
	fireTableRowsDeleted(index, index);
    }

    public void clear() {
	list.clear();
    }

    public void deleteDetail(int index) {
	list.remove(index);
	fireTableRowsDeleted(index, index);
    }

    public int getRowCount() {
	return list.size();
    }

    public int getColumnCount() {
	return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	    case 0:
		return list.get(rowIndex).getProduct().getName();
	    case 1:
		return list.get(rowIndex).getQuantity();
	    case 2:
		return list.get(rowIndex).getTotal();
	    default:
		return null;
	}
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	    case 0:
		return "Barang";
	    case 1:
		return "Banyak";
	    case 2:
		return "Total";
	    default:
		return null;
	}
    }
}
