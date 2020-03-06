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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotNull;

@Entity
public class SafetyTraining {
    @Id
    private Long id;
     
    @OneToOne(optional=true)
    @MapsId
    //@Column(name = "employee_id")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher") 
    private Employee teacher;
   
    @NotNull
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
    private Date date;

    public SafetyTraining() { }
    
    public SafetyTraining(Date date) {
        this.date = date;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getTeacher() {
        return teacher;
    }
    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
