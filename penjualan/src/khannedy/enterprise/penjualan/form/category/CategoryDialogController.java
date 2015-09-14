/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.form.category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.EntityManager;
import khannedy.enterprise.penjualan.entity.Category;

/**
 *
 * @author echo
 */
public class CategoryDialogController extends CategoryDialogHelper implements ActionListener {

    private CategoryDialog categoryDialog;

    private CategoryUnitDialog categoryUnitDialog;

    public CategoryDialogController(CategoryDialog categoryDialog, CategoryUnitDialog dialog) {
	this.categoryDialog = categoryDialog;
	this.categoryUnitDialog = dialog;
    }

    // <editor-fold defaultstate="collapsed" desc="Action Controller">
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == categoryDialog.getCrudPanel().getButtonTambah()) {
	    addController();
	} else if (e.getSource() == categoryDialog.getCrudPanel().getButtonUbah()) {
	    editController();
	} else if (e.getSource() == categoryDialog.getCrudPanel().getButtonHapus()) {
	    deleteController();
	} else if (e.getSource() == categoryDialog.getCrudPanel().getButtonSegarkan()) {
	    refreshController();
	}
    }// </editor-fold>

    public void addController() {
	categoryUnitDialog.setLocationRelativeTo(categoryDialog);
	Category category = categoryUnitDialog.createCategory();
	if (category != null) {
	    TableModelCategory model = categoryDialog.getTableModelCategory();
	    model.addCategory(category);
	}
    }

    public void editController() {
	categoryUnitDialog.setLocationRelativeTo(categoryDialog);
	int selected = categoryDialog.getTableKategori().getSelectedRow();
	if (selected != -1) {
	    TableModelCategory model = categoryDialog.getTableModelCategory();
	    Category selectedCategory = model.getCategory(selected);
	    Category result = categoryUnitDialog.changeCategory(selectedCategory);
	    if (result != null) {
		model.setCategory(selected, result);
	    }
	}
    }

    public void deleteController() {
	int selected = categoryDialog.getTableKategori().getSelectedRow();
	if (selected != -1) {
	    if (confirm(categoryDialog, "Hapus Kategori", "Anda yakin akan menghapus kategori?")) {
		TableModelCategory model = categoryDialog.getTableModelCategory();
		Category category = model.getCategory(selected);
		EntityManager manager = createManager();
		try {
		    begin(manager);
		    Long count = (Long) manager.createQuery("select count(a) from Product a where a.category.id = :id").setParameter("id", category.getId()).getSingleResult();
		    if (count < 1) {
			manager.remove(manager.merge(category));
			model.removeCategory(selected);
		    } else {
			warning(categoryDialog, "Gagal Menghapus Kategori", "Kategori tidak dapat dihapus karena digunakan oleh Barang");
		    }
		    commit(manager);
		} catch (Throwable t) {
		    t.printStackTrace();
		    rollback(manager);
		} finally {
		    close(manager);
		}
	    }
	}
    }

    public void refreshController() {
	TableModelCategory model = categoryDialog.getTableModelCategory();
	EntityManager manager = createManager();
	try {
	    begin(manager);
	    List<Category> list = manager.createQuery("select a from Category a").getResultList();
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
