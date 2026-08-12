package com.company.schoolmanagement.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.*;
import java.util.List;

@NamePattern("%s|name")
@Table(name = "SCHOOLMANAGEMENT_SCHOOL_CLASS")
@Entity(name = "schoolmanagement_SchoolClass")
public class SchoolClass extends StandardEntity {
    private static final long serialVersionUID = -4201816774539956543L;
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "GRADE", nullable = false)
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}