/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : localhost:3306
 Source Schema         : shoppingssm

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : 65001

 Date: 02/07/2020 15:38:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_attache
-- ----------------------------
DROP TABLE IF EXISTS `sys_attache`;
CREATE TABLE `sys_attache`  (
  `attache_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`attache_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_attache
-- ----------------------------
INSERT INTO `sys_attache` VALUES (1, NULL, 'upload/2020-07-02/000caa9a-77a5-4f3c-923a-aeefab24eab0合照 (1).JPG', NULL, 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `father_dept_id` int(11) NULL DEFAULT NULL,
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `create_staff_id` int(11) NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '经理', '001', 1, '处理', '2020-06-24 15:31:59', 1, 0);
INSERT INTO `sys_dept` VALUES (2, '456', '002', 2, '123', '2020-06-24 15:32:25', 2, 0);
INSERT INTO `sys_dept` VALUES (3, '研发部', '003', 1, '研发', '2020-06-26 17:08:40', 1, 0);
INSERT INTO `sys_dept` VALUES (4, '123', '123', 3, '123', '2020-06-28 08:13:04', 1, 1);
INSERT INTO `sys_dept` VALUES (5, '人事部', '159', 4, '好人', '2020-06-28 08:23:28', 1, 1);

-- ----------------------------
-- Table structure for sys_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_item`;
CREATE TABLE `sys_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_item
-- ----------------------------
INSERT INTO `sys_item` VALUES (24, 1, 1, 10, 5);
INSERT INTO `sys_item` VALUES (25, 2, 1, 10, 5);
INSERT INTO `sys_item` VALUES (26, 1, 1, 11, 5);
INSERT INTO `sys_item` VALUES (27, 2, 1, 11, 5);
INSERT INTO `sys_item` VALUES (28, 1, 1, 12, 5);
INSERT INTO `sys_item` VALUES (29, 2, 1, 12, 5);
INSERT INTO `sys_item` VALUES (30, 3, 1, 12, 5);
INSERT INTO `sys_item` VALUES (31, 1, 5, 13, 25);
INSERT INTO `sys_item` VALUES (32, 2, 1, 13, 5);
INSERT INTO `sys_item` VALUES (33, 1, 1, 14, 5);
INSERT INTO `sys_item` VALUES (34, 2, 1, 14, 5);
INSERT INTO `sys_item` VALUES (35, 3, 1, 14, 5);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES (12, 'DD20200702000012', 15, 1);
INSERT INTO `sys_order` VALUES (13, 'DD20200702000013', 30, 1);
INSERT INTO `sys_order` VALUES (14, 'DD20200702000014', 15, 2);

-- ----------------------------
-- Table structure for sys_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_type_id` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_product
-- ----------------------------
INSERT INTO `sys_product` VALUES (1, '001', '笔芯', 5, '/images/backimage.jpg', 3, 1);
INSERT INTO `sys_product` VALUES (2, '002', '可乐', 5, '/images/backimage.jpg', 1, 1);
INSERT INTO `sys_product` VALUES (3, '003', '薯片', 5, '/images/backimage.jpg', 1, 1);
INSERT INTO `sys_product` VALUES (4, '004', '白菜', 5, '/images/backimage.jpg', 2, 1);
INSERT INTO `sys_product` VALUES (5, '005', '辣条', 5, '/images/backimage.jpg', 1, 1);
INSERT INTO `sys_product` VALUES (6, '006', '雪糕', 5, '/images/backimage.jpg', 1, 1);
INSERT INTO `sys_product` VALUES (7, '007', '面包', 5, '/images/backimage.jpg', 1, 1);
INSERT INTO `sys_product` VALUES (10, '008', '毛巾', 5, '/images/backimage.jpg', 8, 0);
INSERT INTO `sys_product` VALUES (11, '009', '内裤', 5, '/images/backimage.jpg', 8, 0);

-- ----------------------------
-- Table structure for sys_product_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_product_type`;
CREATE TABLE `sys_product_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_product_type
-- ----------------------------
INSERT INTO `sys_product_type` VALUES (1, '食品', 1);
INSERT INTO `sys_product_type` VALUES (2, '玩具', 1);
INSERT INTO `sys_product_type` VALUES (3, '图书', 1);
INSERT INTO `sys_product_type` VALUES (4, '电子产品', 0);
INSERT INTO `sys_product_type` VALUES (5, '母婴用品', 0);
INSERT INTO `sys_product_type` VALUES (6, '地方特产', 1);
INSERT INTO `sys_product_type` VALUES (7, '水果生鲜', 1);
INSERT INTO `sys_product_type` VALUES (8, '服饰', 1);
INSERT INTO `sys_product_type` VALUES (9, '面包', 1);
INSERT INTO `sys_product_type` VALUES (10, '炊具碗筷', 0);
INSERT INTO `sys_product_type` VALUES (11, '虚拟货币', 0);
INSERT INTO `sys_product_type` VALUES (14, '美女', 1);

-- ----------------------------
-- Table structure for sys_sequence
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES (1, 'DD', '000014');

-- ----------------------------
-- Table structure for sys_staff
-- ----------------------------
DROP TABLE IF EXISTS `sys_staff`;
CREATE TABLE `sys_staff`  (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NULL DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `create_staff_id` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_staff
-- ----------------------------
INSERT INTO `sys_staff` VALUES (1, '管理员', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '88888888', '123456@itany.com', 1, '2001', 0, '2020-06-27 17:19:54', 1);
INSERT INTO `sys_staff` VALUES (2, 'liuliu', 'ssdf', '4QrcOUm6Wau+VuBX8g+IPg==', '123346', '123456@itany.com', 2, '2001', 0, '2020-06-27 18:06:45', 1);
INSERT INTO `sys_staff` VALUES (3, 'ooo', 'qwe', '4QrcOUm6Wau+VuBX8g+IPg==', 'qwe', 'qwe', 1, '2001', 0, NULL, NULL);
INSERT INTO `sys_staff` VALUES (4, 'qwe', '333', '4QrcOUm6Wau+VuBX8g+IPg==', '123', '123', 2, '2001', 0, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` int(11) NULL DEFAULT NULL,
  `regist_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '张三', 'xiaotree', '4QrcOUm6Wau+VuBX8g+IPg==', '132465798', '撒低级啦', 1, '2020-06-24 11:25:23');
INSERT INTO `sys_user` VALUES (2, '李四', 'lisi', '4QrcOUm6Wau+VuBX8g+IPg==', '132465798', '级', 1, '2020-07-02 00:50:49');

SET FOREIGN_KEY_CHECKS = 1;
