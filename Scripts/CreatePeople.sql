DROP TABLE IF EXISTS people;
CREATE TABLE people(
   personID    VARCHAR(10) NOT NULL PRIMARY KEY
  ,firstName   VARCHAR(50) NOT NULL
  ,middleNames VARCHAR(100)
  ,lastName    VARCHAR(100) NOT NULL
  ,programme   VARCHAR(100)
  ,email       VARCHAR(100)
);
INSERT INTO people(personID,firstName,middleNames,lastName,programme,email) VALUES ('njss3','Jannetta',NULL,NULL,NULL,NULL);
INSERT INTO people(personID,firstName,middleNames,lastName,programme,email) VALUES ('stu','Stuart','M','Lewis',NULL,NULL);
