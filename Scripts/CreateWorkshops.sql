DROP TABLE IF EXISTS workshops;
CREATE TABLE workshops(
   workshopID                   VARCHAR(50) NOT NULL PRIMARY KEY
  ,workshopName                 VARCHAR(50) NOT NULL
  ,badge                        VARCHAR(3)
  ,startDate                    DATE 
  ,endDate                      DATE 
  ,startTime                    VARCHAR(5)
  ,endTime                      VARCHAR(5)
  ,workshopURL                  VARCHAR(150)
  ,communityDocURL              VARCHAR(150)
  ,preWorkshopSurveyURL         VARCHAR(150)
  ,postWorkshopSurveyURL        VARCHAR(150)
  ,surveyResults                VARCHAR(150)
  ,host                         VARCHAR(100)
  ,hostInstitution              VARCHAR(100)
  ,workshopWebsite              VARCHAR(150)
  ,emailRemindDate              VARCHAR(30)
  ,postWorkshopSurveyRemindDate VARCHAR(150)
);
INSERT INTO workshops(workshopID,workshopName,badge,startDate,endDate,startTime,endTime,workshopURL,communityDocURL,preWorkshopSurveyURL,postWorkshopSurveyURL,surveyResults,host,hostInstitution,workshopWebsite,emailRemindDate,postWorkshopSurveyRemindDate) VALUES ('2022-05-30-NCL','Software Carpentries Workshop','swc','2022-05-30','2022-06-01','09:00','17:00','https://nclrse-training.github.io/2022-05-30-NCL/','https://hackmd.io/@jannettasteyn/2022-05-30-NCL','https://carpentries.typeform.com/to/wi32rS?slug=2022-05-30-NCL','https://carpentries.typeform.com/to/UgVdRQ?slug=2022-05-30-NCL','https://workshop-reports.carpentries.org/?slug=2022-05-30-NCL&key=4da73c2e3dc03845ae5f5cad2d3f7d4e32df80f8','Jannetta Steyn','Newcastle University',NULL,NULL,NULL);
INSERT INTO workshops(workshopID,workshopName,badge,startDate,endDate,startTime,endTime,workshopURL,communityDocURL,preWorkshopSurveyURL,postWorkshopSurveyURL,surveyResults,host,hostInstitution,workshopWebsite,emailRemindDate,postWorkshopSurveyRemindDate) VALUES ('2022-09-27-NCL','Software Carpentries Workshop','swc','2022-09-27','2022-09-29','09:00','17:00','https://nclrse-training.github.io/2022-09-26-NCL/',NULL,NULL,NULL,NULL,'Jannetta Steyn','Newcatle University',NULL,NULL,NULL);
INSERT INTO workshops(workshopID,workshopName,badge,startDate,endDate,startTime,endTime,workshopURL,communityDocURL,preWorkshopSurveyURL,postWorkshopSurveyURL,surveyResults,host,hostInstitution,workshopWebsite,emailRemindDate,postWorkshopSurveyRemindDate) VALUES ('2022-11-22-NCL','Software Carpentries Workshop','swc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO workshops(workshopID,workshopName,badge,startDate,endDate,startTime,endTime,workshopURL,communityDocURL,preWorkshopSurveyURL,postWorkshopSurveyURL,surveyResults,host,hostInstitution,workshopWebsite,emailRemindDate,postWorkshopSurveyRemindDate) VALUES ('2022-04-11-NUAcT','Software Carpentries Workshop + OpenRefine',NULL,'2022-04-11','2022-04-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO workshops(workshopID,workshopName,badge,startDate,endDate,startTime,endTime,workshopURL,communityDocURL,preWorkshopSurveyURL,postWorkshopSurveyURL,surveyResults,host,hostInstitution,workshopWebsite,emailRemindDate,postWorkshopSurveyRemindDate) VALUES ('2022-03-29-NCL','Software Carpentries Workshop','swc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
