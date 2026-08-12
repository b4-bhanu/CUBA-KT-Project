package com.company.schoolmanagement.web.screens.schoolclass;

import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

@UiController("schoolmanagement_SchoolClass.browse")
@UiDescriptor("school-class-browse.xml")
@LookupComponent("schoolClassesTable")
@LoadDataBeforeShow
public class SchoolClassBrowse extends StandardLookup<SchoolClass> {
}