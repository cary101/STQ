/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - stq
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`stq` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `stq`;

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `ID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Key',
  `APPLY_ID` char(13) DEFAULT NULL COMMENT '申请单编号',
  `CHARGE_ID` varchar(12) DEFAULT NULL COMMENT '报价单号',
  `APPLY_COMPANY_ID` int(11) NOT NULL COMMENT '申请公司ID',
  `APPLY_COMPANY_NAME` varchar(50) DEFAULT NULL COMMENT '申请公司名',
  `LINKMAN_ID` varchar(30) NOT NULL COMMENT '联系人ID',
  `LINKMAN_NAME` varchar(60) DEFAULT NULL COMMENT '联系人姓名',
  `SAMPLE_ID` int(11) NOT NULL COMMENT '样品ID',
  `SAMPLE_NAME` varchar(60) DEFAULT NULL COMMENT '样品名称',
  `SAMPLE_RECEIVED` char(1) DEFAULT 'N' COMMENT '是否已接样(Y/N)',
  `SAMPLE_RECEIVED_DT` datetime DEFAULT NULL COMMENT '接样时间',
  `TEST_ITEMS` varchar(50) DEFAULT NULL COMMENT '检测项目',
  `OTHER_TEST_ITEMS` varchar(50) DEFAULT NULL COMMENT '其他检测项目',
  `SERVICE_TYPE` varchar(1) DEFAULT 'N' COMMENT '服务类别（标准:N，加急:C）',
  `REPORD_ID` varchar(12) DEFAULT NULL COMMENT '报告号码',
  `REPORT_LANGUAGE` char(1) DEFAULT NULL COMMENT '报告语言',
  `REPORT_TYPE` char(30) DEFAULT NULL COMMENT '报告形式',
  `REPORT_COMPANY_NAME` varchar(50) DEFAULT NULL COMMENT '报告公司名',
  `REPORT_COMPANY_ADDRESS` varchar(1) DEFAULT NULL COMMENT '报告公司地址',
  `REPORT_DT` datetime DEFAULT NULL COMMENT '期望报告时间',
  `INVOICE_COMMANY` varchar(60) DEFAULT NULL COMMENT '发票抬头公司',
  `INVOICE_COMMANY_REMARKS` varchar(60) DEFAULT NULL COMMENT '发票抬头公司备注',
  `REPORT_INVOICE_TO` varchar(60) DEFAULT NULL COMMENT '报告或发票寄送地址',
  `REPORT_INVOICE_TO_REMARKS` varchar(60) DEFAULT NULL COMMENT '报告或发票寄送地址备注',
  `CAN_SUBPACKAGE` char(1) DEFAULT 'N' COMMENT '是否接受分包(Y/N)',
  `CUSTOMER_SIGN_DATE` date DEFAULT NULL COMMENT '客户签名日期',
  `STO_SIGN_DATE` date DEFAULT NULL COMMENT 'STQ代表签名日期',
  `STATUS` char(2) DEFAULT NULL COMMENT '0:cancled,1:apply, 2:verified,3:testing,4:reporting,5:csComfirmed,6:printed,9:reject,99:csDisagree',
  `SELLER_ID` varchar(60) DEFAULT NULL COMMENT '销售编号',
  `CREATE_BY` varchar(60) DEFAULT NULL,
  `CREATE_DT` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(60) DEFAULT NULL,
  `MODIFIED_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `application` */

