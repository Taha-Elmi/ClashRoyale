-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: clash_royale
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `name` varchar(15) DEFAULT NULL,
  `id` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES ('Archer',1),('BabyDragon',2),('Barbarian',3),('Giant',4),('MiniPekka',5),('Valkyrie',6),('Wizard',7),('Arrows',8),('FireBall',9),('Rage',10),('Cannon',11),('InfernoTower',12);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `level` int DEFAULT '1',
  `xp` int DEFAULT '0',
  `card1` tinyint DEFAULT '1',
  `card2` tinyint DEFAULT '2',
  `card3` tinyint DEFAULT '3',
  `card4` tinyint DEFAULT '4',
  `card5` tinyint DEFAULT '5',
  `card6` tinyint DEFAULT '6',
  `card7` tinyint DEFAULT '7',
  `card8` tinyint DEFAULT '8',
  `password` varchar(20) DEFAULT 'admin',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (8,'Taha',5,2160,3,7,9,4,11,2,8,10,'admin'),(10,'Farid',1,0,1,2,3,4,5,6,7,8,'admin'),(14,'Ahmad',1,0,1,2,3,4,5,6,7,8,'1234'),(18,'Saman',1,0,1,2,3,4,5,6,7,8,'1234'),(20,'Arian',1,0,1,2,3,4,5,6,7,8,'1234');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `name` varchar(25) DEFAULT NULL,
  `time` datetime DEFAULT (now()),
  `opponent` varchar(25) DEFAULT NULL,
  `crowns` int DEFAULT NULL,
  `opponent_crowns` int DEFAULT NULL,
  `result` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('Farid','2021-07-13 14:42:11','Taha',1,3,_binary '\0'),('Farid','2021-07-14 20:20:45','Taha',2,0,_binary ''),('Taha','2021-07-16 13:30:31','Farid',3,1,_binary ''),('Taha','2021-07-22 02:32:13','StupidRobot',0,0,_binary ''),('Taha','2021-07-22 03:26:34','StupidRobot',2,1,_binary ''),('Taha','2021-07-22 03:29:59','StupidRobot',1,0,_binary ''),('Taha','2021-07-22 03:30:58','StupidRobot',0,0,_binary '\0'),('Taha','2021-07-22 03:34:26','StupidRobot',0,0,_binary '\0'),('Taha','2021-07-22 05:03:25','StupidRobot',0,3,_binary '\0'),('Taha','2021-07-22 05:12:37','StupidRobot',1,3,_binary '\0'),('Taha','2021-07-22 05:52:44','StupidRobot',3,2,_binary ''),('Taha','2021-07-22 06:00:14','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-22 13:24:02','GeniusRobot',0,3,_binary '\0'),('Taha','2021-07-22 13:58:47','StupidRobot',2,3,_binary '\0'),('Taha','2021-07-22 14:08:54','SmartRobot',2,3,_binary '\0'),('Taha','2021-07-22 14:15:17','StupidRobot',3,2,_binary ''),('Taha','2021-07-22 14:56:17','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-22 15:52:23','StupidRobot',2,3,_binary '\0'),('Taha','2021-07-22 15:55:18','StupidRobot',3,0,_binary ''),('Taha','2021-07-22 16:31:19','StupidRobot',3,0,_binary ''),('Taha','2021-07-22 17:09:08','StupidRobot',3,1,_binary ''),('Taha','2021-07-22 17:25:18','StupidRobot',3,0,_binary ''),('Taha','2021-07-22 17:29:24','StupidRobot',0,3,_binary '\0'),('Taha','2021-07-22 17:33:53','StupidRobot',3,1,_binary ''),('Taha','2021-07-22 18:08:43','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-22 19:06:15','StupidRobot',3,2,_binary ''),('Taha','2021-07-22 20:24:25','StupidRobot',3,1,_binary ''),('Taha','2021-07-22 20:34:18','StupidRobot',3,1,_binary ''),('Taha','2021-07-22 20:34:21','StupidRobot',3,1,_binary ''),('Taha','2021-07-22 20:35:43','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-22 20:35:46','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-23 02:07:10','StupidRobot',0,3,_binary '\0'),('Taha','2021-07-23 02:17:33','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-23 02:36:42','StupidRobot',2,3,_binary '\0'),('Taha','2021-07-23 02:41:03','StupidRobot',0,1,_binary '\0'),('Taha','2021-07-23 02:41:51','StupidRobot',1,1,_binary ''),('Taha','2021-07-23 02:44:04','StupidRobot',1,1,_binary ''),('Taha','2021-07-23 03:10:28','StupidRobot',3,0,_binary ''),('Taha','2021-07-23 03:15:25','StupidRobot',3,0,_binary ''),('Taha','2021-07-23 03:17:00','StupidRobot',0,2,_binary '\0'),('Taha','2021-07-23 03:24:45','StupidRobot',2,3,_binary '\0'),('Taha','2021-07-23 03:51:35','StupidRobot',2,1,_binary '');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-23  3:55:57
