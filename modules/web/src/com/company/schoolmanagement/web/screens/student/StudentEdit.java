package com.company.schoolmanagement.web.screens.student;

import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.Student;

@UiController("schoolmanagement_Student.edit")
@UiDescriptor("student-edit.xml")
@EditedEntityContainer("studentDc")
@LoadDataBeforeShow
public class StudentEdit extends StandardEditor<Student> {
}