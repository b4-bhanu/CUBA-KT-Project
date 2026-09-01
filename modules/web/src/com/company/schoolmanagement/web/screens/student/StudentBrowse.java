package com.company.schoolmanagement.web.screens.student;

import com.company.schoolmanagement.entity.Student;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;


@UiController("schoolmanagement_Student.browse")
@UiDescriptor("student-browse.xml")
@LookupComponent("studentsTable")
@LoadDataBeforeShow
public class StudentBrowse extends StandardLookup<Student> {
    @Inject
    private UiComponents uiComponents;

    @Install(to = "studentsTable.activeStatus", subject = "columnGenerator")
    private Component studentsTableActiveStatusColumnGenerator(Student student) {

        Label<String> label = uiComponents.create(Label.NAME);

        if (Boolean.TRUE.equals(student.getActive())) {
            label.setValue("Active");
            label.setStyleName("active-badge");
        } else {
            label.setValue("Inactive");
            label.setStyleName("inactive-badge");
        }

        return label;
    }

}