/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author echo
 */
@Entity
@Table(name = "table_detail_transaction")
public class DetailTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "generator_detail_transaction", allocationSize = 1,
    pkColumnName = "k", pkColumnValue = "detail_transaction",
    valueColumnName = "v", table = "table_generator")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator_detail_transaction")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_transaction", nullable = false)
    private Transaction transaction;

    public DetailTransaction() {
	this(null, null);
    }

    public DetailTransaction(Product product, Integer quantity) {
	this.product = product;
	this.quantity = quantity;
    }

    public Transaction getTransaction() {
	return transaction;
    }

    public void setTransaction(Transaction transaction) {
	this.transaction = transaction;
    }

    public Double getTotal() {
	total = product.getPrice() * quantity;
	return total;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public Integer getQuantity() {
	return quantity;
    }

    public void setQuantity(Integer quantity) {
	this.quantity = quantity;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
	hash = 79 * hash + (this.product != null ? this.product.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof DetailTransaction)) {
	    return false;
	}
	DetailTransaction other = (DetailTransaction) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "khannedy.enterprise.penjualan.entity.DetailTransaction[id=" + id + "]";
    }
}
