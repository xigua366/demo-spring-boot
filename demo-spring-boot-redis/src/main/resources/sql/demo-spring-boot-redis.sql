/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : demo-spring-boot-redis

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/02/2021 17:43:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category` varchar(11) DEFAULT NULL COMMENT '优惠卷类型[NEW_USER注册赠券，TASK任务卷，PROMOTION促销劵]',
  `publish` varchar(11) DEFAULT NULL COMMENT '发布状态, PUBLISH发布，DRAFT草稿，OFFLINE下线',
  `coupon_img` varchar(524) DEFAULT NULL COMMENT '优惠券图片',
  `coupon_title` varchar(128) DEFAULT NULL COMMENT '优惠券标题',
  `price` decimal(16,2) DEFAULT NULL COMMENT '抵扣价格',
  `user_limit` int(11) DEFAULT NULL COMMENT '每人限制张数',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券开始有效时间',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券失效时间',
  `publish_count` int(11) DEFAULT NULL COMMENT '优惠券总量',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `create_time` datetime DEFAULT NULL,
  `condition_price` decimal(16,2) DEFAULT NULL COMMENT '满多少才可以使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
INSERT INTO `coupon` VALUES (18, 'NEW_USER', 'PUBLISH', 'https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png', '永久有效-新人注册-0元满减-5元抵扣劵-限领取2张-不可叠加使用', 5.00, 2, '2000-01-01 00:00:00', '2099-01-29 00:00:00', 100000000, 99999991, '2020-12-26 16:33:02', 0.00);
INSERT INTO `coupon` VALUES (19, 'PROMOTION', 'PUBLISH', 'https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png', '有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用', 5.00, 2, '2000-01-29 00:00:00', '2025-01-29 00:00:00', 10, 9, '2020-12-26 16:33:03', 20.00);
INSERT INTO `coupon` VALUES (20, 'PROMOTION', 'PUBLISH', 'https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png', '有效中-20年8月到21年9月-商品id1-8.8元抵扣劵-限领取2张-不可叠加使用', 8.80, 2, '2020-08-01 00:00:00', '2021-09-29 00:00:00', 100, 96, '2020-12-26 16:33:03', 0.00);
INSERT INTO `coupon` VALUES (21, 'PROMOTION', 'PUBLISH', 'https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png', '有效中-20年8月到21年9月-商品id2-9.9元抵扣劵-限领取2张-可叠加使用', 8.80, 2, '2020-08-01 00:00:00', '2021-09-29 00:00:00', 100, 96, '2020-12-26 16:33:03', 0.00);
INSERT INTO `coupon` VALUES (22, 'PROMOTION', 'PUBLISH', 'https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png', '过期-20年8月到20年9月-商品id3-6元抵扣劵-限领取1张-可叠加使用', 6.00, 1, '2020-08-01 00:00:00', '2020-09-29 00:00:00', 100, 100, '2020-12-26 16:33:03', 0.00);
COMMIT;

-- ----------------------------
-- Table structure for coupon_record
-- ----------------------------
DROP TABLE IF EXISTS `coupon_record`;
CREATE TABLE `coupon_record` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(11) DEFAULT NULL COMMENT '优惠券id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间获得时间',
  `use_state` varchar(32) DEFAULT NULL COMMENT '使用状态  可用 NEW,已使用USED,过期 EXPIRED;',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `coupon_title` varchar(128) DEFAULT NULL COMMENT '优惠券标题',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单id',
  `price` decimal(16,2) DEFAULT NULL COMMENT '抵扣价格',
  `condition_price` decimal(16,2) DEFAULT NULL COMMENT '满多少才可以使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of coupon_record
-- ----------------------------
BEGIN;
INSERT INTO `coupon_record` VALUES (4, 19, '2021-02-25 03:06:07', 'NEW', 1, NULL, '有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用', '2000-01-29 00:00:00', '2025-01-29 00:00:00', NULL, 5.00, 20.00);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '昵称',
  `pwd` varchar(124) DEFAULT NULL COMMENT '密码',
  `head_img` varchar(524) DEFAULT NULL COMMENT '头像',
  `phone` varchar(64) DEFAULT '' COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'yx', 'E10ADC3949BA59ABBE56E057F20F883E', 'https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg', '13434545565', '2021-02-25 00:56:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
