/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.transaksi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import khannedy.enterprise.penjualan.entity.DetailTransaction;
import khannedy.enterprise.penjualan.entity.Transaction;

/**
 *
 * @author echo
 */
public class TableModelTransaction extends AbstractTableModel {

    private List<Transaction> list = new ArrayList<Transaction>();

    public void setData(List<Transaction> data) {
	list = data;
	fireTableDataChanged();
    }

    public Set<DetailTransaction> getDetail(int index) {
	return list.get(index).getDetails();
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
		return list.get(rowIndex).getName();
	    case 2:
		return list.get(rowIndex).getDate();
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
		return "Nama Pembeli";
	    case 2:
		return "Tanggal Penjualan";
	    case 3:
		return "Total Penjualan";
	    default:
		return null;
	}
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	if (columnIndex == 2) {
	    return Calendar.class;
	}
	return super.getColumnClass(columnIndex);
    }
}
