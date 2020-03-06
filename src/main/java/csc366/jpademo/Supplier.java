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
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long supplierId;

    @NotNull
    private String name;
   
    private String phone;
 
    @NotNull
    @Column(columnDefinition = "VARCHAR(255) UNIQUE")
    private String email;
    
    @NotNull
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    @Column(columnDefinition = "CHAR(2)")
    private String state;

    @NotNull
    private String zipCode;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();
    
    // Store (arbitrarily) designated as "owning" class in the relationship
    @ManyToMany(mappedBy = "suppliers")
    private Set<Store> supplyStores = new HashSet<>();

    public Supplier() { }
    
    public Supplier(String name, String phone, String email, String streetAddress, String city, String state, String zipCode) {
	this.name = name;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public Long getSupplierId() {
	return supplierId;
    }
    public void setSupplierId(Long supplierId) {
	this.supplierId = supplierId;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public List<Orders> getOrders() {
        return orders;
    }
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    public Set<Store> getSupplyStores() {
        return supplyStores;
    }
    public void setSupplyStores(Set<Store> supplyStores) {
       this.supplyStores = supplyStores;
    }
}
