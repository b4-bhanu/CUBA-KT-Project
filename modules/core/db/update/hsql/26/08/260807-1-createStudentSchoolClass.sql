create table SCHOOLMANAGEMENT_STUDENT_SCHOOL_CLASS (
    STUDENT_ID varchar(36) not null,
    SCHOOL_CLASS_ID varchar(36) not null,
    primary key (STUDENT_ID, SCHOOL_CLASS_ID)
);
