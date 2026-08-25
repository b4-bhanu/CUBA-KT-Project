package com.company.schoolmanagement.web.screens.schoolclass;

import com.company.schoolmanagement.entity.School;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.service.EnrollmentService;
import com.company.schoolmanagement.service.EnrollmentStatus;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;

import javax.inject.Inject;
import javax.management.Notification;

@UiController("schoolmanagement_SchoolClass.edit")
@UiDescriptor("school-class-edit.xml")
@EditedEntityContainer("schoolClassDc")
@LoadDataBeforeShow
public class SchoolClassEdit extends StandardEditor<SchoolClass> {

    @Inject
    Notifications notifications;

    @Inject
    EnrollmentService enrollmentService;

    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    protected void onInitEntity(InitEntityEvent<SchoolClass> event) {
        event.getEntity().setCapacity(3);
    }

    @Subscribe("addBtn")
    protected void onAddBtnClick(Button.ClickEvent event){

        screenBuilders.lookup(Student.class, this)
                .withOpenMode(OpenMode.DIALOG)
                .withSelectHandler(students -> {

                    for (Student student : students) {

                        EnrollmentStatus status =
                                enrollmentService.enroll(
                                        student,
                                        getEditedEntity()
                                );

                        if (status == EnrollmentStatus.ENROLLED) {
                            notifications.create()
                                    .withCaption("Student enrolled successfully")
                                    .show();

                        } else if (status == EnrollmentStatus.DUPLICATE) {
                            notifications.create()
                                    .withCaption("Student is already enrolled in this class")
                                    .show();

                        } else if (status == EnrollmentStatus.OVERLOAD) {
                            notifications.create()
                                    .withCaption("Class is full")
                                    .show();
                        }
                    }

                    // Refresh the students shown in this screen
                    getScreenData().loadAll();
                })
                .build()
                .show();

    }

}