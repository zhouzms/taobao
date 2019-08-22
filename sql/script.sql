create table login
(
    id_l       int auto_increment comment '用户id'
        primary key,
    username_l varchar(50) default '淘淘' null,
    password_l varchar(50)              null,
    phone_l    varchar(50)              null,
    email_l    varchar(50)              null
);


