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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    private Long productId;
     
    @NotNull
    private String name;

    @NotNull 
    @Column(columnDefinition = "decimal(7,2) default 0")
    private double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<PurchaseDetails> purchaseDetails = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<>();

    public Product() { }
    
    public Product(String name, double price) {
        this.name = name;
	this.price = price;
    }
    
    public Long getId() {
	return productId;
    }
    public void setId(Long productId) {
	this.productId = productId;
    }
    public String getName() {
       return name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }
    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
