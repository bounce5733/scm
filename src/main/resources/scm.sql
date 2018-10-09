-- MySQL dump 10.13  Distrib 5.7.11, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: scm
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Current Database: `scm`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `scm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `scm`;

--
-- Table structure for table `bas_company`
--

DROP TABLE IF EXISTS `bas_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bas_company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPID` int(11) NOT NULL DEFAULT '0' COMMENT '账套，母公司为0，其他为母公司ID',
  `NAME` varchar(30) NOT NULL,
  `INDUSTRY_CATEGORY` int(11) DEFAULT NULL,
  `AREA` int(11) DEFAULT NULL,
  `POSTCODE` char(6) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `TEL` varchar(45) DEFAULT NULL,
  `FAX` varchar(45) DEFAULT NULL,
  `WEB_SITE` varchar(100) DEFAULT NULL,
  `AVATAR` text,
  `NSRSBH` varchar(20) DEFAULT NULL,
  `INVOICE_TITLE` varchar(30) DEFAULT NULL,
  `SERVICE_TEL` varchar(20) DEFAULT NULL,
  `DESCN` varchar(255) DEFAULT NULL,
  `LINKMAN_NAME` varchar(45) NOT NULL,
  `LINKMAN_POSITION` varchar(45) DEFAULT NULL,
  `LINKMAN_MOBILE` char(11) NOT NULL,
  `LINKMAN_QQ` varchar(11) DEFAULT NULL,
  `LINKMAN_EMAIL` varchar(45) DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='公司信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_company`
--

