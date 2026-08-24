package com.company.schoolmanagement.web.screens.schoolclass;

import com.company.schoolmanagement.entity.School;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

import javax.inject.Inject;
import java.util.Collection;

@UiController("schoolmanagement_SchoolClass.browse")
@UiDescriptor("school-class-browse.xml")
@LookupComponent("schoolClassesTable")
@LoadDataBeforeShow
public class SchoolClassBrowse extends StandardLookup<SchoolClass> {

    @Inject
    private UiComponents uiComponents;

    @Inject
    GroupTable<SchoolClass>schoolClassesTable;

    @Inject
    private LookupPickerField<School> schoolFilter;

    @Inject
    private CollectionLoader<SchoolClass> schoolClassesDl;

    @Install(to = "schoolClassesTable.studentCount", subject = "valueProvider")
    private String schoolClassesTableStudentCountValueProvider(SchoolClass schoolClass) {
        return schoolClass.getStudents().size() + "/" + schoolClass.getCapacity();
    }

    @Subscribe("schoolFilter")
    protected void onSchoolFilterValueChange(
            HasValue.ValueChangeEvent<School> event) {

        School selectedSchool = event.getValue();

        schoolClassesDl.setParameter("school", selectedSchool);
        schoolClassesDl.load();
    }


//    @Install(to = "schoolClassesTable.action", subject = "columnGenerator")
//    private Component schoolClassesTableActionColumnGenerator(SchoolClass schoolClass) {
//        Button button = uiComponents.create(Button.class);
//        button.setCaption("Enroll");
//
//        return button;
//    }



}