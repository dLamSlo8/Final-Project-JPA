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

import javax.validation.constraints.NotNull;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long storeId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255) UNIQUE")
    private String streetAddress;
    
    @NotNull
    private String city;

    @NotNull
    @Column(columnDefinition = "CHAR(2)")
    private String state;
    
    @NotNull
    private String zipCode;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Employee> workers = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Employee> owners = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Employee> managers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Quantity> quantities = new ArrayList<>();

    public Store() { }
    
    public Store(String streetAddress, String city, String state, String zipCode) {
	this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public Long getStoreId() {
	return storeId;
    }
    public void setStoreId(Long storeId) {
	this.storeId = storeId;
    }
    public String getStreetAddress() {
	return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
	this.streetAddress = streetAddress;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Orders> getOrderss() {
        return orders;
    }
    public void setOrderss(List<Orders> orders) {
        this.orders = orders;
    }
    public Set<Employee> getWorkers() {
        return workers;
    }
    public void setWorkers(Set<Employee> workers) {
        this.workers = workers;
    }
    public Set<Employee> getOwners() {
        return owners;
    }
    public void setOwners(Set<Employee> owners) {
        this.owners = owners;
    }
    public Set<Employee> getManagers() {
        return managers;
    }
    public void setManagers(Set<Employee> managers) {
        this.managers = managers;
    }
    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    public List<Quantity> getQuantities() {
        return quantities;
    }
    public void setQuantities(List<Quantity> quantities) {
        this.quantities = quantities;
    }
}
