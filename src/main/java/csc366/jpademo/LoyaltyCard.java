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
public class LoyaltyCard {
    @Id
    private Long id;
     
    @OneToOne(optional=true)
    @MapsId
    private Customer customer;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255) UNIQUE")
    private String phoneNumber;

    @NotNull 
    @Column(columnDefinition = "int(11) default 0")
    private int points;

    @NotNull
    @Column(columnDefinition = "decimal(7,2) default 0")
    private double currencySpent;

    public LoyaltyCard() { }
    
    public LoyaltyCard(Customer customer, String phoneNumber, int points, double currencySpent) {
        this.customer = customer;
	this.phoneNumber = phoneNumber;
        this.points = points;
        this.currencySpent = currencySpent;
    }
    
    public Customer getCustomer() {
	return customer;
    }
    public void setCustomer(Customer customer) {
	this.customer = customer;
    }
    
    public String getPhoneNumber() {
	return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public double getCurrencySpent() {
        return currencySpent;
    }
    public void setPoints(double currencySpent) {
        this.currencySpent = currencySpent;
    }
}
