package com.company.schoolmanagement.web.screens.student;

import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.Student;

@UiController("schoolmanagement_Student.browse")
@UiDescriptor("student-browse.xml")
@LookupComponent("studentsTable")
@LoadDataBeforeShow
public class StudentBrowse extends StandardLookup<Student> {
}