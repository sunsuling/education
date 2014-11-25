create table ACCIDENT_ANALYSIS (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  message                   varchar(255),
  message_url               varchar(255),
  constraint pk_ACCIDENT_ANALYSIS primary key (id))
;

create table ACCIDENT_LETTERS (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  message                   varchar(255),
  detail                    text,
  constraint pk_ACCIDENT_LETTERS primary key (id))
;

create table account_user(
  id                        uuid not null ,-- 主键 
  created_by                varchar(255) ,-- 创建人
  created_at                timestamp ,-- 创建时间
  updated_by                varchar(255) ,-- 更新人
  updated_at                timestamp ,-- 更新时间
  deleted                   boolean default false,-- 是否删除
  username                  varchar(255) ,-- 用户名
  pwd	            varchar(255) ,-- 密码
  ip                        varchar(255) ,-- IP
  account_type	                    int, -- 角色
  lock                      boolean default false, -- 是否锁定 默认是false
  constraint pk_user primary key (id))
;
create sequence user_seq;


create table contact(
  id                        uuid not null ,-- 主键 
  created_by                varchar(255) ,-- 创建人
  created_at                timestamp ,-- 创建时间
  updated_by                varchar(255) ,-- 更新人
  updated_at                timestamp ,-- 更新时间
  deleted                   boolean default false,-- 是否删除
  address                   varchar(255) ,-- 用户名
  phone	                    varchar(255) ,-- 电话
  contact_name				varchar(255), -- 联系人姓名
  constraint pk_contact primary key (id))
;
create sequence contact_seq;
