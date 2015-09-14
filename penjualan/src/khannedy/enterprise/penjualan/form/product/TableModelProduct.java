/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.product;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import khannedy.enterprise.penjualan.entity.Product;

/**
 *
 * @author echo
 */
public class TableModelProduct extends AbstractTableModel {

    private List<Product> list = new ArrayList<Product>();

    public void setData(List<Product> data) {
	list.clear();
	list.addAll(data);
	fireTableDataChanged();
    }

    public void addProduct(Product product) {
	list.add(product);
	fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void removeProduct(int index) {
	list.remove(index);
	fireTableRowsDeleted(index, index);
    }

    public void setProduct(int index, Product product) {
	list.set(index, product);
	fireTableRowsUpdated(index, index);
    }

    public Product getProduct(int index) {
	return list.get(index);
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
		return list.get(rowIndex).getPrice();
	    case 3:
		return list.get(rowIndex).getCategory().getName();
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
		return "Nama";
	    case 2:
		return "Harga";
	    case 3:
		return "Kategori";
	    default:
		return null;
	}
    }
}
