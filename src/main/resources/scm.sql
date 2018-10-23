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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_company`
--

LOCK TABLES `bas_company` WRITE;
/*!40000 ALTER TABLE `bas_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `bas_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bas_dept`
--

DROP TABLE IF EXISTS `bas_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bas_dept` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bas_dept`
--

LOCK TABLES `bas_dept` WRITE;
/*!40000 ALTER TABLE `bas_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `bas_dept` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账期';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_account_period`
--

LOCK TABLES `code_account_period` WRITE;
/*!40000 ALTER TABLE `code_account_period` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_customer_grade`
--

LOCK TABLES `code_customer_grade` WRITE;
/*!40000 ALTER TABLE `code_customer_grade` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_product_catalog`
--

LOCK TABLES `code_product_catalog` WRITE;
/*!40000 ALTER TABLE `code_product_catalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `code_product_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_unit`
--

DROP TABLE IF EXISTS `code_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_unit` (
  `ID` int(11) NOT NULL,
  `APPID` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_TIME` char(19) NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计量单位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_unit`
--

LOCK TABLES `code_unit` WRITE;
/*!40000 ALTER TABLE `code_unit` DISABLE KEYS */;
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
  `APPID` int(11) NOT NULL,
  `CODE` varchar(45) NOT NULL,
  `NAME` varchar(24) NOT NULL,
  `SORT` int(11) NOT NULL DEFAULT '0',
  `ENABLED` char(1) NOT NULL DEFAULT 'F' COMMENT '是否是默认仓库',
  `ADDRESS` varchar(45) DEFAULT NULL,
  `DEFAULTED` char(1) NOT NULL DEFAULT 'F' COMMENT '是否是默认仓库',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_warehouse`
--

LOCK TABLES `code_warehouse` WRITE;
/*!40000 ALTER TABLE `code_warehouse` DISABLE KEYS */;
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
  `NAME` varchar(64) NOT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_code`
--

LOCK TABLES `sys_code` WRITE;
/*!40000 ALTER TABLE `sys_code` DISABLE KEYS */;
INSERT INTO `sys_code` VALUES ('area','区域'),('industry_category','行业类别');
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
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
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
INSERT INTO `sys_code_item` VALUES (1,0,0,'电子机电','industry_category',4,NULL,NULL,'admin','2018-09-02 23:02:10'),(2,0,0,'其他行业','industry_category',10,NULL,NULL,'admin','2018-09-02 23:02:10'),(3,0,0,'医药行业','industry_category',15,NULL,NULL,'admin','2018-09-02 23:02:10'),(4,0,0,'家用电器','industry_category',5,NULL,NULL,'admin','2018-09-02 23:02:10'),(5,0,0,'餐饮连锁','industry_category',14,NULL,NULL,'admin','2018-09-02 23:02:10'),(6,0,24,'鄞州区','area',1,NULL,NULL,'admin','2018-09-02 23:02:10'),(7,0,0,'食品酒水饮料','industry_category',3,NULL,NULL,'admin','2018-09-02 23:02:10'),(9,0,0,'服装鞋帽箱包','industry_category',9,NULL,NULL,'admin','2018-09-02 23:02:10'),(14,0,0,'浙江省','area',1,NULL,NULL,'admin','2018-09-02 23:02:10'),(15,0,0,'母婴用品','industry_category',12,NULL,NULL,'admin','2018-09-02 23:02:10'),(16,0,0,'个护化妆品','industry_category',6,NULL,NULL,'admin','2018-09-02 23:02:10'),(17,0,0,'日用百货','industry_category',11,NULL,NULL,'admin','2018-09-02 23:02:10'),(18,0,0,'手机数码','industry_category',2,NULL,NULL,'admin','2018-09-02 23:02:10'),(19,0,0,'钟表珠宝','industry_category',7,NULL,NULL,'admin','2018-09-02 23:02:10'),(20,0,0,'汽车用品','industry_category',1,NULL,NULL,'admin','2018-09-02 23:02:10'),(21,0,0,'家具家装','industry_category',8,NULL,NULL,'admin','2018-09-02 23:02:10'),(23,0,0,'生鲜农贸','industry_category',13,NULL,NULL,'admin','2018-09-02 23:02:10'),(24,0,14,'宁波市','area',1,NULL,NULL,'admin','2018-09-02 23:02:10'),(25,0,24,'海曙区','area',2,NULL,NULL,'admin','2018-09-02 23:02:10');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统操作表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_opt_log`
--

LOCK TABLES `sys_opt_log` WRITE;
/*!40000 ALTER TABLE `sys_opt_log` DISABLE KEYS */;
INSERT INTO `sys_opt_log` VALUES (1,0,'超级管理员','登录',NULL,'admin','2018-10-23 20:51:12'),(2,0,'超级管理员','登录',NULL,'admin','2018-10-23 20:51:49');
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
  `SORT` int(11) NOT NULL DEFAULT '0',
  `DESCN` varchar(255) DEFAULT NULL COMMENT '描述',
  `UPDATED_BY` varchar(45) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` char(19) DEFAULT NULL COMMENT '更新时间',
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_TIME` char(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (0,0,'super_admin',0,'超级管理员','','','admin','2018-01-09 10:53:26'),(1,0,'系统管理员',1,NULL,'',NULL,'admin','2018-01-09 10:53:26'),(2,0,'业务负责人',2,NULL,'',NULL,'admin','2018-01-09 10:53:26'),(3,0,'订单审核员',3,NULL,'',NULL,'admin','2018-01-09 10:53:26'),(4,0,'财务审核员',4,NULL,'',NULL,'admin','2018-01-09 10:53:26'),(5,0,'发货审核员',5,NULL,'',NULL,'admin','2018-01-09 10:53:26'),(6,0,'业务经理',6,NULL,NULL,NULL,'admin','2018-01-09 10:53:26'),(7,0,'仓库管理员',7,NULL,NULL,NULL,'admin','2018-01-09 10:53:26'),(8,0,'资料维护员',8,NULL,NULL,NULL,'admin','2018-01-09 10:53:26'),(9,0,'自定义角色一',9,NULL,NULL,NULL,'admin','2018-01-09 10:53:26'),(10,0,'自定义角色二',10,NULL,NULL,NULL,'admin','2018-01-09 10:53:26');
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
) ENGINE=InnoDB AUTO_INCREMENT=682 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (0,0,'console',''),(1,0,'console_role',''),(2,0,'console_code','');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色用户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_user`
--

LOCK TABLES `sys_role_user` WRITE;
/*!40000 ALTER TABLE `sys_role_user` DISABLE KEYS */;
INSERT INTO `sys_role_user` VALUES (0,'0','0');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (0,0,'超级管理员','admin','bed128365216c019988915ed3add75fb',NULL,'',NULL,NULL,'','','admin','2018-02-24 05:57:36');
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

-- Dump completed on 2018-10-23 20:54:19
