 package com.company.schoolmanagement.service;

 import com.company.schoolmanagement.entity.SchoolClass;
 import com.company.schoolmanagement.entity.Student;

 public interface EnrollmentService {
    String NAME = "schoolmanagement_TestService";
     EnrollmentStatus enroll(Student student, SchoolClass clazz);

     EnrollmentStatus exclude(Student student, SchoolClass clazz);

  void unenrollFromAllClasses(Student student);
 }