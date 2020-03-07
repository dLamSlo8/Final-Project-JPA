package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.sql.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
    Date orderDate;

    @NotNull        
    @Column(columnDefinition = "DATETIME")
    Date deliverDate;
 
    @NotNull
    @Column(columnDefinition = "DECIMAL(7,2) default 0")
    private double totalCost;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
 
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    public Orders() { }
    
    public Orders(Date orderDate, Date deliverDate, double totalCost) {
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
        this.totalCost = totalCost;
    }
    
    public Date getOrdersDate() {
        return orderDate;
    }
    public void setOrdersDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public Date getDeliverDate() {
        return deliverDate;
    }
    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    public double getTotalCost() {
	return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) { 
        this.store=store;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier=supplier;
    }
    public List<Inventory> getInventories() {
        return inventories;
    }
    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
