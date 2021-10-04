/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 04/10/2021 10:54:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `wait1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1005, '系统管理员', '123456', '18586050000', '2021-09-01', '1', 'admin', '3007', NULL);
INSERT INTO `admin` VALUES (1006, '酒店管理员', '123456', '18586050000', '2021-09-01', '1', 'hotel', '3007', NULL);

-- ----------------------------
-- Table structure for estimate
-- ----------------------------
DROP TABLE IF EXISTS `estimate`;
CREATE TABLE `estimate`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '投诉内容',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `hotel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '酒店',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单',
  `num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评分',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `wait1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7022 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of estimate
-- ----------------------------
INSERT INTO `estimate` VALUES (7017, '真不错', '2067', '2021-09-27 13:36:04', '3007', '6040', '4', '1', NULL, NULL, NULL);
INSERT INTO `estimate` VALUES (7018, '不好', '2067', '2021-09-27 13:52:24', '3007', '6041', '5', '1', NULL, NULL, NULL);
INSERT INTO `estimate` VALUES (7019, '很不错ho', '2067', '2021-09-28 09:53:12', '3007', '6042', '4', '1', NULL, NULL, NULL);
INSERT INTO `estimate` VALUES (7020, '很不爽', '2067', '2021-09-28 11:04:58', '3007', '6043', '4', '1', NULL, NULL, NULL);
INSERT INTO `estimate` VALUES (7021, '很好啊', '2067', '2021-09-28 11:36:44', '3007', '6044', '4', '1', NULL, NULL, NULL);
INSERT INTO `estimate` VALUES (7022, '不错', '2067', '2021-10-02 20:55:27', '3007', '6045', '4', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经度',
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '纬度',
  `assignmark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评分',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级',
  `open_date` datetime(0) NULL DEFAULT NULL COMMENT '开业时间',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '照片',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `wait1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (3007, '盛乐大酒店', '内蒙古自治区呼和浩特市赛罕区锡林南路', '40.816146', '111.678921', '4', '豪华型', '2021-09-03 09:27:36', '1', 'b7251048-cc16-4a3c-bf51-c6577efad5fa.png', '地理位置优越', ' ', '青岛市', NULL);
INSERT INTO `hotel` VALUES (3008, '内蒙古饭店11111111', '内蒙古自治区呼和浩特市赛罕区锡林南路', '41', '111', '10', '111', '2021-09-22 15:44:12', '0', 'c3d462a4-0e76-4c41-9e7c-839dbcc1fe5a.png', '111', ',', '呼和浩特市', NULL);
INSERT INTO `hotel` VALUES (30009, '青软酒店', '山东省青岛市', '36.274275', '120.261826', '10', '豪华型', '2021-09-27 10:54:18', '1', '6821f797-d01d-4583-ba23-a7828b874a76.png', '从这个角度来看， 一般来讲，我们都必须务必慎重的考虑考虑。 问题的关键究竟为何？ 一般来说， 问题的关键究竟为何？ 这样看来， 所谓青软酒店，关键是青软酒店需要如何写。 既然如此， 本人也是经过了深思熟虑，在每个日日夜夜思考这个问题。 每个人都不得不面对这些问题。 在面对这种问题时， 青软酒店因何而发生？ 青软酒店的发生，到底需要如何做到，不青软酒店的发生，又会如何产生。 伏尔泰曾经提到过，坚持意志伟大的事业需要始终不渝的精神。这不禁令我深思。', ',', '青岛市', NULL);

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间 ',
  `people` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住人数',
  `room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预订房间',
  `day_long` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入住时长',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单状态',
  `price` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总价',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `wait1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1',
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6047 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES (6019, '2041', '2021-09-23 15:16:02', '2', '5010', '1', '2021-09-01 00:00:00', '2021-09-01 00:00:00', '1', '340', '1', '1', NULL, NULL);
INSERT INTO `note` VALUES (6034, '1001', '2021-09-23 23:00:20', NULL, '5010', '2', '2021-09-01 00:00:00', '2021-09-02 00:00:00', '0', '680', '0', '1', NULL, NULL);
INSERT INTO `note` VALUES (6036, '1001', '2021-09-23 23:26:01', NULL, '5010', '3', '2021-09-15 00:00:00', '2021-09-17 00:00:00', '2', '1020', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6037, '1001', '2021-09-23 23:27:44', NULL, '5012', '3', '2021-09-15 00:00:00', '2021-09-17 00:00:00', '2', '960', '1', '1', NULL, NULL);
INSERT INTO `note` VALUES (6038, '1001', '2021-09-24 20:09:11', NULL, '5011', '11', '2021-09-15 00:00:00', '2021-09-25 00:00:00', '2', '3740', '1', '1', NULL, NULL);
INSERT INTO `note` VALUES (6039, '1001', '2021-09-26 18:23:17', '1', '5010', '9', '2021-09-15 00:00:00', '2021-09-23 00:00:00', '0', '3060', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6040, '2067', '2021-09-27 13:28:29', NULL, '5010', '2', '2021-09-01 00:00:00', '2021-09-02 00:00:00', '2', '680', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6041, '2067', '2021-09-27 13:50:47', NULL, '5010', '2', '2021-09-01 00:00:00', '2021-09-02 00:00:00', '1', '680', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6042, '2067', '2021-09-28 09:46:16', NULL, '5010', '2', '2021-09-01 00:00:00', '2021-09-02 00:00:00', '2', '680', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6043, '2067', '2021-09-28 11:01:06', NULL, '5010', '3', '2021-09-01 00:00:00', '2021-09-03 00:00:00', '2', '1020', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6044, '2067', '2021-09-28 11:34:39', NULL, '5010', '10', '2021-09-08 00:00:00', '2021-09-17 00:00:00', '2', '3400', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6045, '2067', '2021-10-02 18:37:16', NULL, '5010', '2', '2021-10-21 00:00:00', '2021-10-22 00:00:00', '2', '680', '1', '0', NULL, NULL);
INSERT INTO `note` VALUES (6046, '2067', '2021-10-02 20:52:30', NULL, '5010', '2', '2021-10-01 00:00:00', '2021-10-02 00:00:00', '1', '680', '1', '1', NULL, NULL);
INSERT INTO `note` VALUES (6047, '2067', '2021-10-02 20:53:12', NULL, '5010', '2', '2021-10-03 00:00:00', '2021-10-04 00:00:00', '0', '680', '1', '1', NULL, NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `room_style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房间类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '楼层',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `wait1` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (5010, '4006', '301', '1', '1', ',,2021-10-21,,2021-10-22,,2021-10-01,,2021-10-02,,2021-10-03,,2021-10-04,', NULL, NULL);
INSERT INTO `room` VALUES (5011, '4006', '301', '1', '1', ',  ', NULL, NULL);
INSERT INTO `room` VALUES (5012, '4007', '401', '1', '1', ',', NULL, NULL);

-- ----------------------------
-- Table structure for room_type
-- ----------------------------
DROP TABLE IF EXISTS `room_type`;
CREATE TABLE `room_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hotel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属酒店',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房间名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `windows` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有窗',
  `bed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '床型',
  `people` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人数',
  `layer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级',
  `wifi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有WiFi',
  `note` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '详情',
  `price` int(0) NULL DEFAULT NULL COMMENT '价格',
  `wait1` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_type
-- ----------------------------
INSERT INTO `room_type` VALUES (4006, '3007', '大床房', '0b02c241-03e8-4751-8d1a-f7525378894b.png', '有', '2*单人床', '2人', '3-4', '有', '可以住两个人', 340, ' ', ' ', NULL, '1');
INSERT INTO `room_type` VALUES (4007, '3007', '标间', 'a1de15b6-efaf-4ecf-92c7-2a0cc162e402.png', '有', '单人床', '2', '1-2', '有', '', 320, ' ', ' ', NULL, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `vip` int(0) NULL DEFAULT NULL COMMENT '积分',
  `card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `is_out` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除',
  `open_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信登录',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `wait1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wait3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2067 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
