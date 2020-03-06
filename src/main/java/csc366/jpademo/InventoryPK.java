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
public class InventoryPK implements Serializable {
    private Long stockId;
    private Long orderId;

    public InventoryPK() { }
    
    public InventoryPK(Long orderId, Long stockId) {
	this.stockId = stockId;
        this.orderId = orderId;
    }
        
    public Long getStockId() {
	return stockId;
    }
    public void setStockId(Long stockId) {
	this.stockId = stockId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    } 
}
