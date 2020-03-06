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
import javax.persistence.EmbeddedId;

import javax.validation.constraints.NotNull;

@Entity
public class Recipe {
    @EmbeddedId
    private RecipePK id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
   
    @MapsId("stockId") 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int quantity;

    public Recipe() { }
    
    public Recipe(int quantity) {
	this.quantity = quantity;
    }
    public Product getProduct() {
	return product;
    }
    public void setProduct(Product product) {
	this.product = product;
    }
    public Stock getStock() {
        return stock;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    } 
    public int getQuantity() {
	return quantity;
    }
    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }
}
