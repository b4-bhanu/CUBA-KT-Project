package com.company.schoolmanagement.service;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.entity.SchoolClass;

public interface EnrollmentService {
    String NAME = "scm_EnrollmentService";

    void enroll(Student student, SchoolClass clazz);
}