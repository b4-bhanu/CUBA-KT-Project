package com.company.schoolmanagement.web.screens.schoolclass;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

import javax.inject.Inject;

@UiController("schoolmanagement_SchoolClass.browse")
@UiDescriptor("school-class-browse.xml")
@LookupComponent("schoolClassesTable")
@LoadDataBeforeShow
public class SchoolClassBrowse extends StandardLookup<SchoolClass> {

    @Inject
    private UiComponents uiComponents;

    @Install(to = "schoolClassesTable.studentCount", subject = "valueProvider")
    private String schoolClassesTableStudentCountValueProvider(SchoolClass schoolClass) {
        return schoolClass.getStudents().size() + "/" + schoolClass.getCapacity();
    }

//    @Install(to = "schoolClassesTable.action", subject = "columnGenerator")
//    private Component schoolClassesTableActionColumnGenerator(SchoolClass schoolClass) {
//        Button button = uiComponents.create(Button.class);
//        button.setCaption("Enroll");
//
//        return button;
//    }



}