package com.company.schoolmanagement.web.screens.student;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.service.EnrollmentService;
import com.company.schoolmanagement.service.EnrollmentStatus;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;

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

    @Inject private Button enrollBtn;


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
            notifications.create().withCaption("Student enrolled successfully").show();
        }
        else if(status == EnrollmentStatus.OVERLOAD){
            notifications.create().withCaption("This Class is full").show();
        }
        else{
            notifications.create().withCaption("Student is already enrolled in this class").show();
        }
        studentDl.load();
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Student> event) {
        //setting default values for new student
        Student student = event.getEntity();
        student.setName("New Student");
        student.setDob(new Date());
    }
}