insert  into `application`(`ID`,`APPLY_ID`,`CHARGE_ID`,`APPLY_COMPANY_ID`,`APPLY_COMPANY_NAME`,`LINKMAN_ID`,`LINKMAN_NAME`,`SAMPLE_ID`,`SAMPLE_NAME`,`SAMPLE_RECEIVED`,`SAMPLE_RECEIVED_DT`,`TEST_ITEMS`,`OTHER_TEST_ITEMS`,`SERVICE_TYPE`,`REPORD_ID`,`REPORT_LANGUAGE`,`REPORT_TYPE`,`REPORT_COMPANY_NAME`,`REPORT_COMPANY_ADDRESS`,`REPORT_DT`,`INVOICE_COMMANY`,`INVOICE_COMMANY_REMARKS`,`REPORT_INVOICE_TO`,`REPORT_INVOICE_TO_REMARKS`,`CAN_SUBPACKAGE`,`CUSTOMER_SIGN_DATE`,`STO_SIGN_DATE`,`STATUS`,`SELLER_ID`,`CREATE_BY`,`CREATE_DT`,`MODIFIED_BY`,`MODIFIED_DT`) values (0000000001,'',NULL,6,'常州市跨越木业有限公司','11','范建新',1,'保温杯盖保温杯盖','1','2013-02-07 14:09:58',NULL,NULL,'1',NULL,NULL,'2,3','杭州汉德质量认证服务有限公司','杭','2013-02-07 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','CARY','2012-01-24 17:17:56','cary','2013-02-07 14:09:58'),(0000000002,'',NULL,6,'常州市跨越木业有限公司','11','范建新',2,'保温杯盖保温杯盖','2','2013-02-07 14:09:58',NULL,NULL,'2',NULL,NULL,'2,3','杭州汉德质量认证服务有限公司','杭','2013-02-07 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','CARY','2013-01-24 17:18:17','cary','2013-02-07 14:09:58'),(0000000003,'',NULL,6,'常州市跨越木业有限公司','11','范建新',3,'保温杯盖保温杯盖','1','2013-02-07 14:09:58',NULL,NULL,'1',NULL,NULL,'2,3','杭州汉德质量认证服务有限公司','杭','2013-02-07 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','CARY','2013-01-24 17:18:29','cary','2013-02-07 14:09:58'),(0000000004,'',NULL,6,'常州市跨越木业有限公司','11','范建新',9,'保温杯盖保温杯盖','2','2013-02-07 14:09:58',NULL,NULL,'2',NULL,NULL,'2,3','杭州汉德质量认证服务有限公司','杭','2013-02-07 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','cary','2013-02-06 18:05:24','cary','2013-02-07 14:09:58'),(0000000005,'',NULL,1,'上海联广认证有限公司','2','孙海芹',10,'haha','1','2013-02-01 00:00:00',NULL,NULL,'1',NULL,NULL,'2,3','杭州汉德质量认证服务有限公司','杭','2013-03-01 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','cary','2013-02-06 18:19:43','cary','2013-02-07 15:09:00'),(0000000006,'',NULL,7,'圣戈班研发（上海）有限公司','12','贾小华',6,'保温杯盖保','2','2013-02-07 15:03:38',NULL,NULL,'2',NULL,NULL,'1,2,3,4','杭州汉德质量认证服务有限公司','杭','2013-02-28 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'Y',NULL,NULL,'1','','cary','2013-02-06 18:23:52','cary','2013-02-07 15:03:38'),(0000000007,'',NULL,1,'上海联广认证有限公司','2','孙海芹',7,'保温杯盖保温杯盖','1','2013-02-07 14:46:48',NULL,NULL,'1',NULL,NULL,'1,2,3,4','杭州汉德质量认证服务有限公司','杭','2013-02-01 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'N',NULL,NULL,'1','','cary','2013-02-07 13:35:05','cary','2013-02-07 14:46:48'),(0000000008,'',NULL,7,'圣戈班研发（上海）有限公司','12','贾小华',11,'保温杯盖保温杯盖','1','2013-04-04 00:00:00',NULL,NULL,'2',NULL,NULL,'2','杭州汉德质量认证服务有限公司','杭','2013-04-05 00:00:00','范罗士办公用品（苏州）有限公司',NULL,'中国江苏省苏州市高新区石林路1号',NULL,'2',NULL,NULL,'1','','cary','2013-02-07 13:43:51','cary','2013-05-03 18:19:42');

/*Table structure for table `code` */

DROP TABLE IF EXISTS `code`;

