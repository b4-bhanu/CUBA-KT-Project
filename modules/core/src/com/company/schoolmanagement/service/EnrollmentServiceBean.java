package com.company.schoolmanagement.service;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.management.Notification;

@Service(EnrollmentService.NAME)
public class EnrollmentServiceBean implements EnrollmentService {

    @Inject
    private DataManager dataManager;

    @Override
    public EnrollmentStatus enroll(Student student, SchoolClass clazz) {
        if(student.getClasses().contains(clazz)){
            // avoid duplicate entries
            return EnrollmentStatus.DUPLICATE;
        }
        System.out.println("Student count: " + clazz.getStudents().size());
        System.out.println("Capacity: " + clazz.getCapacity());
        if(clazz.getStudents().size() >= clazz.getCapacity()){
            // avoid class overload
            return EnrollmentStatus.OVERLOAD;

        }
        student.getClasses().add(clazz);
        dataManager.commit(student);
        return EnrollmentStatus.ENROLLED;
    }
}