-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: lab
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `tb_book_author`
--

DROP TABLE IF EXISTS `tb_book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_book_author` (
  `AUTHOR_ID` bigint NOT NULL AUTO_INCREMENT,
  `AUTHOR_NAME` varchar(128) COLLATE utf8mb3_unicode_ci NOT NULL,
  `DATE_OF_BIRTH` date NOT NULL,
  `AUTHOR_DESCRIBE` text COLLATE utf8mb3_unicode_ci NOT NULL,
  `DEL_F` bigint DEFAULT '0',
  `DEL_DT` datetime DEFAULT NULL,
  `UPDT_USER_ID` bigint DEFAULT NULL,
  `CRT_DT` datetime DEFAULT NULL,
  `UPDT_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`AUTHOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_book_author`
--

LOCK TABLES `tb_book_author` WRITE;
/*!40000 ALTER TABLE `tb_book_author` DISABLE KEYS */;
INSERT INTO `tb_book_author` VALUES (101,'ChienLVM','2022-12-25','Yêu màu tím',1,'2022-12-25 14:05:56',NULL,'2022-12-25 14:05:56','2022-12-25 14:05:56'),(102,'ChienLVM','2022-12-25','Yêu màu tím',1,'2022-12-25 14:05:58',NULL,'2022-12-25 14:05:58','2022-12-25 14:05:58'),(103,'ChienLVM','2022-12-25','Yêu màu tím',0,'2022-12-25 14:05:59',NULL,'2022-12-25 14:05:59','2022-12-25 14:05:59'),(104,'ChienLVM','2022-12-25','Yêu màu tím',0,'2022-12-25 14:07:34',NULL,'2022-12-25 14:07:34','2022-12-25 14:07:34'),(106,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 14:09:19',NULL),(107,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 14:09:21',NULL),(108,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 14:22:07',NULL),(109,'ChienLVM','2022-12-25','Yêu màu tím',1,NULL,11111,'2022-12-25 14:33:03','2022-12-25 14:33:03'),(113,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:44',NULL),(114,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:45',NULL),(115,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:47',NULL),(116,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:48',NULL),(117,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:48',NULL),(118,'ChienLVM','2022-12-25','Yêu màu tím',0,NULL,NULL,'2022-12-25 17:59:49',NULL),(119,'Le Võ minh chiến','2023-01-06','Chuyên viết ngôn tình',0,NULL,NULL,'2023-01-02 22:28:45',NULL),(120,'Le Võ minh chiến','2023-01-06','Chuyên viết ngôn tình',1,NULL,NULL,'2023-01-02 22:28:53',NULL),(121,'Le Võ minh chiến','2023-01-06','Chuyên viết ngôn tình',0,NULL,NULL,'2023-01-02 22:28:55',NULL),(122,'Le Võ minh chiến','2023-01-06','Chuyên viết ngôn tình',0,NULL,NULL,'2023-01-02 22:28:58',NULL),(123,'Le Võ minh chiến','2023-01-06','Chuyên viết ngôn tình',1,NULL,NULL,'2023-01-02 22:28:59',NULL),(125,'Hoài Linh','2021-10-05','Là nhà văn hay',0,NULL,11111,'2023-01-02 22:30:23','2023-01-02 22:43:43'),(126,'a','2023-01-10','a',0,NULL,NULL,'2023-01-02 23:08:26',NULL);
/*!40000 ALTER TABLE `tb_book_author` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-06  1:19:32
