package com.company.schoolmanagement.service;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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

        student.getClasses().add(clazz);
        dataManager.commit(student);
        return EnrollmentStatus.ENROLLED;
    }
}