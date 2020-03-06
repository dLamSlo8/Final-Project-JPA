package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    public Customer() { }
    
    public Customer(String name) {
	this.name = name;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransaction(List<Transaction> transactions) {
        this.transactions = transactions;
    }    
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(name);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Customer)) return false;
	return id != null && id.equals(((Customer) o).getId());
    }

    @Override
    public int hashCode() {
	return (int) (id ^ (id >>> 32));
    }
}
