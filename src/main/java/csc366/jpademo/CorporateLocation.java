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
public class CorporateLocation {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long locationId;

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

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.LAZY)
    private List<WorkDetails> workDetails = new ArrayList<>();

    public CorporateLocation() { }
    
    public CorporateLocation(String streetAddress, String city, String state, String zipCode) {
	this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public Long getLocationId() {
	return locationId;
    }
    public void setLocationId(Long locationId) {
	this.locationId = locationId;
    }
    public String getName() {
	return streetAddress;
    }
    public void setName(String streetAddress) {
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
    public List<WorkDetails> getWorkDetails() {
        return workDetails;
    }
    public void setWorkDetails(List<WorkDetails> workDetails) {
        this.workDetails = workDetails;
    }
}
