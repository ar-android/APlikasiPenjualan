/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.transaksi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import khannedy.enterprise.penjualan.entity.DetailTransaction;

/**
 *
 * @author echo
 */
public class TableModelDetailTransaction extends AbstractTableModel {

    private List<DetailTransaction> list = new ArrayList<DetailTransaction>();

    public void setData(Collection<DetailTransaction> collection) {
	list.clear();
	list.addAll(collection);
	fireTableDataChanged();
    }

    public int getRowCount() {
	return list.size();
    }

    public int getColumnCount() {
	return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	    case 0:
		return list.get(rowIndex).getId();
	    case 1:
		return list.get(rowIndex).getProduct().getName();
	    case 2:
		return list.get(rowIndex).getQuantity();
	    case 3:
		return list.get(rowIndex).getTotal();
	    default:
		return null;
	}
    }

    @Override
    public String getColumnName(int column) {
	switch (column) {
	    case 0:
		return "Id";
	    case 1:
		return "Barang";
	    case 2:
		return "Banyak";
	    case 3:
		return "Total";
	    default:
		return null;
	}
    }
}
