# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  detail                    varchar(255),
  constraint pk_ACCIDENT_LETTERS primary key (id))
;

create sequence ACCIDENT_ANALYSIS_seq;

create sequence ACCIDENT_LETTERS_seq;




# --- !Downs

drop table if exists ACCIDENT_ANALYSIS cascade;

drop table if exists ACCIDENT_LETTERS cascade;

drop sequence if exists ACCIDENT_ANALYSIS_seq;

drop sequence if exists ACCIDENT_LETTERS_seq;

