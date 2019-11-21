-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema tiktopgym
--

CREATE DATABASE IF NOT EXISTS tiktopgym;
USE tiktopgym;

--
-- Definition of table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int(10) unsigned NOT NULL auto_increment,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `attendance_date` date NOT NULL,
  `member_id` int(10) unsigned NOT NULL,
  `update_date` date default NULL,
  PRIMARY KEY  (`attendance_id`),
  KEY `FK_attendance_1` (`member_id`),
  CONSTRAINT `FK_attendance_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` (`attendance_id`,`height`,`weight`,`attendance_date`,`member_id`,`update_date`) VALUES 
 (1,5.8,100.1,'2019-11-02',20,'2019-11-02'),
 (2,6.1,300,'2019-11-03',20,NULL),
 (4,6.2,140,'2019-11-04',20,'2019-11-02'),
 (5,6.1,150,'2019-11-05',20,'2019-11-03'),
 (6,5,100,'2019-11-11',32,NULL),
 (7,6,500,'2019-11-12',32,NULL),
 (8,5,50,'2019-11-12',29,NULL),
 (9,5,60,'2019-11-18',31,NULL),
 (10,6,90,'2019-11-19',34,NULL);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;


--
-- Definition of table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `booking_id` int(10) unsigned NOT NULL auto_increment,
  `schedule_id` int(10) unsigned default NULL,
  `trainer_id` int(10) unsigned default NULL,
  `package_id` int(10) unsigned default NULL,
  `member_id` int(10) unsigned default NULL,
  `status` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `expired_date` date NOT NULL,
  PRIMARY KEY  (`booking_id`),
  KEY `FK_booking_1` (`schedule_id`),
  KEY `FK_booking_2` (`trainer_id`),
  KEY `FK_booking_3` (`package_id`),
  KEY `FK_booking_4` (`member_id`),
  CONSTRAINT `FK_booking_1` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`schedule_id`),
  CONSTRAINT `FK_booking_2` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`trainer_id`),
  CONSTRAINT `FK_booking_3` FOREIGN KEY (`package_id`) REFERENCES `package` (`package_id`),
  CONSTRAINT `FK_booking_4` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` (`booking_id`,`schedule_id`,`trainer_id`,`package_id`,`member_id`,`status`,`create_date`,`expired_date`) VALUES 
 (2,NULL,NULL,5,20,'pending','2019-10-15 18:48:38','2020-10-14'),
 (3,NULL,NULL,5,20,'pending','2019-10-15 18:51:12','2020-10-14'),
 (6,NULL,NULL,3,20,'confirmed','2019-10-15 19:17:07','2019-11-14'),
 (7,NULL,NULL,3,20,'pending','2019-10-15 19:18:59','2019-11-14'),
 (8,NULL,NULL,5,20,'pending','2019-10-15 19:37:56','2020-10-14'),
 (13,NULL,NULL,5,20,'pending','2019-10-15 21:21:10','2020-10-14'),
 (18,NULL,NULL,2,20,'confirmed','2019-10-17 10:11:50','2020-01-16'),
 (19,NULL,NULL,1,20,'confirmed','2019-10-17 10:20:18','2020-04-16'),
 (21,38,NULL,NULL,20,'confirmed','2019-10-17 10:21:11','2019-10-18'),
 (22,NULL,NULL,5,20,'confirmed','2019-10-17 10:22:08','2020-10-16'),
 (23,38,NULL,NULL,20,'confirmed','2019-10-17 11:28:07','2019-10-18'),
 (24,38,NULL,NULL,20,'confirmed','2019-10-17 11:35:42','2019-10-18'),
 (25,39,NULL,NULL,20,'confirmed','2019-10-17 11:36:57','2019-10-19'),
 (26,39,NULL,NULL,20,'pending','2019-10-17 11:40:59','2019-10-19');
INSERT INTO `booking` (`booking_id`,`schedule_id`,`trainer_id`,`package_id`,`member_id`,`status`,`create_date`,`expired_date`) VALUES 
 (28,NULL,NULL,2,26,'confirmed','2019-10-17 12:53:55','2020-01-16'),
 (29,41,NULL,NULL,26,'confirmed','2019-10-17 12:53:59','2019-10-21'),
 (30,NULL,NULL,2,26,'pending','2019-10-17 12:54:02','2020-01-16'),
 (31,40,NULL,NULL,26,'pending','2019-10-17 12:54:06','2019-10-20'),
 (32,NULL,NULL,3,26,'pending','2019-10-17 12:54:09','2019-11-16'),
 (33,38,NULL,NULL,20,'confirmed','2019-10-17 14:33:58','2019-10-18'),
 (34,NULL,NULL,2,20,'pending','2019-10-17 14:34:53','2020-01-16'),
 (35,NULL,NULL,5,20,'confirmed','2019-10-24 15:57:35','2020-10-23'),
 (36,43,NULL,NULL,20,'confirmed','2019-10-26 10:21:05','2019-10-18'),
 (37,43,NULL,NULL,26,'pending','2019-10-27 17:43:11','2019-10-18'),
 (38,NULL,NULL,1,26,'confirmed','2019-10-27 17:43:23','2020-04-26'),
 (39,43,NULL,NULL,26,'pending','2019-10-27 17:56:04','2019-10-18'),
 (40,NULL,NULL,5,31,'pending','2019-10-27 22:36:32','2020-10-26'),
 (41,39,NULL,NULL,31,'pending','2019-10-27 22:36:46','2019-10-19');
INSERT INTO `booking` (`booking_id`,`schedule_id`,`trainer_id`,`package_id`,`member_id`,`status`,`create_date`,`expired_date`) VALUES 
 (44,NULL,NULL,5,20,'pending','2019-11-13 00:06:17','2020-11-12');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;


--
-- Definition of table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classes_id` int(10) unsigned NOT NULL auto_increment,
  `classes_name` varchar(45) NOT NULL,
  `create_date` date default NULL,
  `update_date` date default NULL,
  PRIMARY KEY  (`classes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` (`classes_id`,`classes_name`,`create_date`,`update_date`) VALUES 
 (1,'Gym Class','2019-10-14',NULL),
 (2,'Karatay Class','2019-10-14',NULL),
 (3,'Boxing class','2019-10-14',NULL),
 (4,'Yoga class','2019-10-14',NULL),
 (5,'Swimming','2019-10-17',NULL);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;


--
-- Definition of table `member`
--

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(10) unsigned NOT NULL auto_increment,
  `member_name` varchar(45) NOT NULL,
  `member_phone` varchar(15) NOT NULL,
  `member_address` varchar(45) NOT NULL,
  `member_email` varchar(45) NOT NULL,
  `create_date` date default NULL,
  `update_date` date default NULL,
  `enable` bit(1) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY  USING BTREE (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `member`
--

/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`,`member_name`,`member_phone`,`member_address`,`member_email`,`create_date`,`update_date`,`enable`,`login`,`password`,`role`) VALUES 
 (20,'Paye Phyo Paing','097777777','Bago','paingpaing4228@gmail.com','2019-10-09','2019-10-15',0x01,'ppp','$2a$10$giMshPgzwGzwfvVPA/jaHuArgQNocwYYxTK8esGXzq9nW0GgPB.ry','ROLE_MEMBER'),
 (22,'paing Paing','097777777','Yangon','paingpaing4228@gmail.com','2019-10-14',NULL,0x01,'p123','$2a$10$J46S5samrY4Lf5M2Ad4LfOdfUbYKPJfPASaxgiz2qo13UZCJtRING','ROLE_MEMBER'),
 (23,'paing','09796964228','Bago','paingpaing4228@gmail.com','2019-10-14',NULL,0x01,'ppp','$2a$10$dRTK.PCio.qx/NoFb02fS..GEhBNqjMN1bB5PLVuauj6VNX8sglw2','ROLE_MEMBER'),
 (24,'ppp','097777777','Bago','paingpaing4228@gmail.com','2019-10-14',NULL,0x01,'ppp','$2a$10$Kuef4E0NnS8CRS13dJDYIul34tfOSzpsuJw9ekSyMMbX/mLAckt0.','ROLE_MEMBER'),
 (25,'ppp','097777777','Bago','paingpaing4228@gmail.com','2019-10-14',NULL,0x01,'ppp','$2a$10$AYi8Yjdr9Myc2D2vKp6/ZeV0fgV3MihbRmN.9A0scK8wcsK7X51W.','ROLE_MEMBER'),
 (26,'Paing Paing','097777777','Bago','paingpaing4228@gmail.com','2019-10-17',NULL,0x01,'paing123','$2a$10$CpLbxmOG/KYKi.xUOXZbauodXTvarX8uZwz6T4IcLCtCXj0C8rYeK','ROLE_MEMBER');
INSERT INTO `member` (`member_id`,`member_name`,`member_phone`,`member_address`,`member_email`,`create_date`,`update_date`,`enable`,`login`,`password`,`role`) VALUES 
 (28,'Paing','097777777','Bago','paingpaing4228@gmail.com','2019-10-27',NULL,0x01,'ppp1122','$2a$10$rMrAfiETnFictqrkd9sFMujCEpNKuC.XJqe7T9uxf8RGZSHIxZB76','ROLE_MEMBER'),
 (29,'Paing Paing','09796964228','Bago','paingpaing4228@gmail.com','2019-10-27',NULL,0x01,'p11','$2a$10$CpLbxmOG/KYKi.xUOXZbauodXTvarX8uZwz6T4IcLCtCXj0C8rYeK','ROLE_MEMBER'),
 (30,'ppp','097777777','Bago','paingpaing4228@gmail.com','2019-10-27',NULL,0x01,'pp1122','$2a$10$SyjGO1sB1BB4s9JW5vnOiuR9eGn2bf3074r4zBmxjysaeACYscO/G','ROLE_MEMBER'),
 (31,'Oo Paing','097888887979','Bago','paingpaing4228@gmail.com','2019-10-27',NULL,0x01,'oo123','$2a$10$uQ3Er21ArpmZxiv.0Z7JyeruOGuXicLyM0onIYh3ToTNCnVV86Dau','ROLE_MEMBER'),
 (32,'Thae Myat Noe Htut','0955555555','Yangon','thaaaa@gmail.com','2019-11-10',NULL,0x01,'htut123','$2a$10$ynUpXkf9rY4Y9tGzt/xBsO5H1r9eehRMEC1NlpENDYz.Qzx3G4HAq','ROLE_MEMBER'),
 (33,'admin','09796964228','Bago','paingpaing4228@gmail.com','2019-11-12',NULL,0x01,'admin','$2a$10$EcKDRrJB8U.IC4zXNfZb2e.8X65u9LApz760Jc9wO1i7x1LWhIIke','ROLE_ADMIN');
INSERT INTO `member` (`member_id`,`member_name`,`member_phone`,`member_address`,`member_email`,`create_date`,`update_date`,`enable`,`login`,`password`,`role`) VALUES 
 (34,'po po','097777777','Bago','popo123@gmail.com','2019-11-12',NULL,0x01,'popo','$2a$10$yaniCkn.fz5gD36v7XKFWuhpamve22xsaLBurdN9Ngqstel4oktmG','ROLE_MEMBER'),
 (35,'ppp','097777777','Bago','paingpaing4228@gmail.com','2019-11-18',NULL,0x01,'poe','$2a$10$2v9sjJY9ospa1ctZ0vrx6.Fa.dRGkpF4cNpMYXvQfSp4rq5vhGY2a','ROLE_MEMBER'),
 (36,'pp','097777777','Bago','paingpaing4228@gmail.com','2019-11-19',NULL,0x01,'ppp112233','$2a$10$LKcZQ/fhegkRQ9jX.R7N7Oorf2B1yCmPDF.ISFcQzuI2CPqKRpVQG','ROLE_MEMBER');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


--
-- Definition of table `package`
--

DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `package_id` int(10) unsigned NOT NULL auto_increment,
  `package_name` varchar(45) NOT NULL,
  `package_duration` int(10) unsigned NOT NULL,
  `package_fees` int(10) unsigned NOT NULL,
  `create_date` date default NULL,
  `update_date` date default NULL,
  PRIMARY KEY  (`package_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `package`
--

/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` (`package_id`,`package_name`,`package_duration`,`package_fees`,`create_date`,`update_date`) VALUES 
 (1,'Diamod',6,150000,'2019-09-06','2019-10-14'),
 (2,'Silver',3,80000,'2019-09-06','2019-10-14'),
 (3,'Bronze',1,30000,'2019-09-06','2019-10-14'),
 (5,'Platinum',12,300000,'2019-10-14',NULL);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;


--
-- Definition of table `paymentinfo`
--

DROP TABLE IF EXISTS `paymentinfo`;
CREATE TABLE `paymentinfo` (
  `paymentinfo_id` int(10) unsigned NOT NULL auto_increment,
  `payment_partnername` varchar(45) NOT NULL,
  `payment_ac` varchar(255) NOT NULL,
  PRIMARY KEY  (`paymentinfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymentinfo`
--

/*!40000 ALTER TABLE `paymentinfo` DISABLE KEYS */;
INSERT INTO `paymentinfo` (`paymentinfo_id`,`payment_partnername`,`payment_ac`) VALUES 
 (2,'Wave Money','09975401554'),
 (3,'OKDollar','09975401554'),
 (4,'KBZ pay','09796964228');
/*!40000 ALTER TABLE `paymentinfo` ENABLE KEYS */;


--
-- Definition of table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `schedule_id` int(10) unsigned NOT NULL auto_increment,
  `schedule_time` varchar(45) NOT NULL,
  `schedule_day` varchar(45) NOT NULL,
  `classes_id` int(10) unsigned NOT NULL,
  `trainer_id` int(10) unsigned NOT NULL,
  `create_date` date NOT NULL,
  `update_date` date default NULL,
  `fees` varchar(45) NOT NULL,
  PRIMARY KEY  (`schedule_id`),
  KEY `FK_schedule_2` (`trainer_id`),
  CONSTRAINT `FK_schedule_2` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`trainer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` (`schedule_id`,`schedule_time`,`schedule_day`,`classes_id`,`trainer_id`,`create_date`,`update_date`,`fees`) VALUES 
 (35,'11am - 1pm','Sunday',2,3,'2019-10-15','2019-10-24','6000'),
 (36,'1pm - 3pm','Sunday',1,3,'2019-10-16',NULL,'6000'),
 (37,'11am - 1pm','Sunday',1,4,'2019-10-17',NULL,'7000'),
 (38,'1pm - 3pm','Sunday',1,4,'2019-10-18',NULL,'8000'),
 (39,'11am - 1pm','Sunday',1,3,'2019-10-19',NULL,'6000'),
 (40,'1pm - 3pm','Tuesday',2,3,'2019-10-20',NULL,'8000'),
 (41,'11am - 1pm','Tuesday',2,4,'2019-10-21',NULL,'8000'),
 (42,'9am - 11am','Sunday',1,4,'2019-10-21',NULL,'6000'),
 (43,'9am - 11am','Sunday',1,3,'2019-10-18',NULL,'9000'),
 (44,'9am - 11am','Sunday',3,3,'2019-10-16',NULL,'9000'),
 (45,'9am - 11am','Sunday',1,3,'2019-10-18',NULL,'5000');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;


--
-- Definition of table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
CREATE TABLE `trainer` (
  `trainer_id` int(10) unsigned NOT NULL auto_increment,
  `trainer_name` varchar(45) NOT NULL,
  `trainer_type` varchar(45) NOT NULL,
  `trainer_phone` varchar(45) NOT NULL,
  `trainer_address` varchar(45) NOT NULL,
  `create_date` date default NULL,
  `update_date` date default NULL,
  `trainer_description` varchar(500) NOT NULL,
  `trainer_image_name` varchar(45) NOT NULL,
  `trainer_fees` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`trainer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trainer`
--

/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` (`trainer_id`,`trainer_name`,`trainer_type`,`trainer_phone`,`trainer_address`,`create_date`,`update_date`,`trainer_description`,`trainer_image_name`,`trainer_fees`) VALUES 
 (3,'John','Gym Trainer','097960626545','Yangon','2019-10-15','2019-11-12','He is the good trainer and has the experience of 4 years.','John.png',100000),
 (4,'Aung Aung','Boxing Trainer','09796062654','Yangon','2019-10-15','2019-10-15','Boxing experience of 5 years and trainer from him got the country awards.','Aung Aung.png',70000),
 (5,'Kyaw Soe','Karatay','09974561259','Yangon','2019-10-15','2019-10-15','Karatay Master and white belt owner.','Kyaw Soe.png',50000),
 (6,'Sara','Yoga Trainer','09787878452','Yangon','2019-10-15','2019-10-15','She is the best Yoga instructor and all clients obey her instruction.','sara.jpg',100000);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
