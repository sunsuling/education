-- 事故案例分析
create table ACCIDENT_ANALYSIS (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  message                   varchar(255),
  detail               		text,
  constraint pk_ACCIDENT_ANALYSIS primary key (id))
;

-- 事故快报
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
-- 用户
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

-- 联系人
create table contact(
  id                        varchar(255) not null ,-- 主键 
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

-- 法律
create table law (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  title                     varchar(255),
  detail                    text,
  effective_at			    timestamp ,-- 法律生效时间
  constraint PK_LAW primary key (id))
;
create sequence law_seq;

-- 行政法规
create table regulation (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  title                     varchar(255),
  detail                    text,
  effective_at			    timestamp ,-- 行政法规生效时间
  constraint PK_REGULATION primary key (id))
;
create sequence regulation_seq;

-- 部门规章
create table department (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  title                     varchar(255),
  detail                    text,
  effective_at			    timestamp ,-- 部门规章生效时间
  constraint PK_DEPARTMENT primary key (id))
;
create sequence department_seq;

-- 地方法规
create table locality (
  id                        varchar(255) not null,
  created_by                varchar(255),
  created_at                timestamp,
  updated_by                varchar(255),
  updated_at                timestamp,
  deleted                   boolean,
  title                     varchar(255),
  detail                    text,
  effective_at			    timestamp ,-- 地方法规生效时间
  constraint PK_LOCALITY primary key (id))
;
create sequence locality_seq;
