# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups
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

# --- !Downs
