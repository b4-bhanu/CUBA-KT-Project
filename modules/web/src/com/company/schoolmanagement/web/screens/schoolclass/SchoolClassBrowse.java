package com.company.schoolmanagement.web.screens.schoolclass;

import com.company.schoolmanagement.entity.School;
import com.company.schoolmanagement.entity.Student;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.schoolmanagement.entity.SchoolClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@UiController("schoolmanagement_SchoolClass.browse")
@UiDescriptor("school-class-browse.xml")
@LookupComponent("schoolClassesTable")
@LoadDataBeforeShow
public class SchoolClassBrowse extends StandardLookup<SchoolClass> {

    private static final Logger log = LoggerFactory.getLogger(SchoolClassBrowse.class);
    @Inject
    private UiComponents uiComponents;

    @Inject
    GroupTable<SchoolClass>schoolClassesTable;

    @Inject
    private LookupPickerField<School> schoolFilter;

    @Inject
    private CollectionLoader<SchoolClass> schoolClassesDl;

    @Inject
    DataManager dataManager;

    @Install(to = "schoolClassesTable.studentCount", subject = "valueProvider")
    private String schoolClassesTableStudentCountValueProvider(SchoolClass schoolClass) {

         /*
         explanation for the following jpql query
         >join the students 's' with their classes (s.classes) and call the classes 'c'
         >now where c.id == classId or our current schoolId., so whichever of those classes associated
          with the students in equal to this classId
         >Count them(count(s) from ... ),and we expect a long value as COUNT() query returns a long value.

         .one() -> execute the query and give me the single result.
         */
        Long count = dataManager.loadValue(
                        "select count(s) from schoolmanagement_Student s " +
                                "join s.classes c " +
                                "where c.id = :classId",
                        Long.class)
                .parameter("classId", schoolClass.getId())
                .one();

           // just a little test for how these dataManager query work inside 'formatter'
        // the following outputs a lot of repeat students because this query works on each row.
//        School school= schoolFilter.getValue();
//        assert school != null;
//        List<Student> list = dataManager.load(Student.class)
//        .query("select distinct s from schoolmanagement_Student s " +
//                "join s.classes c " +
//                "where c.school = :sc")
//                .parameter("sc",school).list();

        return count + "/" + schoolClass.getCapacity();
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