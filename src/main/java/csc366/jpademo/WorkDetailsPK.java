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
import javax.persistence.Embeddable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class WorkDetailsPK implements Serializable {
    private Long employeeId;
    private Long locationId;

    public WorkDetailsPK() { }
    
    public WorkDetailsPK(Long employeeId, Long locationId) {
	this.employeeId = employeeId;
        this.locationId = locationId;
    }
        
    public Long getEmployeeId() {
	return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
	this.employeeId = employeeId;
    }
    public Long getLocationId() {
        return locationId;
    }
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    } 
}
