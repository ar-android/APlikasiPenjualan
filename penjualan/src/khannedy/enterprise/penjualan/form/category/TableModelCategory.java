/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.category;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import khannedy.enterprise.penjualan.entity.Category;

/**
 *
 * @author echo
 */
public class TableModelCategory extends AbstractTableModel {

    private List<Category> list = new ArrayList<Category>();

    public void setData(List<Category> list) {
	clear();
	this.list.addAll(list);
	fireTableDataChanged();
    }

    public void clear() {
	list.clear();
	fireTableDataChanged();
    }

    public void addCategory(Category category) {
	list.add(category);
	fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void removeCategory(int index) {
	list.remove(index);
	fireTableRowsDeleted(index, index);
    }

    public void setCategory(int index, Category category) {
	list.set(index, category);
	fireTableRowsUpdated(index, index);
    }

    public Category getCategory(int index) {
	return list.get(index);
    }

    public int getRowCount() {
	return list.size();
    }

    public int getColumnCount() {
	return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	    case 0:
		return list.get(rowIndex).getId();
	    case 1:
		return list.get(rowIndex).getName();
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
	    default:
		return null;
	}
    }
}
