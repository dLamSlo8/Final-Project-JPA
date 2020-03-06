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
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employeeId;

    @NotNull
    private String name;
    
    @NotNull
    @Column(columnDefinition = "DECIMAL(7,2) default 12")
    private double salary;

    @NotNull
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
    private Date hireDate;
  
    private String phone;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255) UNIQUE")
    private String email;

    @NotNull
    @Column(columnDefinition = "ENUM('0', '1', '2')")
    private char accessLevel;    

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<SafetyTraining> safetyTrainings = new ArrayList<>();
    
    // Store (arbitrarily) designated as "owning" class in the relationship
    @ManyToMany(mappedBy = "workers")
    private Set<Store> workStores = new HashSet<>();
    // Store (arbitrarily) designated as "owning" class in the relationship
    @ManyToMany(mappedBy = "owners")
    private Set<Store> ownStores = new HashSet<>();
    // Store (arbitrarily) designated as "owning" class in the relationship
    @ManyToMany(mappedBy = "managers")
    private Set<Store> manageStores = new HashSet<>();

    public Employee() { }
    
    public Employee(String name, double salary, Date hireDate, String phone, String email, char accessLevel) {
	this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.phone = phone;
        this.email = email;
        this.accessLevel = accessLevel;
    }
    
    public Long getEmployeeId() {
	return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
	this.employeeId = employeeId;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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
    public char getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(char accessLevel) {
        this.accessLevel = accessLevel;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }    
    public List<SafetyTraining> getSafetyTrainings() {
        return safetyTrainings;
    }
    public void setSafetyTrainings(List<SafetyTraining> safetyTrainings) {
        this.safetyTrainings = safetyTrainings;
    }
    public Set<Store> getWorkStores() {
        return workStores;
    }
    public void setWorkStores(Set<Store> workStores) {
        this.workStores = workStores;
    }
    public Set<Store> getOwnStores() {
        return ownStores;
    }
    public void setOwnStores(Set<Store> ownStores) {
        this.ownStores = ownStores;
    }
    public Set<Store> getManageStores() {
        return manageStores;
    }
    public void setManageStores(Set<Store> manageStores) {
        this.manageStores = manageStores;
    }


    /*   
    public CorporateLocation getLocation() {
        return location;
    }
    public void setLocation(CorporateLocation location) {
        this.location = location;
    }
    */
}
