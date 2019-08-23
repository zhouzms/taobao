create table category
(
    id_cg   int auto_increment comment '商品的类别id'
        primary key,
    name_cg varchar(255) null comment '商品种类名称'
)
    comment '商品类别表';

create table login
(
    id_l       int auto_increment comment '用户id'
        primary key,
    username_l varchar(50) default '淘淘' null,
    password_l varchar(50)              null,
    phone_l    varchar(50)              null,
    email_l    varchar(50)              null
);

create table product
(
    id_pro           int auto_increment comment '某件商品id'
        primary key,
    name_pro         varchar(255) null comment '商品名',
    subTitle_pro     varchar(255) null comment '小标题',
    orignalPrice_pro float        null comment '原始价格',
    promotePrice_pro float        null comment '优惠价格',
    stock_pro        int          null comment '库存',
    cid_pro          int          null comment '与分类表中id关联',
    createDate       datetime     null comment '创建时间',
    constraint fk_product_category
        foreign key (cid_pro) references category (id_cg)
)
    comment '商品表';

create table productimage
(
    id_proimg   int auto_increment comment '图片id'
        primary key,
    pid_proimg  int          null comment '某产品的图片',
    type_proimg varchar(255) null comment '图片路径',
    constraint fk_productimage_product
        foreign key (pid_proimg) references product (id_pro)
)
    comment '产品图片表';

create table review
(
    id_re         int auto_increment comment '评价id'
        primary key,
    content_re    varchar(4000) null comment '评价的内容',
    uid_re        int           null comment '哪个用户评价',
    pid_re        int           null comment '评价的是哪个产品',
    createDate_re datetime      null comment '评价的时间',
    constraint fk_review_product
        foreign key (pid_re) references product (id_pro),
    constraint fk_review_user
        foreign key (uid_re) references login (id_l)
)
    comment '评价表';


