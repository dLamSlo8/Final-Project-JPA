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
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.Embeddable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class PurchaseDetailsPK implements Serializable {
    private Long transactionId;
    private Long productId;

    public PurchaseDetailsPK() { }
    
    public PurchaseDetailsPK(Long transactionId, Long productId) {
	this.transactionId = transactionId;
        this.productId = productId;
    }
        
    public Long getTransactionId() {
	return transactionId;
    }
    public void setTransactionId(Long transactionId) {
	this.transactionId = transactionId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    } 
}
