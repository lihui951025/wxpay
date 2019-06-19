/*
SQLyog Ultimate v11.22 (64 bit)
MySQL - 5.7.12-log : Database - vx_pay
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vx_pay` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vx_pay`;

/*Table structure for table `vx_commodity` */

DROP TABLE IF EXISTS `vx_commodity`;

CREATE TABLE `vx_commodity` (
  `id` varchar(32) NOT NULL COMMENT '商品id',
  `title` varchar(64) NOT NULL COMMENT '商品标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `online` int(11) DEFAULT NULL,
  `point` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vx_commodity` */

insert  into `vx_commodity`(`id`,`title`,`summary`,`price`,`createTime`,`online`,`point`) values ('1','信息技术','和牛逼',1,'2019-06-18 14:41:12',1,4.7),('4b2bc80d439e41bdb576b9c5394cde87','自然与科学','学了会飞',1,'2019-06-18 06:59:38',1,3.5),('801c905aef8d47d2a9817f014b6f8147','自然与科学1','学了会飞',1,'2019-06-18 06:59:43',1,3.5),('bc8153ee0bfa4262af04e8fde750eda6','自然与科学12','学了会飞',1,'2019-06-18 06:59:45',1,3.5);

/*Table structure for table `vx_order` */

DROP TABLE IF EXISTS `vx_order`;

CREATE TABLE `vx_order` (
  `id` varchar(32) NOT NULL COMMENT '订单id',
  `outTradeNo` varchar(255) DEFAULT NULL COMMENT '支付单号',
  `state` int(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `notifyTime` datetime DEFAULT NULL COMMENT '通知时间',
  `totalFee` int(32) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `videoId` varchar(32) DEFAULT NULL COMMENT '视频id',
  `videoTitle` varchar(255) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL COMMENT '用户id',
  `ip` varchar(255) DEFAULT NULL,
  `del` int(1) DEFAULT NULL COMMENT '逻辑删除1，未删除0',
  PRIMARY KEY (`id`),
  KEY `fm_id` (`userId`),
  KEY `fm_comm_id` (`videoId`),
  CONSTRAINT `fm_comm_id` FOREIGN KEY (`videoId`) REFERENCES `vx_commodity` (`id`),
  CONSTRAINT `fm_id` FOREIGN KEY (`userId`) REFERENCES `vx_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vx_order` */

insert  into `vx_order`(`id`,`outTradeNo`,`state`,`createTime`,`notifyTime`,`totalFee`,`nickname`,`videoId`,`videoTitle`,`userId`,`ip`,`del`) values ('0b8bb7121362441f98bd06116d6002b5','1b6e26e99c6a4794b7c06fb825920465',0,'2019-06-18 09:51:33',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('0e2ff75a908346539a7a48d03e268ccb','dca10d3fdafc4d7ab3187353a567d836',0,'2019-06-18 10:22:23',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('1','1',0,'2019-06-18 15:06:44','2019-06-18 15:27:40',1,'阿萨大大萨达','1','信息技术','1','192.168.33.183',0),('168e75bf9ada47b8808ae78ed7f22525','ee9834ae146f4ef8927fc395519b4f37',0,'2019-06-18 10:04:11',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('1aafb62a891c414594d2c8b9d60911ba','939dbac8dccf434190aabf451e9d245b',0,'2019-06-18 09:55:42',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('1e9b41166b474871a518a2f13cefd072','9b4af349af3047d5aa7cade9cbc3d0cb',0,'2019-06-18 10:21:32',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('2343fc83381f45ab86a0d1724631c589','5dd5f2b98050445481928d32f3bf2e27',0,'2019-06-18 10:12:54',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('36512245a6434bdd866bfa42f0c99f6c','0d8461ac7d474aa2bf8f9ceaac1b4edf',0,'2019-06-18 10:20:19',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('527d6bef733b4699b188b6416dfd3b65','9c13a430e8344879bdaa8437386bd432',0,'2019-06-18 10:46:27',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('529061e67b3345029a45a8948cc1ad10','f93219ef7c7f466da64b1ade0e024a0c',0,'2019-06-18 09:15:51',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('73a2efe944a64441add2e2e91f460c0d','b4d191523e6140dda7f82f7f5d905aa1',0,'2019-06-18 10:10:42',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('7ee47a1d55d84fea9c0d6fe29803de89','755459710deb4c69b23cc0c3003df8f0',0,'2019-06-18 10:23:08',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('84d1152d00f1433d9470a48d11f4c1ff','55fecba7d0f742d4a8df15a28780b469',0,'2019-06-18 10:12:54',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('84ddb853be45463c8fc907089b053837','69c74aa85f614c43bdacb918a9b5af2d',0,'2019-06-18 09:09:34',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('8ff84b7d78bb4b35a9ada0c95b3f6cc3','e716ec7166cb426d86d1b1038bc7b8d7',0,'2019-06-18 10:34:51',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('a05ada31ff364c7c8fed440e56e53428','771b8884538c4f96a3d03b7dee4764f2',0,'2019-06-18 09:07:49',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('a322072670614a468661ac0e40683f0d','6122ebea377a44958220b338301dde5f',0,'2019-06-18 09:57:53',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('d8158101cbde4b4188e0111a52e21bca','7cbedce2cd9a4e66a667786a552442d2',0,'2019-06-18 10:23:08',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('e6f1b4facdf94d6ba564d973d2049658','c14ce76c076a4c989eec9226408380e5',1,'2019-06-18 10:37:49','2019-06-18 18:38:03',1,'lih','1','信息技术','1','192.168.31.183',0),('eef2f3b3b30a47b89556ec542f123a2c','1e32e17e946148c1ad6af50e909f4e42',0,'2019-06-18 10:11:11',NULL,1,'lih','1','信息技术','1','192.168.31.183',0),('f3512afbc11b4170800c01c3bc0b0bb4','915ddfac226a4c6582e75911cade6a3d',1,'2019-06-18 10:32:21','2019-06-18 18:32:42',1,'lih','1','信息技术','1','192.168.31.183',0);

/*Table structure for table `vx_user` */

DROP TABLE IF EXISTS `vx_user`;

CREATE TABLE `vx_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT '名字',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vx_user` */

insert  into `vx_user`(`id`,`name`,`phone`,`sex`,`city`,`createTime`) values ('1','lih','130075224659','男','河南郑州','2019-06-18 13:49:37'),('2059300a605547e0942fadcadf1683e8','嘻嘻','13007522469','男','河南郑州','2019-06-18 06:29:13'),('20ec4e9471634d09bbd2fb4976b6ca8b','嘻嘻3','13007522469','男','河南郑州','2019-06-18 06:30:23'),('3476a9d1a4374f3aaf6ce8c6c2a23307','嘻嘻4','13007522469','男','河南郑州','2019-06-18 06:30:25'),('45ab60d27cb64e65ade6e90faf59618d','嘻嘻1','13007522469','男','河南郑州','2019-06-18 06:30:18'),('4914c049881a44f9bf39aea8c524e86d','嘻嘻2','13007522469','男','河南郑州','2019-06-18 06:30:21');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
