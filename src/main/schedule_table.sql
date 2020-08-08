DROP TABLE IF EXISTS un_calender;
DROP TABLE IF EXISTS un_group;
DROP TABLE IF EXISTS un_teacher;
DROP TABLE IF EXISTS un_subject;
DROP TABLE IF EXISTS un_auditorium;

CREATE TABLE un_subject (
    ID SERIAL,
    subject_title VARCHAR(40) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE un_teacher (
    ID SERIAL,
    full_name_teacher VARCHAR(200) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE un_group (
    ID SERIAL,
    group_number INT NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE un_auditorium (
  ID SERIAL,
  auditorium_number INT NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE un_calender (
  ID SERIAL PRIMARY KEY,
  weekday_and_number_pair VARCHAR NOT NULL,
  id_subject SERIAL NOT NULL,
  id_teacher SERIAL NOT NULL,
  id_group SERIAL NOT NULL,
  id_auditorium SERIAL NOT NULL,
  FOREIGN KEY(id_subject) REFERENCES un_subject(ID),
  FOREIGN KEY(id_teacher) REFERENCES un_teacher(ID),
  FOREIGN KEY(id_group) REFERENCES un_group(ID),
  FOREIGN KEY(id_auditorium) REFERENCES un_auditorium(ID)
);