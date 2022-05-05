-- Schema elective_db
SET NAMES utf8;
-- -----------------------------------------------------
create SCHEMA IF NOT EXISTS `elective_db` CHARACTER SET utf8 COLLATE utf8_bin;
USE `elective_db` ;
-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
drop table IF EXISTS role ;

create TABLE IF NOT EXISTS role (
  role_id INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(15) NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE INDEX role_name_UNIQUE (role_name ASC) VISIBLE);
-- -----------------------------------------------------
-- Table account
-- -----------------------------------------------------
drop table IF EXISTS account ;

create TABLE IF NOT EXISTS  account (
  id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(16) NOT NULL,
  password VARCHAR(32) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(13) NOT NULL,
  name VARCHAR(45) NULL,
  surname VARCHAR(45) NULL,
  role VARCHAR(15) NOT NULL DEFAULT 'STUDENT',
  block_status TINYINT NULL DEFAULT 0,
  user_avatar BLOB NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX login_UNIQUE (login ASC) VISIBLE,
  UNIQUE INDEX telephone_UNIQUE (telephone ASC) VISIBLE,
  UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE,
  INDEX fk_account_1_idx (role ASC) VISIBLE,
  CONSTRAINT fk_account_1
    FOREIGN KEY (role)
    REFERENCES role (role_name)
    ON delete NO ACTION
    ON update CASCADE);
-- -----------------------------------------------------
-- Table topic
-- -----------------------------------------------------
drop table IF EXISTS topic;

create TABLE IF NOT EXISTS topic (
  topic_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) default NULL,
  PRIMARY KEY (topic_id),
  del_status TINYINT NULL DEFAULT 0,
  UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE);
-- -----------------------------------------------------
-- Table course
-- -----------------------------------------------------
drop table IF EXISTS course ;

create TABLE IF NOT EXISTS course (
  id INT NOT NULL AUTO_INCREMENT,
  course_name VARCHAR(255) NOT NULL,
  course_topic VARCHAR(64) ,
  start_day DATE NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_course_topic1_idx (course_topic ASC) VISIBLE,
  UNIQUE INDEX course_name_UNIQUE (course_name ASC) VISIBLE,
  CONSTRAINT fk_course_topic1
    FOREIGN KEY (course_topic)
    REFERENCES topic (name)
    ON delete NO ACTION
    ON update NO ACTION);
-- -----------------------------------------------------
-- Table course_description
-- -----------------------------------------------------
drop table IF EXISTS course_description ;

create TABLE IF NOT EXISTS course_description (
  course_description_id_course INT NULL,
  course_info LONGTEXT NULL,
  UNIQUE INDEX course_description_id_course_UNIQUE (course_description_id_course ASC),
  CONSTRAINT fk_course_description_course
    FOREIGN KEY (course_description_id_course)
    REFERENCES course (id)
    ON delete CASCADE
    ON update CASCADE);
-- -----------------------------------------------------
-- Table gradebook
-- -----------------------------------------------------
drop table IF EXISTS gradebook ;

create TABLE IF NOT EXISTS gradebook (
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  mark INT NULL,
  PRIMARY KEY (student_id, course_id),
  INDEX fk_course_id_course_idx (course_id ASC) VISIBLE,
  CONSTRAINT fk_course_id_course
    FOREIGN KEY (course_id)
    REFERENCES elective_db.course (id)
    ON delete CASCADE
    ON update CASCADE,
  CONSTRAINT fk_account_id_account
    FOREIGN KEY (student_id)
    REFERENCES elective_db.account (id)
    ON delete CASCADE
    ON update CASCADE);
-- -----------------------------------------------------
-- Table profile_course
-- -----------------------------------------------------
drop table IF EXISTS profile_course ;

create TABLE IF NOT EXISTS profile_course (
  id_account INT NULL,
  id_course INT NULL,
  duration VARCHAR(64) NULL,
  unique INDEX fk_profile_has_course_course2_idx (id_course ASC) VISIBLE,
   INDEX fk_profile_has_course_profile2_idx (id_account ASC) VISIBLE,
  CONSTRAINT fk_profile_has_course_profile2
    FOREIGN KEY (id_account)
    REFERENCES account (id)
    ON delete CASCADE
    ON update CASCADE,
  CONSTRAINT fk_profile_has_course_course2
    FOREIGN KEY (id_course)
    REFERENCES course (id)
    ON delete CASCADE
    ON update CASCADE);

