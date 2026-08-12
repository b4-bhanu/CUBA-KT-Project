package com.company.schoolmanagement.entity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import java.util.List;
import javax.persistence.*;

@NamePattern("%s|name")
@Table(name = "SCHOOLMANAGEMENT_SCHOOL")
@Entity(name = "schoolmanagement_School")
public class School extends StandardEntity {
    private static final long serialVersionUID = 6575283288558312411L;
    @Column(name = "NAME" ,nullable = false)
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "school")
    private List<SchoolClass>classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SchoolClass> getClasses(){
        return classes;
    }

    public void setClasses(List<SchoolClass> classes){
        this.classes = classes;
    }
}