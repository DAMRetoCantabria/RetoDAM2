CREATE DATABASE  IF NOT EXISTS `ACEX_IESMH` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `ACEX_IESMH`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 192.168.1.52    Database: ACEX_IESMH
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.17-MariaDB

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
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades` (
  `id_actividad` int(11) NOT NULL AUTO_INCREMENT,
  `solicitante` int(10) unsigned NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `tipo` enum('extraescolar','complementaria') NOT NULL,
  `fini` date NOT NULL,
  `ffin` date NOT NULL,
  `hini` time NOT NULL,
  `hfin` time NOT NULL,
  `prevista` tinyint(4) NOT NULL,
  `transporte_req` tinyint(4) NOT NULL,
  `coment_transporte` varchar(75) DEFAULT NULL,
  `alojamiento_req` tinyint(4) NOT NULL,
  `coment_alojamiento` varchar(75) DEFAULT NULL,
  `comentarios` varchar(100) DEFAULT NULL,
  `estado` enum('Solicitada','Denegada','Aprobada','Realizada') NOT NULL,
  `coment_estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_actividad`),
  UNIQUE KEY `id_actividad_UNIQUE` (`id_actividad`),
  KEY `fk_actividades_profesores1_idx` (`solicitante`),
  CONSTRAINT `fk_actividades_profesores1` FOREIGN KEY (`solicitante`) REFERENCES `profesores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (1,1,'Nos vamos a Microsoft','complementaria','2024-05-04','2024-05-31','12:00:00','12:00:00',0,1,'Cruzamos el charco en Kayak',1,'Dormimos en casa de Bill Gates','Nos traeremos licencias de Windows de estraperlo','Aprobada',''),(2,1,'Pintar la casa del director','complementaria','2024-05-10','2024-04-30','12:01:00','15:13:00',1,0,NULL,0,NULL,NULL,'Denegada',NULL),(3,1,'Ghgj','complementaria','2024-05-02','2024-05-02','12:00:00','12:01:00',1,1,NULL,0,NULL,NULL,'Aprobada','Axeptada'),(4,1,'Titulo','complementaria','2024-05-09','2024-05-09','12:00:00','01:00:00',1,1,NULL,0,NULL,NULL,'Aprobada',''),(5,1,'Vamos a misa','complementaria','2024-05-09','2024-05-10','12:00:00','01:00:00',1,1,'Hola',1,'K ase','Loko','Denegada',NULL),(6,1,'Vamos a misa','complementaria','2024-05-10','2024-05-10','12:00:00','01:01:00',1,1,'Ola',1,'K ase','Loko','Aprobada',''),(7,1,'Asdasd','complementaria','2024-05-10','2024-05-10','12:00:00','01:59:00',1,1,NULL,0,NULL,NULL,'Aprobada',''),(8,1,'Solicitud','complementaria','2024-05-12','2024-05-18','12:00:00','06:00:00',1,1,NULL,0,NULL,NULL,'Denegada',NULL),(9,1,'Nos vamos','complementaria','2024-05-10','2024-05-11','12:00:00','01:05:00',1,1,NULL,0,NULL,NULL,'Aprobada',''),(10,1,'Titulo Bien','complementaria','2024-05-10','2024-05-11','12:00:00','01:00:00',1,0,NULL,0,NULL,NULL,'Aprobada',''),(11,1,'Juerga','complementaria','2024-05-10','2024-05-10','12:00:00','12:30:00',1,1,'Si',0,'No','Todo lo contrario o no','Aprobada','Juega para todos'),(12,1,'Asdasd','extraescolar','2024-05-10','2024-05-11','12:00:00','12:00:00',1,1,NULL,0,NULL,NULL,'Solicitada',NULL),(13,1,'Justiniano','complementaria','2024-05-10','2024-05-10','12:00:00','12:00:00',1,1,'Julioooo',1,'K nos vamos','Al monte','Aprobada','');
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `cod_curso` char(5) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `etapa` enum('ESO','Bachillerato','FPGS','FPGM','FPB','FPCE') NOT NULL,
  `activo` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_curso`),
  UNIQUE KEY `id_curso_UNIQUE` (`id_curso`),
  UNIQUE KEY `cod_curso_UNIQUE` (`cod_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,'ESO1','EducaciÃ³n Secundaria Obligatoria - 1Âº','ESO',1),(2,'ESO2','EducaciÃ³n Secundaria Obligatoria - 2Âº','ESO',1),(3,'ESO3','EducaciÃ³n Secundaria Obligatoria - 3Âº','ESO',1),(4,'ESO4','EducaciÃ³n Secundaria Obligatoria - 4Âº','ESO',1),(5,'BCH1','Bachillerato 1Âº','Bachillerato',1),(6,'BCH2','Bachillerato 2Âº','Bachillerato',1),(7,'FM1','FabricaciÃ³n y montaje - 1Âº','FPGM',1),(9,'MV1','Mantenimiento de VehÃ­culos - 1Âº','FPGM',1),(10,'MV2','Mantenimiento de VehÃ­culos - 2Âº','FPGM',1),(11,'CAR1','CarrocerÃ­a - 1Âº','FPGM',1),(12,'CAR2','CarrocerÃ­a - 2Âº','FPGM',1),(13,'EVA1','ElectromecÃ¡nica de VehÃ­culos AutomÃ³viles - 1Âº','FPGM',1),(14,'EVA2','ElectromecÃ¡nica de VehÃ­culos AutomÃ³viles - 2Âº','FPGM',1),(15,'SMR1','Sistemas MicroinformÃ¡ticos y Redes - 1Âº','FPGM',1),(16,'SMR2','Sistemas MicroinformÃ¡ticos y Redes - 2Âº','FPGM',1),(17,'AF1','AdministraciÃ³n y Finanzas - 1Âº','FPGS',1),(18,'AF2','AdministraciÃ³n y Finanzas - 2Âº','FPGS',1),(19,'DAM1','Desarrollo de Aplicaciones Multiplataforma - 1Âº','FPGS',1),(20,'DAM2','Desarrollo de Aplicaciones Multiplataforma - 2Âº','FPGS',1),(21,'DAW1','Desarrollo de Aplicaciones Web - 1Âº','FPGS',1),(22,'DAW2','Desarrollo de Aplicaciones Web - 2Âº','FPGS',1),(23,'DFM1','DiseÃ±o en FabricaciÃ³n MecÃ¡nica - 1Âº','FPGS',1),(24,'DFM2','DiseÃ±o en FabricaciÃ³n MecÃ¡nica - 2Âº','FPGS',1);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_participa`
--

DROP TABLE IF EXISTS `cursos_participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos_participa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_curso`,`id_actividad`),
  UNIQUE KEY `id_curso_UNIQUE` (`id_curso`,`id_actividad`),
  KEY `fk_cursos_participa_actividades1_idx` (`id_actividad`),
  CONSTRAINT `fk_cursos_participa_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursos_participa_cursos1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_participa`
--

LOCK TABLES `cursos_participa` WRITE;
/*!40000 ALTER TABLE `cursos_participa` DISABLE KEYS */;
INSERT INTO `cursos_participa` VALUES (3,2,17),(4,2,18),(5,3,3),(6,3,6),(7,3,10),(8,4,2),(9,4,3),(10,6,4),(11,6,3),(12,7,1),(26,9,7),(27,9,9),(28,9,10),(33,11,2),(34,11,4),(35,12,3),(36,12,7),(37,13,2),(38,13,4);
/*!40000 ALTER TABLE `cursos_participa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_programadas`
--

DROP TABLE IF EXISTS `cursos_programadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos_programadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_programada` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_programada`,`id_curso`),
  UNIQUE KEY `id_curso_UNIQUE` (`id_curso`,`id_programada`),
  KEY `fk_cursos_programadas_cursos1_idx` (`id_curso`),
  KEY `fk_cursos_programadas_programadas1` (`id_programada`),
  CONSTRAINT `fk_cursos_programadas_cursos1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursos_programadas_programadas1` FOREIGN KEY (`id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_programadas`
--

LOCK TABLES `cursos_programadas` WRITE;
/*!40000 ALTER TABLE `cursos_programadas` DISABLE KEYS */;
INSERT INTO `cursos_programadas` VALUES (1,5,3),(2,5,6),(3,5,10),(4,6,2),(5,6,3),(6,7,4),(7,7,3),(8,9,7),(9,9,9),(10,9,10),(13,10,1),(14,11,2),(15,11,4);
/*!40000 ALTER TABLE `cursos_programadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `id_depar` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` char(3) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `jefe` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_depar`),
  UNIQUE KEY `id_depar_UNIQUE` (`id_depar`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `fk_departamentos_profesores_idx` (`jefe`),
  CONSTRAINT `fk_departamentos_profesores` FOREIGN KEY (`jefe`) REFERENCES `profesores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,'BG','BiologÃ­a y GeologÃ­a',NULL),(2,'DIB','Dibujo',10),(3,'ECO','EconomÃ­a',NULL),(4,'EF','EducaciÃ³n FÃ­sica',NULL),(5,'FIL','FilosofÃ­a',NULL),(6,'FQ','FÃ­sica y QuÃ­mica',NULL),(7,'FRA','FrancÃ©s',NULL),(8,'GH','GeografÃ­a e Historia',NULL),(9,'ING','InglÃ©s',NULL),(10,'LAT','LatÃ­n',NULL),(11,'LEN','Lengua Castellana y Literatura',NULL),(12,'MAT','MatemÃ¡ticas',NULL),(13,'MUS','MÃºsica',NULL),(14,'TEC','TecnologÃ­a',NULL),(15,'AG','AdministraciÃ³n y GestiÃ³n',NULL),(16,'FOL','FormaciÃ³n y OrientaciÃ³n Laboral',NULL),(17,'INF','Informática y Comunicaciones',1),(18,'FM','FabricaciÃ³n MecÃ¡nica',NULL),(19,'TMV','Transporte y Mantenimiento de VehÃ­culos',NULL);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `id_grupo` int(11) NOT NULL AUTO_INCREMENT,
  `curso` int(11) NOT NULL,
  `cod_grupo` char(8) NOT NULL,
  `num_alumnos` int(11) NOT NULL,
  `activo` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_grupo`,`curso`),
  UNIQUE KEY `cod_curso_UNIQUE` (`cod_grupo`),
  KEY `fk_grupos_cursos1_idx` (`curso`),
  CONSTRAINT `fk_grupos_cursos1` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,1,'ESO1A',22,1),(2,1,'ESO1B',21,1),(3,1,'ESO1C',20,1),(4,1,'ESO1D',22,1),(5,2,'ESO2A',19,1),(6,2,'ESO2B',21,1),(7,2,'ESO2C',20,1),(8,2,'ESO2D',19,1),(9,3,'ESO3A',25,1),(10,3,'ESO3B',24,1),(11,3,'ESO3C',25,1),(12,4,'ESO4A',19,1),(13,4,'ESO4B',18,1),(14,4,'ESO4C',18,1),(15,4,'ESO4D',17,1),(16,5,'BCH1HCS',21,1),(17,5,'BCH1CT',22,1),(18,6,'BCH2HCS',20,1),(19,6,'BDH2CT',19,1),(20,7,'FM1',24,1),(22,9,'MV1',25,1),(23,10,'MV2',15,1),(24,11,'CAR1',25,1),(25,12,'CAR2',16,1),(26,13,'EVA1',24,1),(27,14,'EVA2',14,1),(28,15,'SMR1',28,1),(29,16,'SMR2',19,1),(30,17,'AF1',24,1),(31,18,'AF2',25,1),(32,19,'DAM1',28,1),(33,20,'DAM2',35,1),(34,21,'DAW1',24,1),(35,22,'DAW2',18,1),(36,23,'DFM1',16,1),(37,24,'DFM2',9,1);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos_participa`
--

DROP TABLE IF EXISTS `grupos_participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos_participa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_actividad`,`id_grupo`),
  UNIQUE KEY `id_grupo_UNIQUE` (`id_grupo`,`id_actividad`),
  KEY `fk_grupos_participa_grupos1_idx` (`id_grupo`),
  KEY `fk_grupos_participa_actividades1` (`id_actividad`),
  CONSTRAINT `fk_grupos_participa_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupos_participa_grupos1` FOREIGN KEY (`id_grupo`) REFERENCES `grupos` (`id_grupo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos_participa`
--

LOCK TABLES `grupos_participa` WRITE;
/*!40000 ALTER TABLE `grupos_participa` DISABLE KEYS */;
INSERT INTO `grupos_participa` VALUES (5,1,32),(6,1,33),(7,1,34),(8,1,35),(9,8,1),(10,8,2),(11,8,3),(21,10,2),(22,10,3),(23,10,4);
/*!40000 ALTER TABLE `grupos_participa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos_programadas`
--

DROP TABLE IF EXISTS `grupos_programadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos_programadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_programada` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_grupo`,`id_programada`),
  UNIQUE KEY `id_grupo_UNIQUE` (`id_grupo`,`id_programada`),
  KEY `fk_grupos_programadas_programadas1_idx` (`id_programada`),
  CONSTRAINT `fk_grupos_programadas_grupos1` FOREIGN KEY (`id_grupo`) REFERENCES `grupos` (`id_grupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupos_programadas_programadas1` FOREIGN KEY (`id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos_programadas`
--

LOCK TABLES `grupos_programadas` WRITE;
/*!40000 ALTER TABLE `grupos_programadas` DISABLE KEYS */;
INSERT INTO `grupos_programadas` VALUES (13,4,32),(14,4,33),(15,4,34),(16,4,35),(17,8,2),(18,8,3),(19,8,4);
/*!40000 ALTER TABLE `grupos_programadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `programadas_id_programada` int(11) NOT NULL,
  PRIMARY KEY (`id`,`programadas_id_programada`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `fk_imagenes_programadas1_idx` (`programadas_id_programada`),
  CONSTRAINT `fk_imagenes_programadas1` FOREIGN KEY (`programadas_id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mediostransporte`
--

DROP TABLE IF EXISTS `mediostransporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mediostransporte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mediostransporte`
--

LOCK TABLES `mediostransporte` WRITE;
/*!40000 ALTER TABLE `mediostransporte` DISABLE KEYS */;
INSERT INTO `mediostransporte` VALUES (2,'Autobus'),(1,'Bicicleta'),(3,'Kayak');
/*!40000 ALTER TABLE `mediostransporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participantes`
--

DROP TABLE IF EXISTS `participantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_participante` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`id_actividad`,`id_participante`),
  UNIQUE KEY `id_participante_UNIQUE` (`id_participante`,`id_actividad`),
  KEY `fk_participantes_profesores1_idx` (`id_participante`),
  KEY `fk_participantes_actividades1` (`id_actividad`),
  CONSTRAINT `fk_participantes_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_profesores1` FOREIGN KEY (`id_participante`) REFERENCES `profesores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participantes`
--

LOCK TABLES `participantes` WRITE;
/*!40000 ALTER TABLE `participantes` DISABLE KEYS */;
INSERT INTO `participantes` VALUES (3,1,6),(4,1,14),(7,2,6),(8,2,9),(9,3,3),(10,3,5),(11,4,3),(12,4,6),(13,6,3),(14,6,9),(15,7,5),(16,7,9),(17,8,4),(18,8,6),(19,8,7),(32,9,15),(33,9,14),(34,9,13),(39,11,3),(40,11,6),(41,12,4),(42,12,11),(43,13,2),(44,13,5),(45,13,9);
/*!40000 ALTER TABLE `participantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participantes_programada`
--

DROP TABLE IF EXISTS `participantes_programada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participantes_programada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_programada` int(11) NOT NULL,
  `id_participante` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`id_participante`,`id_programada`),
  UNIQUE KEY `id_participante_UNIQUE` (`id_participante`,`id_programada`),
  KEY `fk_participantes_programada_programadas1_idx` (`id_programada`),
  CONSTRAINT `fk_participantes_programada_profesores1` FOREIGN KEY (`id_participante`) REFERENCES `profesores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_programada_programadas1` FOREIGN KEY (`id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participantes_programada`
--

LOCK TABLES `participantes_programada` WRITE;
/*!40000 ALTER TABLE `participantes_programada` DISABLE KEYS */;
INSERT INTO `participantes_programada` VALUES (7,4,6),(8,4,14),(9,5,3),(10,5,5),(11,6,3),(12,6,6),(13,7,3),(14,7,9),(15,9,15),(16,9,14),(17,9,13),(20,10,5),(21,10,9),(22,11,2),(23,11,5),(24,11,9);
/*!40000 ALTER TABLE `participantes_programada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dni` char(9) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `correo` varchar(80) NOT NULL,
  `password` char(32) DEFAULT NULL,
  `nivel` enum('Superusuario','Administrador','Equipodirectivo','Profesor') DEFAULT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT 1,
  `dep_id` int(11) NOT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  KEY `fk_profesores_departamentos1_idx` (`dep_id`),
  CONSTRAINT `fk_profesores_departamentos1` FOREIGN KEY (`dep_id`) REFERENCES `departamentos` (`id_depar`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES (1,'31566939Z','Santiago Manuel','Tamayo Arozamena','santi@santi.com','santisanti','Superusuario',1,1,'/images/perfil.jpg'),(2,'22221940W','Pablo','Sanz Campo','pablo.sanzcampo@educantabria.es','Justiniano11','Profesor',1,9,'/images/perfil.jpg'),(3,'84434964K','Luis Manuel','Serrano Ceballos','luism.serranoceballos@educantabria.es','Contraseña1','Profesor',1,12,'/images/perfil.jpg'),(4,'04266894X','Olatz','San Miguel Martinez','olatz.sanmiguelmart@educantabria.es',NULL,'Profesor',1,12,'/images/perfil.jpg'),(5,'21156345S','Alejandro','Carrera Ruiz','alejandro.carreraruiz@educantabria.es',NULL,'Profesor',1,17,'/images/perfil.jpg'),(6,'63568530G','Lulu','Ortiz Royuela','lulu.ortiz01@educantabria.es',NULL,'Profesor',1,17,'/images/perfil.jpg'),(7,'17394999M','Raul','Reigadas Fonfria','rreigadasfo01@educantabria.es',NULL,'Profesor',1,12,'/images/perfil.jpg'),(8,'58388245M','Marcos','Fernandez Vallejo','marcos.fernandezvallejo@educantabria.es',NULL,'Profesor',1,11,'/images/perfil.jpg'),(9,'52821689C','Jonatan','Hevia Ortiz','joheviaort01@educantabria.es',NULL,'Profesor',1,9,'/images/perfil.jpg'),(10,'78117930Y','Manuel','Gomez Arronte','mgomezarro01@educantabria.es',NULL,'Profesor',1,6,'/images/perfil.jpg'),(11,'08845506J','Yolanda','Escudero Valdes','yescuderova01@educantabria.es',NULL,'Profesor',1,17,'/images/perfil.jpg'),(12,'65003264B','David','Benito Almeida','david.benitoalmeida@educantabria.es',NULL,'Profesor',1,3,'/images/perfil.jpg'),(13,'55452491Y','Francisco Jose','Trueba Rojas','fjtruebaroja01@educantabria.es',NULL,'Profesor',1,12,'/images/perfil.jpg'),(14,'68567981Z','Maria','Gutierrez Castañeda','mgutierrez95@educantabria.es',NULL,'Profesor',1,17,'/images/perfil.jpg'),(15,'03719263F','David','Sanchez Junco','david.sanchezjunco@educantabria.es',NULL,'Profesor',1,5,'/images/perfil.jpg'),(16,'53554206S','Victoria','Martinez Balbas','vmartinezba01@educantabria.es',NULL,'Profesor',1,8,'/images/perfil.jpg');
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programadas`
--

DROP TABLE IF EXISTS `programadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programadas` (
  `id_programada` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `solicitante` int(10) unsigned NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `tipo` enum('extraescolar','complementaria') NOT NULL,
  `fini` date NOT NULL,
  `ffin` date NOT NULL,
  `hini` time NOT NULL,
  `hfin` time NOT NULL,
  `prevista` tinyint(4) NOT NULL,
  `transporte_req` tinyint(4) NOT NULL,
  `coment_transporte` varchar(75) DEFAULT NULL,
  `alojamiento_req` tinyint(4) NOT NULL,
  `coment_alojamiento` varchar(75) DEFAULT NULL,
  `comentarios` varchar(100) DEFAULT NULL,
  `estado` enum('Solicitada','Denegada','Aprobada','Realizada') NOT NULL,
  `coment_estado` varchar(100) DEFAULT NULL,
  `empresa_transporte` varchar(45) DEFAULT NULL,
  `precio_transporte` float DEFAULT NULL,
  PRIMARY KEY (`id_programada`,`id_actividad`),
  UNIQUE KEY `id_actividad_UNIQUE` (`id_programada`),
  KEY `fk_programadas_actividades1_idx` (`id_actividad`),
  CONSTRAINT `fk_programadas_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programadas`
--

LOCK TABLES `programadas` WRITE;
/*!40000 ALTER TABLE `programadas` DISABLE KEYS */;
INSERT INTO `programadas` VALUES (4,1,0,'Nos vamos a Microsoft','complementaria','2024-05-04','2024-05-31','12:00:00','12:00:00',0,1,'Cruzamos el charco en Kayak',1,'Dormimos en casa de Bill Gates','Nos traeremos licencias de Windows de estraperlo','Aprobada','','',0),(5,3,0,'Ghgj','complementaria','2024-05-02','2024-05-02','12:00:00','12:01:00',1,1,NULL,0,NULL,NULL,'Aprobada','Axeptada','',0),(6,4,0,'Titulo','complementaria','2024-05-09','2024-05-09','12:00:00','01:00:00',1,1,NULL,0,NULL,NULL,'Aprobada','','',0),(7,6,0,'Vamos a misa','complementaria','2024-05-10','2024-05-10','12:00:00','01:01:00',1,1,'Ola',1,'K ase','Loko','Aprobada','','',0),(8,10,0,'Titulo Bien','complementaria','2024-05-10','2024-05-11','12:00:00','01:00:00',1,0,NULL,0,NULL,NULL,'Aprobada','','',0),(9,9,0,'Nos vamos','complementaria','2024-05-10','2024-05-11','12:00:00','01:05:00',1,1,NULL,0,NULL,NULL,'Aprobada','','',0),(10,7,1,'Asdasd','complementaria','2024-05-10','2024-05-10','12:00:00','01:59:00',1,1,NULL,0,NULL,NULL,'Aprobada','','',0),(11,13,1,'Justiniano','complementaria','2024-05-10','2024-05-10','12:00:00','12:00:00',1,1,'Julioooo',1,'K nos vamos','Al monte','Aprobada','','',0);
/*!40000 ALTER TABLE `programadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsables`
--

DROP TABLE IF EXISTS `responsables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_responsable` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`id_actividad`,`id_responsable`),
  UNIQUE KEY `id_responsable_UNIQUE` (`id_responsable`,`id_actividad`),
  KEY `fk_table1_profesores1_idx` (`id_responsable`),
  KEY `fk_table1_actividades1` (`id_actividad`),
  CONSTRAINT `fk_table1_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_profesores1` FOREIGN KEY (`id_responsable`) REFERENCES `profesores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsables`
--

LOCK TABLES `responsables` WRITE;
/*!40000 ALTER TABLE `responsables` DISABLE KEYS */;
INSERT INTO `responsables` VALUES (4,1,1),(5,1,2),(6,1,4),(9,2,3),(10,2,6),(11,3,4),(12,3,6),(13,4,3),(14,4,8),(15,4,11),(16,6,3),(17,6,4),(18,6,5),(19,7,3),(20,7,6),(21,8,1),(22,8,2),(23,8,3),(36,10,1),(37,10,9),(38,10,6),(48,9,1),(49,9,2),(50,9,3),(57,11,3),(58,11,5),(59,11,8),(60,12,4),(61,12,7),(62,13,4),(63,13,7),(64,13,10);
/*!40000 ALTER TABLE `responsables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsables_programada`
--

DROP TABLE IF EXISTS `responsables_programada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsables_programada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_programada` int(11) NOT NULL,
  `id_responsable` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`id_responsable`,`id_programada`),
  UNIQUE KEY `id_responsable_UNIQUE` (`id_responsable`,`id_programada`),
  KEY `fk_responsables_programada_programadas1_idx` (`id_programada`),
  CONSTRAINT `fk_responsables_programada_profesores1` FOREIGN KEY (`id_responsable`) REFERENCES `profesores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_responsables_programada_programadas1` FOREIGN KEY (`id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsables_programada`
--

LOCK TABLES `responsables_programada` WRITE;
/*!40000 ALTER TABLE `responsables_programada` DISABLE KEYS */;
INSERT INTO `responsables_programada` VALUES (10,4,1),(11,4,2),(12,4,4),(13,5,4),(14,5,6),(15,6,3),(16,6,8),(17,6,11),(18,7,3),(19,7,4),(20,7,5),(21,8,1),(22,8,9),(23,8,6),(24,9,1),(25,9,2),(26,9,3),(30,10,3),(31,10,6),(32,11,4),(33,11,7),(34,11,10);
/*!40000 ALTER TABLE `responsables_programada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporte`
--

DROP TABLE IF EXISTS `transporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transporte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_actividad` int(11) NOT NULL,
  `id_transporte` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_actividad`,`id_transporte`),
  UNIQUE KEY `id_transporte_UNIQUE` (`id_transporte`,`id_actividad`),
  KEY `fk_transporte_mediostransporte1_idx` (`id_transporte`),
  KEY `fk_transporte_actividades1` (`id_actividad`),
  CONSTRAINT `fk_transporte_actividades1` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transporte_mediostransporte1` FOREIGN KEY (`id_transporte`) REFERENCES `mediostransporte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporte`
--

LOCK TABLES `transporte` WRITE;
/*!40000 ALTER TABLE `transporte` DISABLE KEYS */;
INSERT INTO `transporte` VALUES (2,1,2),(3,1,3),(4,3,3),(5,4,1),(6,4,2),(7,6,1),(8,6,2),(9,7,1),(10,7,2),(11,8,2),(19,9,3),(20,9,1),(25,11,2),(26,11,1),(27,12,1),(28,13,1),(29,13,2);
/*!40000 ALTER TABLE `transporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporte_programado`
--

DROP TABLE IF EXISTS `transporte_programado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transporte_programado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_programada` int(11) NOT NULL,
  `id_transporte` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_transporte`,`id_programada`),
  UNIQUE KEY `id_programada_UNIQUE` (`id_programada`,`id_transporte`),
  KEY `fk_transporte_programado_programadas1_idx` (`id_programada`),
  KEY `fk_transporte_programado_mediostransporte1` (`id_transporte`),
  CONSTRAINT `fk_transporte_programado_mediostransporte1` FOREIGN KEY (`id_transporte`) REFERENCES `mediostransporte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transporte_programado_programadas1` FOREIGN KEY (`id_programada`) REFERENCES `programadas` (`id_programada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporte_programado`
--

LOCK TABLES `transporte_programado` WRITE;
/*!40000 ALTER TABLE `transporte_programado` DISABLE KEYS */;
INSERT INTO `transporte_programado` VALUES (7,4,2),(8,4,3),(9,5,3),(10,6,1),(11,6,2),(12,7,1),(13,7,2),(14,9,3),(15,9,1),(18,10,1),(19,10,2),(20,11,1),(21,11,2);
/*!40000 ALTER TABLE `transporte_programado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10 14:12:13
