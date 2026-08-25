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
        // Reload Student with classes fetched
        Student loadedStudent = dataManager.load(Student.class)
                .id(student.getId())
                .view("student-view")
                .one();

        // Check whether student is already enrolled
        if (loadedStudent.getClasses().contains(clazz)) {
            return EnrollmentStatus.DUPLICATE;
        }

        // Count students directly in the database
        Long studentCount = dataManager.loadValue(
                        "select count(s) from schoolmanagement_Student s " +
                                "join s.classes c " +
                                "where c.id = :classId",
                        Long.class)
                .parameter("classId", clazz.getId())
                .one();

        // Check capacity
        if (studentCount >= clazz.getCapacity()) {
            return EnrollmentStatus.OVERLOAD;
        }

        // Enroll student
        loadedStudent.getClasses().add(clazz);

        // Persist the change
        dataManager.commit(loadedStudent);

        return EnrollmentStatus.ENROLLED;
    }

    @Override
    public EnrollmentStatus exclude(Student student, SchoolClass clazz) {

        Student loadedStudent = dataManager.load(Student.class)
                .id(student.getId())
                .view("student-view")
                .one();

        if (!loadedStudent.getClasses().contains(clazz)) {
            return EnrollmentStatus.NOT_ENROLLED;
        }

        loadedStudent.getClasses().remove(clazz);

        dataManager.commit(loadedStudent);

        return EnrollmentStatus.EXCLUDED;
    }
}