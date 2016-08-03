-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: alas2
-- ------------------------------------------------------
-- Server version	5.6.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `ItemId` varchar(20) NOT NULL,
  `TypeId` int(10) NOT NULL,
  `ItemName` varchar(40) NOT NULL,
  `ISBN` int(30) NOT NULL,
  `Publisher` varchar(30) NOT NULL,
  `PublishedYear` date DEFAULT NULL,
  `Author` varchar(20) DEFAULT NULL,
  `Status` varchar(20) NOT NULL,
  `LocId` int(10) NOT NULL,
  PRIMARY KEY (`ItemId`),
  KEY `TypeId_idx` (`TypeId`),
  KEY `LocId_x` (`LocId`),
  CONSTRAINT `LocId` FOREIGN KEY (`LocId`) REFERENCES `location` (`LocId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TypeId` FOREIGN KEY (`TypeId`) REFERENCES `itemtype` (`TypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('ALGO0001',502,'find a new book',1203923,'pub','2004-06-15','wengjin','Unvaliable',405),('ALGO0002',502,'Algorithm X',8763456,'Grew Hills','2015-01-13','Victor','Unvaliable',408),('ALGO0003',502,'Applied Optics ',8284755,'one','2001-06-12','xx','Unavailable',405),('ALGO0004',502,'Applied Organometallic Chemistry',2836756,'two','2006-03-15','sss','Unvaliable',405),('ALGO0005',502,'Applied Physics Letters',294756,'three','2016-01-19','dd','Unvaliable',405),('ALGO0006',502,'Applied Radiation ',392374,'four','2015-12-09','xiaohong','Unavailable',405),('ALGO0007',502,'Applied ',283756,'candice','2015-12-01','xs','Available',405),('ALGO0008',502,'Applied Surface ',3837456,'wang','2015-01-05','xl','Available',405),('ALGO0009',502,'Aquatic ',2928345,'bule','2014-06-10','ba','Available',405),('ALGO0010',502,'Biomass ',2903845,'wan','2009-02-09','angela','Available',405),('BIZ0001',505,'Business World',1346278,'BizWorld','2009-02-18','John','Available',405),('BIZ0002',505,'economist',234567,'ai','2009-02-19','baby','Available',405),('BIZ0003',505,'fianance',2345678,'wan','2009-02-20','fantasitic','Available',405),('BIZ0004',505,'accounting',356346,'blue','2009-06-18','xiao','Available',405),('BIZ0005',505,'fianance analysis',325432,'green','2009-02-19','ming','Available',405),('BIZ0006',505,'accounting world',565858,'yellow','2009-07-16','haung','Available',405),('BIZ0007',505,'bank',546745,'wang','2009-02-19','derek','Available',405),('BIZ0008',505,'analysis bank',456476,'can','2009-02-19','ester','Available',405),('BIZ0009',505,'accounting world',453547,'ee','2009-02-17','da','Available',405),('E0000749',502,'candice',8393821,'iss','1999-08-08','wengjin','Available',405),('ISS087623',500,'java',2834532,'iss','2015-12-09','Super man','Unvaliable',402),('ISS09345',500,'c#',398356,'iss','2015-12-09','venkat','Unvaliable',402),('KITS0001',503,'SKOOLS',2317685,'SKOOL','2009-02-10','Jenny','Available',407),('KITS0002',503,'IEEE Micro ',385733,'angela','2009-02-18','suria','Available',407),('KITS0003',503,'Annual Review ',2423525,'babyl','2009-07-22','zhimin','Available',407),('KITS0004',503,'Antarctic Science',214357,'baby','2009-02-20','zhimin','Available',407),('KITS0005',503,'Appita Journal',675844,'angela','2009-02-26','surai','Available',407),('KITS0006',503,'Applied Energy',4645754,'wang','2009-06-16','wang','Available',407),('KITS0007',503,'Applied Energy',475648,'shiling','2009-02-12','sicong','Available',407),('KITS0008',503,'Applied Optics ',436346,'shing','2009-02-17','guo','Available',407),('KITS0009',503,'Applied  Energy',4364754,'light','2009-02-19','fucheng','Available',407),('MGZN0001',501,'Complete Health',6775898,'CVR','2009-02-18','Keith','Available',402),('MGZN0002',500,'The Woman',7876432,'white Star','2009-02-09','ShobhaDe','Unavailable',403),('MGZN0003',500,'Forbes',2877667,'Forbe','2009-02-19','Forbe','Unavailable',401),('MGZN0004',500,'Advanced Materials',234556,'ABC','2009-02-06','xiaoming','Unavailable',402),('MGZN0005',500,'Applied Physics ',334566,'WHite star','2009-02-17','can','Unavailable',402),('MGZN0006',500,'Chemical Communications',123345,'Blue Star','2009-02-19','wang','Unavailable',402),('MGZN0007',500,'Chemistry of Materials',345787,'common','2009-02-12','lily','Unavailable',402),('MGZN0008',500,'Journal of Physical',3345567,'wealth','2009-02-05','Xman','Unavailable',402),('MGZN0009',500,'Nano Letters',332224,'ABC','2009-02-05','Weng','Available',402),('MGZN0010',500,'Physical Review B',988764,'Skool','2009-02-13','Lima','Unavailable',402),('MGZN0011',500,'Physical Review letters ',334556,'wonder','2009-02-18','Xiaohong','Unavailable',403),('MGZN0012',500,'Sunshine Days',345664,'gem','2009-02-18','Spider Man','Available',403),('MGZN0013',500,'My Vacation',2345767,'imm','2009-02-12','team leader','Available',403),('MGZN0014',500,'Astronomy Journal',2345677,'orchard','2009-02-19','red','Available',403),('MGZN0015',500,'Astronomy Reports ',22355667,'one','2009-02-13','bule','Available',403),('MGZN0016',500,'American Zoologist',9287522,'two','2009-02-20','hotel','Available',403),('MGZN0017',500,'Analytical Sciences',2343466,'amazine','2009-02-19','yellow','Available',403),('PRGMM0001',504,'Python',563428,'CCR','2013-06-10','ProgrammerX','Available',409),('TECH0001',504,'Java 8',76543267,'Complete Ref','1993-01-10','HBSLD91887','Available',404);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemtype`
--

DROP TABLE IF EXISTS `itemtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemtype` (
  `TypeId` int(10) NOT NULL,
  `TypeName` varchar(30) NOT NULL,
  PRIMARY KEY (`TypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemtype`
--

LOCK TABLES `itemtype` WRITE;
/*!40000 ALTER TABLE `itemtype` DISABLE KEYS */;
INSERT INTO `itemtype` VALUES (500,'Magazine'),(501,'Manuscript'),(502,'Algorithm'),(503,'Kits'),(504,'Technology'),(505,'Business');
/*!40000 ALTER TABLE `itemtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loantransaction`
--

DROP TABLE IF EXISTS `loantransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loantransaction` (
  `LoanId` int(10) NOT NULL AUTO_INCREMENT,
  `ItemId` varchar(20) NOT NULL,
  `StuLoginId` varchar(20) NOT NULL,
  `IssueDate` date NOT NULL,
  `ActualReturnDate` date DEFAULT NULL,
  `IssuedBy` int(10) DEFAULT NULL,
  `itemStatus` varchar(30) NOT NULL,
  PRIMARY KEY (`LoanId`),
  KEY `LoginId_dx` (`StuLoginId`),
  KEY `Item_dx` (`ItemId`),
  KEY `Loan_dx` (`LoanId`),
  KEY `IssuedBy` (`IssuedBy`),
  CONSTRAINT `IssuedBy` FOREIGN KEY (`IssuedBy`) REFERENCES `role` (`RoleId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ItemIdd` FOREIGN KEY (`ItemId`) REFERENCES `items` (`ItemId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StuLoginId` FOREIGN KEY (`StuLoginId`) REFERENCES `users` (`LoginId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1060 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loantransaction`
--

LOCK TABLES `loantransaction` WRITE;
/*!40000 ALTER TABLE `loantransaction` DISABLE KEYS */;
INSERT INTO `loantransaction` VALUES (1001,'MGZN0001','Student1','2008-10-11',NULL,101,'onLoan'),(1002,'MGZN0002','Student1','2008-10-11','2008-11-11',101,'returned'),(1003,'MGZN0003','Student1','2008-10-11',NULL,102,'onLoan'),(1004,'TECH0001','Student1','2008-10-11',NULL,101,'onLoan'),(1005,'TECH0001','Student1','2008-10-11',NULL,101,'onLoan'),(1006,'ALGO0001','Student1','2008-10-11','2008-11-19',101,'returned'),(1007,'KITS0001','Student1','2008-10-11','2008-11-11',101,'returned'),(1008,'ALGO0002','Student1','2008-10-11','2008-11-11',102,'returned'),(1009,'PRGMM0001','Student1','2008-10-11','2008-11-11',102,'returned'),(1010,'ALGO0009','Student1','2015-12-08','2015-12-08',102,'returned'),(1011,'ALGO0005','Student1','2015-12-08','2016-01-21',101,'returned'),(1012,'MGZN0002','E0000293','2015-12-08','2015-12-08',102,'returned'),(1013,'E0000749','E0000293','2015-12-08','2016-01-21',102,'returned'),(1014,'ALGO0007','Student1','2015-12-08','2016-01-21',102,'returned'),(1015,'BIZ0008','E0000293','2015-12-08','2015-12-09',102,'returned'),(1016,'MGZN0002','E0000293','2015-12-08','2016-01-21',102,'returned'),(1017,'MGZN0002','Student1','2016-01-21','2016-03-09',102,'returned'),(1018,'MGZN0004','Student1','2016-01-21','2016-01-21',101,'returned'),(1019,'MGZN0004','Student1','2016-01-21','2016-03-09',101,'returned'),(1020,'MGZN0005','Student1','2016-01-21','2016-03-09',101,'returned'),(1021,'MGZN0006','Student1','2016-01-21','2016-03-09',101,'returned'),(1022,'MGZN0007','Student1','2016-01-21','2016-03-09',101,'returned'),(1023,'MGZN0008','Student1','2016-01-21','2016-03-09',101,'returned'),(1024,'MGZN0009','Student1','2016-01-21','2016-03-09',101,'returned'),(1025,'MGZN0010','Student1','2016-01-21','2016-03-09',101,'returned'),(1026,'MGZN0011','Student1','2016-01-21','2016-03-09',101,'returned'),(1027,'MGZN0012','Student1','2016-01-21','2016-03-09',101,'returned'),(1028,'MGZN0002','Student1','2016-03-09',NULL,101,'on Loan'),(1029,'MGZN0004','Student1','2016-03-09',NULL,101,'on Loan'),(1030,'MGZN0005','Student1','2016-03-09',NULL,101,'on Loan'),(1031,'MGZN0013','E0000293','2016-03-09',NULL,102,'on Loan'),(1032,'ALGO0002','E0000293','2015-09-01',NULL,102,'on Loan'),(1033,'ALGO0003','E0000293','2015-09-01',NULL,102,'on Loan'),(1034,'ALGO0004','E0000293','2015-09-01',NULL,102,'on Loan'),(1035,'ALGO0006','E0000293','2015-09-01',NULL,102,'on Loan'),(1036,'ALGO0007','E0000293','2015-09-01',NULL,102,'on Loan'),(1037,'ALGO0008','E0000293','2015-09-01','2015-12-09',102,'returned'),(1038,'ALGO0009','E0000293','2015-09-01','2015-12-09',102,'returned'),(1039,'ALGO0010','E0000293','2015-09-01','2015-12-09',102,'returned'),(1040,'MGZN0009','S001','2015-12-08','2015-12-09',102,'returned'),(1041,'MGZN0006','Student1','2015-12-08',NULL,102,'on Loan'),(1042,'MGZN0007','Student1','2015-12-09','2015-12-09',102,'returned'),(1043,'ALGO0003','Student1','2015-12-09',NULL,101,'on Loan'),(1044,'ALGO0006','Student1','2015-12-09',NULL,101,'on Loan'),(1045,'ALGO0007','Student1','2015-12-09','2008-07-24',101,'returned'),(1046,'ALGO0008','Student1','2008-07-24','2008-07-24',101,'returned'),(1047,'ALGO0009','Student1','2008-07-24',NULL,101,'on Loan'),(1048,'ALGO0007','S002','2015-12-09','2015-12-09',102,'returned'),(1049,'MGZN0003','S002','2015-12-09','2015-12-09',102,'returned'),(1050,'MGZN0002','S002','2015-12-09',NULL,102,'on Loan'),(1051,'MGZN0003','S002','2015-12-09',NULL,102,'on Loan'),(1052,'MGZN0004','S002','2015-12-09',NULL,102,'on Loan'),(1053,'MGZN0005','S002','2015-12-09',NULL,102,'on Loan'),(1054,'MGZN0006','S002','2015-12-09',NULL,102,'on Loan'),(1055,'MGZN0007','S002','2015-12-09',NULL,102,'on Loan'),(1056,'MGZN0008','S002','2015-12-09',NULL,102,'on Loan'),(1057,'MGZN0009','S002','2015-12-09',NULL,102,'on Loan'),(1058,'MGZN0010','S002','2015-12-09',NULL,102,'on Loan'),(1059,'MGZN0011','S002','2015-12-09',NULL,102,'on Loan');
/*!40000 ALTER TABLE `loantransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `LocId` int(10) NOT NULL,
  `RackNumber` varchar(10) NOT NULL,
  `ShelfId` varchar(10) NOT NULL,
  PRIMARY KEY (`LocId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (401,'RK01','U02'),(402,'RK01','L01'),(403,'RK01','U02'),(404,'RK01','U03'),(405,'RK01','U04'),(406,'RK01','L02'),(407,'RK01','L03'),(408,'RK01','L04'),(409,'RK001','L05');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `RoleId` int(10) NOT NULL,
  `RoleName` varchar(20) NOT NULL,
  PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (101,'STUDENT'),(102,'LIBRARIAN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `LoginId` varchar(20) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `RoleId` int(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `PhoneNo` int(15) DEFAULT NULL,
  `CreatedOn` date NOT NULL,
  `Status` varchar(30) NOT NULL,
  PRIMARY KEY (`LoginId`),
  KEY `RoleId_idx` (`RoleId`),
  CONSTRAINT `RoleIdd` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('E0000293','19930110jing',101,'yangdashuai','simonyang@qq.com',28378888,'2015-12-08','Inactive'),('E0000759','19930110jing',102,'wengjin','506651198@qq.com',86567670,'2015-06-06','Inactive'),('L001','L001',102,'Yang','Yang@library.com',22222222,'2010-10-23','Inactive'),('L002','L002',102,'Jack','Jack@library.com',44444444,'2012-10-27','Inactive'),('L003','L00',102,'Zaw','Zaw@library.com',6666655,'2010-10-23','Inactive'),('L004','L004',102,'Steven','Steven@library.com',88888888,'2006-10-25','Active'),('L005','L005',102,'Poorni','Poorni@library.com',80808080,'2006-10-28','Active'),('L006','L006',102,'Derek','Derek@library.com',1212121212,'2006-10-29','Active'),('L007','L007',102,'Leo','Leo@library.com',34343434,'2006-10-21','Inactive'),('Librarian1','Librarian1',102,'Yang','Librarian1@library.com',80808080,'2006-10-20','Active'),('Librarian3','1234',102,'yiheng','yiheng@dream.com',8888887,'2016-01-21','Inactive'),('s0001','s0001',101,'Candcie','Candice@library.com',11111111,'2015-12-09','Active'),('S001','S001',101,'Candice','Candice@library.com',11111111,'2008-12-15','Inactive'),('S002','S002',101,'Summer','Summer@library.com',33333333,'2011-12-19','Active'),('S003','S003',101,'Christ','Christ@library.com',55555555,'2011-12-14','Active'),('S004','S004',101,'Lu','Lu@library.com',77777777,'2007-12-14','Active'),('S005','S005',101,'Dave','Dave@library.com',99999999,'2007-12-16','Active'),('S006','S006',101,'Grace','Grace@library.com',90909090,'2007-12-16','Active'),('S007','S007',101,'Alice','Alice@library.com',23232323,'2007-12-17','Inactive'),('S11','S11',101,'roster','roster@gmail.com',56789007,'2016-03-09','Active'),('Student1','Student1',101,'Candice','student1@library.com',90988888,'2007-12-10','Active'),('student11','student11',101,'yiheng','yiheng1@dream.com',3947656,'2016-03-09','Active');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v`
--

DROP TABLE IF EXISTS `v`;
/*!50001 DROP VIEW IF EXISTS `v`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v` AS SELECT 
 1 AS `ItemId`,
 1 AS `ItemName`,
 1 AS `ISBN`,
 1 AS `Publisher`,
 1 AS `PublishedYear`,
 1 AS `Author`,
 1 AS `Status`,
 1 AS `TypeName`,
 1 AS `RackNumber`,
 1 AS `ShelfId`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v`
--

/*!50001 DROP VIEW IF EXISTS `v`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v` AS select `items`.`ItemId` AS `ItemId`,`items`.`ItemName` AS `ItemName`,`items`.`ISBN` AS `ISBN`,`items`.`Publisher` AS `Publisher`,`items`.`PublishedYear` AS `PublishedYear`,`items`.`Author` AS `Author`,`items`.`Status` AS `Status`,`itemtype`.`TypeName` AS `TypeName`,`location`.`RackNumber` AS `RackNumber`,`location`.`ShelfId` AS `ShelfId` from ((`items` join `itemtype`) join `location`) where ((`items`.`TypeId` = `itemtype`.`TypeId`) and (`items`.`LocId` = `location`.`LocId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-09 11:15:29
