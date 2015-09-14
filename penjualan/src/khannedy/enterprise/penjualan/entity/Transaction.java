/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package khannedy.enterprise.penjualan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author echo
 */
@Entity
@Table(name = "table_transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "generator_transaction", allocationSize = 1,
    pkColumnName = "k", pkColumnValue = "transaction",
    valueColumnName = "v", table = "table_generator")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator_transaction")
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "transaction", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<DetailTransaction> details = new HashSet<DetailTransaction>();

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Calendar date = Calendar.getInstance();

    @Column(name = "total", nullable = false)
    private Double total;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Double getTotal() {
	total = 0.0;
	for (DetailTransaction detail : getDetails()) {
	    total += detail.getTotal();
	}
	return total;
    }

    public Calendar getDate() {
	return date;
    }

    public void setDate(Calendar date) {
	this.date = date;
    }

    public Set<DetailTransaction> getDetails() {
	return details;
    }

    public void setDetails(Set<DetailTransaction> details) {
	this.details = details;
    }

    public void addDetail(DetailTransaction detail) {
	if (detail.getTransaction() != this) {
	    detail.setTransaction(this);
	}
	getDetails().add(detail);
    }

    public void removeDetail(DetailTransaction detail) {
	if (detail.getTransaction() == this) {
	    detail.setTransaction(null);
	}
	getDetails().remove(detail);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Transaction)) {
	    return false;
	}
	Transaction other = (Transaction) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "khannedy.enterprise.penjualan.entity.Transaction[id=" + id + "]";
    }
}
