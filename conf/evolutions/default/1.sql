# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table results (
  id                            integer auto_increment not null,
  city                          varchar(255),
  country                       varchar(255),
  home_score                    integer not null,
  away_score                    integer not null,
  constraint pk_results primary key (id)
);

create table attendance (
  id                            integer auto_increment not null,
  check_in                      DATETIME,
  check_out                     DATETIME,
  person_pid                    integer,
  constraint pk_attendance primary key (id)
);

create table person (
  pid                           integer auto_increment not null,
  name                          varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  created_on                    DATETIME,
  role_id                       integer,
  constraint pk_person primary key (pid)
);

create table role (
  id                            integer auto_increment not null,
  role                          varchar(255),
  constraint pk_role primary key (id)
);

create index ix_attendance_person_pid on attendance (person_pid);
alter table attendance add constraint fk_attendance_person_pid foreign key (person_pid) references person (pid) on delete restrict on update restrict;

create index ix_person_role_id on person (role_id);
alter table person add constraint fk_person_role_id foreign key (role_id) references role (id) on delete restrict on update restrict;


# --- !Downs

alter table attendance drop foreign key fk_attendance_person_pid;
drop index ix_attendance_person_pid on attendance;

alter table person drop foreign key fk_person_role_id;
drop index ix_person_role_id on person;

drop table if exists results;

drop table if exists attendance;

drop table if exists person;

drop table if exists role;

