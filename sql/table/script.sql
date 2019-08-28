create table cert
(
    id         int auto_increment comment '购车id'
        primary key,
    uid        int              not null comment '用户id',
    pid        int              not null comment '产品id',
    num        int(10)          not null comment '商品数量',
    status     int(4) default 0 not null comment '0 正常，1删除',
    createTime varchar(20)      null comment '创建时间',
    constraint fk_pid
        foreign key (pid) references product (id),
    constraint fk_uid
        foreign key (uid) references login (id_l)
)
    comment '购物车表';


