DROP TABLE IF EXISTS lessons;
CREATE TABLE lessons(
   lessonID    VARCHAR(10) NOT NULL PRIMARY KEY
  ,description VARCHAR(100) NOT NULL
);
INSERT INTO lessons(lessonID,description) VALUES ('g1','Version Control with Git');
INSERT INTO lessons(lessonID,description) VALUES ('u1','The Unix Shell');
INSERT INTO lessons(lessonID,description) VALUES ('p1','Programming with Python');
INSERT INTO lessons(lessonID,description) VALUES ('m1','Introduction to Markdown');
INSERT INTO lessons(lessonID,description) VALUES ('p2','Plotting and Programming in Python');
