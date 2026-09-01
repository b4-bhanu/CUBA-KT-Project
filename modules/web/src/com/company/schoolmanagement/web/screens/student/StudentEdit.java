package com.company.schoolmanagement.web.screens.student;

import com.company.schoolmanagement.entity.SchoolClass;
import com.company.schoolmanagement.entity.Student;
import com.company.schoolmanagement.service.EnrollmentService;
import com.company.schoolmanagement.service.EnrollmentStatus;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private RadioButtonGroup<Boolean> activeField;

    @Inject
    private Dialogs dialogs;

    @Inject
    DataManager dataManager;


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
        student.setEnrolledOn(new Date());
        student.setActive(true);
    }

//     for active/inactive radio button
    @Subscribe
    public void onInit(InitEvent event) {
        Map<String, Boolean> map = new LinkedHashMap<>();

        map.put("Active", true);
        map.put("Inactive", false);

        activeField.setOptionsMap(map);

    }

    // active/ inactive functionality
    @Subscribe(id = "activeField")
    protected void onActiveFieldValueChange(HasValue.ValueChangeEvent<Boolean> event){
        Boolean newValue = event.getValue();

        if(Boolean.FALSE.equals(newValue)){
            showInactiveConfirmation();
        }
    }


    private void showInactiveConfirmation(){
        dialogs.createOptionDialog()
                .withCaption("Confirm")
                .withMessage("Doing this will Unenroll you from all the classes.")
                .withActions(
                        new DialogAction(DialogAction.Type.YES).withHandler(e -> {
                            getEditedEntity().setActive(false);
                            dataManager.commit(getEditedEntity());
                            enrollmentService.unenrollFromAllClasses(getEditedEntity());
                            getScreenData().loadAll();
                        }),
                        new DialogAction(DialogAction.Type.CANCEL).withHandler(e -> {
                            getEditedEntity().setActive(true);
                        })
                ).show();
    }



  // custom "Add".
    @Subscribe("addBtn")
    protected void onAddBtnClick(Button.ClickEvent event) {

        screenBuilders.lookup(SchoolClass.class, this)
                .withOpenMode(OpenMode.DIALOG)
                .withSelectHandler(classes -> {
                    SchoolClass schoolClass = classes.iterator().next();

                        EnrollmentStatus status =
                                enrollmentService.enroll(
                                        getEditedEntity(),
                                        schoolClass
                                );

                        if (status == EnrollmentStatus.ENROLLED) {
                            notifications.create()
                                    .withCaption("Class enrolled successfully")
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

                    getScreenData().loadAll();
                })
                .build()
                .show();
    }


}