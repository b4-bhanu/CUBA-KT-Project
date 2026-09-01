-- begin SCHOOLMANAGEMENT_STUDENT
create table SCHOOLMANAGEMENT_STUDENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DOB date not null,
    ENROLLED_ON date,
    ACTIVE boolean,
    --
    primary key (ID)
)^
-- end SCHOOLMANAGEMENT_STUDENT
-- begin SCHOOLMANAGEMENT_SCHOOL
create table SCHOOLMANAGEMENT_SCHOOL (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    ADDRESS varchar(255),
    --
    primary key (ID)
)^
-- end SCHOOLMANAGEMENT_SCHOOL
-- begin SCHOOLMANAGEMENT_SCHOOL_CLASS
create table SCHOOLMANAGEMENT_SCHOOL_CLASS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    GRADE varchar(255) not null,
    CAPACITY integer,
    STUDENT_COUNT integer,
    SCHOOL_ID varchar(36),
    --
    primary key (ID)
)^
-- end SCHOOLMANAGEMENT_SCHOOL_CLASS
-- begin SCHOOLMANAGEMENT_STUDENT_SCHOOL_CLASS
create table SCHOOLMANAGEMENT_STUDENT_SCHOOL_CLASS (
    STUDENT_ID varchar(36) not null,
    SCHOOL_CLASS_ID varchar(36) not null,
    primary key (STUDENT_ID, SCHOOL_CLASS_ID)
)^
-- end SCHOOLMANAGEMENT_STUDENT_SCHOOL_CLASS
