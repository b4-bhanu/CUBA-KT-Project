package com.company.schoolmanagement.web.screens.school;

import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.School;

@UiController("schoolmanagement_School.edit")
@UiDescriptor("school-edit.xml")
@EditedEntityContainer("schoolDc")
@LoadDataBeforeShow
public class SchoolEdit extends StandardEditor<School> {
    @Subscribe
    public void onInitEntity(InitEntityEvent<School> event) {
        School school = event.getEntity();
        school.setName("NEW SCHOOL");
    }



}