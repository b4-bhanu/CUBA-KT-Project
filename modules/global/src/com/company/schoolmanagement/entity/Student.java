package com.company.schoolmanagement.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.*;
import java.util.List;
import java.util.Date;



@NamePattern("%s|name")
@EntityListeners(StudentEntityListener.class)
@Table(name = "SCHOOLMANAGEMENT_STUDENT")
@Entity(name = "schoolmanagement_Student")
public class Student extends StandardEntity {
    private static final long serialVersionUID = -2411760068197606194L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "DOB", nullable = false )
    private Date dob;

    @Column(name = "ENROLLED_ON")
    @Temporal(TemporalType.DATE)
    private Date enrolledOn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SCHOOLMANAGEMENT_STUDENT_SCHOOL_CLASS",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SCHOOL_CLASS_ID")
    )
    private List<SchoolClass> classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<SchoolClass> getClasses(){
        return classes;
    }

    public void setClasses(List<SchoolClass>classes){
        this.classes = classes;
    }

    public Date getEnrolledOn() {
        return enrolledOn;
    }

    public void setEnrolledOn(Date enrolledOn) {
        this.enrolledOn = enrolledOn;
    }
}