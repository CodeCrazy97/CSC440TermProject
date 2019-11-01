CREATE DATABASE grades; USE grades;
CREATE TABLE course(
	id INT PRIMARY KEY,
	title CHAR(7) NOT NULL,
	requirement_satisfaction CHAR(50) NOT NULL,
	credits DOUBLE NOT NULL,
	semester_taken CHAR(12) NOT NULL,
	year_taken INT(4) NOT NULL,
	final_grade CHAR(1), 
	completion_status CHAR(12) NOT NULL
);
CREATE TABLE assignment(
	title CHAR(50) PRIMARY KEY,
	weight DOUBLE NOT NULL,
	grade DOUBLE NOT NULL,
	course_id INT NOT NULL, CONSTRAINT course_id_constraint FOREIGN KEY (course_id) REFERENCES course(id)
);