LOCK TABLES `bas_company` WRITE;
/*!40000 ALTER TABLE `bas_company` DISABLE KEYS */;
INSERT INTO `bas_company` VALUES (8,0,'1111',18,6,'343434','sdfsdfsdf','08923453','08923453','http://www.google.com','','1111','dfsdfsdfsd','1111','08923453','2','','13738430892','','yh_jiang@126.com','admin','2018-09-29 00:05:22','111','2018-09-08 22:02:03'),(20,0,'创微',0,NULL,'','','','','','iVBORw0KGgoAAAANSUhEUgAAABIAAABICAYAAAD726XGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo3Q0E1MUU2MDc1OUMxMUU2OTk0NzhFMTVGRTZFN0RERSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo3Q0E1MUU2MTc1OUMxMUU2OTk0NzhFMTVGRTZFN0RERSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjdDQTUxRTVFNzU5QzExRTY5OTQ3OEUxNUZFNkU3RERFIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjdDQTUxRTVGNzU5QzExRTY5OTQ3OEUxNUZFNkU3RERFIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+UyjiUAAAAUJJREFUeNpi/P//PwM1ABMDlQALElsHiOOAmJ9IvR+BeBEQXwHzQF6D4i4gVkHiE8IqUD1gPrLXQC65Q4Jv7iC7nmphNGrQqEHUNgiUd1RI0KsMxJ9gHEakYgSUaeOBmI9Igz4A8WJYpmUcdOXR4DNotIQczbSjBo2WkKOxRrCEZNl51hNIzQViSSL1Pgfi5D/uxtvRXUSKIQxQtXOxeU2SDB9JDmxgi7KxMKTLihKsjggastdUjUGLh5OBhZGRYeqjV4RdBLIVpBGbIde+fGdY9eIdYa9ly4kxTNWSA2sEGYBuiPPpWwyvf/0h7DWQbemyImCNIANAgJAhWF0EUgjSANIIMoAYQ3CGEbJhxBiCN9ZghsHYpLRGnqOnbiIMeIHNaynIEkSAp6BMi62oHS0hR0vI0RJytIQcLSEHdwkJEGAA4k9KWLKCaBcAAAAASUVORK5CYII=','','','1111','','江永华','','13738430892','','','123','2018-09-16 00:26:32','123','2018-09-16 00:20:23'),(21,0,'abc',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'111',NULL,'13738430892',NULL,NULL,NULL,NULL,'abc','2018-09-16 01:10:15'),(23,0,'admin1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin1',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin1','2018-10-07 23:24:07'),(28,0,'admin2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin2',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin2','2018-10-08 20:23:01'),(29,0,'admin4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin4',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin4','2018-10-08 20:38:01'),(30,0,'admin5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin5',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin5','2018-10-08 20:40:40'),(31,0,'admin6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin6',NULL,'13738434098',NULL,NULL,NULL,NULL,'admin6','2018-10-09 22:14:53');
/*!40000 ALTER TABLE `bas_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_account_period`
--

DROP TABLE IF EXISTS `code_account_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_account_period` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(24) NOT NULL,
  `APPID` int(11) NOT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='账期';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_account_period`
--

LOCK TABLES `code_account_period` WRITE;
/*!40000 ALTER TABLE `code_account_period` DISABLE KEYS */;
INSERT INTO `code_account_period` VALUES (2,'2222111',8,2,'admin','2018-10-01 22:34:52','admin','2018-09-19 22:14:21'),(4,'3333',8,0,'admin','2018-10-01 22:35:20','admin','2018-09-19 22:29:52'),(5,'1111',23,0,'admin1','2018-10-08 10:19:27','admin1','2018-10-08 10:19:25');
/*!40000 ALTER TABLE `code_account_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_customer_grade`
--

DROP TABLE IF EXISTS `code_customer_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_customer_grade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(8) NOT NULL,
  `APPID` int(11) NOT NULL,
  `DISCOUNT` float NOT NULL COMMENT '折扣',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `DEFAULTED` char(1) NOT NULL DEFAULT 'F' COMMENT '是否是默认仓库',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='客户级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_customer_grade`
--

LOCK TABLES `code_customer_grade` WRITE;
/*!40000 ALTER TABLE `code_customer_grade` DISABLE KEYS */;
INSERT INTO `code_customer_grade` VALUES (1,'11',23,11,2,'F','admin1','2018-10-08 20:03:04','admin1','2018-10-08 18:03:26'),(2,'22',23,22,0,'F','admin1','2018-10-08 20:06:29','admin1','2018-10-08 20:05:05'),(3,'普通',28,100,0,'T',NULL,NULL,'admin2','2018-10-08 20:23:01'),(4,'普通',29,100,0,'T',NULL,NULL,'admin4','2018-10-08 20:38:01'),(5,'普通',30,100,0,'T',NULL,NULL,'admin5','2018-10-08 20:40:40'),(6,'普通',31,100,0,'T',NULL,NULL,'admin6','2018-10-09 22:14:53');
/*!40000 ALTER TABLE `code_customer_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_product_catalog`
--

DROP TABLE IF EXISTS `code_product_catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_product_catalog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPID` int(11) NOT NULL,
  `PID` int(11) NOT NULL DEFAULT '0',
  `NAME` varchar(30) NOT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `DEFAULTED` char(1) NOT NULL DEFAULT 'F' COMMENT '是否是默认商品分类',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_TIME` char(19) NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_product_catalog`
--

LOCK TABLES `code_product_catalog` WRITE;
/*!40000 ALTER TABLE `code_product_catalog` DISABLE KEYS */;
INSERT INTO `code_product_catalog` VALUES (26,8,0,'1',0,'F','admin','2018-10-07 23:02:46','2018-10-07 23:02:32','admin'),(27,8,0,'2',1,'F','admin','2018-10-07 23:02:40','2018-10-07 23:02:38','admin'),(28,8,27,'21',3,'F',NULL,NULL,'2018-10-07 23:02:52','admin'),(29,8,26,'11',0,'F',NULL,NULL,'2018-10-07 23:02:54','admin'),(30,8,27,'22',1,'F','admin','2018-10-09 20:58:49','2018-10-07 23:03:04','admin'),(31,8,27,'23',0,'F','admin','2018-10-09 20:58:51','2018-10-07 23:03:32','admin'),(32,23,0,'通用',0,'T','admin1','2018-10-08 10:17:45','2018-10-07 23:24:07','admin1'),(33,23,0,'2222',1,'F','admin1','2018-10-08 10:17:24','2018-10-08 10:17:23','admin1'),(38,28,0,'通用',0,'T',NULL,NULL,'2018-10-08 20:23:01','admin2'),(39,29,0,'通用',0,'T',NULL,NULL,'2018-10-08 20:38:01','admin4'),(40,30,0,'通用',0,'T','admin5','2018-10-08 20:40:56','2018-10-08 20:40:40','admin5'),(41,8,0,'111',2,'F',NULL,NULL,'2018-10-09 21:43:28','admin'),(42,8,0,'33',3,'F',NULL,NULL,'2018-10-09 21:43:39','admin'),(43,8,0,'44',4,'F',NULL,NULL,'2018-10-09 21:43:45','admin'),(44,8,41,'222',1,'F','admin','2018-10-09 21:44:05','2018-10-09 21:43:53','admin'),(45,31,0,'通用',0,'T',NULL,NULL,'2018-10-09 22:14:53','admin6'),(46,31,50,'111',1,'F','admin6','2018-10-09 22:51:18','2018-10-09 22:15:16','admin6'),(47,31,49,'11',1,'F','admin6','2018-10-09 22:51:30','2018-10-09 22:15:20','admin6'),(49,31,0,'11',1,'F',NULL,NULL,'2018-10-09 22:25:46','admin6'),(50,31,0,'22',2,'F',NULL,NULL,'2018-10-09 22:51:15','admin6');
/*!40000 ALTER TABLE `code_product_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_unit`
--

DROP TABLE IF EXISTS `code_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_unit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(12) NOT NULL,
  `APPID` int(11) NOT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='计量单位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_unit`
--

LOCK TABLES `code_unit` WRITE;
/*!40000 ALTER TABLE `code_unit` DISABLE KEYS */;
INSERT INTO `code_unit` VALUES (2,'斤',23,0,'admin1','2018-10-08 11:55:04','admin1','2018-10-08 11:03:40'),(3,'件',23,1,'admin1','2018-10-08 11:54:54','admin1','2018-10-08 11:03:44'),(4,'斤',8,0,NULL,NULL,'admin','2018-10-09 20:59:00');
/*!40000 ALTER TABLE `code_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_warehouse`
--

DROP TABLE IF EXISTS `code_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_warehouse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(45) NOT NULL,
  `APPID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `ENABLED` char(1) NOT NULL DEFAULT 'T' COMMENT '是否开启',
  `DEFAULTED` char(1) NOT NULL DEFAULT 'F' COMMENT '是否是默认仓库',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='仓库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_warehouse`
--

LOCK TABLES `code_warehouse` WRITE;
/*!40000 ALTER TABLE `code_warehouse` DISABLE KEYS */;
INSERT INTO `code_warehouse` VALUES (1,'001122',8,'默认仓1','11111',0,'T','T','admin','2018-09-21 21:43:42','admin','2018-09-08 22:02:03'),(2,'0021',8,'仓库2','002',0,'F','F','admin','2018-09-21 21:45:49','admin','2018-09-17 23:26:54'),(4,'001',23,'默认仓',NULL,1,'T','T','admin1','2018-10-08 11:53:39','admin1','2018-10-07 23:24:07'),(5,'111',23,'111','111',0,'T','F','admin1','2018-10-08 11:54:14','admin1','2018-10-08 11:43:01'),(10,'001',28,'默认仓',NULL,0,'T','T',NULL,NULL,'admin2','2018-10-08 20:23:01'),(11,'001',29,'默认仓',NULL,0,'T','T',NULL,NULL,'admin4','2018-10-08 20:38:01'),(12,'001',30,'默认仓',NULL,0,'T','T',NULL,NULL,'admin5','2018-10-08 20:40:40'),(13,'001',31,'默认仓',NULL,0,'T','T',NULL,NULL,'admin6','2018-10-09 22:14:53');
/*!40000 ALTER TABLE `code_warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_code`
--

DROP TABLE IF EXISTS `sys_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_code` (
  `CODE` varchar(32) NOT NULL,
  `APPID` int(11) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `CREATED_TIME` char(32) NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_code`
--

LOCK TABLES `sys_code` WRITE;
/*!40000 ALTER TABLE `sys_code` DISABLE KEYS */;
INSERT INTO `sys_code` VALUES ('aaa',31,'111','2018-10-09 22:16:19','admin6'),('abc',20,'111','2018-09-16 00:25:11','123'),('area',8,'区域','2018-09-06 23:41:14','admin'),('bbb',31,'22','2018-10-09 23:10:26','admin6'),('http_method',8,'HTTP方法','2018-03-07 16:52:27','admin'),('industry_category',8,'行业类别','2018-09-06 23:28:25','admin');
/*!40000 ALTER TABLE `sys_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_code_item`
--

DROP TABLE IF EXISTS `sys_code_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_code_item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UUID主键',
  `APPID` int(11) NOT NULL,
  `PID` int(11) NOT NULL DEFAULT '0' COMMENT '父主键',
  `NAME` varchar(100) NOT NULL COMMENT '名称',
  `TYPE` char(32) NOT NULL COMMENT '所属类型',
  `SORT` tinyint(2) NOT NULL COMMENT '排序',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_TIME` char(19) NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK.BAS_CODE_ITEM.TYPE_idx` (`TYPE`),
  CONSTRAINT `FK.BAS_CODE_ITEM.TYPE` FOREIGN KEY (`TYPE`) REFERENCES `sys_code` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='编码子项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_code_item`
--

LOCK TABLES `sys_code_item` WRITE;
/*!40000 ALTER TABLE `sys_code_item` DISABLE KEYS */;
INSERT INTO `sys_code_item` VALUES (1,8,0,'电子机电','industry_category',4,NULL,NULL,'2018-09-06 23:30:11','admin'),(2,8,0,'其他行业','industry_category',10,NULL,NULL,'2018-09-06 23:31:47','admin'),(3,8,0,'医药行业','industry_category',15,NULL,NULL,'2018-09-06 23:34:55','admin'),(4,8,0,'家用电器','industry_category',5,NULL,NULL,'2018-09-06 23:30:24','admin'),(5,8,0,'餐饮连锁','industry_category',14,NULL,NULL,'2018-09-06 23:34:49','admin'),(6,8,24,'鄞州区','area',1,'admin','2018-09-15 00:14:59','2018-09-06 23:42:37','admin'),(7,8,0,'食品酒水饮料','industry_category',3,NULL,NULL,'2018-09-06 23:30:00','admin'),(8,8,0,'patch','http_method',4,'admin','2018-09-06 23:35:49','2018-03-07 16:53:09','admin'),(9,8,0,'服装鞋帽箱包','industry_category',9,NULL,NULL,'2018-09-06 23:31:33','admin'),(10,8,0,'put','http_method',5,'admin','2018-09-06 23:35:53','2018-03-07 16:53:25','admin'),(11,8,0,'get','http_method',1,'admin','2018-09-06 23:35:16','2018-03-07 16:52:44','admin'),(12,8,0,'delete','http_method',3,'admin','2018-09-06 23:35:43','2018-03-07 16:53:16','admin'),(14,8,0,'浙江省','area',1,'admin','2018-10-01 15:07:52','2018-09-06 23:41:55','admin'),(15,8,0,'母婴用品','industry_category',12,NULL,NULL,'2018-09-06 23:32:29','admin'),(16,8,0,'个护化妆品','industry_category',6,NULL,NULL,'2018-09-06 23:30:35','admin'),(17,8,0,'日用百货','industry_category',11,NULL,NULL,'2018-09-06 23:32:03','admin'),(18,8,0,'手机数码','industry_category',2,NULL,NULL,'2018-09-06 23:29:37','admin'),(19,8,0,'钟表珠宝','industry_category',7,NULL,NULL,'2018-09-06 23:30:53','admin'),(20,8,0,'汽车用品','industry_category',1,NULL,NULL,'2018-09-06 23:29:29','admin'),(21,8,0,'家具家装','industry_category',8,NULL,NULL,'2018-09-06 23:31:13','admin'),(22,8,0,'post','http_method',2,'admin','2018-09-06 23:35:31','2018-03-07 16:52:53','admin'),(23,8,0,'生鲜农贸','industry_category',13,NULL,NULL,'2018-09-06 23:34:33','admin'),(24,8,14,'宁波市','area',1,NULL,NULL,'2018-09-06 23:42:14','admin'),(25,8,24,'海曙区','area',2,NULL,NULL,'2018-09-15 00:14:50','admin'),(26,20,0,'111','abc',1,NULL,NULL,'2018-09-16 00:25:45','123'),(27,20,26,'2222','abc',2,NULL,NULL,'2018-09-16 00:25:50','123'),(33,31,0,'11','aaa',0,'admin6','2018-10-09 23:02:36','2018-10-09 22:58:18','admin6'),(35,31,36,'22','aaa',1,'admin6','2018-10-09 23:09:53','2018-10-09 23:02:45','admin6'),(36,31,0,'22','aaa',1,NULL,NULL,'2018-10-09 23:09:49','admin6'),(38,31,36,'11','aaa',0,'admin6','2018-10-09 23:10:06','2018-10-09 23:10:02','admin6'),(39,31,0,'111','bbb',0,NULL,NULL,'2018-10-09 23:10:36','admin6'),(40,31,0,'222','bbb',1,'admin6','2018-10-09 23:10:43','2018-10-09 23:10:39','admin6'),(41,31,39,'11','bbb',7,'admin6','2018-10-09 23:11:05','2018-10-09 23:10:46','admin6'),(42,31,39,'22','bbb',0,'admin6','2018-10-09 23:11:15','2018-10-09 23:10:48','admin6');
/*!40000 ALTER TABLE `sys_code_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_opt_log`
--

DROP TABLE IF EXISTS `sys_opt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_opt_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作日志',
  `APPID` int(11) NOT NULL,
  `USER_NAME` varchar(64) NOT NULL,
  `OPT_TYPE` varchar(100) DEFAULT NULL COMMENT '操作类型',
  `OPT_LOG` varchar(4000) DEFAULT NULL COMMENT '操作日志',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=978 DEFAULT CHARSET=utf8 COMMENT='系统操作表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_opt_log`
--

LOCK TABLES `sys_opt_log` WRITE;
/*!40000 ALTER TABLE `sys_opt_log` DISABLE KEYS */;
INSERT INTO `sys_opt_log` VALUES (1,8,'管理员','授权用户','[\"e7b043dd693b418ab8ce71af1a08c850\",[\"16da73f6973f443ba1f754ccb294eb98\",\"2531896e9a8e4913b77dbda63efbedf0\"]]','admin','2018-08-30 20:52:59'),(2,8,'管理员','编辑用户','[{\"account\":\"admin\",\"createdBy\":\"admin\",\"createdTime\":\"2018-02-24 05:57:36\",\"id\":\"16da73f6973f443ba1f754ccb294eb98\",\"name\":\"管理员\",\"pwd\":\"202cb962ac59075b964b07152d234b70\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-08-30 20:53:10\"}]','admin','2018-08-30 20:53:10'),(3,8,'管理员','删除用户','[\"ba3a596bd6794466abf9daade37688e6\"]','admin','2018-08-30 20:53:14'),(4,8,'管理员','删除用户','[\"7e144a64331245eaa2aa56a6a6bb0813\"]','admin','2018-08-30 20:53:15'),(5,8,'管理员','编辑用户','[{\"account\":\"jiangyonghua\",\"createdBy\":\"admin\",\"createdTime\":\"2018-02-27 17:08:59\",\"id\":\"2531896e9a8e4913b77dbda63efbedf0\",\"name\":\"江永华\",\"pwd\":\"202cb962ac59075b964b07152d234b70\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-08-30 20:59:46\"}]','admin','2018-08-30 20:59:46'),(6,8,'管理员','分配权限','[\"e7b043dd693b418ab8ce71af1a08c850\",{\"console\":[],\"component\":[],\"component_table\":[],\"console_user\":[\"addUser\",\"editUser\",\"removeUser\"],\"component_table_EditTable\":[],\"console_role\":[\"addRole\",\"editRole\",\"removeRole\",\"assignMenus\",\"assignUsers\"],\"console_code\":[\"addCode\",\"removeCode\",\"addItem\",\"editItem\",\"removeItem\"]}]','admin','2018-08-30 21:17:54'),(7,8,'管理员','分配权限','[\"e7b043dd693b418ab8ce71af1a08c850\",{\"console\":[],\"component\":[],\"component_table\":[],\"console_user\":[\"editUser\",\"addUser\",\"removeUser\"],\"console_optlog\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"addRole\",\"editRole\",\"assignUsers\",\"removeRole\"],\"console_code\":[\"removeItem\",\"addItem\",\"removeCode\",\"addCode\",\"editItem\"]}]','admin','2018-08-30 21:21:28'),(8,8,'管理员','登录','[{\"pwd\":\"202cb962ac59075b964b07152d234b70\",\"account\":\"admin\"}]','admin','2018-09-02 22:39:43'),(9,8,'管理员','注销','[]','admin','2018-09-02 22:40:22'),(370,8,'管理员','注销','[]','admin','2018-09-02 22:59:26'),(371,8,'admin','登录',NULL,'admin','2018-09-02 22:59:36'),(372,8,'管理员','注销','[]','admin','2018-09-02 23:00:36'),(373,8,'admin','登录',NULL,'admin','2018-09-02 23:00:40'),(374,8,'管理员','注销','[]','admin','2018-09-02 23:00:52'),(375,8,'admin','登录',NULL,'admin','2018-09-02 23:01:23'),(376,8,'管理员','注销','[]','admin','2018-09-02 23:01:44'),(377,8,'admin','登录',NULL,'admin','2018-09-02 23:02:05'),(378,8,'管理员','编辑用户','[{\"account\":\"admin\",\"createdBy\":\"admin\",\"createdTime\":\"2018-02-24 05:57:36\",\"id\":\"16da73f6973f443ba1f754ccb294eb98\",\"name\":\"管理员\",\"pwd\":\"202cb962ac59075b964b07152d234b70\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-08-30 20:53:10\"}]','admin','2018-09-02 23:02:10'),(379,8,'管理员','注销','[]','admin','2018-09-02 23:02:14'),(380,8,'admin','登录',NULL,'admin','2018-09-02 23:02:17'),(381,8,'管理员','登录',NULL,'admin','2018-09-02 23:16:00'),(382,8,'管理员','注销',NULL,'admin','2018-09-02 23:16:05'),(383,8,'管理员','登录',NULL,'admin','2018-09-02 23:16:07'),(384,8,'管理员','新增用户','[{\"account\":\"管理员\",\"name\":\"111\"}]','admin','2018-09-02 23:16:23'),(385,8,'管理员','删除用户','[\"b35af1305e5b49ed97cb78da2180d739\"]','admin','2018-09-02 23:16:26'),(386,8,'管理员','新增用户','[{\"account\":\"acc\",\"name\":\"管理员\"}]','admin','2018-09-02 23:16:37'),(387,8,'管理员','删除用户','[\"c352a6ecb25a4854ba96498eaea10f22\"]','admin','2018-09-02 23:16:42'),(388,8,'管理员','登录',NULL,'admin','2018-09-03 22:27:08'),(389,8,'管理员','注销',NULL,'admin','2018-09-03 22:27:19'),(390,8,'管理员','登录',NULL,'admin','2018-09-03 22:27:22'),(391,8,'管理员','登录',NULL,'admin','2018-09-03 22:42:48'),(392,8,'管理员','登录',NULL,'admin','2018-09-04 20:39:35'),(393,8,'管理员','注销',NULL,'admin','2018-09-04 20:43:51'),(394,8,'管理员','登录',NULL,'admin','2018-09-04 20:43:55'),(395,8,'管理员','登录',NULL,'admin','2018-09-04 21:00:57'),(396,8,'管理员','登录',NULL,'admin','2018-09-04 21:35:44'),(397,8,'管理员','分配权限','[\"e7b043dd693b418ab8ce71af1a08c850\",{\"console\":[],\"component\":[],\"component_table\":[],\"console_user\":[\"removeUser\",\"editUser\",\"addUser\"],\"console_optlog\":[],\"component_table_EditTable\":[],\"console_role\":[\"removeRole\",\"assignMenus\",\"assignUsers\",\"editRole\",\"addRole\"],\"console_set\":[],\"console_code\":[\"removeCode\",\"editItem\",\"removeItem\",\"addItem\",\"addCode\"]}]','admin','2018-09-04 21:42:36'),(398,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:09\",\"id\":\"30484258aad24fecb298f9888563dc13\",\"name\":\"patch\",\"path\":[],\"pid\":\"0\",\"sort\":4,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-08-30 20:35:37\"}]','admin','2018-09-04 21:47:28'),(399,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:52:44\",\"id\":\"4b89e085a5314d549184afaaefc9a869\",\"name\":\"get\",\"path\":[],\"pid\":\"0\",\"sort\":3,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-08-30 20:35:40\"}]','admin','2018-09-04 21:47:32'),(400,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:52:44\",\"id\":\"4b89e085a5314d549184afaaefc9a869\",\"name\":\"get\",\"path\":[],\"pid\":\"0\",\"sort\":3,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:32\"}]','admin','2018-09-04 21:47:39'),(401,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:52:44\",\"id\":\"4b89e085a5314d549184afaaefc9a869\",\"name\":\"get\",\"path\":[],\"pid\":\"0\",\"sort\":4,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:39\"}]','admin','2018-09-04 21:47:44'),(402,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:16\",\"id\":\"6c13c03291174c6dbbd8c27ae941f3c3\",\"name\":\"delete\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-03-09 17:30:58\"}]','admin','2018-09-04 21:47:48'),(403,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:16\",\"id\":\"6c13c03291174c6dbbd8c27ae941f3c3\",\"name\":\"delete\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:48\"}]','admin','2018-09-04 21:47:57'),(404,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:16\",\"id\":\"6c13c03291174c6dbbd8c27ae941f3c3\",\"name\":\"delete\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:57\"}]','admin','2018-09-04 21:48:04'),(405,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:16\",\"id\":\"6c13c03291174c6dbbd8c27ae941f3c3\",\"name\":\"delete\",\"path\":[],\"pid\":\"0\",\"sort\":99,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:48:04\"}]','admin','2018-09-04 21:48:08'),(406,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:09\",\"id\":\"30484258aad24fecb298f9888563dc13\",\"name\":\"patch\",\"path\":[],\"pid\":\"0\",\"sort\":99,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:28\"}]','admin','2018-09-04 21:48:15'),(407,8,'管理员','注销',NULL,'admin','2018-09-04 21:50:15'),(408,8,'管理员','登录',NULL,'admin','2018-09-04 21:51:31'),(409,8,'管理员','登录',NULL,'admin','2018-09-05 21:48:12'),(410,8,'管理员','分配权限','[\"e7b043dd693b418ab8ce71af1a08c850\",{\"console\":[],\"component\":[],\"baseinfo\":[],\"component_table\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"console_optlog\":[],\"component_table_EditTable\":[],\"console_role\":[\"editRole\",\"assignUsers\",\"assignMenus\",\"removeRole\",\"addRole\"],\"console_set\":[],\"console_code\":[\"editItem\",\"addItem\",\"removeItem\",\"removeCode\",\"addCode\"],\"baseinfo_companyInfo\":[]}]','admin','2018-09-05 22:19:03'),(411,8,'管理员','登录',NULL,'admin','2018-09-06 22:21:52'),(412,8,'管理员','登录',NULL,'admin','2018-09-06 22:57:02'),(413,8,'管理员','分配权限','[\"e7b043dd693b418ab8ce71af1a08c850\",{\"console\":[],\"component\":[],\"component_table\":[],\"console_user\":[\"editUser\",\"removeUser\",\"addUser\"],\"bas\":[],\"bas_company\":[],\"console_optlog\":[],\"component_table_EditTable\":[],\"console_role\":[\"editRole\",\"addRole\",\"removeRole\",\"assignUsers\",\"assignMenus\"],\"console_set\":[],\"console_code\":[\"addCode\",\"editItem\",\"addItem\",\"removeCode\",\"removeItem\"]}]','admin','2018-09-06 22:57:17'),(414,8,'管理员','新增字典','[{\"code\":\"industry_category\",\"items\":[],\"name\":\"行业类别\"}]','admin','2018-09-06 23:28:25'),(415,8,'管理员','新增字典项目','[{\"name\":\"汽车用品\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"industry_category\"}]','admin','2018-09-06 23:29:29'),(416,8,'管理员','新增字典项目','[{\"name\":\"手机数码\",\"path\":[],\"pid\":\"0\",\"sort\":2,\"type\":\"industry_category\"}]','admin','2018-09-06 23:29:37'),(417,8,'管理员','新增字典项目','[{\"name\":\"食品酒水饮料\",\"path\":[],\"pid\":\"0\",\"sort\":3,\"type\":\"industry_category\"}]','admin','2018-09-06 23:30:00'),(418,8,'管理员','新增字典项目','[{\"name\":\"电子机电\",\"path\":[],\"pid\":\"0\",\"sort\":4,\"type\":\"industry_category\"}]','admin','2018-09-06 23:30:11'),(419,8,'管理员','新增字典项目','[{\"name\":\"家用电器\",\"path\":[],\"pid\":\"0\",\"sort\":5,\"type\":\"industry_category\"}]','admin','2018-09-06 23:30:24'),(420,8,'管理员','新增字典项目','[{\"name\":\"个护化妆品\",\"path\":[],\"pid\":\"0\",\"sort\":6,\"type\":\"industry_category\"}]','admin','2018-09-06 23:30:35'),(421,8,'管理员','新增字典项目','[{\"name\":\"钟表珠宝\",\"path\":[],\"pid\":\"0\",\"sort\":7,\"type\":\"industry_category\"}]','admin','2018-09-06 23:30:53'),(422,8,'管理员','新增字典项目','[{\"name\":\"家具家装\",\"path\":[],\"pid\":\"0\",\"sort\":8,\"type\":\"industry_category\"}]','admin','2018-09-06 23:31:13'),(423,8,'管理员','新增字典项目','[{\"name\":\"服装鞋帽箱包\",\"path\":[],\"pid\":\"0\",\"sort\":9,\"type\":\"industry_category\"}]','admin','2018-09-06 23:31:33'),(424,8,'管理员','新增字典项目','[{\"name\":\"其他行业\",\"path\":[],\"pid\":\"0\",\"sort\":10,\"type\":\"industry_category\"}]','admin','2018-09-06 23:31:47'),(425,8,'管理员','新增字典项目','[{\"name\":\"日用百货\",\"path\":[],\"pid\":\"0\",\"sort\":11,\"type\":\"industry_category\"}]','admin','2018-09-06 23:32:03'),(426,8,'管理员','新增字典项目','[{\"name\":\"母婴用品\",\"path\":[],\"pid\":\"0\",\"sort\":12,\"type\":\"industry_category\"}]','admin','2018-09-06 23:32:29'),(427,8,'管理员','新增字典项目','[{\"name\":\"生鲜农贸\",\"path\":[],\"pid\":\"0\",\"sort\":13,\"type\":\"industry_category\"}]','admin','2018-09-06 23:34:33'),(428,8,'管理员','新增字典项目','[{\"name\":\"餐饮连锁\",\"path\":[],\"pid\":\"0\",\"sort\":14,\"type\":\"industry_category\"}]','admin','2018-09-06 23:34:49'),(429,8,'管理员','新增字典项目','[{\"name\":\"医药行业\",\"path\":[],\"pid\":\"0\",\"sort\":15,\"type\":\"industry_category\"}]','admin','2018-09-06 23:34:55'),(430,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:52:44\",\"id\":\"4b89e085a5314d549184afaaefc9a869\",\"name\":\"get\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:47:44\"}]','admin','2018-09-06 23:35:16'),(431,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:52:53\",\"id\":\"d192ef6b8a0f4169a98e5074f255a8ce\",\"name\":\"post\",\"path\":[],\"pid\":\"0\",\"sort\":2,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-03-14 11:10:01\"}]','admin','2018-09-06 23:35:31'),(432,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:16\",\"id\":\"6c13c03291174c6dbbd8c27ae941f3c3\",\"name\":\"delete\",\"path\":[],\"pid\":\"0\",\"sort\":3,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:48:08\"}]','admin','2018-09-06 23:35:43'),(433,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:09\",\"id\":\"30484258aad24fecb298f9888563dc13\",\"name\":\"patch\",\"path\":[],\"pid\":\"0\",\"sort\":4,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-04 21:48:15\"}]','admin','2018-09-06 23:35:49'),(434,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-03-07 16:53:25\",\"id\":\"3fa8effa370849bb8b75ffeef4c0eb4e\",\"name\":\"put\",\"path\":[],\"pid\":\"0\",\"sort\":5,\"type\":\"http_method\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-05-04 22:33:21\"}]','admin','2018-09-06 23:35:53'),(435,8,'管理员','新增字典','[{\"code\":\"area\",\"items\":[],\"name\":\"区域\"}]','admin','2018-09-06 23:41:14'),(436,8,'管理员','新增字典项目','[{\"name\":\"浙江省\",\"path\":[],\"pid\":\"0\",\"sort\":1,\"type\":\"area\"}]','admin','2018-09-06 23:41:55'),(437,8,'管理员','新增字典项目','[{\"name\":\"宁波市\",\"path\":[\"7fe83121df464e48aab19df97c719172\"],\"pid\":\"7fe83121df464e48aab19df97c719172\",\"sort\":1,\"type\":\"area\"}]','admin','2018-09-06 23:42:14'),(438,8,'管理员','新增字典项目','[{\"name\":\"鄞州区\",\"path\":[\"7fe83121df464e48aab19df97c719172\",\"fc72c73c357e4ccd9054a6b217cee4be\"],\"pid\":\"fc72c73c357e4ccd9054a6b217cee4be\",\"sort\":1,\"type\":\"area\"}]','admin','2018-09-06 23:42:37'),(439,8,'管理员','新增字典项目','[{\"name\":\"海曙区\",\"path\":[\"7fe83121df464e48aab19df97c719172\",\"fc72c73c357e4ccd9054a6b217cee4be\"],\"pid\":\"fc72c73c357e4ccd9054a6b217cee4be\",\"sort\":1,\"type\":\"area\"}]','admin','2018-09-06 23:42:57'),(440,8,'管理员','编辑字典项目','[{\"createdBy\":\"admin\",\"createdTime\":\"2018-09-06 23:42:37\",\"id\":\"2b331c815ffd4028bbacb1256427e3ff\",\"name\":\"鄞州区\",\"path\":[\"7fe83121df464e48aab19df97c719172\",\"fc72c73c357e4ccd9054a6b217cee4be\"],\"pid\":\"fc72c73c357e4ccd9054a6b217cee4be\",\"sort\":2,\"type\":\"area\",\"updatedBy\":\"\",\"updatedTime\":\"\"}]','admin','2018-09-06 23:43:01'),(441,8,'管理员','登录',NULL,'admin','2018-09-07 20:18:58'),(442,8,'管理员','登录',NULL,'admin','2018-09-07 21:42:02'),(443,8,'111','登录',NULL,'111','2018-09-08 21:49:13'),(444,8,'111','注销',NULL,'111','2018-09-08 21:49:17'),(445,13,'444','登录',NULL,'444','2018-09-09 17:11:13'),(836,13,'444','登录',NULL,'444','2018-09-09 18:08:17'),(837,13,'444','登录',NULL,'444','2018-09-09 18:11:34'),(838,13,'444','登录',NULL,'444','2018-09-09 18:17:46'),(839,13,'444','登录',NULL,'444','2018-09-09 18:19:28'),(840,8,'444','登录',NULL,'444','2018-09-09 18:19:34'),(841,8,'444','登录',NULL,'444','2018-09-09 18:26:03'),(842,8,'444','登录',NULL,'444','2018-09-09 20:25:19'),(843,8,'444','登录',NULL,'444','2018-09-10 08:01:05'),(844,8,'444','登录',NULL,'444','2018-09-10 08:05:11'),(845,8,'444','分配权限','[0,{\"console\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas\":[],\"bas_company\":[],\"console_optlog\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"console_code\":[\"editItem\",\"removeItem\",\"addItem\",\"addCode\",\"removeCode\"]}]','444','2018-09-10 08:17:37'),(846,8,'444','登录',NULL,'444','2018-09-11 08:01:46'),(847,8,'管理员','登录',NULL,'admin','2018-09-13 22:54:52'),(848,8,'管理员','登录',NULL,'admin','2018-09-13 23:15:59'),(849,8,'管理员','登录',NULL,'admin','2018-09-14 08:20:57'),(850,8,'管理员','登录',NULL,'admin','2018-09-15 00:12:08'),(851,8,'管理员','删除字典项目','[13]','admin','2018-09-15 00:14:26'),(852,8,'管理员','新增字典项目','[{\"name\":\"海曙区\",\"path\":[14,24],\"pid\":24,\"sort\":2,\"type\":\"area\"}]','admin','2018-09-15 00:14:50'),(853,8,'管理员','编辑字典项目','[{\"appid\":8,\"createdBy\":\"admin\",\"createdTime\":\"2018-09-06 23:42:37\",\"id\":6,\"name\":\"鄞州区\",\"path\":[14,24],\"pid\":24,\"sort\":1,\"type\":\"area\",\"updatedBy\":\"admin\",\"updatedTime\":\"2018-09-06 23:43:01\"}]','admin','2018-09-15 00:14:59'),(854,8,'管理员','登录',NULL,'admin','2018-09-15 00:40:53'),(855,8,'管理员','登录',NULL,'admin','2018-09-15 11:21:19'),(856,8,'管理员','登录',NULL,'admin','2018-09-15 20:52:42'),(857,8,'管理员','登录',NULL,'admin','2018-09-15 21:12:57'),(858,8,'管理员','登录',NULL,'admin','2018-09-15 21:47:07'),(859,8,'管理员','注销',NULL,'admin','2018-09-15 23:48:09'),(860,20,'江永华','登录',NULL,'123','2018-09-16 00:21:12'),(861,20,'江永华','注销',NULL,'123','2018-09-16 00:21:17'),(862,20,'江永华','登录',NULL,'123','2018-09-16 00:21:27'),(863,20,'江永华','登录',NULL,'123','2018-09-16 00:24:57'),(864,20,'江永华','新增字典','[{\"code\":\"abc\",\"items\":[],\"name\":\"111\"}]','123','2018-09-16 00:25:11'),(865,20,'江永华','新增字典项目','[{\"name\":\"111\",\"path\":[],\"pid\":0,\"sort\":1,\"type\":\"abc\"}]','123','2018-09-16 00:25:45'),(866,20,'江永华','新增字典项目','[{\"name\":\"2222\",\"path\":[26],\"pid\":26,\"sort\":2,\"type\":\"abc\"}]','123','2018-09-16 00:25:50'),(867,20,'江永华','新增字典项目','[{\"name\":\"3333\",\"path\":[26,27],\"pid\":27,\"sort\":3,\"type\":\"abc\"}]','123','2018-09-16 00:25:56'),(868,20,'江永华','新增字典项目','[{\"name\":\"22222\",\"path\":[26],\"pid\":26,\"sort\":4,\"type\":\"abc\"}]','123','2018-09-16 00:26:00'),(869,20,'江永华','删除字典项目','[29]','123','2018-09-16 00:26:06'),(870,20,'江永华','删除字典项目','[28]','123','2018-09-16 00:26:08'),(871,20,'江永华','登录',NULL,'123','2018-09-16 00:55:42'),(872,20,'江永华','登录',NULL,'123','2018-09-16 01:02:12'),(873,20,'江永华','注销',NULL,'123','2018-09-16 01:04:15'),(874,8,'管理员','登录',NULL,'admin','2018-09-16 01:04:20'),(875,8,'管理员','注销',NULL,'admin','2018-09-16 01:09:10'),(876,20,'江永华','登录',NULL,'123','2018-09-16 01:09:16'),(877,20,'江永华','注销',NULL,'123','2018-09-16 01:09:41'),(878,21,'111','登录',NULL,'abc','2018-09-16 01:10:21'),(879,8,'管理员','登录',NULL,'admin','2018-09-16 19:41:01'),(880,8,'管理员','登录',NULL,'admin','2018-09-17 19:55:35'),(881,8,'管理员','登录',NULL,'admin','2018-09-17 20:37:05'),(882,8,'管理员','登录',NULL,'admin','2018-09-17 20:57:38'),(883,8,'管理员','分配权限','[0,{\"console\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_code\":[],\"bas\":[],\"code_warehouse\":[],\"console_optlog\":[]}]','admin','2018-09-17 21:01:16'),(884,8,'管理员','登录',NULL,'admin','2018-09-17 21:06:44'),(885,8,'管理员','登录',NULL,'admin','2018-09-17 21:20:10'),(886,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_code\":[],\"bas\":[],\"code_warehouse\":[],\"console_optlog\":[]}]','admin','2018-09-17 21:23:15'),(887,8,'管理员','登录',NULL,'admin','2018-09-17 23:18:06'),(888,8,'管理员','登录',NULL,'admin','2018-09-17 23:32:57'),(889,8,'管理员','登录',NULL,'admin','2018-09-18 00:17:04'),(890,8,'管理员','登录',NULL,'admin','2018-09-18 11:06:05'),(891,8,'管理员','登录',NULL,'admin','2018-09-19 20:48:21'),(892,8,'管理员','登录',NULL,'admin','2018-09-19 21:52:58'),(893,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[],\"code_code\":[],\"bas\":[],\"code_warehouse\":[],\"console_optlog\":[]}]','admin','2018-09-19 21:53:09'),(894,8,'管理员','登录',NULL,'admin','2018-09-19 22:12:35'),(895,8,'管理员','登录',NULL,'admin','2018-09-19 22:25:30'),(896,8,'管理员','登录',NULL,'admin','2018-09-19 22:27:44'),(897,8,'管理员','登录',NULL,'admin','2018-09-19 22:28:37'),(898,8,'管理员','登录',NULL,'admin','2018-09-21 20:59:57'),(899,8,'管理员','登录',NULL,'admin','2018-09-21 21:14:48'),(900,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"code_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\"],\"console_optlog\":[]}]','admin','2018-09-21 21:15:06'),(901,8,'管理员','登录',NULL,'admin','2018-09-21 21:38:37'),(902,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"code_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"console_optlog\":[]}]','admin','2018-09-21 21:38:48'),(903,8,'管理员','登录',NULL,'admin','2018-09-21 21:43:05'),(904,8,'管理员','登录',NULL,'admin','2018-09-21 21:45:44'),(905,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"code_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\"],\"console_optlog\":[]}]','admin','2018-09-21 21:47:05'),(906,8,'管理员','分配权限','[0,{\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"console_set\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"code_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"console_optlog\":[]}]','admin','2018-09-21 21:47:49'),(907,8,'管理员','注销',NULL,'admin','2018-09-21 21:52:14'),(908,8,'管理员','登录',NULL,'admin','2018-09-21 21:52:23'),(909,8,'管理员','登录',NULL,'admin','2018-09-21 22:11:36'),(910,8,'管理员','登录',NULL,'admin','2018-09-21 22:20:38'),(911,8,'管理员','注销',NULL,'admin','2018-09-22 23:37:08'),(912,8,'管理员','登录',NULL,'admin','2018-09-22 23:37:12'),(913,8,'管理员','登录',NULL,'admin','2018-09-24 08:06:13'),(914,8,'管理员','登录',NULL,'admin','2018-09-24 10:39:31'),(915,8,'管理员','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin','2018-09-24 11:21:23'),(916,8,'管理员','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin','2018-09-24 11:23:35'),(917,8,'管理员','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"code_accountperiod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"sys_set\":[],\"code_productcatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"sys_optlog\":[]}]','admin','2018-09-24 13:15:27'),(918,8,'管理员','登录',NULL,'admin','2018-09-24 21:20:14'),(919,8,'管理员','登录',NULL,'admin','2018-09-24 22:09:56'),(920,8,'管理员','登录',NULL,'admin','2018-09-28 23:26:37'),(921,8,'管理员','登录',NULL,'admin','2018-09-29 21:06:51'),(922,8,'管理员','登录',NULL,'admin','2018-09-29 21:10:50'),(923,8,'管理员','注销',NULL,'admin','2018-09-29 21:39:09'),(924,8,'管理员','登录',NULL,'admin','2018-09-29 21:39:12'),(925,8,'管理员','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code_productCatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin','2018-09-29 22:23:37'),(926,8,'管理员','登录',NULL,'admin','2018-10-01 09:34:43'),(927,8,'管理员','登录',NULL,'admin','2018-10-01 13:13:55'),(928,8,'管理员','登录',NULL,'admin','2018-10-01 14:31:24'),(929,8,'管理员','登录',NULL,'admin','2018-10-01 16:48:31'),(930,8,'管理员','登录',NULL,'admin','2018-10-01 20:08:25'),(931,8,'管理员','登录',NULL,'admin','2018-10-01 21:45:27'),(932,8,'管理员','登录',NULL,'admin','2018-10-02 09:47:27'),(933,8,'管理员','登录',NULL,'admin','2018-10-03 22:24:37'),(934,8,'管理员','登录',NULL,'admin','2018-10-04 17:11:06'),(935,8,'管理员','登录',NULL,'admin','2018-10-04 19:01:02'),(936,8,'管理员','注销',NULL,'admin','2018-10-04 21:10:35'),(937,8,'管理员','登录',NULL,'admin','2018-10-04 21:11:23'),(938,8,'管理员','注销',NULL,'admin','2018-10-04 21:14:27'),(939,8,'管理员','登录',NULL,'admin','2018-10-04 21:14:34'),(940,8,'管理员','登录',NULL,'admin','2018-10-05 22:30:52'),(941,8,'管理员','登录',NULL,'admin','2018-10-05 22:32:22'),(942,8,'管理员','登录',NULL,'admin','2018-10-06 19:09:59'),(943,8,'管理员','登录',NULL,'admin','2018-10-06 22:41:13'),(944,8,'管理员','登录',NULL,'admin','2018-10-06 23:35:21'),(945,8,'管理员','登录',NULL,'admin','2018-10-07 22:52:05'),(946,8,'管理员','登录',NULL,'admin','2018-10-07 22:55:40'),(947,8,'管理员','登录',NULL,'admin','2018-10-07 22:57:10'),(948,8,'管理员','登录',NULL,'admin','2018-10-07 22:59:56'),(949,8,'管理员','登录',NULL,'admin','2018-10-07 23:19:01'),(950,8,'管理员','注销',NULL,'admin','2018-10-07 23:19:22'),(951,23,'admin1','登录',NULL,'admin1','2018-10-07 23:24:28'),(952,8,'管理员','登录',NULL,'admin','2018-10-08 09:30:59'),(953,8,'管理员','注销',NULL,'admin','2018-10-08 09:31:09'),(954,23,'admin1','登录',NULL,'admin1','2018-10-08 09:31:17'),(955,23,'admin1','登录',NULL,'admin1','2018-10-08 09:41:38'),(956,23,'admin1','登录',NULL,'admin1','2018-10-08 09:46:38'),(957,23,'admin1','登录',NULL,'admin1','2018-10-08 09:48:44'),(958,23,'admin1','登录',NULL,'admin1','2018-10-08 10:15:42'),(959,23,'admin1','登录',NULL,'admin1','2018-10-08 11:02:46'),(960,23,'admin1','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code_productCatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"code_unit\":[\"addUnit\",\"removeUnit\",\"moveTopUnit\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin1','2018-10-08 11:03:02'),(961,23,'admin1','登录',NULL,'admin1','2018-10-08 11:40:14'),(962,23,'admin1','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code_productCatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"code_unit\":[\"addUnit\",\"removeUnit\",\"moveTopUnit\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\",\"moveTopWarehouse\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin1','2018-10-08 11:42:40'),(963,23,'admin1','登录',NULL,'admin1','2018-10-08 11:53:28'),(964,23,'admin1','登录',NULL,'admin1','2018-10-08 16:41:56'),(965,23,'admin1','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code_productCatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"code_unit\":[\"addUnit\",\"removeUnit\",\"moveTopUnit\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\",\"moveTopWarehouse\"],\"code_customerGrade\":[\"addCustomerGrade\",\"editCustomerGrade\",\"moveTopCustomerGrade\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin1','2018-10-08 16:42:07'),(966,23,'admin1','分配权限','[0,{\"sys_code\":[\"addCode\",\"removeCode\",\"addCodeItem\",\"editCodeItem\",\"removeCodeItem\"],\"console\":[],\"code_productCatalog\":[\"addProductCatalog\",\"editProductCatalog\",\"removeProductCatalog\",\"moveTopProductCatalog\"],\"code\":[],\"console_user\":[\"removeUser\",\"addUser\",\"editUser\"],\"code_unit\":[\"addUnit\",\"removeUnit\",\"moveTopUnit\"],\"bas_company\":[],\"component_table_EditTable\":[],\"console_role\":[\"assignMenus\",\"editRole\",\"removeRole\",\"addRole\",\"assignUsers\"],\"sys\":[],\"component\":[],\"bas_hr\":[],\"component_table\":[],\"code_accountPeriod\":[\"addAccountPeriod\",\"editAccountPeriod\",\"removeAccountPeriod\",\"moveTopAccountPeriod\"],\"bas\":[],\"code_warehouse\":[\"addWarehouse\",\"editWarehouse\",\"removeWarehouse\",\"setDefaultWarehouse\",\"enableWarehouse\",\"moveTopWarehouse\"],\"code_customerGrade\":[\"addCustomerGrade\",\"editCustomerGrade\",\"moveTopCustomerGrade\",\"removeCustomerGrade\"],\"sys_set\":[],\"sys_optlog\":[]}]','admin1','2018-10-08 18:43:47'),(967,23,'admin1','登录',NULL,'admin1','2018-10-08 20:02:44'),(968,28,'admin2','登录',NULL,'admin2','2018-10-08 20:23:08'),(969,30,'admin5','登录',NULL,'admin5','2018-10-08 20:40:45'),(970,8,'管理员','登录',NULL,'admin','2018-10-09 20:58:31'),(971,8,'管理员','登录',NULL,'admin','2018-10-09 21:48:15'),(972,8,'管理员','登录',NULL,'admin','2018-10-09 22:13:17'),(973,8,'管理员','注销',NULL,'admin','2018-10-09 22:13:32'),(974,31,'admin6','登录',NULL,'admin6','2018-10-09 22:14:59'),(975,31,'admin6','登录',NULL,'admin6','2018-10-09 22:31:48'),(976,31,'admin6','登录',NULL,'admin6','2018-10-09 22:45:55'),(977,31,'admin6','登录',NULL,'admin6','2018-10-09 23:09:37');
/*!40000 ALTER TABLE `sys_opt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `APPID` int(11) NOT NULL,
  `NAME` varchar(64) NOT NULL COMMENT '名称',
  `DESCN` varchar(255) DEFAULT NULL COMMENT '描述',
  `ENABLED` char(1) NOT NULL DEFAULT 'T' COMMENT '是否启动 T|F',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (0,0,'super_admin','超级管理员','T','160503006','2018-01-19 16:20:24','160503006','2018-01-09 10:53:26');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `MENU_KEY` varchar(100) NOT NULL,
  `ACTION_KEY` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=540 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (495,0,'sys_code','addCode'),(496,0,'sys_code','removeCode'),(497,0,'sys_code','addCodeItem'),(498,0,'sys_code','editCodeItem'),(499,0,'sys_code','removeCodeItem'),(500,0,'console',''),(501,0,'code_productCatalog','addProductCatalog'),(502,0,'code_productCatalog','editProductCatalog'),(503,0,'code_productCatalog','removeProductCatalog'),(504,0,'code_productCatalog','moveTopProductCatalog'),(505,0,'code',''),(506,0,'console_user','removeUser'),(507,0,'console_user','addUser'),(508,0,'console_user','editUser'),(509,0,'code_unit','addUnit'),(510,0,'code_unit','removeUnit'),(511,0,'code_unit','moveTopUnit'),(512,0,'bas_company',''),(513,0,'component_table_EditTable',''),(514,0,'console_role','assignMenus'),(515,0,'console_role','editRole'),(516,0,'console_role','removeRole'),(517,0,'console_role','addRole'),(518,0,'console_role','assignUsers'),(519,0,'sys',''),(520,0,'component',''),(521,0,'bas_hr',''),(522,0,'component_table',''),(523,0,'code_accountPeriod','addAccountPeriod'),(524,0,'code_accountPeriod','editAccountPeriod'),(525,0,'code_accountPeriod','removeAccountPeriod'),(526,0,'code_accountPeriod','moveTopAccountPeriod'),(527,0,'bas',''),(528,0,'code_warehouse','addWarehouse'),(529,0,'code_warehouse','editWarehouse'),(530,0,'code_warehouse','removeWarehouse'),(531,0,'code_warehouse','setDefaultWarehouse'),(532,0,'code_warehouse','enableWarehouse'),(533,0,'code_warehouse','moveTopWarehouse'),(534,0,'code_customerGrade','addCustomerGrade'),(535,0,'code_customerGrade','editCustomerGrade'),(536,0,'code_customerGrade','moveTopCustomerGrade'),(537,0,'code_customerGrade','removeCustomerGrade'),(538,0,'sys_set',''),(539,0,'sys_optlog','');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_user`
--

DROP TABLE IF EXISTS `sys_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` char(32) NOT NULL COMMENT '工号',
  `ROLE_ID` char(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色用户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_user`
--

LOCK TABLES `sys_role_user` WRITE;
/*!40000 ALTER TABLE `sys_role_user` DISABLE KEYS */;
INSERT INTO `sys_role_user` VALUES (1,'1','0'),(2,'2','0'),(3,'3','0'),(4,'4','0'),(5,'5','0'),(6,'6','0'),(7,'13','0'),(8,'14','0'),(9,'16','0'),(10,'21','0'),(11,'22','0'),(12,'23','0'),(13,'24','0');
/*!40000 ALTER TABLE `sys_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `ACCOUNT` varchar(45) NOT NULL,
  `PWD` varchar(45) NOT NULL,
  `POSITION` varchar(45) DEFAULT NULL,
  `MOBILE` char(11) NOT NULL,
  `QQ` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,8,'111','111','e10adc3949ba59abbe56e057f20f883e',NULL,'',NULL,NULL,NULL,NULL,'111','2018-09-08 21:14:09'),(2,8,'111','111','c6f057b86584942e415435ffb1fa93d4',NULL,'',NULL,NULL,NULL,NULL,'111','2018-09-08 22:02:03'),(3,8,'管理员','admin','202cb962ac59075b964b07152d234b70',NULL,'',NULL,NULL,'admin','2018-09-02 23:02:10','admin','2018-02-24 05:57:36'),(4,8,'333','3333','c6f057b86584942e415435ffb1fa93d4',NULL,'333',NULL,NULL,NULL,NULL,'3333','2018-09-09 00:24:39'),(5,8,'江永华','jiangyonghua','202cb962ac59075b964b07152d234b70',NULL,'',NULL,NULL,'admin','2018-08-30 20:59:46','admin','2018-02-27 17:08:59'),(6,8,'444','444','c6f057b86584942e415435ffb1fa93d4','','444','','','444','2018-09-09 18:01:30','444','2018-09-09 17:11:03'),(13,20,'江永华','123','161ebd7d45089b3446ee4e0d86dbcf92',NULL,'13738430892',NULL,NULL,NULL,NULL,'123','2018-09-16 00:20:23'),(14,21,'111','abc','161ebd7d45089b3446ee4e0d86dbcf92',NULL,'13738430892',NULL,NULL,NULL,NULL,'abc','2018-09-16 01:10:15'),(16,23,'admin1','admin1','96e79218965eb72c92a549dd5a330112',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin1','2018-10-07 23:24:07'),(21,28,'admin2','admin2','96e79218965eb72c92a549dd5a330112',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin2','2018-10-08 20:23:01'),(22,29,'admin4','admin4','96e79218965eb72c92a549dd5a330112',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin4','2018-10-08 20:38:01'),(23,30,'admin5','admin5','96e79218965eb72c92a549dd5a330112',NULL,'13738430892',NULL,NULL,NULL,NULL,'admin5','2018-10-08 20:40:40'),(24,31,'admin6','admin6','bed128365216c019988915ed3add75fb',NULL,'13738434098',NULL,NULL,NULL,NULL,'admin6','2018-10-09 22:14:53');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-09 23:12:49
