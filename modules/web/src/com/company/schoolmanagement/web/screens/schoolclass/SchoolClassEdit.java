package com.company.schoolmanagement.web.screens.schoolclass;

import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

@UiController("schoolmanagement_SchoolClass.edit")
@UiDescriptor("school-class-edit.xml")
@EditedEntityContainer("schoolClassDc")
@LoadDataBeforeShow
public class SchoolClassEdit extends StandardEditor<SchoolClass> {
}