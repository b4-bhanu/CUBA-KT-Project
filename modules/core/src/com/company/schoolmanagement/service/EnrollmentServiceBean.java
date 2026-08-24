package com.company.schoolmanagement.service;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(EnrollmentService.NAME)
public class EnrollmentServiceBean implements EnrollmentService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EnrollmentServiceBean.class);
    @Inject
    private DataManager dataManager;

    @Override
    public EnrollmentStatus enroll(Student student, SchoolClass clazz) {
        if(student.getClasses().contains(clazz)){
            // avoid duplicate entries
            return EnrollmentStatus.DUPLICATE;
        }
//        log.info("Student count: {}", clazz.getStudents().size());
//        log.info("Capacity: {}", clazz.getCapacity());
        if(clazz.getStudents().size() >= clazz.getCapacity()){
//             avoid class overload
            return EnrollmentStatus.OVERLOAD;

        }
        student.getClasses().add(clazz);
        dataManager.commit(student);
        return EnrollmentStatus.ENROLLED;
    }
}