CREATE TABLE `code` (
  `CODE_ID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `CATEGORY` varchar(10) DEFAULT NULL,
  `ID` varchar(10) DEFAULT NULL,
  `DESCRIPTION` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`CODE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `code` */

insert  into `code`(`CODE_ID`,`CATEGORY`,`ID`,`DESCRIPTION`) values (00001,'RPTYPE','1','中文电子报告'),(00002,'RPTYPE','2','英文电子报告'),(00003,'RPTYPE','3','中文纸质报告'),(00004,'RPTYPE','4','英文纸质报告'),(00005,'APPSEQ','201208','024'),(00006,'APPSEQ','201209','050'),(00007,NULL,NULL,NULL),(00008,'APPSEQ','201301','0004'),(00009,'P0001','1','是'),(00010,'P0001','2','否'),(00011,'P0002','1','标准服务'),(00012,'P0002','2','加急服务'),(00013,'P0003','1','无危险'),(00014,'P0003','2','腐蚀性'),(00015,'P0003','3','毒性'),(00016,'P0003','4','致癌性'),(00017,'P0003','5','易燃易爆'),(00018,'P0003','6','挥发性'),(00019,'P0003','7','磁性'),(00020,'P0003','99','其他'),(00021,'P0004','1','烘干测试'),(00022,'P0004','2','直接测试'),(00023,'P0005','1','中文电子'),(00024,'P0005','2','中文纸质'),(00025,'P0005','3','英文电子'),(00026,'P0005','4','英文纸质');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `COMPANY_ID` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(40) NOT NULL COMMENT '公司名称',
  `SHORT_NAME` varchar(40) DEFAULT NULL COMMENT '公司简称',
  `COMPANY_NAME_E` varchar(100) NOT NULL COMMENT '公司名（英文）',
  `COMPANY_ADDRESS` varchar(60) DEFAULT NULL COMMENT '公司地址',
  `COMPANY_ADDRESS_E` varchar(100) DEFAULT NULL COMMENT '公司地址（英文）',
  `POST_CODE` char(6) DEFAULT NULL COMMENT '邮编',
  `LINKMAN_IDS` varchar(60) NOT NULL COMMENT '联系人',
  `WEB_ADDRESS` varchar(50) DEFAULT NULL COMMENT '网址',
  `HELP` varchar(10) DEFAULT NULL,
  `SELLER` varchar(40) DEFAULT NULL COMMENT '对应的销售',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`COMPANY_ID`,`COMPANY_NAME`,`SHORT_NAME`,`COMPANY_NAME_E`,`COMPANY_ADDRESS`,`COMPANY_ADDRESS_E`,`POST_CODE`,`LINKMAN_IDS`,`WEB_ADDRESS`,`HELP`,`SELLER`) values (000001,'上海联广认证有限公司','上海联广','Nemko Shanghai Ltd.','上海市徐汇区虹梅路2007号1号楼7楼','7th Floor, Building No.1, No.2007, Hongmei Road, Xuhui District, Shanghai, China','201103','000001,000002','www.nemko.com','shlg',NULL),(000004,'广州汉德工业技术服务有限公司','广州汉德','Guangzhou TUV Industrial Technical Services Company Ltd.','广州市天河区天河路385号太古汇发展项目办公楼1第6层604号房','Room 604, TaiKoo Hui Tower 1, 385 Tianhe Road, Tianhe District, Guangzhou, China','123456','000004,000005','','gzhd',NULL),(000006,'常州市跨越木业有限公司','常州跨越','changshu kuayue wooden co.,ltd','常熟市辛庄镇张桥卫滨工业区','weibang industrial park,zhangqiao xinzhuang town,changshu city,Jiangsu province, China','','000011','','czky',NULL),(000007,'圣戈班研发（上海）有限公司','圣戈班','Saint-Gobain Research (Shanghai) Co.,Ltd.','上海闵行开发区文井路55号','55 Wen jing Road, Minhang Development Zone, Shanghai, China ','','000012','','sgb',NULL),(000008,'南京科远自动化集团股份有限公司','南京科远','NANJING SCIYON AUTOMATION GROUP CO., LTD.','江宁经济技术开发区西门子路27号','No.27 Siemens Road, Jiangning Development Zone, Nanjing, P.R.C.','','000013','','njky',NULL),(000009,'嘉实多深圳有限公司上海浦东分公司','深圳嘉实多','Castrol (Shenzhen) Lim. Co, Shanghai Pudong Branch','上海桂桥路255号E幢','Building E, 205, GuiQiao Road, Shanghai','','000007','','jsd',NULL),(000010,'宁波惠康国际工业有限公司','宁波惠康','Ningbo Hicon International Industry Co., Ltd','宁波杭州湾新区滨海4路55号','No.55, Binhai 4th Road, Hangzhou Bay New Zone, Ningbo, Zhejiang, P.R.China','','000014','','nbsk',NULL),(000011,'宁波赛尔公司义乌办事处','宁波赛尔','','义乌市宗河北路220号','','','000009','','nbse',NULL);

/*Table structure for table `linkman` */

DROP TABLE IF EXISTS `linkman`;

