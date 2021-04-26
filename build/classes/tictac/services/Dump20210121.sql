CREATE DATABASE  IF NOT EXISTS `tictactoe` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tictactoe`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tictactoe
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `replay`
--

DROP TABLE IF EXISTS `replay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replay` (
  `gameid` int(11) NOT NULL AUTO_INCREMENT,
  `steps` varchar(500) NOT NULL,
  `players` varchar(500) NOT NULL,
  `Winner` varchar(45) NOT NULL,
  `play_with_who` varchar(45) NOT NULL,
  PRIMARY KEY (`gameid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replay`
--

LOCK TABLES `replay` WRITE;
/*!40000 ALTER TABLE `replay` DISABLE KEYS */;
INSERT INTO `replay` VALUES (1,'B00,B12,B11,B10,B02,B21,B20,','player,Computer,player,Computer,player,Computer,player,','sara','Computer'),(2,'B00,B12,B11,B10,B02,B21,B20,B01,B21,B11,B02,B22,B20,B10,B00,B12,B02,B00,B11,B12,B21,B01,B20,','player,Computer,player,Computer,player,Computer,player,player,Computer,player,Computer,player,Computer,player,Computer,player,player,Computer,player,Computer,player,Computer,player,','sara','Computer'),(3,'B00,B11,B10,B20,B01,B12,B02,','player,Computer,player,Computer,player,Computer,player,','sara','Computer'),(4,'B00,B11,B10,B20,B01,B12,B02,B00,B10,B11,B02,B22,','player,Computer,player,Computer,player,Computer,player,player,Computer,player,Computer,player,','sara','Computer'),(5,'B00,B10,B01,B21,B02,','player,computer,player,computer,player,','sara','Computer'),(6,'B00,B22,B01,B02,B11,B10,B20,B21,B12,B01,B11,B00,B22,B10,B12,B20,','player,computer,player,computer,player,computer,player,computer,player,player,computer,player,computer,player,computer,player,','Amira','Computer'),(7,'B02,B22,B12,B00,B10,B20,B01,B11,','player,computer,player,computer,player,computer,player,computer,','Amira','Computer'),(8,'B02,B22,B12,B00,B10,B20,B01,B11,B21,B00,B22,B01,B02,B11,B20,B10,B12,','player,computer,player,computer,player,computer,player,computer,player,player,computer,player,computer,player,computer,player,computer,','Amira','Computer'),(9,'B02,B21,B12,B00,B11,B20,B01,B22,','player,computer,player,computer,player,computer,player,computer,','Amira','Computer'),(10,'B00,B12,B11,B22,B02,B21,B10,B20,','player,computer,player,computer,player,computer,player,computer,','Amira','Computer'),(11,'B01,B02,B12,B20,B00,B22,B21,B11,','player,computer,player,computer,player,computer,player,computer,','Mariam','Computer'),(12,'B00,B01,B10,B22,B20,','player,computer,player,computer,player,','Mariam','Computer'),(13,'B01,B22,B00,B11,B20,B21,B10,','player,computer,player,computer,player,computer,player,','Mariam','Computer'),(14,'B01,B20,B11,B10,B21,','player,Computer,player,Computer,player,','sara','Computer'),(15,'B00,B10,B01,B12,B02,','player,Computer,player,Computer,player,','sara','Computer'),(16,'B00,B10,B01,B12,B02,B00,B20,B22,B11,B02,B01,B10,B12,B21,','player,Computer,player,Computer,player,player,Computer,player,Computer,player,Computer,player,Computer,player,','sara','Computer'),(17,'B01,B00,B02,B11,B22,B10,B12,','userName,Computer,userName,Computer,userName,Computer,userName,','sara','Computer'),(18,'B00,B12,B10,B01,B02,B11,B21,B22,B20,','amira,Computer,amira,Computer,amira,Computer,amira,Computer,amira,','sara','Computer'),(19,'B00,B11,B10,B12,B20,','amira,Computer,amira,Computer,amira,','sara','Computer'),(20,'B00B02','amira,Computer,amira,Computer,amira,','amiraa','Computer'),(21,'B00B22','amiraa,Computer,amiraa,Computer,amiraa,','amiraa','Computer'),(22,'B00,B12,B10,B01,B20,','rrrr,Computer,rrrr,Computer,rrrr,','amiraa','Computer'),(23,'B00,B12,B10,B01,B20,B00,B22,B10,B02,B20,','rrrr,Computer,rrrr,Computer,rrrr,rrrr,Computer,rrrr,Computer,rrrr,','amiraa','Computer'),(24,'B00,B12,B10,B20,B01,B11,B02,','ww,Computer,ww,Computer,ww,Computer,ww,','amiraa','Computer'),(25,'B00,B22,B10,B20,B01,B11,B02,','sds,Computer,sds,Computer,sds,Computer,sds,','amiraa','Computer'),(26,'B00,B22,B10,B20,B01,B11,B02,B00,B22,B10,B02,B20,','sds,Computer,sds,Computer,sds,Computer,sds,sds,Computer,sds,Computer,sds,','amiraa','Computer'),(27,'B00,B20,B01,B02,B11,B12,B22,','aa,Computer,aa,Computer,aa,Computer,aa,','amiraa','Computer'),(28,'B00,B20,B01,B02,B11,B12,B22,B00,B12,B10,B20,B11,B22,B01,B02,','aa,Computer,aa,Computer,aa,Computer,aa,aa,Computer,aa,Computer,aa,Computer,aa,Computer,','amiraa','Computer'),(29,'B00,B20,B01,B11,B02,','qwe,Computer,qwe,Computer,qwe,','eithar','Computer'),(30,'B00,B01,B10,B21,B20,','sssssssssssss,Computer,sssssssssssss,Computer,sssssssssssss,','eithar','Computer'),(31,'B00,B02,B10,B11,B01,B21,B12,B20,','sssssssssssss,Computer,sssssssssssss,Computer,sssssssssssss,sssssssssssss,Computer,sssssssssssss,Computer,sssssssssssss,Computer,sssssssssssss,Computer,','eithar','Computer'),(32,'B00,B21,B10,B12,B20,','www,Computer,www,Computer,www,','eithar','Computer'),(33,'B00,B12,B01,B10,B02,','www,Computer,www,Computer,www,www,Computer,www,Computer,www,','eithar','Computer'),(34,'B00,','yhyyy,Computer,yhyyy,Computer,yhyyy,','eithar','Computer'),(35,'B00','kalahaj,Computer,kalahaj,Computer,kalahaj,Computer,kalahaj,','eithar','Computer'),(36,'B00,B02,B10,B21,B20,','zxczx,Computer,zxczx,Computer,zxczx,','eithar','Computer'),(37,'B00,B02,B10,B21,B20,B00,B12,B01,B21,B02,','zxczx,Computer,zxczx,Computer,zxczx,zxczx,Computer,zxczx,Computer,zxczx,','eithar','Computer'),(38,'B00,B10,B01,B20,B02,','addd,Computer,addd,Computer,addd,','eithar','Computer'),(39,'B00,B02,B10,B01,B20,','addd,Computer,addd,Computer,addd,addd,Computer,addd,Computer,addd,','eithar','Computer'),(40,'B00,B22,B10,B21,B20,','mo7sena,Computer,mo7sena,Computer,mo7sena,','eithar','Computer'),(41,'B00,B11,B01,B20,B02,','mo7sena,Computer,mo7sena,Computer,mo7sena,','eithar','Computer'),(42,'B00,B02,B10,B22,B20,','bate5a,Computer,bate5a,Computer,bate5a,','eithar','Computer'),(43,'B00,B11,B10,B21,B20,','ssss,Computer,ssss,Computer,ssss,','eithar','Computer'),(44,'B00,B10,B11,B20,B22,','za3bola,Computer,za3bola,Computer,za3bola,','You Won','Computer'),(45,'B00,B22,B11,B21,B20,B02,B12,B01,B10,','aaa,Computer,aaa,Computer,aaa,Computer,aaa,Computer,aaa,','You Won','Computer'),(46,'B00,B20,B22,B12,B10,B11,B21,B02,','adsd,Computer,adsd,Computer,adsd,Computer,adsd,Computer,','You Lost','Computer'),(47,'B00,B11,B10,B20,B02,B01,B21,B12,B22,','hard awii,Computer,hard awii,Computer,hard awii,Computer,hard awii,Computer,hard awii,','','Computer'),(48,'B00,B11,B22,B01,B21,B20,B10,B02,','hard awii,Computer,hard awii,Computer,hard awii,Computer,hard awii,Computer,','','Computer'),(49,'B00,B11,B10,B20,B12,B01,B21,B02,','hard awi awi awi,Computer,hard awi awi awi,Computer,hard awi awi awi,Computer,hard awi awi awi,Computer,','','Computer'),(50,'B00,B11,B02,B01,B21,B10,B12,B22,B20,','ssss,Computer,ssss,Computer,ssss,Computer,ssss,Computer,ssss,','It\'s a Tie','Computer'),(51,'B00,B11,B10,B20,B21,B02,','ssss,Computer,ssss,Computer,ssss,Computer,','You Lost','Computer'),(52,'[Ljava.lang.String;@10864367','ay betngan','ay bate5a','ay za3bola'),(53,'B00B01B10B02B20xnull','ay betngan','ay bate5a','ay za3bola'),(54,'[B00, B01, B10, B02, B20, x, null]','ay betngan','ay bate5a','ay za3bola'),(55,'B00,B01,B02,B11,B10,B21,','ay betngan','ay bate5a','ay za3bola'),(56,'B00,B01,B02,B10,B12,B20,B22,','ay betngan','ay bate5a','ay za3bola'),(57,'B00amira,B01amira,B10amira,B02amira,B20amira,','username','username','online'),(58,'B00,maramero,B01,maramero,B10,maramero,B02,maramero,B20,maramero,','username','username','online'),(59,'B00,B01,B10,B02,B20,','username','username','online'),(60,'B00,sara,B01,sara,B02,sara,B10,sara,B11,sara,B12,sara,B22,sara,','username','username','online'),(61,'B00,sara,B01,sara,B02,sara,B10,sara,B11,sara,B12,sara,B22,sara,You Won,','username','username','online'),(62,'B00,B01,B10,B02,B20,','username','username','online'),(63,'B00,B01,B10,B02,B20,','username','username','online'),(64,'B00,B01,B10,B02,B20,','username','username','online'),(65,'B00,B01,B10,B02,B20,','username','username','online'),(66,'B00,B01,B10,B02,B20,','username','username','online'),(67,'B00,B01,B10,B02,B20,','username','username','online'),(68,'B00,B01,B10,B02,B20,','username','username','online'),(69,'B00,B01,B10,B02,B20,','username','username','online'),(70,'B00,B01,B10,B02,B20,','username','username','online'),(71,'B00,B01,B10,B02,B20,','username','username','online'),(72,'B00,B01,B10,B02,B20,','username','username','online'),(73,'B00,B01,B10,B02,B20,','username','username','online'),(74,'B00,B01,B10,B02,B20,','username','username','online'),(75,'B00,B01,B10,B02,B20,','username','username','online'),(76,'B00,B01,B10,B02,B20,','username','username','online'),(77,'B00,B01,B10,B02,B20,','username','username','online'),(78,'B00,B01,B11,B02,B22,','amira,omar,amira,omar,amira,','username','online');
/*!40000 ALTER TABLE `replay` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-21 22:42:05