insert into role(role_id, role_name) values (1, 'ADMIN'), (2, 'TEACHER'), (3, 'STUDENT');

insert into topic(topic_id, name) values (1, 'JAVA'), (2, 'Phyton'), (3, '.NET'), (4, 'JavaSkript'),(5, 'null');

insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('1', 'Nikolay', '2D00F43F07911355D4151F13925FF292', 'Nikolay@gmail.com', '38060111500', 'Nikolay', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('2', 'Vasiliy', '2D00F43F07911355D4151F13925FF292', 'Vasiliy@gmail.com', '38050111400', 'Vasiliy', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('3', 'Oleg', '2D00F43F07911355D4151F13925FF292', 'Oleg@gmail.com', '38050111300', 'Oleg', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('4', 'Konstantin', '2D00F43F07911355D4151F13925FF292', 'Konstantin@gmail.com', '38050111200', 'Konstantin', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('5', 'Egor', '2D00F43F07911355D4151F13925FF292', 'Egor@gmail.com', '38060111100', 'Egor', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('6', 'Vacheslav', '2D00F43F07911355D4151F13925FF292', 'Vacheslav@gmail.com', '38050111190', 'Vacheslav', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('7', 'Aleksandr', '2D00F43F07911355D4151F13925FF292', 'Aleksandr@gmail.com', '38050111180', 'Aleksandr', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('8', 'Aleksey', '2D00F43F07911355D4151F13925FF292', 'Aleksey@gmail.com', '38050111170', 'Aleksey', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('9', 'Albert', '2D00F43F07911355D4151F13925FF292', 'Albert@gmail.com', '38060111160', 'Albert', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('10', 'Anatoly', '2D00F43F07911355D4151F13925FF292', 'Anatoly@gmail.com', '38050111150', 'Anatoly', 'Sergeevich', 'TEACHER');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values('11', 'Andrey', '2D00F43F07911355D4151F13925FF292', 'Andrey@gmail.com', '38050111140', 'Andrey', 'Sergeevich', 'TEACHER');


insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Danil', '2D00F43F07911355D4151F13925FF292', 'Danil@gmail.com', '38060111111', 'Danil', 'Danilovich', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Anton', '2D00F43F07911355D4151F13925FF292', 'Anton@gmail.com', '38050111112', 'Anton', 'Antonovich', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Arkady', '2D00F43F07911355D4151F13925FF292', 'Arkady@gmail.com', '38050111113', 'Arkady', 'Arkadyovich', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Artem', '2D00F43F07911355D4151F13925FF292', 'Artem@gmail.com', '38050111114', 'Artem', 'Artemovich', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Artur', '2D00F43F07911355D4151F13925FF292', 'Artur@gmail.com', '38060111115', 'Danil1', 'Shatov1', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Arkhip', '2D00F43F07911355D4151F13925FF292', 'Arkhip@gmail.com', '38050111116', 'Danil2', 'Shatov2', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Afanasii', '2D00F43F07911355D4151F13925FF292', 'Afanasii@gmail.com', '38050111117', 'Danil3', 'Shatov3', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Boris', '2D00F43F07911355D4151F13925FF292', 'Boris@gmail.com', '38050111118', 'Danil4', 'Shatov4', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Bronislav', '2D00F43F07911355D4151F13925FF292', 'Bronislav@gmail.com', '38060111119', 'Danil5', 'Shatov5', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Vadim', '2D00F43F07911355D4151F13925FF292', 'Vadim@gmail.com', '38050111110', 'Danil6', 'Shatov6', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Valentin', '2D00F43F07911355D4151F13925FF292', 'Valentin@gmail.com', '38050111120', 'Danil7', 'Shatov7', 'STUDENT');
insert into `elective_db`.`account` (id, login, password, email, telephone, name, surname, role) values(default, 'Viktor', '2D00F43F07911355D4151F13925FF292', 'Viktor@gmail.com', '38050111130', 'Danil8', 'Shatov8', 'STUDENT');
insert into `elective_db`.`account` (`login`, `password`, `email`, `telephone`, `name`, `surname`, `role`) VALUES ('admin', '2D00F43F07911355D4151F13925FF292', 'danilshatov10@gmail.com', '38098391538', 'Danil', 'Shatov', 'ADMIN');


insert into `elective_db`.`course` (`id`, `course_name`, `course_topic`, `start_day`) VALUES ('1', 'Back-end development', 'JAVA', '2022-02-14');
insert into `elective_db`.`course` (`id`, `course_name`, `course_topic`, `start_day`) VALUES ('2', 'Dig Data', 'Phyton', '2022-05-15');
insert into `elective_db`.`course` (`id`, `course_name`, `course_topic`, `start_day`) VALUES ('3', 'Programm for windows', '.NET', '2022-05-15');
insert into `elective_db`.`course` (`id`, `course_name`, `course_topic`, `start_day`) VALUES ('4', 'Sites', 'JavaSkript', '2022-02-15');

insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('Test Automation Engineers', 'JAVA', '2022-01-01');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('Test Automation Engineers on Phyton', 'Phyton', '2022-06-05');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('Front-End Online Program', 'JavaSkript', '2022-01-20');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('Test Automation in JavaScript', 'JavaSkript', '2022-07-14');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('DevSecOps Fast Track to Pre-Production', 'Phyton', '2022-04-08');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('DevOps Online Program', 'Phyton', '2022-06-09');
insert into `elective_db`.`course` (`course_name`, `course_topic`, `start_day`) VALUES ('Android developing', 'JAVA', '2022-06-09');

insert into profile_course values (1,1,'1 month');
insert into profile_course values (2,2,'1 month');
insert into profile_course values (3,3,'2 month');
insert into profile_course values (4,4,'3 month');
insert into profile_course values (5,5,'1 month');
insert into profile_course values (6,6,'4 month');
insert into profile_course values (7,7,'3 month');
insert into profile_course values (8,8,'2 month');
insert into profile_course values (9,9,'1 month');
insert into profile_course values (10,10,'6 month');
insert into profile_course values (11,11,'6 month');

insert into course_description(`course_description_id_course`,`course_info`) values('1','Back-end development now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('2','Dig Data now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('3','Programm for windows now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('4','Sites now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('5','Test Automation Engineers now one of the most popular areas');
insert into course_description(`course_description_id_course`,`course_info`) values('6','Test Automation Engineers now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('7','Front-End Online Program now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('8','Test Automation in JavaScript now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('9','DevSecOps Fast Track to Pre-Production now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('10','DevOps  now one of the most popular areas of programming');
insert into course_description(`course_description_id_course`,`course_info`) values('11','Android developing now one of the most popular areas of programming');


insert into gradebook (student_id, course_id) values(12,1);
insert into gradebook (student_id, course_id) values(12,3);
insert into gradebook (student_id, course_id) values(13,5);
insert into gradebook (student_id, course_id) values(14,2);
insert into gradebook (student_id, course_id) values(15,4);
insert into gradebook (student_id, course_id) values(16,5);
insert into gradebook (student_id, course_id) values(17,6);
insert into gradebook (student_id, course_id) values(18,7);
insert into gradebook (student_id, course_id) values(19,8);
insert into gradebook (student_id, course_id) values(20,9);
insert into gradebook (student_id, course_id) values(11,2);
insert into gradebook (student_id, course_id) values(12,4);
insert into gradebook (student_id, course_id) values(13,6);
insert into gradebook (student_id, course_id) values(14,7);
insert into gradebook (student_id, course_id) values(15,8);
insert into gradebook (student_id, course_id) values(16,9);
insert into gradebook (student_id, course_id) values(17,1);
insert into gradebook (student_id, course_id) values(18,2);
insert into gradebook (student_id, course_id) values(19,3);
insert into gradebook (student_id, course_id) values(20,4);
insert into gradebook (student_id, course_id) values(12,5);
insert into gradebook (student_id, course_id) values(12,6);
insert into gradebook (student_id, course_id) values(13,7);
insert into gradebook (student_id, course_id) values(14,8);
insert into gradebook (student_id, course_id) values(15,9);
insert into gradebook (student_id, course_id) values(16,1);