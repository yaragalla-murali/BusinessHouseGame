-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: businesshousegame
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `board_board_cells`
--

DROP TABLE IF EXISTS `board_board_cells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_board_cells` (
  `board_board_id` int NOT NULL,
  `board_cells_id` int NOT NULL,
  UNIQUE KEY `UK9pbxrtn6dhlctp1rkwhn8ju2b` (`board_cells_id`),
  KEY `FKhul7w5t98tinc155b5bw1x4j8` (`board_board_id`),
  CONSTRAINT `FK5al6eddedscmmdoqh9vxjfav8` FOREIGN KEY (`board_cells_id`) REFERENCES `cell` (`id`),
  CONSTRAINT `FKhul7w5t98tinc155b5bw1x4j8` FOREIGN KEY (`board_board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cell`
--

DROP TABLE IF EXISTS `cell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cell` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cell_type` enum('EMPTY','HOTEL','JAIL','TREASURE') DEFAULT NULL,
  `hotel_rent` int NOT NULL,
  `hotel_worth` int NOT NULL,
  `jail_penalty` int NOT NULL,
  `sequence_on_board` int NOT NULL,
  `treasure_value` int NOT NULL,
  `hotel_owner_player_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKb6vyymvvu18feaxwygug05drg` (`hotel_owner_player_id`),
  CONSTRAINT `FK762594yoomjplvuisyerckcqw` FOREIGN KEY (`hotel_owner_player_id`) REFERENCES `player` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dice`
--

DROP TABLE IF EXISTS `dice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_used_output_index` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dice_outputs`
--

DROP TABLE IF EXISTS `dice_outputs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dice_outputs` (
  `dice_id` int NOT NULL,
  `output` int DEFAULT NULL,
  KEY `FKcjkhkbrpfyropxl9rk9l0rxsp` (`dice_id`),
  CONSTRAINT `FKcjkhkbrpfyropxl9rk9l0rxsp` FOREIGN KEY (`dice_id`) REFERENCES `dice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `number_of_turns_completed` int NOT NULL,
  `status` enum('COMPLETED','CREATED','INPROGRESS') DEFAULT NULL,
  `board_board_id` int DEFAULT NULL,
  `dice_id` int DEFAULT NULL,
  `next_player_player_id` int DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `UK9cg8u68dl6hi6r4uvyg193jfk` (`board_board_id`),
  UNIQUE KEY `UKp8qg84c63kiu51u80vi0ciemm` (`dice_id`),
  UNIQUE KEY `UK1rnc2saoerbaaer3vdtrut09r` (`next_player_player_id`),
  CONSTRAINT `FK7hjdfdnbwm8ma272d6f4ih8q2` FOREIGN KEY (`dice_id`) REFERENCES `dice` (`id`),
  CONSTRAINT `FKana02t2dv69dnew6d6gb2xx6a` FOREIGN KEY (`board_board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `FKlldqstvhmqsgcnhe7bpro838a` FOREIGN KEY (`next_player_player_id`) REFERENCES `player` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `game_players`
--

DROP TABLE IF EXISTS `game_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_players` (
  `game_game_id` int NOT NULL,
  `players_player_id` int NOT NULL,
  UNIQUE KEY `UK4gwfxnj2u5anx2mhuo39boobe` (`players_player_id`),
  KEY `FKtaqwqj699sigdheaesl1idxk2` (`game_game_id`),
  CONSTRAINT `FKey7nfyw2nv5o3dptg1ksi93vl` FOREIGN KEY (`players_player_id`) REFERENCES `player` (`player_id`),
  CONSTRAINT `FKtaqwqj699sigdheaesl1idxk2` FOREIGN KEY (`game_game_id`) REFERENCES `game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `player_id` int NOT NULL AUTO_INCREMENT,
  `current_position_on_board` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `player_position` int NOT NULL,
  `total_balance` int NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 19:38:44
