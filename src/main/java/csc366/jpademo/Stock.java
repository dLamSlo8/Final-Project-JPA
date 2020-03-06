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
public class Stock {
    @Id
    private Long stockId;
     
    @NotNull
    private String name;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<>();
    
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Quantity> quantities = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();


    public Stock() { }
    
    public Stock(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    public List<Quantity> getQuantities() {
        return quantities;
    }
    public void setQuantities(List<Quantity> quantities) {
        this.quantities = quantities;
    }
    public List<Inventory> getInventories() {
        return inventories;
    }
    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
