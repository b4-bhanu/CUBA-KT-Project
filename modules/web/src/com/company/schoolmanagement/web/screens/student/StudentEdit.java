package com.company.schoolmanagement.web.screens.student;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.service.EnrollmentService;
import com.company.schoolmanagement.service.EnrollmentStatus;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("schoolmanagement_Student.edit")
@UiDescriptor("student-edit.xml")
@EditedEntityContainer("studentDc")
@LoadDataBeforeShow
public class StudentEdit extends StandardEditor<Student> {


    @Inject
    private EnrollmentService enrollmentService;

    @Inject
    private LookupPickerField<SchoolClass> classPicker;

    @Inject
    private InstanceLoader<Student> studentDl;

    @Inject
    Notifications notifications;

    @Subscribe("enrollBtn")
    protected void onEnrollBtnClick(Button.ClickEvent event){
        SchoolClass clazz = classPicker.getValue();
        Student student = getEditedEntity();

        if(clazz == null){
            notifications.create().withCaption("Please select a class first").show();
            return;
        }

        EnrollmentStatus status = enrollmentService.enroll(student,clazz);

        if(status == EnrollmentStatus.ENROLLED){
            notifications.create().withCaption("Student already enrolled successfully").show();
        }
        else{
            notifications.create().withCaption("Student is already enrolled in this class").show();
        }
        studentDl.load();
    }

}