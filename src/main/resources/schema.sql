drop table if exists Student;

create table Student (
	ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	COURSE_ID varchar(5) NOT NULL,
	NAME varchar(256) NOT NULL,
	SCORE double
);