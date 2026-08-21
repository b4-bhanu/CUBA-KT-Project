
package com.company.schoolmanagement.entity;

import com.company.schoolmanagement.entity.Student;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.util.Date;


public class StudentEntityListener {

    @PrePersist
    public void onPrePersist(Student student) {
        if (student.getEnrolledOn() == null) {
            student.setEnrolledOn(new Date());
        }
    }
}