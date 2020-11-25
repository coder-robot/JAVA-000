-- 仅用于初始化数据库,仅需执行一次
-- drop schema if exists `week6_mall`;
-- 创建商城数据库
create schema `week6_mall` default character set utf8mb4 collate utf8mb4_general_ci;

-- 切换数据库
use week6_mall;

-- 创建用户表
drop table if exists user;
create table if not exists week6_mall.user
(
    id              varchar(64)          not null primary key,
    name            varchar(32)          not null comment '姓名',
    password        varchar(255)         not null comment '用户密码,已加密',
    phone_number    varchar(16)          null comment '手机号码',
    gender          tinyint(1)           null comment '性别 0 女性 1 男性 2 其它',
    age             tinyint(3)           null comment '年龄',
    is_seller       tinyint(1) default 0 null comment '是否是卖家 0买家 1卖家',
    create_date     timestamp            null comment '注册时间',
    update_date     timestamp            null comment '用户基本信息更新时间',
    last_login_date timestamp            null comment '最后登录时间',
    is_deleted      tinyint(1) default 0 not null comment '是否软删除 0 未删除 1 已删除',
    constraint phone_number_unique_index
        unique (phone_number)
)
    comment '用户表';
create index create_date_index
    on week6_mall.user (create_date);
create index update_date_index
    on week6_mall.user (update_date);

-- 创建商品表
drop table if exists product;
create table if not exists week6_mall.product
(
    id             varchar(64)          not null primary key,
    name           varchar(64)          not null comment '商品名称',
    original_price decimal(15, 2)       null comment '原价',
    selling_price  decimal(15, 2)       null comment '售价',
    enabled        tinyint(1) default 0 null comment '上下架 0下架 1上架',
    stock          int        default 0 null comment '商品库存',
    img_url        varchar(255)         null comment '商品图片URL',
    create_date    timestamp            null comment '商品创建时间',
    update_date    timestamp            null comment '商品更新时间',
    is_deleted     tinyint(1) default 0 not null comment '是否软删除 0 未删除 1 已删除',
    seller_id      varchar(64)          null comment '卖家ID'
)
    comment '商品表';
create index seller_id_index
    on week6_mall.product (seller_id);
create index create_date_index
    on week6_mall.product (create_date);
create index update_date_index
    on week6_mall.product (update_date);

-- 创建订单表
drop table if exists `order`;
create table if not exists week6_mall.`order`
(
    id            varchar(64)    not null primary key,
    buyer_id      varchar(64)    null comment '买方ID',
    seller_id     varchar(64)    null comment '卖方ID',
    total_amount  decimal(15, 2) not null comment '订单金额',
    paid_amount   decimal(15, 2) not null comment '实付金额',
    status        int            null comment '订单状态',
    create_date   timestamp      null comment '订单创建时间',
    update_date   timestamp      null comment '订单更新时间',
    complete_date timestamp      null comment '订单完成时间',
    delivery_type int            null comment '配送方式',
    delivery_date timestamp      null comment '订单配送时间',
    refund_status int            null comment '退款状态',
    refund_date   timestamp      null comment '订单退款时间',
    address_id    varchar(64)    null comment '收货地址ID'
)
    comment '订单表';
create index buyer_id_index
    on week6_mall.`order` (buyer_id);
create index seller_id_index
    on week6_mall.`order` (seller_id);
create index create_date_index
    on week6_mall.`order` (create_date);
create index update_date_index
    on week6_mall.`order` (update_date);

-- 创建订单商品详情表
drop table if exists order_product;
create table if not exists week6_mall.order_product
(
    order_id            varchar(64)    not null comment '订单ID',
    product_id          varchar(64)    not null,
    quantity            int            not null comment '购买数量',
    name                varchar(64)    not null comment '商品名称快照',
    original_price      decimal(15, 2) null comment '商品原价快照',
    selling_price       decimal(15, 2) null comment '商品售价快照',
    img_url             varchar(255)   null comment '商品图片URL快照',
    create_date         timestamp      null comment '商品快照创建时间',
    product_snapshot_id varchar(64)    null comment '购买商品时的完整快照ID,备用',
    primary key (order_id, product_id)
)
    comment '订单商品快照表';

-- 创建订单商品快照表,比商品表多了个order_id
drop table if exists `order_product_snapshot`;
create table if not exists week6_mall.order_product_snapshot
(
    order_id       varchar(64)          not null comment '订单ID',
    product_id     varchar(64)          not null,
    name           varchar(64)          not null comment '商品名称',
    original_price decimal(15, 2)       null comment '原价',
    selling_price  decimal(15, 2)       null comment '售价',
    enabled        tinyint(1) default 0 null comment '上下架 0下架 1上架',
    stock          int        default 0 null comment '商品库存',
    img_url        varchar(255)         null comment '商品图片URL',
    create_date    timestamp            null comment '商品创建时间',
    update_date    timestamp            null comment '商品更新时间',
    is_deleted     tinyint(1) default 0 not null comment '是否软删除 0 未删除 1 已删除',
    seller_id      varchar(64)          null comment '卖家ID',
    primary key (order_id, product_id)
)
    comment '订单商品快照表';

-- 初始化测试数据
-- user
insert into week6_mall.user (id, name, password, phone_number, gender, age, is_seller, create_date, update_date,last_login_date, is_deleted)
values ('buyer_id_1', 'buyer_name_1', 'xAvddrtgrter==', 13601987965, 0, 20, 0, '2020-11-25 13:58:51', null, null, 0);
insert into week6_mall.user (id, name, password, phone_number, gender, age, is_seller, create_date, update_date,last_login_date, is_deleted)
values ('seller_id_1', 'seller_name_1', 'fKDLSDDLSD==', 13631987961, 1, 33, 1, '2020-11-25 14:00:40', null, null, 0);
-- product
insert into week6_mall.product (id, name, original_price, selling_price, enabled, stock, img_url, create_date, update_date, is_deleted, seller_id)
values ('product_id_1', 'product_name_1', 100.00, 99.00, 1, 1000, null, '2020-11-25 14:02:19', null, 0, 'seller_id_1');
insert into week6_mall.product (id, name, original_price, selling_price, enabled, stock, img_url, create_date, update_date, is_deleted, seller_id)
values ('product_id_2', 'product_name_2', 200.00, 188.00, 1, 2000, null, '2020-11-25 14:03:44', null, 0, 'seller_id_1');
insert into week6_mall.product (id, name, original_price, selling_price, enabled, stock, img_url, create_date, update_date, is_deleted, seller_id)
values ('product_id_3', 'product_name_3', 500.00, 499.00, 0, 800, null, '2020-11-25 14:04:49', null, 0, 'seller_id_1');

-- 待完善(其它表设计) 例如商品分类表、收货地址表、配送方式、订单收货地址快照表、省市区表、用户信息扩展表等