CREATE TABLE `linkman` (
  `LINKMAN_ID` int(6) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `NAME` varchar(40) DEFAULT NULL COMMENT '联系人名字',
  `NAME_E` varchar(60) DEFAULT NULL COMMENT '联系人英文名',
  `POSITION` varchar(40) DEFAULT NULL COMMENT '职位',
  `POSITION_E` varchar(40) DEFAULT NULL COMMENT '职位（英文）',
  `TELEPHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `FAX` varchar(30) DEFAULT NULL COMMENT '传真',
  `MOBILE` varchar(11) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `REF_COM_ID` char(6) NOT NULL COMMENT '所属公司ID',
  PRIMARY KEY (`LINKMAN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `linkman` */

insert  into `linkman`(`LINKMAN_ID`,`NAME`,`NAME_E`,`POSITION`,`POSITION_E`,`TELEPHONE`,`FAX`,`MOBILE`,`EMAIL`,`REF_COM_ID`) values (000001,'许芮茵','Annie Xu','环境业务拓展经理','Business Development Manager of ENV','021-54453132-1987','021-54453215','13916115787','annie.xu@nemko.com','1'),(000002,'孙海芹','Stefanie Sun',NULL,NULL,'021 54453132 -1961','021 54453215','18616816308','stefanie.sun@nemko.com','1'),(000003,'王凌瑶 ','Amanda Wang','项目工程师','Chemical Engineer','021-60567535','021-60567555','13761178134','amanda.wang@dekra-certification.cn','a'),(000004,'梁璐菲','Lucy Liang','','Customer Service Officer','020-38911187-136','020-38911193','13790748835','lliang@tuv-nord.com','4'),(000005,'','Sally Zhang',NULL,NULL,'020-38911187-107',NULL,NULL,NULL,'4'),(000006,'沈德明',NULL,'副总经理','','025-6859 8905','025-6859 8945\r\n','13951705649','shendm@sciyon.com','b'),(000007,'钱晓平   ',NULL,NULL,NULL,NULL,NULL,'18621616620',NULL,'c'),(000008,'夏莹',NULL,NULL,NULL,NULL,NULL,'15988579979',NULL,'d'),(000009,'','skye wen',NULL,NULL,'0579-85096045\r\n','0579-85096011\r\n','13777900853','sales@sellersunion.com\r\n','e'),(000010,'','Demy',NULL,NULL,'0574-28883667\r\n',NULL,NULL,NULL,'f'),(000011,'范建新',NULL,NULL,NULL,'0512-52417015\r\n','0512-52417220','13913102008','fjx869@vip.com\r\n','g'),(000012,'贾小华',NULL,NULL,NULL,'021-5475-5669\r\n','021-5475-4283\r\n','13764652906','xiaohua.jia@saint-gobain.com\r\n','l'),(000013,'蒋国渊  乔熙',NULL,NULL,NULL,'025-6859 8968\r\n',NULL,NULL,NULL,'l'),(000014,'毛工',NULL,'技术开发部',NULL,'',NULL,'13705849446','maobinjun@163.com\r\n','l');

/*Table structure for table `sample` */

DROP TABLE IF EXISTS `sample`;

CREATE TABLE `sample` (
  `SAMPLE_ID` int(9) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `NAME` varchar(80) DEFAULT NULL COMMENT '样品名称',
  `QUANTITY` int(11) DEFAULT NULL COMMENT '样品数量',
  `EXPRESS_NO` varchar(30) DEFAULT NULL COMMENT '样品快递单号',
  `MODEL_NO` varchar(60) DEFAULT NULL COMMENT '样品型号',
  `MAY_COVER_MODEL` varchar(60) DEFAULT NULL COMMENT '可覆盖型号',
  `MAIN_METERIAL` varchar(40) DEFAULT NULL COMMENT '样品主要材料',
  `LOT_NO` varchar(40) DEFAULT NULL COMMENT '样品批号',
  `REVEICED_DT` datetime DEFAULT NULL COMMENT '接样日期',
  `BUYER` varchar(60) DEFAULT NULL COMMENT '买家',
  `SUPPLIER` varchar(60) DEFAULT NULL COMMENT '供应商',
  `ISLIQUID` char(2) DEFAULT NULL COMMENT '样品是否为液体',
  `LIQUID_METHOD` char(2) DEFAULT NULL COMMENT '液体样品处理（1:烘干测试，2:直接测试）',
  `PHOTO_REQURIMENT` varchar(200) DEFAULT NULL COMMENT '拍照要求',
  `SPECIFIC_REQURIMENT` varchar(200) DEFAULT NULL COMMENT '特殊要求',
  `CONCENTRATION_GT1` char(1) DEFAULT 'Y' COMMENT '样品浓度>1%(Y/N)',
  `DANGEROUS_CODE` varchar(50) DEFAULT NULL COMMENT '样品危险性',
  `DANGEROUS_REMARKS` varchar(200) DEFAULT NULL COMMENT '样品危险性备注',
  PRIMARY KEY (`SAMPLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `sample` */

insert  into `sample`(`SAMPLE_ID`,`NAME`,`QUANTITY`,`EXPRESS_NO`,`MODEL_NO`,`MAY_COVER_MODEL`,`MAIN_METERIAL`,`LOT_NO`,`REVEICED_DT`,`BUYER`,`SUPPLIER`,`ISLIQUID`,`LIQUID_METHOD`,`PHOTO_REQURIMENT`,`SPECIFIC_REQURIMENT`,`CONCENTRATION_GT1`,`DANGEROUS_CODE`,`DANGEROUS_REMARKS`) values (000000001,'望远镜',2,'1231313','CX1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,'Y','2,3,5',NULL),(000000002,'淋浴器',4,'6848','SOIOI','a','b','c',NULL,'e','f',NULL,'1','1','1','N','2,5,6','1'),(000000003,'保温杯盖',12,'56456','IIIk',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,'Y','3,4',NULL),(000000006,'保温杯盖保',NULL,NULL,'123','999','9','999',NULL,'99','9',NULL,'1',NULL,NULL,'N','3,4',''),(000000007,'保温杯盖保温杯盖',NULL,NULL,'123','','','',NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,''),(000000008,'保温杯盖保温杯盖',NULL,NULL,'123','','','',NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,''),(000000009,'保温杯盖保温杯盖',NULL,NULL,'123','','','',NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,''),(000000010,'haha',NULL,NULL,'ha','ha','ha','ha',NULL,'ha','ha',NULL,'1',NULL,NULL,'N','3,4,5,8',''),(000000011,'保温杯盖保温杯盖',NULL,NULL,'abcabc','abc','abc','abc',NULL,'abc','abc',NULL,'1',NULL,NULL,NULL,'1,3,5,7,99','huiuu');

/*Table structure for table `testitems` */

DROP TABLE IF EXISTS `testitems`;

CREATE TABLE `testitems` (
  `TID` int(7) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `ITEM_ID` varchar(10) DEFAULT NULL,
  `ITEM_DESC` varchar(100) DEFAULT NULL,
  `SUB_ITEM_ID1` varchar(10) DEFAULT NULL,
  `SUB_ITEM_DESC1` varchar(100) DEFAULT NULL,
  `SUB_ITEM_ID2` varchar(10) DEFAULT NULL,
  `SUB_ITEM_DESC2` varchar(100) DEFAULT NULL,
  `SUB_ITEM_ID3` varchar(10) DEFAULT NULL,
  `SUB_ITEM_DESC3` varchar(100) DEFAULT NULL,
  `STANDARD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `testitems` */

insert  into `testitems`(`TID`,`ITEM_ID`,`ITEM_DESC`,`SUB_ITEM_ID1`,`SUB_ITEM_DESC1`,`SUB_ITEM_ID2`,`SUB_ITEM_DESC2`,`SUB_ITEM_ID3`,`SUB_ITEM_DESC3`,`STANDARD`) values (0000001,'01','欧盟RoHS测试','0101','Cd 镉',NULL,NULL,NULL,NULL,NULL),(0000002,'01','欧盟RoHS测试','0102','Hg 汞',NULL,NULL,NULL,NULL,NULL),(0000003,'01','欧盟RoHS测试','0103','Pb铅',NULL,NULL,NULL,NULL,NULL),(0000004,'01','欧盟RoHS测试','0104','CrVI六价铬',NULL,NULL,NULL,NULL,NULL),(0000005,'01','欧盟RoHS测试','0105','PBB多溴联苯',NULL,NULL,NULL,NULL,NULL),(0000006,'01','欧盟RoHS测试','0106','PBDE多溴二苯醚',NULL,NULL,NULL,NULL,NULL),(0000007,'01','欧盟RoHS测试','0107','RoHS2.0','010701','HBCDD','01070101','IEC62321 ',NULL),(0000008,'01','欧盟RoHS测试','0107','RoHS2.0','010701','HBCDD','01070102','EPA3550',NULL),(0000009,'01','欧盟RoHS测试','0107','RoHS2.0','010702','DEHP/DBP/BBP EN 14372',NULL,NULL,NULL),(0000010,'02','Phthalates 邻苯二甲酸酯类 EN14372','0201','6P',NULL,NULL,NULL,NULL,NULL),(0000011,'02','Phthalates 邻苯二甲酸酯类 EN14372','0202','17P',NULL,NULL,NULL,NULL,NULL),(0000012,'03','EACH-SVHC 84项 高度关注物质',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(0000013,'04','包装材料 EPA3052/EPA3060/EPA3050/ISO17075','0401','PPW 94/62/EEC',NULL,NULL,NULL,NULL,NULL),(0000014,'04','包装材料 EPA3052/EPA3060/EPA3050/ISO17075','0402','TPCH/CONEG',NULL,NULL,NULL,NULL,NULL),(0000015,'05','电池','0501','2006/66/EC 有害物质含量',NULL,NULL,NULL,NULL,NULL),(0000016,'05','电池','0502','2006/66/EC 电池标识评估',NULL,NULL,NULL,NULL,NULL),(0000017,'05','电池','0503','US Law 104-142',NULL,NULL,NULL,NULL,NULL),(0000018,'06','Ni镍','0601','总含量 EPA3052/EPA3050',NULL,NULL,NULL,NULL,NULL),(0000019,'06','Ni镍','0602','释放量','060201','无镀层EN1811',NULL,NULL,NULL),(0000020,'06','Ni镍','0602','释放量','060202','有镀层EN12472+ EN1811',NULL,NULL,NULL),(0000021,'06','Ni镍','0602',NULL,NULL,NULL,NULL,NULL,NULL),(0000022,'07','全氟','0701','PFOS 全氟辛烷磺酰基化合物 EPA 3550',NULL,NULL,NULL,NULL,NULL),(0000023,'07','全氟','0702','PFOA 全氟辛酸盐及酯类 EPA3550 ',NULL,NULL,NULL,NULL,NULL),(0000024,'08','Halogen 卤素EN14582','0801','F氟',NULL,NULL,NULL,NULL,NULL),(0000025,'08','Halogen 卤素EN14582','0802','Cl氯',NULL,NULL,NULL,NULL,NULL),(0000026,'08','Halogen 卤素EN14582','0803','Br溴',NULL,NULL,NULL,NULL,NULL),(0000027,'08','Halogen 卤素EN14582','0804','I碘',NULL,NULL,NULL,NULL,NULL),(0000028,'09','TBBP-A 四溴双酚A  EPA3550',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(0000029,'10','PAHs 多环芳香烃','1001','16P ',NULL,NULL,NULL,NULL,NULL),(0000030,'10','PAHs 多环芳香烃','1002','18P ZEK 01.4-08',NULL,NULL,NULL,NULL,NULL),(0000031,'11','SCCP 短链氯化石蜡 EPA 3550 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(0000032,'12',' DMF 富马酸二甲酯 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(0000033,'13','Formaldehyde 甲醛',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(0000034,'14','CPSIA ','1401','涂层铅 CPSC-CH-E1003/ASTM 1645/ASTM1613',NULL,NULL,NULL,NULL,NULL),(0000035,'14','CPSIA ','1402','基材铅 CPSC-CH-E1001/CPSC-CH-E1002 ',NULL,NULL,NULL,NULL,NULL),(0000036,'14','CPSIA ','1403','邻苯二甲酸酯 Phthalates CPSC-CH-C1001 ',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `USER_ID` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(60) NOT NULL,
  `REAL_NAME` varchar(60) DEFAULT NULL,
  `MOBILE` char(11) DEFAULT NULL,
  `EMAIL` varchar(40) DEFAULT NULL,
  `ENAME` varchar(25) DEFAULT NULL,
  `PASSWORD` varchar(80) NOT NULL,
  `GENDER` char(1) DEFAULT NULL,
  `ROLES` varchar(25) DEFAULT NULL COMMENT '1:admin,2:cs,3:rept,4:seller',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`USER_ID`,`USER_NAME`,`REAL_NAME`,`MOBILE`,`EMAIL`,`ENAME`,`PASSWORD`,`GENDER`,`ROLES`) values (00001,'cary','chb','13004508251','chenxun101@126.com','cary','c4ca4238a0b923820dcc509a6f75849b','1','1'),(00002,'tina','lt',NULL,NULL,'tina','c4ca4238a0b923820dcc509a6f75849b','2','2'),(00003,'admin','admin',NULL,NULL,'admin','c4ca4238a0b923820dcc509a6f75849b','1','1'),(00004,'seller','seller',NULL,'seller@stq.com','seller','c4ca4238a0b923820dcc509a6f75849b','1','4');

/*Table structure for table `workday` */

DROP TABLE IF EXISTS `workday`;

CREATE TABLE `workday` (
  `NATURALDAY` varchar(10) NOT NULL,
  `ISWORKDAY` char(1) DEFAULT NULL,
  PRIMARY KEY (`NATURALDAY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `workday` */

insert  into `workday`(`NATURALDAY`,`ISWORKDAY`) values ('2012-12-01','1'),('2012-12-02','1'),('2012-12-03','1'),('2012-12-04','1'),('2012-12-05','1'),('2012-12-06','1'),('2012-12-07','1'),('2012-12-08','0'),('2012-12-09','0'),('2012-12-10','1'),('2012-12-11','1'),('2012-12-12','1'),('2012-12-13','1'),('2012-12-14','1'),('2012-12-15','0'),('2012-12-16','0'),('2012-12-17','1'),('2012-12-18','1'),('2012-12-19','1'),('2012-12-20','1'),('2012-12-21','1'),('2012-12-22','0'),('2012-12-23','0'),('2012-12-24','1'),('2012-12-25','1'),('2012-12-26','1'),('2012-12-27','1'),('2012-12-28','1'),('2012-12-29','0'),('2012-12-30','0'),('2012-12-31','1'),('2013-01-01','1'),('2013-01-02','1'),('2013-01-03','1'),('2013-01-04','1'),('2013-01-05','0'),('2013-01-06','0'),('2013-01-07','1'),('2013-01-08','1'),('2013-01-09','1'),('2013-01-10','1'),('2013-01-11','1'),('2013-01-12','0'),('2013-01-13','0'),('2013-01-14','1'),('2013-01-15','1'),('2013-01-16','1'),('2013-01-17','1'),('2013-01-18','1'),('2013-01-19','0'),('2013-01-20','0'),('2013-01-21','1'),('2013-01-22','1'),('2013-01-23','1'),('2013-01-24','1'),('2013-01-25','1'),('2013-01-26','0'),('2013-01-27','0'),('2013-01-28','1'),('2013-01-29','1'),('2013-01-30','1'),('2013-01-31','1'),('2013-02-01','1'),('2013-02-02','0'),('2013-02-03','0'),('2013-02-04','1'),('2013-02-05','1'),('2013-02-06','1'),('2013-02-07','1'),('2013-02-08','1'),('2013-02-09','0'),('2013-02-10','0'),('2013-02-11','1'),('2013-02-12','1'),('2013-02-13','1'),('2013-02-14','1'),('2013-02-15','1'),('2013-02-16','0'),('2013-02-17','0'),('2013-02-18','1'),('2013-02-19','1'),('2013-02-20','1'),('2013-02-21','1'),('2013-02-22','1'),('2013-02-23','0'),('2013-02-24','0'),('2013-02-25','1'),('2013-02-26','1'),('2013-02-27','1'),('2013-02-28','1'),('2013-03-01','1'),('2013-03-02','0'),('2013-03-03','0'),('2013-03-04','1'),('2013-03-05','1'),('2013-03-06','1'),('2013-03-07','0'),('2013-03-08','1'),('2013-03-09','0'),('2013-03-10','0'),('2013-03-11','1'),('2013-03-12','1'),('2013-03-13','1'),('2013-03-14','1'),('2013-03-15','0'),('2013-03-16','0'),('2013-03-17','0'),('2013-03-18','1'),('2013-03-19','1'),('2013-03-20','1'),('2013-03-21','1'),('2013-03-22','1'),('2013-03-23','0'),('2013-03-24','0'),('2013-03-25','1'),('2013-03-26','1'),('2013-03-27','1'),('2013-03-28','1'),('2013-03-29','1'),('2013-03-30','0'),('2013-03-31','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
