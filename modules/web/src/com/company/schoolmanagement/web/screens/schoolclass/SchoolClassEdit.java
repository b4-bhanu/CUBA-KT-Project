package com.company.schoolmanagement.web.screens.schoolclass;

import com.company.schoolmanagement.entity.School;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.service.EnrollmentService;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

import javax.inject.Inject;

@UiController("schoolmanagement_SchoolClass.edit")
@UiDescriptor("school-class-edit.xml")
@EditedEntityContainer("schoolClassDc")
@LoadDataBeforeShow
public class SchoolClassEdit extends StandardEditor<SchoolClass> {

    @Subscribe
    protected void onInitEntity(InitEntityEvent<SchoolClass> event) {
        event.getEntity().setCapacity(3);
    }

}