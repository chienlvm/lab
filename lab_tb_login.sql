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
-- Table structure for table `tb_login`
--

DROP TABLE IF EXISTS `tb_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_login` (
  `USER_ID` bigint NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `HASH_PASSWORD` varchar(500) COLLATE utf8mb3_unicode_ci NOT NULL,
  `ENABLED` bit(1) DEFAULT NULL,
  `ROLE` int NOT NULL DEFAULT '0',
  `AUTH_KEY` varchar(500) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `LOGIN_FAIL_NUM` int DEFAULT '0',
  `EMAIL` varchar(45) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_login`
--

LOCK TABLES `tb_login` WRITE;
/*!40000 ALTER TABLE `tb_login` DISABLE KEYS */;
INSERT INTO `tb_login` VALUES (1,'chienlvm','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',0,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxX2NoaWVubHZtXzE2NzI5NDI2MzIyNTkiLCJleHAiOjE2NzM4MDY2MzJ9.v2mUToUItqtrSsx6pOLKMfkP90OJoMp7fjYVlYbuGQb9Y2XXSObc17wXRfmdT9HnqyZGyvoty8_W2dQ9G52KZQ',1,'fcluongloc@yopmail.com'),(100,'chienlvm2','$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',_binary '',1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDBfY2hpZW5sdm0yIiwiZXhwIjoxNjcyNzMzMjMzfQ.2Vgn8PpZ5FLG84ggO9uAv2mScbQzYX9opZPTN3eqoAxiFefkD7IM41AjlL5l4r6WYQYjgyH8AkUpk_Jg9nSbEA',6,'chienlvm@localhost.com'),(101,'aa','aa',_binary '',1,'aa',1,'fcluongloc4@yopmail.com'),(102,'aa','aa',_binary '',1,'aa',1,'fcluongloc3@yopmail.com'),(103,'chienlvm22','$2a$10$BtwKbMPi4CU/inS8U4uU3OzN1DSl9FN57XrulywrQa0RWeJW.Re5W',_binary '\0',0,NULL,0,'chienlvm@localhost.com'),(104,'chienlvm222','$2a$10$JRITB7h.TmmtWzoeWg0yaeqW4eXMUOX8PC2twszX7WjKWWKIvvKiK',_binary '\0',0,NULL,0,'chienlvm@localhost.com'),(105,'admin','$2a$10$45QXI9hegUBmEebAZOOjeetHknVl0YGalalNm74R1YRSHq9EvuTva',_binary '\0',0,NULL,0,'chienlvm'),(106,'admin111','$2a$10$tIzhKDbegdcXScyCbVdC7eAIs8qi6Cg1O7a7dnHQAbekHNQlO483i',_binary '\0',0,NULL,0,'chienlvm@localhost.com'),(107,'admin1112','$2a$10$PShVLom4tIouj2.cVb7.R.yLRiHL27k.H8oI6MVpV2FWYMX3mLgAu',_binary '\0',1,NULL,0,'chienlvm@localhost.com'),(108,'chienlvm333','$2a$10$Q/iofRS9e1a2Lf3v95Mxze6UEjbwjs0P6rcjCPKe9uug0fXO2YVMy',_binary '\0',1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDhfY2hpZW5sdm0zMzNfMTY3Mjg1NTA3ODc0OCIsImV4cCI6MTY3MzcxOTA3OH0.Vp0sJoa_Jt5Lhf6yW7MZibMLvXwdgOfJpU5ILL5jU8JRmT3-yRPXf_Z-irSZyNA-lBr-JFgj8EUQZfwunDZkbQ',0,NULL);
/*!40000 ALTER TABLE `tb_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-06  1:19:34
