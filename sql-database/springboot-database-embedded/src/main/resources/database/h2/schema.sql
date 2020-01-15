create table if not exists USER (
    USER_ID int not null primary key auto_increment,
    USER_NAME varchar(100),
    USER_SEX varchar(1),
    USER_AGE NUMBER(3),
    USER_ID_NO VARCHAR(18),
    USER_PHONE_NUM VARCHAR(11),
    USER_EMAIL VARCHAR(100),
    CREATE_TIME DATE,
    MODIFY_TIME DATE,
    USER_STATE VARCHAR(1)
);

