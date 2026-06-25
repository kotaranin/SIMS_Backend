/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.18 : Database - sims_web
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sims_web` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sims_web`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id_city` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_country` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_city`),
  KEY `FK1dmt4f3nfuon26epr3ueg4e` (`id_country`),
  CONSTRAINT `FK1dmt4f3nfuon26epr3ueg4e` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `city` */

insert  into `city`(`id_city`,`name`,`id_country`) values 
(4,'Beograd',1),
(5,'Novi Sad',1),
(6,'Kraljevo',1),
(7,'Tivat',2),
(8,'Podgorica',2),
(11,'Rim',5),
(12,'Rimini',5),
(15,'Milano',5),
(16,'Bukurešt',8),
(17,'Temišvar',8),
(18,'Kruševac',1);

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id_company` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_city` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_company`),
  KEY `FKsg6e8onu4yjxy0nct9xvu318c` (`id_city`),
  CONSTRAINT `FKsg6e8onu4yjxy0nct9xvu318c` FOREIGN KEY (`id_city`) REFERENCES `city` (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `company` */

insert  into `company`(`id_company`,`address`,`name`,`id_city`) values 
(4,'Milutina Milankovića 11','Ernst and Young',4),
(5,'milutina milankovica 12','Delta Real Estate',5),
(6,'Narodnih heroja 23','Microsoft',4),
(7,'Avalska 45','Ananas',7);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id_country` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `country` */

insert  into `country`(`id_country`,`name`) values 
(1,'Srbija'),
(2,'Crna Gora'),
(3,'Bosna i Hercegovina'),
(4,'Mađarska'),
(5,'Italija'),
(6,'Severna Makedonija'),
(7,'Severna Koreja'),
(8,'Rumunija');

/*Table structure for table `exam_period` */

DROP TABLE IF EXISTS `exam_period`;

CREATE TABLE `exam_period` (
  `id_exam_period` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id_exam_period`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `exam_period` */

insert  into `exam_period`(`id_exam_period`,`end_date`,`name`,`start_date`) values 
(1,'2026-06-30','Junski ispitni rok 2026','2026-06-01'),
(2,'2026-07-31','Julski ispitni rok 2026','2026-07-01'),
(3,'2026-09-30','Septembarski ispitni rok 2026','2026-09-01'),
(4,'2026-10-31','Oktobarski ispitni rok 2026','2026-10-01'),
(5,'2026-01-31','Januarski ispitni rok 2026','2026-01-01');

/*Table structure for table `internship` */

DROP TABLE IF EXISTS `internship`;

CREATE TABLE `internship` (
  `id_internship` bigint(20) NOT NULL AUTO_INCREMENT,
  `defense_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `grade` tinyint(4) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  `id_exam_period` bigint(20) DEFAULT NULL,
  `id_report` bigint(20) DEFAULT NULL,
  `id_student` bigint(20) DEFAULT NULL,
  `id_student_officer` bigint(20) DEFAULT NULL,
  `id_teacher` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_internship`),
  KEY `FK11xqdk7p0orkqeuoq9qyl2pej` (`id_company`),
  KEY `FKgxk2ylnlqj23a2jwg6nckm64o` (`id_exam_period`),
  KEY `FKn2hv9t95hw8uodxytbpwf469y` (`id_report`),
  KEY `FKsdp65o2xypdq6ea42dwssskqc` (`id_student`),
  KEY `FKq0824fbt0wk9koa8xghx9qnel` (`id_student_officer`),
  KEY `FKmgkgqhtmeqqlep09qfcjxag28` (`id_teacher`),
  CONSTRAINT `FK11xqdk7p0orkqeuoq9qyl2pej` FOREIGN KEY (`id_company`) REFERENCES `company` (`id_company`),
  CONSTRAINT `FKgxk2ylnlqj23a2jwg6nckm64o` FOREIGN KEY (`id_exam_period`) REFERENCES `exam_period` (`id_exam_period`),
  CONSTRAINT `FKmgkgqhtmeqqlep09qfcjxag28` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_teacher`),
  CONSTRAINT `FKn2hv9t95hw8uodxytbpwf469y` FOREIGN KEY (`id_report`) REFERENCES `report` (`id_report`),
  CONSTRAINT `FKq0824fbt0wk9koa8xghx9qnel` FOREIGN KEY (`id_student_officer`) REFERENCES `student_officer` (`id_student_officer`),
  CONSTRAINT `FKsdp65o2xypdq6ea42dwssskqc` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`),
  CONSTRAINT `internship_chk_1` CHECK ((`grade` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `internship` */

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id_module` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_study_program` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_module`),
  KEY `FKsdahlr3s3xsatue8ivu6tadu8` (`id_study_program`),
  CONSTRAINT `FKsdahlr3s3xsatue8ivu6tadu8` FOREIGN KEY (`id_study_program`) REFERENCES `study_program` (`id_study_program`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `module` */

insert  into `module`(`id_module`,`name`,`id_study_program`) values 
(1,'Softversko inženjerstvo',1),
(2,'Informacioni sistemi',1),
(3,'Informacione tehnologije',1),
(4,'Projektni menadžment',2),
(5,'Marketing',2),
(6,'Upravljanje projektima',2),
(20,'Elektronsko poslovanje',8),
(21,'Tehnologije elektronskog poslovanja',8),
(22,'Informacione tehnologije i sajber bezbednost',10),
(23,'Informacioni sistemi',10),
(24,'Softversko inženjerstvo i veštačka inteligencija',18),
(25,'Elektronsko poslovanje',18),
(26,'Elektronsko poslovanje',21),
(27,'Java tehnologije',21),
(28,'Poslovni i inženjerski menadžment',22),
(29,'Menadžment i organizacija rada u zdravstvu i farmaciji',22);

/*Table structure for table `registration_request` */

DROP TABLE IF EXISTS `registration_request`;

CREATE TABLE `registration_request` (
  `id_registration_request` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin` bit(1) DEFAULT NULL,
  `answer_salt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `hashed_answer` varchar(255) DEFAULT NULL,
  `hashed_password` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `id_study_level` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_registration_request`),
  KEY `FKoo57lsrfredfwej33u9biidlg` (`id_study_level`),
  CONSTRAINT `FKoo57lsrfredfwej33u9biidlg` FOREIGN KEY (`id_study_level`) REFERENCES `study_level` (`id_study_level`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `registration_request` */

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `id_report` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_content` longblob,
  `file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_report`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `report` */

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id_student` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `index_number` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `year_of_study` int(11) DEFAULT NULL,
  `id_city` bigint(20) DEFAULT NULL,
  `id_module` bigint(20) DEFAULT NULL,
  `id_study_program` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_student`),
  KEY `FKlmc7b1qg1yt1elhmkqnlt4o4l` (`id_city`),
  KEY `FKt986jlj42ak9gmk53bxsea416` (`id_module`),
  KEY `FKnx65vgamasutmqdu193dvs6ra` (`id_study_program`),
  CONSTRAINT `FKlmc7b1qg1yt1elhmkqnlt4o4l` FOREIGN KEY (`id_city`) REFERENCES `city` (`id_city`),
  CONSTRAINT `FKnx65vgamasutmqdu193dvs6ra` FOREIGN KEY (`id_study_program`) REFERENCES `study_program` (`id_study_program`),
  CONSTRAINT `FKt986jlj42ak9gmk53bxsea416` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`id_student`,`date_of_birth`,`first_name`,`index_number`,`last_name`,`year_of_study`,`id_city`,`id_module`,`id_study_program`) values 
(1,'2004-01-05','Petar','2022/0001','Petrović',5,4,4,2),
(2,'2026-06-12','Milica','2022/0002','Miličević',3,5,1,1),
(3,'2026-06-11','Pavle','2022/0003','Pavlović',2,5,2,1),
(7,'2026-06-11','Mitar','2022/0004','Mitrović',2,5,3,1),
(8,'2026-06-04','Jana','2022/0005','Janković',1,12,3,1),
(9,'2026-06-17','Nevena','2022/0006','Bošković',4,5,NULL,1);

/*Table structure for table `student_officer` */

DROP TABLE IF EXISTS `student_officer`;

CREATE TABLE `student_officer` (
  `id_student_officer` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin` bit(1) DEFAULT NULL,
  `answer_salt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `hashed_answer` varchar(255) DEFAULT NULL,
  `hashed_password` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `id_study_level` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_student_officer`),
  KEY `FKf0x7k08gdr2iqsa3wdwk5pud8` (`id_study_level`),
  CONSTRAINT `FKf0x7k08gdr2iqsa3wdwk5pud8` FOREIGN KEY (`id_study_level`) REFERENCES `study_level` (`id_study_level`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student_officer` */

insert  into `student_officer`(`id_student_officer`,`admin`,`answer_salt`,`email`,`first_name`,`hashed_answer`,`hashed_password`,`last_name`,`password_salt`,`question`,`id_study_level`) values 
(17,'','7pmXC7jzRwdnLvgI3uEe6A==','marko.markovic@fon.bg.ac.rs','Marko','tgbsLrtJb6t/NhB7UQHAID6ib0VD6ESDpNdmF7K8twM=','qgLpfQDyEdZ4ioo/5ok7XJnxhjwxUvprCE/xYGHAK3s=','Marković','nkJGpvJMPo+ClEE7/YNtTg==','123',1);

/*Table structure for table `study_level` */

DROP TABLE IF EXISTS `study_level`;

CREATE TABLE `study_level` (
  `id_study_level` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_study_level`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `study_level` */

insert  into `study_level`(`id_study_level`,`name`) values 
(1,'Osnovne akademske studije'),
(2,'Master akademske studije'),
(3,'Doktorske akademske studije'),
(4,'Specijalističke akademske studije'),
(5,'Strukovne studije');

/*Table structure for table `study_program` */

DROP TABLE IF EXISTS `study_program`;

CREATE TABLE `study_program` (
  `id_study_program` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_study_level` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_study_program`),
  KEY `FKby4hskqr23ncjunuo47bcu5mu` (`id_study_level`),
  CONSTRAINT `FKby4hskqr23ncjunuo47bcu5mu` FOREIGN KEY (`id_study_level`) REFERENCES `study_level` (`id_study_level`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `study_program` */

insert  into `study_program`(`id_study_program`,`name`,`id_study_level`) values 
(1,'OAS - Informacioni sistemi i tehnologije',1),
(2,'OAS - Menadžment i organizacija',1),
(8,'MAS - Elektronsko poslovanje',2),
(9,'MAS - Softversko inženjerstvo i veštačka inteligencija',2),
(10,'MAS - Informacioni sistemi i tehnologije',2),
(15,'DAS - Informacioni sistemi i tehnologije',3),
(16,'DAS - Menadžment i organizacija',3),
(17,'DAS - Optimizacija i analitika',3),
(18,'DAS - Softversko inženjerstvo i elektrosnko poslovanje',3),
(20,'SAS - Informacioni sistemi i tehnologije',4),
(21,'SAS - Elektronsko poslovanje i Java tehnologije',4),
(22,'SAS - Menadžment i organizacija',4);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id_teacher` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `teacher` */

insert  into `teacher`(`id_teacher`,`first_name`,`last_name`) values 
(1,'Nikola','Nikolić'),
(2,'Marta','Martinović'),
(4,'Isidora','Isidorović'),
(5,'Nikola','Mirković'),
(6,'Boban','Nikolić');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
