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
public class PaymentMethod {
    @Id
    private String type;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    public PaymentMethod() { }
    
    public PaymentMethod(String type) {
	this.type = type;
    }
    
    public String getType() {
	return type;
    }
    public void setType(String type) {
	this.type = type;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransaction(List<Transaction> transactions) {
        this.transactions = transactions;
    }    
}
