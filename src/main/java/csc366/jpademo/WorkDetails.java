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
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.EmbeddedId;

import javax.validation.constraints.NotNull;

@Entity
public class WorkDetails {
    @EmbeddedId
    private WorkDetailsPK id;

    @MapsId("employeeId")
    @OneToOne(optional = true)
    @JoinColumn(name = "employee_id")
    private Employee employee;
   
    @MapsId("locationId") 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private CorporateLocation location;

    @NotNull
    private int desk;
    
    @NotNull
    private int floor;

    public WorkDetails() { }
    
    public WorkDetails(int desk, int floor) {
	this.desk = desk;
        this.floor = floor;
    }

    public Employee getEmployee() {
	return employee;
    }
    public void setEmployee(Employee employee) {
	this.employee = employee;
    }
    public CorporateLocation getCorporateLocation() {
        return location;
    }
    public void setCorporateLocation(CorporateLocation location) {
        this.location = location;
    } 
    public int getDesk() {
	return desk;
    }
    public void setDesk(int desk) {
	this.desk = desk;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
}
