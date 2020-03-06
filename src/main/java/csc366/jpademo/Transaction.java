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
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
    Date date;
         
    @NotNull
    @Column(columnDefinition = "DECIMAL(7,2) default 0")
    private double totalPrice;

    @NotNull
    @Column(columnDefinition = "DECIMAL(7,2) default 0")
    private double salesTax;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
 
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method")
    private PaymentMethod payment;
   
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
 
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<PurchaseDetails> purchaseDetails = new ArrayList<>();

    public Transaction() { }
    
    public Transaction(Date date, double totalPrice, double salesTax) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.salesTax = salesTax;
    }
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getTotalPrice() {
	return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getSalesTax() {
        return salesTax;
    }
    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) { 
        this.customer=customer;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee=employee;
    }
    public PaymentMethod getPaymentMethod() {
        return payment;
    }
    public void setPaymentMethod(PaymentMethod payment) {
        this.payment=payment;
    }
    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }
    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
