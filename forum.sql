/*
 Navicat Premium Data Transfer

 Source Server         : forum
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 13/03/2019 10:19:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for background
-- ----------------------------
DROP TABLE IF EXISTS `background`;
CREATE TABLE `background`  (
  `background_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '背景图片id',
  `background_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '背景图片名字',
  `picture` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '背景图地址',
  PRIMARY KEY (`background_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of background
-- ----------------------------
INSERT INTO `background` VALUES (1, '123', '456');
INSERT INTO `background` VALUES (2, '123', '456');

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `board_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '论坛版块ID',
  `board_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '论坛版块名',
  `board_descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论坛版块描述',
  `topic_num` int(11) NOT NULL DEFAULT 0 COMMENT '帖子数目',
  PRIMARY KEY (`board_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES (1, 'genxing', '112', 13);
INSERT INTO `board` VALUES (2, '计算机', '头发越来越少了', 0);
INSERT INTO `board` VALUES (3, '数学', '这题，我有两种解法', 0);

-- ----------------------------
-- Table structure for board_manager
-- ----------------------------
DROP TABLE IF EXISTS `board_manager`;
CREATE TABLE `board_manager`  (
  `board_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`board_id`, `user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `board_manager_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `board_manager_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_email` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`feedback_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, 4, 'untilyouydc', '15087581161@163.com', 'ttttt', '2019-02-10 16:57:06');

-- ----------------------------
-- Table structure for follower
-- ----------------------------
DROP TABLE IF EXISTS `follower`;
CREATE TABLE `follower`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follow_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '互粉时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follower
-- ----------------------------
INSERT INTO `follower` VALUES (2, 4, 6, '2019-01-25 13:05:22');
INSERT INTO `follower` VALUES (3, 4, 5, '2019-01-25 13:05:36');
INSERT INTO `follower` VALUES (15, 2, 4, '2019-02-04 19:21:22');
INSERT INTO `follower` VALUES (16, 4, 15, '2019-02-10 20:36:08');
INSERT INTO `follower` VALUES (17, 4, 2, '2019-02-15 11:55:37');

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter`  (
  `letter_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `letter_type` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`letter_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of letter
-- ----------------------------
INSERT INTO `letter` VALUES (1, 4, 0, ' hhhhh', '2019-02-04 16:21:59', 'ORIGINAL');
INSERT INTO `letter` VALUES (2, 2, 0, ' hhhh', '2019-02-07 09:20:13', 'ORIGINAL');
INSERT INTO `letter` VALUES (3, 4, 0, ' 11122', '2019-02-07 09:23:06', 'ORIGINAL');
INSERT INTO `letter` VALUES (4, 4, 0, ' hhhhhhh', '2019-02-07 09:30:24', 'ORIGINAL');
INSERT INTO `letter` VALUES (5, 4, 0, ' mmmmm', '2019-02-07 09:51:07', 'ORIGINAL');
INSERT INTO `letter` VALUES (6, 4, 0, ' mmmm', '2019-02-07 09:52:49', 'ORIGINAL');
INSERT INTO `letter` VALUES (7, 4, 0, ' 1111', '2019-02-07 09:55:13', 'ORIGINAL');
INSERT INTO `letter` VALUES (8, 4, 0, ' cccc', '2019-02-07 09:56:44', 'ORIGINAL');
INSERT INTO `letter` VALUES (9, 4, 0, ' ddddd', '2019-02-07 09:58:29', 'ORIGINAL');
INSERT INTO `letter` VALUES (10, 4, 0, 'dd', '2019-02-07 09:58:48', 'ORIGINAL');
INSERT INTO `letter` VALUES (11, 4, 0, ' mmmm', '2019-02-07 10:00:59', 'ORIGINAL');
INSERT INTO `letter` VALUES (12, 4, 2, ' 1244f', '2019-02-07 10:02:55', 'ORIGINAL');
INSERT INTO `letter` VALUES (13, 2, 2, ' mmm0000', '2019-02-07 10:04:18', 'ORIGINAL');
INSERT INTO `letter` VALUES (14, 2, 4, '', '2019-02-07 11:19:29', 'REPLY');
INSERT INTO `letter` VALUES (15, 2, 4, '', '2019-02-07 11:21:16', 'REPLY');
INSERT INTO `letter` VALUES (16, 2, 4, '@untilyou 333', '2019-02-07 11:26:35', 'REPLY');
INSERT INTO `letter` VALUES (17, 4, 2, '@ydc 666', '2019-02-07 11:27:29', 'REPLY');
INSERT INTO `letter` VALUES (18, 2, 4, '@untilyou 999', '2019-02-07 11:30:46', 'REPLY');
INSERT INTO `letter` VALUES (19, 2, 2, '@ydc hhh', '2019-02-07 11:31:36', 'REPLY');
INSERT INTO `letter` VALUES (20, 2, 2, '@ydc lll', '2019-02-07 11:37:56', 'REPLY');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'ip地址',
  `login_time` datetime(0) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`login_log_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `login_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 287 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES (1, 2, '1111', '2019-01-08 10:24:17');
INSERT INTO `login_log` VALUES (2, 2, '111', '2019-01-10 20:29:13');
INSERT INTO `login_log` VALUES (3, 2, '0:0:0:0:0:0:0:1', '2019-01-12 14:43:48');
INSERT INTO `login_log` VALUES (4, 2, '0:0:0:0:0:0:0:1', '2019-01-12 14:47:00');
INSERT INTO `login_log` VALUES (5, 2, '0:0:0:0:0:0:0:1', '2019-01-12 15:20:35');
INSERT INTO `login_log` VALUES (6, 2, '0:0:0:0:0:0:0:1', '2019-01-18 10:22:00');
INSERT INTO `login_log` VALUES (7, 2, '0:0:0:0:0:0:0:1', '2019-01-18 10:36:20');
INSERT INTO `login_log` VALUES (8, 2, '0:0:0:0:0:0:0:1', '2019-01-18 10:40:46');
INSERT INTO `login_log` VALUES (9, 2, '0:0:0:0:0:0:0:1', '2019-01-18 10:46:24');
INSERT INTO `login_log` VALUES (10, 4, '0:0:0:0:0:0:0:1', '2019-01-18 10:46:51');
INSERT INTO `login_log` VALUES (11, 2, '0:0:0:0:0:0:0:1', '2019-01-18 10:55:13');
INSERT INTO `login_log` VALUES (12, 4, '0:0:0:0:0:0:0:1', '2019-01-18 11:02:23');
INSERT INTO `login_log` VALUES (13, 2, '0:0:0:0:0:0:0:1', '2019-01-18 13:31:23');
INSERT INTO `login_log` VALUES (14, 2, '0:0:0:0:0:0:0:1', '2019-01-18 13:34:00');
INSERT INTO `login_log` VALUES (15, 4, '0:0:0:0:0:0:0:1', '2019-01-18 14:32:21');
INSERT INTO `login_log` VALUES (16, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:09:34');
INSERT INTO `login_log` VALUES (17, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:23:35');
INSERT INTO `login_log` VALUES (18, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:30:46');
INSERT INTO `login_log` VALUES (19, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:37:05');
INSERT INTO `login_log` VALUES (20, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:46:41');
INSERT INTO `login_log` VALUES (21, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:49:12');
INSERT INTO `login_log` VALUES (22, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:53:08');
INSERT INTO `login_log` VALUES (23, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:57:06');
INSERT INTO `login_log` VALUES (24, 4, '0:0:0:0:0:0:0:1', '2019-01-18 15:58:32');
INSERT INTO `login_log` VALUES (25, 4, '0:0:0:0:0:0:0:1', '2019-01-19 10:43:15');
INSERT INTO `login_log` VALUES (26, 4, '0:0:0:0:0:0:0:1', '2019-01-19 14:56:54');
INSERT INTO `login_log` VALUES (27, 4, '0:0:0:0:0:0:0:1', '2019-01-19 14:59:35');
INSERT INTO `login_log` VALUES (28, 4, '0:0:0:0:0:0:0:1', '2019-01-19 15:05:42');
INSERT INTO `login_log` VALUES (29, 4, '0:0:0:0:0:0:0:1', '2019-01-19 15:33:29');
INSERT INTO `login_log` VALUES (30, 4, '0:0:0:0:0:0:0:1', '2019-01-19 15:47:56');
INSERT INTO `login_log` VALUES (31, 4, '0:0:0:0:0:0:0:1', '2019-01-19 16:00:26');
INSERT INTO `login_log` VALUES (32, 4, '0:0:0:0:0:0:0:1', '2019-01-19 16:03:35');
INSERT INTO `login_log` VALUES (33, 4, '0:0:0:0:0:0:0:1', '2019-01-19 16:51:54');
INSERT INTO `login_log` VALUES (34, 4, '0:0:0:0:0:0:0:1', '2019-01-19 19:46:56');
INSERT INTO `login_log` VALUES (35, 4, '0:0:0:0:0:0:0:1', '2019-01-20 16:45:57');
INSERT INTO `login_log` VALUES (36, 4, '0:0:0:0:0:0:0:1', '2019-01-20 16:50:19');
INSERT INTO `login_log` VALUES (37, 4, '0:0:0:0:0:0:0:1', '2019-01-20 16:52:12');
INSERT INTO `login_log` VALUES (38, 4, '0:0:0:0:0:0:0:1', '2019-01-20 17:05:06');
INSERT INTO `login_log` VALUES (39, 4, '0:0:0:0:0:0:0:1', '2019-01-20 21:21:59');
INSERT INTO `login_log` VALUES (40, 4, '0:0:0:0:0:0:0:1', '2019-01-23 21:35:35');
INSERT INTO `login_log` VALUES (41, 4, '0:0:0:0:0:0:0:1', '2019-01-25 10:59:49');
INSERT INTO `login_log` VALUES (42, 4, '0:0:0:0:0:0:0:1', '2019-01-25 12:36:35');
INSERT INTO `login_log` VALUES (43, 4, '0:0:0:0:0:0:0:1', '2019-01-25 12:41:58');
INSERT INTO `login_log` VALUES (44, 4, '0:0:0:0:0:0:0:1', '2019-01-25 12:50:34');
INSERT INTO `login_log` VALUES (45, 4, '0:0:0:0:0:0:0:1', '2019-01-25 13:36:38');
INSERT INTO `login_log` VALUES (46, 4, '0:0:0:0:0:0:0:1', '2019-01-25 14:03:16');
INSERT INTO `login_log` VALUES (47, 4, '0:0:0:0:0:0:0:1', '2019-01-25 15:53:21');
INSERT INTO `login_log` VALUES (48, 4, '0:0:0:0:0:0:0:1', '2019-01-25 16:05:05');
INSERT INTO `login_log` VALUES (49, 4, '0:0:0:0:0:0:0:1', '2019-01-25 16:08:25');
INSERT INTO `login_log` VALUES (50, 4, '0:0:0:0:0:0:0:1', '2019-01-26 09:10:12');
INSERT INTO `login_log` VALUES (51, 4, '0:0:0:0:0:0:0:1', '2019-01-26 09:22:15');
INSERT INTO `login_log` VALUES (52, 4, '0:0:0:0:0:0:0:1', '2019-01-26 09:25:02');
INSERT INTO `login_log` VALUES (53, 4, '0:0:0:0:0:0:0:1', '2019-01-26 09:34:04');
INSERT INTO `login_log` VALUES (54, 4, '0:0:0:0:0:0:0:1', '2019-01-26 09:39:05');
INSERT INTO `login_log` VALUES (55, 4, '0:0:0:0:0:0:0:1', '2019-01-26 14:56:11');
INSERT INTO `login_log` VALUES (56, 4, '0:0:0:0:0:0:0:1', '2019-01-26 15:01:52');
INSERT INTO `login_log` VALUES (57, 4, '0:0:0:0:0:0:0:1', '2019-01-26 15:10:45');
INSERT INTO `login_log` VALUES (58, 4, '0:0:0:0:0:0:0:1', '2019-01-26 15:24:19');
INSERT INTO `login_log` VALUES (59, 4, '0:0:0:0:0:0:0:1', '2019-01-26 15:28:44');
INSERT INTO `login_log` VALUES (60, 4, '0:0:0:0:0:0:0:1', '2019-01-26 17:35:17');
INSERT INTO `login_log` VALUES (61, 4, '0:0:0:0:0:0:0:1', '2019-01-27 17:20:59');
INSERT INTO `login_log` VALUES (62, 4, '0:0:0:0:0:0:0:1', '2019-01-27 17:24:35');
INSERT INTO `login_log` VALUES (63, 4, '0:0:0:0:0:0:0:1', '2019-01-27 17:25:46');
INSERT INTO `login_log` VALUES (64, 4, '0:0:0:0:0:0:0:1', '2019-01-27 17:27:44');
INSERT INTO `login_log` VALUES (65, 4, '0:0:0:0:0:0:0:1', '2019-01-27 17:29:22');
INSERT INTO `login_log` VALUES (66, 4, '0:0:0:0:0:0:0:1', '2019-01-28 13:27:11');
INSERT INTO `login_log` VALUES (67, 4, '0:0:0:0:0:0:0:1', '2019-01-28 14:22:38');
INSERT INTO `login_log` VALUES (68, 4, '0:0:0:0:0:0:0:1', '2019-01-28 14:30:04');
INSERT INTO `login_log` VALUES (69, 4, '0:0:0:0:0:0:0:1', '2019-01-28 15:37:31');
INSERT INTO `login_log` VALUES (70, 4, '0:0:0:0:0:0:0:1', '2019-01-28 17:27:09');
INSERT INTO `login_log` VALUES (71, 4, '0:0:0:0:0:0:0:1', '2019-01-28 17:51:41');
INSERT INTO `login_log` VALUES (72, 4, '0:0:0:0:0:0:0:1', '2019-01-28 18:45:09');
INSERT INTO `login_log` VALUES (73, 4, '0:0:0:0:0:0:0:1', '2019-01-29 08:29:16');
INSERT INTO `login_log` VALUES (74, 4, '0:0:0:0:0:0:0:1', '2019-01-29 08:37:59');
INSERT INTO `login_log` VALUES (75, 4, '0:0:0:0:0:0:0:1', '2019-01-29 09:48:06');
INSERT INTO `login_log` VALUES (76, 4, '0:0:0:0:0:0:0:1', '2019-01-29 12:51:18');
INSERT INTO `login_log` VALUES (77, 4, '0:0:0:0:0:0:0:1', '2019-01-29 13:29:30');
INSERT INTO `login_log` VALUES (78, 4, '0:0:0:0:0:0:0:1', '2019-01-30 08:31:37');
INSERT INTO `login_log` VALUES (79, 4, '0:0:0:0:0:0:0:1', '2019-01-30 09:17:00');
INSERT INTO `login_log` VALUES (80, 4, '0:0:0:0:0:0:0:1', '2019-01-30 10:11:49');
INSERT INTO `login_log` VALUES (81, 4, '0:0:0:0:0:0:0:1', '2019-01-30 10:29:39');
INSERT INTO `login_log` VALUES (82, 4, '0:0:0:0:0:0:0:1', '2019-01-30 11:15:43');
INSERT INTO `login_log` VALUES (83, 4, '0:0:0:0:0:0:0:1', '2019-01-30 12:47:33');
INSERT INTO `login_log` VALUES (84, 4, '0:0:0:0:0:0:0:1', '2019-01-30 13:16:32');
INSERT INTO `login_log` VALUES (85, 4, '0:0:0:0:0:0:0:1', '2019-01-31 08:14:37');
INSERT INTO `login_log` VALUES (86, 4, '0:0:0:0:0:0:0:1', '2019-01-31 14:18:57');
INSERT INTO `login_log` VALUES (87, 4, '0:0:0:0:0:0:0:1', '2019-01-31 14:21:04');
INSERT INTO `login_log` VALUES (88, 4, '0:0:0:0:0:0:0:1', '2019-01-31 14:26:00');
INSERT INTO `login_log` VALUES (89, 4, '0:0:0:0:0:0:0:1', '2019-01-31 14:37:05');
INSERT INTO `login_log` VALUES (90, 4, '0:0:0:0:0:0:0:1', '2019-01-31 14:58:46');
INSERT INTO `login_log` VALUES (91, 4, '0:0:0:0:0:0:0:1', '2019-01-31 15:08:15');
INSERT INTO `login_log` VALUES (92, 4, '0:0:0:0:0:0:0:1', '2019-01-31 15:21:32');
INSERT INTO `login_log` VALUES (93, 4, '0:0:0:0:0:0:0:1', '2019-01-31 15:23:57');
INSERT INTO `login_log` VALUES (94, 4, '0:0:0:0:0:0:0:1', '2019-01-31 15:26:50');
INSERT INTO `login_log` VALUES (95, 4, '0:0:0:0:0:0:0:1', '2019-02-01 17:00:59');
INSERT INTO `login_log` VALUES (96, 4, '0:0:0:0:0:0:0:1', '2019-02-02 08:28:01');
INSERT INTO `login_log` VALUES (97, 4, '0:0:0:0:0:0:0:1', '2019-02-02 16:54:02');
INSERT INTO `login_log` VALUES (98, 4, '0:0:0:0:0:0:0:1', '2019-02-02 17:13:17');
INSERT INTO `login_log` VALUES (99, 4, '0:0:0:0:0:0:0:1', '2019-02-02 17:24:48');
INSERT INTO `login_log` VALUES (100, 4, '0:0:0:0:0:0:0:1', '2019-02-02 17:26:47');
INSERT INTO `login_log` VALUES (101, 4, '0:0:0:0:0:0:0:1', '2019-02-04 10:17:21');
INSERT INTO `login_log` VALUES (102, 4, '0:0:0:0:0:0:0:1', '2019-02-04 16:21:14');
INSERT INTO `login_log` VALUES (103, 4, '0:0:0:0:0:0:0:1', '2019-02-04 17:54:59');
INSERT INTO `login_log` VALUES (104, 4, '0:0:0:0:0:0:0:1', '2019-02-04 18:07:23');
INSERT INTO `login_log` VALUES (105, 4, '0:0:0:0:0:0:0:1', '2019-02-04 18:23:56');
INSERT INTO `login_log` VALUES (106, 4, '0:0:0:0:0:0:0:1', '2019-02-04 18:29:38');
INSERT INTO `login_log` VALUES (107, 4, '0:0:0:0:0:0:0:1', '2019-02-04 18:40:54');
INSERT INTO `login_log` VALUES (108, 4, '0:0:0:0:0:0:0:1', '2019-02-04 19:02:22');
INSERT INTO `login_log` VALUES (109, 4, '0:0:0:0:0:0:0:1', '2019-02-04 19:16:43');
INSERT INTO `login_log` VALUES (110, 4, '0:0:0:0:0:0:0:1', '2019-02-04 19:20:50');
INSERT INTO `login_log` VALUES (111, 4, '0:0:0:0:0:0:0:1', '2019-02-05 19:08:21');
INSERT INTO `login_log` VALUES (112, 4, '0:0:0:0:0:0:0:1', '2019-02-05 19:53:16');
INSERT INTO `login_log` VALUES (113, 4, '0:0:0:0:0:0:0:1', '2019-02-05 21:04:10');
INSERT INTO `login_log` VALUES (114, 4, '0:0:0:0:0:0:0:1', '2019-02-06 18:43:39');
INSERT INTO `login_log` VALUES (115, 4, '0:0:0:0:0:0:0:1', '2019-02-06 19:01:32');
INSERT INTO `login_log` VALUES (116, 4, '0:0:0:0:0:0:0:1', '2019-02-06 19:43:11');
INSERT INTO `login_log` VALUES (117, 4, '0:0:0:0:0:0:0:1', '2019-02-06 20:24:37');
INSERT INTO `login_log` VALUES (118, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:07:27');
INSERT INTO `login_log` VALUES (119, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:14:53');
INSERT INTO `login_log` VALUES (120, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:17:51');
INSERT INTO `login_log` VALUES (121, 2, '0:0:0:0:0:0:0:1', '2019-02-07 09:19:38');
INSERT INTO `login_log` VALUES (122, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:21:44');
INSERT INTO `login_log` VALUES (123, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:28:28');
INSERT INTO `login_log` VALUES (124, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:50:47');
INSERT INTO `login_log` VALUES (125, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:52:34');
INSERT INTO `login_log` VALUES (126, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:54:53');
INSERT INTO `login_log` VALUES (127, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:56:28');
INSERT INTO `login_log` VALUES (128, 4, '0:0:0:0:0:0:0:1', '2019-02-07 09:58:13');
INSERT INTO `login_log` VALUES (129, 4, '0:0:0:0:0:0:0:1', '2019-02-07 10:00:45');
INSERT INTO `login_log` VALUES (130, 4, '0:0:0:0:0:0:0:1', '2019-02-07 10:02:41');
INSERT INTO `login_log` VALUES (131, 2, '0:0:0:0:0:0:0:1', '2019-02-07 10:03:21');
INSERT INTO `login_log` VALUES (132, 2, '0:0:0:0:0:0:0:1', '2019-02-07 10:07:04');
INSERT INTO `login_log` VALUES (133, 2, '0:0:0:0:0:0:0:1', '2019-02-07 10:11:41');
INSERT INTO `login_log` VALUES (134, 2, '0:0:0:0:0:0:0:1', '2019-02-07 10:59:16');
INSERT INTO `login_log` VALUES (135, 4, '0:0:0:0:0:0:0:1', '2019-02-07 11:11:58');
INSERT INTO `login_log` VALUES (136, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:12:15');
INSERT INTO `login_log` VALUES (137, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:16:30');
INSERT INTO `login_log` VALUES (138, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:19:19');
INSERT INTO `login_log` VALUES (139, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:20:59');
INSERT INTO `login_log` VALUES (140, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:26:24');
INSERT INTO `login_log` VALUES (141, 4, '0:0:0:0:0:0:0:1', '2019-02-07 11:27:10');
INSERT INTO `login_log` VALUES (142, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:27:56');
INSERT INTO `login_log` VALUES (143, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:30:28');
INSERT INTO `login_log` VALUES (144, 2, '0:0:0:0:0:0:0:1', '2019-02-07 11:37:41');
INSERT INTO `login_log` VALUES (145, 4, '0:0:0:0:0:0:0:1', '2019-02-07 12:21:50');
INSERT INTO `login_log` VALUES (146, 4, '0:0:0:0:0:0:0:1', '2019-02-08 08:44:50');
INSERT INTO `login_log` VALUES (147, 4, '0:0:0:0:0:0:0:1', '2019-02-08 08:47:46');
INSERT INTO `login_log` VALUES (148, 4, '0:0:0:0:0:0:0:1', '2019-02-08 09:21:33');
INSERT INTO `login_log` VALUES (149, 4, '0:0:0:0:0:0:0:1', '2019-02-08 09:26:37');
INSERT INTO `login_log` VALUES (150, 4, '0:0:0:0:0:0:0:1', '2019-02-08 09:30:02');
INSERT INTO `login_log` VALUES (151, 4, '0:0:0:0:0:0:0:1', '2019-02-08 09:33:34');
INSERT INTO `login_log` VALUES (152, 4, '0:0:0:0:0:0:0:1', '2019-02-08 09:36:10');
INSERT INTO `login_log` VALUES (153, 4, '0:0:0:0:0:0:0:1', '2019-02-09 10:47:09');
INSERT INTO `login_log` VALUES (154, 4, '0:0:0:0:0:0:0:1', '2019-02-09 10:51:33');
INSERT INTO `login_log` VALUES (155, 4, '0:0:0:0:0:0:0:1', '2019-02-09 10:54:31');
INSERT INTO `login_log` VALUES (156, 4, '0:0:0:0:0:0:0:1', '2019-02-09 11:45:37');
INSERT INTO `login_log` VALUES (157, 4, '0:0:0:0:0:0:0:1', '2019-02-09 11:52:38');
INSERT INTO `login_log` VALUES (158, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:05:30');
INSERT INTO `login_log` VALUES (159, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:08:21');
INSERT INTO `login_log` VALUES (160, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:10:30');
INSERT INTO `login_log` VALUES (161, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:12:32');
INSERT INTO `login_log` VALUES (162, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:19:43');
INSERT INTO `login_log` VALUES (163, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:26:39');
INSERT INTO `login_log` VALUES (164, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:30:58');
INSERT INTO `login_log` VALUES (165, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:37:49');
INSERT INTO `login_log` VALUES (166, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:41:59');
INSERT INTO `login_log` VALUES (167, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:46:51');
INSERT INTO `login_log` VALUES (168, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:54:04');
INSERT INTO `login_log` VALUES (169, 4, '0:0:0:0:0:0:0:1', '2019-02-09 12:58:54');
INSERT INTO `login_log` VALUES (170, 4, '0:0:0:0:0:0:0:1', '2019-02-09 13:11:32');
INSERT INTO `login_log` VALUES (171, 4, '0:0:0:0:0:0:0:1', '2019-02-09 13:23:46');
INSERT INTO `login_log` VALUES (172, 4, '0:0:0:0:0:0:0:1', '2019-02-09 13:30:38');
INSERT INTO `login_log` VALUES (173, 4, '0:0:0:0:0:0:0:1', '2019-02-09 13:32:57');
INSERT INTO `login_log` VALUES (174, 4, '0:0:0:0:0:0:0:1', '2019-02-09 13:57:09');
INSERT INTO `login_log` VALUES (175, 4, '0:0:0:0:0:0:0:1', '2019-02-09 14:03:36');
INSERT INTO `login_log` VALUES (176, 4, '0:0:0:0:0:0:0:1', '2019-02-09 14:26:16');
INSERT INTO `login_log` VALUES (177, 4, '0:0:0:0:0:0:0:1', '2019-02-09 14:27:01');
INSERT INTO `login_log` VALUES (178, 4, '0:0:0:0:0:0:0:1', '2019-02-09 16:36:21');
INSERT INTO `login_log` VALUES (179, 4, '0:0:0:0:0:0:0:1', '2019-02-09 16:39:52');
INSERT INTO `login_log` VALUES (180, 4, '0:0:0:0:0:0:0:1', '2019-02-09 16:44:36');
INSERT INTO `login_log` VALUES (181, 4, '0:0:0:0:0:0:0:1', '2019-02-09 17:03:43');
INSERT INTO `login_log` VALUES (182, 4, '0:0:0:0:0:0:0:1', '2019-02-09 17:07:13');
INSERT INTO `login_log` VALUES (183, 4, '0:0:0:0:0:0:0:1', '2019-02-10 13:06:02');
INSERT INTO `login_log` VALUES (184, 4, '0:0:0:0:0:0:0:1', '2019-02-10 13:06:59');
INSERT INTO `login_log` VALUES (185, 4, '0:0:0:0:0:0:0:1', '2019-02-10 16:50:12');
INSERT INTO `login_log` VALUES (186, 4, '0:0:0:0:0:0:0:1', '2019-02-10 16:56:57');
INSERT INTO `login_log` VALUES (187, 4, '0:0:0:0:0:0:0:1', '2019-02-10 17:16:26');
INSERT INTO `login_log` VALUES (188, 4, '0:0:0:0:0:0:0:1', '2019-02-10 17:17:36');
INSERT INTO `login_log` VALUES (189, 4, '0:0:0:0:0:0:0:1', '2019-02-10 17:26:28');
INSERT INTO `login_log` VALUES (191, 15, '0:0:0:0:0:0:0:1', '2019-02-10 20:34:58');
INSERT INTO `login_log` VALUES (192, 4, '0:0:0:0:0:0:0:1', '2019-02-12 18:12:28');
INSERT INTO `login_log` VALUES (193, 4, '0:0:0:0:0:0:0:1', '2019-02-12 18:19:38');
INSERT INTO `login_log` VALUES (194, 4, '0:0:0:0:0:0:0:1', '2019-02-14 08:44:29');
INSERT INTO `login_log` VALUES (195, 4, '0:0:0:0:0:0:0:1', '2019-02-14 11:11:31');
INSERT INTO `login_log` VALUES (196, 4, '0:0:0:0:0:0:0:1', '2019-02-14 11:59:03');
INSERT INTO `login_log` VALUES (197, 4, '0:0:0:0:0:0:0:1', '2019-02-14 14:22:55');
INSERT INTO `login_log` VALUES (198, 4, '0:0:0:0:0:0:0:1', '2019-02-14 16:33:49');
INSERT INTO `login_log` VALUES (199, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:16:54');
INSERT INTO `login_log` VALUES (200, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:17:28');
INSERT INTO `login_log` VALUES (201, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:25:44');
INSERT INTO `login_log` VALUES (202, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:33:34');
INSERT INTO `login_log` VALUES (203, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:34:51');
INSERT INTO `login_log` VALUES (204, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:43:45');
INSERT INTO `login_log` VALUES (205, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:47:21');
INSERT INTO `login_log` VALUES (206, 2, '0:0:0:0:0:0:0:1', '2019-02-14 17:52:20');
INSERT INTO `login_log` VALUES (207, 19, '0:0:0:0:0:0:0:1', '2019-02-14 18:10:38');
INSERT INTO `login_log` VALUES (208, 2, '0:0:0:0:0:0:0:1', '2019-02-14 18:27:46');
INSERT INTO `login_log` VALUES (209, 2, '0:0:0:0:0:0:0:1', '2019-02-14 18:40:16');
INSERT INTO `login_log` VALUES (210, 2, '0:0:0:0:0:0:0:1', '2019-02-14 18:49:44');
INSERT INTO `login_log` VALUES (211, 4, '0:0:0:0:0:0:0:1', '2019-02-15 10:10:57');
INSERT INTO `login_log` VALUES (212, 2, '0:0:0:0:0:0:0:1', '2019-02-15 11:42:48');
INSERT INTO `login_log` VALUES (213, 2, '0:0:0:0:0:0:0:1', '2019-02-15 11:57:36');
INSERT INTO `login_log` VALUES (214, 2, '0:0:0:0:0:0:0:1', '2019-02-18 13:00:58');
INSERT INTO `login_log` VALUES (215, 2, '0:0:0:0:0:0:0:1', '2019-02-21 14:19:23');
INSERT INTO `login_log` VALUES (216, 2, '0:0:0:0:0:0:0:1', '2019-02-24 10:24:25');
INSERT INTO `login_log` VALUES (217, 2, '0:0:0:0:0:0:0:1', '2019-02-24 11:10:50');
INSERT INTO `login_log` VALUES (218, 2, '0:0:0:0:0:0:0:1', '2019-02-24 11:12:58');
INSERT INTO `login_log` VALUES (219, 2, '0:0:0:0:0:0:0:1', '2019-02-24 11:51:46');
INSERT INTO `login_log` VALUES (220, 2, '0:0:0:0:0:0:0:1', '2019-02-24 12:48:04');
INSERT INTO `login_log` VALUES (221, 2, '0:0:0:0:0:0:0:1', '2019-02-24 13:01:06');
INSERT INTO `login_log` VALUES (222, 2, '0:0:0:0:0:0:0:1', '2019-02-24 13:03:59');
INSERT INTO `login_log` VALUES (223, 2, '0:0:0:0:0:0:0:1', '2019-02-24 13:08:39');
INSERT INTO `login_log` VALUES (224, 2, '0:0:0:0:0:0:0:1', '2019-02-24 13:14:08');
INSERT INTO `login_log` VALUES (225, 2, '0:0:0:0:0:0:0:1', '2019-02-24 14:07:58');
INSERT INTO `login_log` VALUES (226, 2, '0:0:0:0:0:0:0:1', '2019-02-24 15:04:12');
INSERT INTO `login_log` VALUES (227, 2, '0:0:0:0:0:0:0:1', '2019-02-24 15:12:18');
INSERT INTO `login_log` VALUES (228, 2, '0:0:0:0:0:0:0:1', '2019-02-24 18:38:51');
INSERT INTO `login_log` VALUES (229, 2, '0:0:0:0:0:0:0:1', '2019-02-24 19:27:21');
INSERT INTO `login_log` VALUES (230, 2, '0:0:0:0:0:0:0:1', '2019-02-24 19:34:46');
INSERT INTO `login_log` VALUES (231, 2, '0:0:0:0:0:0:0:1', '2019-02-24 20:14:25');
INSERT INTO `login_log` VALUES (232, 2, '0:0:0:0:0:0:0:1', '2019-02-24 20:37:14');
INSERT INTO `login_log` VALUES (233, 2, '0:0:0:0:0:0:0:1', '2019-02-24 21:09:18');
INSERT INTO `login_log` VALUES (234, 2, '0:0:0:0:0:0:0:1', '2019-02-24 21:12:03');
INSERT INTO `login_log` VALUES (235, 2, '0:0:0:0:0:0:0:1', '2019-02-24 22:45:07');
INSERT INTO `login_log` VALUES (236, 2, '0:0:0:0:0:0:0:1', '2019-02-24 22:51:33');
INSERT INTO `login_log` VALUES (237, 2, '0:0:0:0:0:0:0:1', '2019-02-24 22:53:38');
INSERT INTO `login_log` VALUES (238, 2, '0:0:0:0:0:0:0:1', '2019-02-24 22:58:46');
INSERT INTO `login_log` VALUES (239, 2, '0:0:0:0:0:0:0:1', '2019-02-24 23:02:15');
INSERT INTO `login_log` VALUES (240, 2, '0:0:0:0:0:0:0:1', '2019-02-24 23:35:41');
INSERT INTO `login_log` VALUES (241, 2, '0:0:0:0:0:0:0:1', '2019-02-24 23:43:49');
INSERT INTO `login_log` VALUES (242, 2, '0:0:0:0:0:0:0:1', '2019-02-24 23:51:32');
INSERT INTO `login_log` VALUES (243, 2, '0:0:0:0:0:0:0:1', '2019-02-25 00:04:49');
INSERT INTO `login_log` VALUES (244, 4, '0:0:0:0:0:0:0:1', '2019-02-25 08:08:59');
INSERT INTO `login_log` VALUES (245, 2, '0:0:0:0:0:0:0:1', '2019-02-25 08:23:57');
INSERT INTO `login_log` VALUES (246, 4, '0:0:0:0:0:0:0:1', '2019-02-25 08:31:48');
INSERT INTO `login_log` VALUES (247, 2, '0:0:0:0:0:0:0:1', '2019-02-25 08:39:15');
INSERT INTO `login_log` VALUES (248, 2, '0:0:0:0:0:0:0:1', '2019-02-25 08:47:50');
INSERT INTO `login_log` VALUES (249, 2, '0:0:0:0:0:0:0:1', '2019-02-25 09:13:49');
INSERT INTO `login_log` VALUES (250, 2, '0:0:0:0:0:0:0:1', '2019-02-25 09:29:53');
INSERT INTO `login_log` VALUES (251, 2, '0:0:0:0:0:0:0:1', '2019-02-25 09:30:47');
INSERT INTO `login_log` VALUES (252, 2, '0:0:0:0:0:0:0:1', '2019-02-25 09:32:29');
INSERT INTO `login_log` VALUES (253, 2, '0:0:0:0:0:0:0:1', '2019-02-25 09:49:20');
INSERT INTO `login_log` VALUES (254, 2, '0:0:0:0:0:0:0:1', '2019-02-25 11:53:41');
INSERT INTO `login_log` VALUES (255, 2, '0:0:0:0:0:0:0:1', '2019-02-25 11:54:53');
INSERT INTO `login_log` VALUES (256, 2, '0:0:0:0:0:0:0:1', '2019-02-25 12:01:02');
INSERT INTO `login_log` VALUES (257, 2, '0:0:0:0:0:0:0:1', '2019-02-25 12:13:59');
INSERT INTO `login_log` VALUES (258, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:08:09');
INSERT INTO `login_log` VALUES (259, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:18:10');
INSERT INTO `login_log` VALUES (260, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:27:32');
INSERT INTO `login_log` VALUES (261, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:31:09');
INSERT INTO `login_log` VALUES (262, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:33:54');
INSERT INTO `login_log` VALUES (263, 2, '0:0:0:0:0:0:0:1', '2019-02-25 13:36:56');
INSERT INTO `login_log` VALUES (264, 2, '0:0:0:0:0:0:0:1', '2019-02-25 15:59:24');
INSERT INTO `login_log` VALUES (265, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:02:23');
INSERT INTO `login_log` VALUES (266, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:03:43');
INSERT INTO `login_log` VALUES (267, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:04:13');
INSERT INTO `login_log` VALUES (268, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:06:32');
INSERT INTO `login_log` VALUES (269, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:15:03');
INSERT INTO `login_log` VALUES (270, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:19:24');
INSERT INTO `login_log` VALUES (271, 2, '0:0:0:0:0:0:0:1', '2019-02-25 16:25:28');
INSERT INTO `login_log` VALUES (272, 2, '0:0:0:0:0:0:0:1', '2019-03-03 12:51:09');
INSERT INTO `login_log` VALUES (273, 2, '0:0:0:0:0:0:0:1', '2019-03-03 12:55:35');
INSERT INTO `login_log` VALUES (274, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:08:51');
INSERT INTO `login_log` VALUES (275, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:20:38');
INSERT INTO `login_log` VALUES (276, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:23:17');
INSERT INTO `login_log` VALUES (277, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:24:49');
INSERT INTO `login_log` VALUES (278, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:33:31');
INSERT INTO `login_log` VALUES (279, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:51:18');
INSERT INTO `login_log` VALUES (280, 2, '0:0:0:0:0:0:0:1', '2019-03-03 13:53:29');
INSERT INTO `login_log` VALUES (281, 2, '0:0:0:0:0:0:0:1', '2019-03-03 14:44:12');
INSERT INTO `login_log` VALUES (282, 2, '0:0:0:0:0:0:0:1', '2019-03-03 14:54:22');
INSERT INTO `login_log` VALUES (283, 2, '0:0:0:0:0:0:0:1', '2019-03-03 15:26:06');
INSERT INTO `login_log` VALUES (284, 2, '0:0:0:0:0:0:0:1', '2019-03-04 17:15:02');
INSERT INTO `login_log` VALUES (285, 4, '0:0:0:0:0:0:0:1', '2019-03-04 17:22:53');
INSERT INTO `login_log` VALUES (286, 2, '0:0:0:0:0:0:0:1', '2019-03-04 17:32:23');

-- ----------------------------
-- Table structure for love
-- ----------------------------
DROP TABLE IF EXISTS `love`;
CREATE TABLE `love`  (
  `love_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `creatTime` datetime(0) NULL DEFAULT NULL,
  `creat_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`love_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `love_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `love_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love
-- ----------------------------
INSERT INTO `love` VALUES (1, 82, 4, NULL, '2019-02-08 09:30:10');
INSERT INTO `love` VALUES (2, 85, 2, NULL, '2019-02-15 11:46:21');
INSERT INTO `love` VALUES (3, 79, 2, NULL, '2019-02-24 21:24:00');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `board_id` int(11) NOT NULL COMMENT '论坛ID',
  `user_id` int(11) NOT NULL COMMENT '发表者ID',
  `post_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `topic_id` int(11) NOT NULL COMMENT '话题ID',
  `post_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子内容',
  `post_type` tinyint(4) NOT NULL DEFAULT 2 COMMENT '帖子类型 1:主帖子 2:回复帖子',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `board_id`(`board_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (5, 1, 4, '0000', '2019-01-31 15:09:14', 3, 'afk', 1, NULL, 'untilyou');
INSERT INTO `post` VALUES (9, 1, 4, '0000', '2019-02-09 17:06:12', 82, '1', 1, NULL, 'untilyouydc');
INSERT INTO `post` VALUES (10, 1, 4, '0000', '2019-02-15 10:43:37', 85, '哈哈哈', 1, NULL, 'untilyouydc');
INSERT INTO `post` VALUES (12, 1, 2, '0000', '2019-02-18 13:17:49', 85, '777', 1, NULL, 'ydc');
INSERT INTO `post` VALUES (17, 1, 2, '0000', '2019-02-24 23:52:35', 58, '111', 1, NULL, 'ydc');

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise`  (
  `praise_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `praise_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`praise_id`) USING BTREE,
  INDEX `topic_id`(`topic_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `praise_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `praise_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES (8, 4, 4, '2019-01-26 15:11:33');
INSERT INTO `praise` VALUES (10, 6, 4, '2019-01-26 15:11:37');
INSERT INTO `praise` VALUES (13, 2, 4, '2019-01-31 08:15:54');
INSERT INTO `praise` VALUES (27, 78, 4, '2019-02-09 11:54:24');
INSERT INTO `praise` VALUES (33, 82, 4, '2019-02-10 17:26:42');
INSERT INTO `praise` VALUES (36, 79, 4, '2019-02-10 17:27:01');
INSERT INTO `praise` VALUES (38, 86, 2, '2019-02-15 11:46:15');
INSERT INTO `praise` VALUES (39, 85, 2, '2019-02-18 13:17:37');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `board_id` int(11) NOT NULL COMMENT '所属论坛',
  `user_id` int(11) NOT NULL COMMENT '发表用户',
  `topic_title` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '帖子标题',
  `create_time` datetime(0) NOT NULL COMMENT '发表时间',
  `last_post` datetime(0) NOT NULL COMMENT '最后回复时间',
  `topic_views` int(11) NOT NULL DEFAULT 1 COMMENT '浏览数',
  `topic_replies` int(11) NOT NULL DEFAULT 0 COMMENT '回复数',
  `hot` int(11) NOT NULL COMMENT '0:不是精华话题 1:是精华话题',
  `background_id` int(11) NOT NULL COMMENT '选用的背景图片id',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `praise` int(11) NULL DEFAULT NULL,
  `logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`topic_id`) USING BTREE,
  INDEX `board_id`(`board_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `background_id`(`background_id`) USING BTREE,
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `topic_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `topic_ibfk_3` FOREIGN KEY (`background_id`) REFERENCES `background` (`background_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES (2, 1, 4, '123', '2019-01-08 11:11:47', '2019-01-08 11:11:47', 22, 21, 1, 2, 'PRIVATE', '1111111', 3, '111', NULL, NULL);
INSERT INTO `topic` VALUES (3, 1, 4, '11', '2019-01-20 17:16:10', '2019-01-20 17:16:10', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (4, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 1, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (5, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (6, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 1, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (7, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (8, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 3, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (9, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (10, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (11, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (12, 1, 4, '11', '2019-01-20 17:17:07', '2019-01-20 17:17:07', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, 'img/logo.png', NULL, NULL);
INSERT INTO `topic` VALUES (13, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (14, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (15, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (16, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (18, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (21, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (27, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (29, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (30, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (31, 1, 4, '1221', '2019-01-20 17:20:45', '2019-01-20 17:20:45', 12, 12, 1, 1, 'PRIVATE', 'hhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (32, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (33, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (34, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (35, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (36, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (37, 1, 4, '1221', '2019-01-20 17:25:45', '2019-01-20 17:25:45', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (38, 1, 4, '1221', '2019-01-20 17:25:46', '2019-01-20 17:25:46', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 3, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (39, 1, 4, '1221', '2019-01-20 17:25:46', '2019-01-20 17:25:46', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (41, 1, 4, '1221', '2019-01-20 17:25:46', '2019-01-20 17:25:46', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (53, 1, 4, '1221', '2019-01-20 17:28:11', '2019-01-20 17:28:11', 12, 12, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 3, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (58, 1, 4, '1221', '2019-01-20 17:28:11', '2019-01-20 17:28:11', 12, 13, 1, 1, 'PRIVATE', 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 3, '/img/favicon.png', NULL, NULL);
INSERT INTO `topic` VALUES (61, 1, 4, '第一个文章', '2019-01-28 14:37:01', '2019-01-28 14:37:01', 0, 0, 0, 1, 'PRIVATE', '[object Object]', 1, '/img', NULL, NULL);
INSERT INTO `topic` VALUES (62, 1, 4, '第一个文章', '2019-01-28 14:39:22', '2019-01-28 14:39:22', 0, 0, 0, 1, 'PRIVATE', NULL, 1, '/img', NULL, NULL);
INSERT INTO `topic` VALUES (75, 1, 4, '又是一篇测试文章', '2019-01-29 12:53:29', '2019-01-29 12:53:29', 0, 0, 0, 1, 'PRIVATE', '<p>对对对</p><p>没什么不对啊</p><p style=\"text-align:center\"><img src=\"/image/20190129/1548737555092043305.jpg\" title=\"1548737555092043305.jpg\" alt=\"1548737555092043305.jpg\" width=\"200\" height=\"200\"/></p><p>嗯嗯</p><p><span style=\"color: rgb(0, 176, 80);\">的点点滴滴</span></p>', 0, '/img', NULL, NULL);
INSERT INTO `topic` VALUES (78, 1, 4, '打扰了', '2019-01-30 11:16:24', '2019-01-30 11:16:24', 1, 0, 0, 1, 'PRIVATE', '<p>达瓦大女爱我的苦苦刚才v</p><p><img src=\"http://img.baidu.com/hi/jx2/j_0071.gif\"/></p><p><br/></p>', 1, '/img', '达瓦大女爱我的苦苦刚才v', NULL);
INSERT INTO `topic` VALUES (79, 1, 4, '又要测试了，hhhh', '2019-01-30 12:48:07', '2019-01-30 12:48:07', 3, 0, 0, 1, 'PRIVATE', '挖的防', 1, '/img', '<p>挖的防</p><p><br/></p>', NULL);
INSERT INTO `topic` VALUES (82, 2, 2, '22', '2019-02-04 10:08:55', '2019-02-04 10:08:58', 2, 0, 0, 1, 'PRIVATE', '<h1>hhh></h1>', 1, '/img', '22222', NULL);
INSERT INTO `topic` VALUES (83, 2, 2, '2月14日第2次测试', '2019-02-14 18:41:23', '2019-02-14 18:41:23', 1, 0, 0, 1, 'PRIVATE', '比赛还有4分46秒时，法里埃德空中接力扣篮得手，火箭只以102-105落后。蒂格中投还以一球，哈登马上命中三分。比赛成为两人的对决，在比赛还有1分52秒时，哈登三分命中，火箭只以111-115落后。奥格吉和唐斯相继得手，而哈登终于失去手感，连续投篮不中。在比赛还有7.4秒时，蒂格投篮命中，森林狼以121-111领先，锁定胜局。火箭在最后近2分钟未能得分，无力回天。（吴哥）', 0, '/img/favicon.png', '<p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">比赛还有4分46秒时，法里埃德空中接力扣篮得手，火箭只以102-105落后。蒂格中投还以一球，哈登马上命中三分。比赛成为两人的对决，在比赛还有1分52秒时，哈登三分命中，火箭只以111-115落后。奥格吉和唐斯相继得手，而哈登终于失去手感，连续投篮不中。在比赛还有7.4秒时，蒂格投篮命中，森林狼以121-111领先，锁定胜局。火箭在最后近2分钟未能得分，无力回天。（吴哥）</p><p><br/></p>', '体育');
INSERT INTO `topic` VALUES (84, 1, 2, '2月14日第3次测试', '2019-02-14 18:50:34', '2019-02-14 18:50:34', 4, 0, 0, 1, 'PRIVATE', '新浪娱乐讯 2月14日，北京大学光华管理学院关于翟天临“涉嫌学术不端”事件发布声明，称根据学校要求，2月12日，北京大学光华管理学院召开博士后工作小组会议，正式启动对翟天临“涉嫌学术不端”事件的调查。2月13日下午，学院召开职业道德和纪律委员会会议，对翟天临涉嫌学术不端的情况进行认真研究、作出初步认定，并根据国家及学校博士后管理有关规定提出初步处理意见。同日傍晚，学院按照程序，就初步认定结果和处理意见与翟天临本人进行了当面沟通。我们注意到翟天临今天公开表达退出博士后科研流动站的意愿，学院对于学术不端行为一直持零容忍态度，将依法依规按程序作出处理。　　近日，翟天临的“学术不端事件”受到了网友的广泛关注与讨论。2月14日下午，北京电影学院学院宣布此事已经进入正式调查阶段。随后，翟天临在其本人微博发布致歉信。翟天临在致歉信中称自己懊悔不已，深度自责并称是“虚荣心和侥幸心让我迷失了自己”。他表示在参加了一系列影视作品取得一定成绩后自己开始“飘飘然”，并将这种心态带入到论文写作过程中。翟天临还宣布正式申请退出北大博士后科研流动站相关工作，并积极接受北京电影学院一切调查，承担一切责任。sAASDASDASDASD', 0, '/img/favicon.png', '<p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">新浪娱乐讯 2月14日，北京大学光华管理学院关于翟天临“涉嫌学术不端”事件发布声明，称根据学校要求，2月12日，北京大学光华管理学院召开博士后工作小组会议，正式启动对翟天临“涉嫌学术不端”事件的调查。2月13日下午，学院召开职业道德和纪律委员会会议，对翟天临涉嫌学术不端的情况进行认真研究、作出初步认定，并根据国家及学校博士后管理有关规定提出初步处理意见。同日傍晚，学院按照程序，就初步认定结果和处理意见与翟天临本人进行了当面沟通。我们注意到翟天临今天公开表达退出博士后科研流动站的意愿，学院对于学术不端行为一直持零容忍态度，将依法依规按程序作出处理。</p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; color: rgb(77, 79, 83); font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, SimSun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">　　近日，翟天临的“学术不端事件”受到了网友的广泛关注与讨论。2月14日下午，北京电影学院学院宣布此事已经进入正式调查阶段。随后，翟天临在其本人微博发布致歉信。翟天临在致歉信中称自己懊悔不已，深度自责并称是“虚荣心和侥幸心让我迷失了自己”。他表示在参加了一系列影视作品取得一定成绩后自己开始“飘飘然”，并将这种心态带入到论文写作过程中。翟天临还宣布正式申请退出北大博士后科研流动站相关工作，并积极接受北京电影学院一切调查，承担一切责任。</p><p><br/></p><p>sA</p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p>ASDASD</p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p>ASDASD</p><p><br/></p>', '娱乐');
INSERT INTO `topic` VALUES (85, 3, 4, '2月15日测试', '2019-02-15 10:41:58', '2019-02-15 10:41:58', 5, 2, 0, 1, 'PRIVATE', '宣传视屏也重点展示了这个特点：手机屏幕上方“水滴”摄像头消失，听筒扬声器变成一道细缝，而后前置镜头从手机顶部弹出。此外，该机还将搭载4800万像数后置镜头，预计支持超级夜景功能。　　根据此前爆料，OPPO F11 Pro将采用联发科处理器，具体型号未知，猜测是联发科P90，因为这款处理器支持4800万像素摄像头。存储规格方面，该剧预计有6GB和128GB运存可选。　　弹出式前置镜头最早出现在vivo NEX手机上，增加这样一个机械结构目的就是为了提升屏幕占比，以实现真正的全面屏，如今OPPO也采用了相同的设计。目前还不知道该机是否会在国内上市，有一种说法是该机就是R19系列。（苏航）', 1, '/img/favicon.png', '<p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; font-family: &quot;Microsoft Yahei&quot;, 微软雅黑, &quot;STHeiti Light&quot;, 华文细黑, SimSun, 宋体, Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal;\">宣传视屏也重点展示了这个特点：手机屏幕上方“水滴”摄像头消失，听筒扬声器变成一道细缝，而后前置镜头从手机顶部弹出。此外，该机还将搭载4800万像数后置镜头，预计支持超级夜景功能。</p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; font-family: &quot;Microsoft Yahei&quot;, 微软雅黑, &quot;STHeiti Light&quot;, 华文细黑, SimSun, 宋体, Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal;\">　　根据此前爆料，OPPO F11 Pro将采用联发科处理器，具体型号未知，猜测是联发科P90，因为这款处理器支持4800万像素摄像头。存储规格方面，该剧预计有6GB和128GB运存可选。</p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; text-size-adjust: 100%; font-family: &quot;Microsoft Yahei&quot;, 微软雅黑, &quot;STHeiti Light&quot;, 华文细黑, SimSun, 宋体, Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal;\">　　弹出式前置镜头最早出现在vivo NEX手机上，增加这样一个机械结构目的就是为了提升屏幕占比，以实现真正的全面屏，如今OPPO也采用了相同的设计。目前还不知道该机是否会在国内上市，有一种说法是该机就是R19系列。（苏航）</p><p><br/></p>', '科技');
INSERT INTO `topic` VALUES (86, 1, 2, '2月15日第2次测试', '2019-02-15 11:45:19', '2019-02-15 11:45:19', 5, 0, 0, 1, 'PRIVATE', '猪年开市后A股渐入佳境，不少投资者开始按捺不住了，这也让刚从春节假期回归工作岗位的卖方分析师们忙碌起来。　　某TMT行业分析师向《每日经济新闻》记者表示：“昨天收到很多客户的咨询电话，都问这个时间还能买什么。”这位分析师给出的回答显得谨慎有加，其表示，“从前期超跌+近期涨幅较小的角度挖掘。”　　历史以来，每轮反弹该买什么都是让投资者困扰的一个问题。那么现在是跟风“折叠屏”、5G等硬科技？还是买“万金油”券商板块？目前，看看哪些板块还处于滞涨、超跌的状态，或许是投资人眼下更需要做的功课。　　折叠屏主题究竟还能火多久？　　疲弱了一年的A股最近开始逆袭了！中金公司策略团队日前指出，截至2月13日收盘，刨去几个异常市场，中国股市以本币计算今年初至今领先全球，这是多重因素共振下的上涨。　　当然，在火爆的行情之下，分析师在朋友圈秀行情、K线图的多了，被客户咨询、要求路演的也多了。　　某TMT行业分析师认为，在大涨之后，目前更应该从前期超跌+近期涨幅较小的角度挖掘标的。这样偏谨慎的态度在当下的市况中不无道理。有策略分析师认为，目前市场信心还在恢复过程中，在短线快速上涨之后，市场心态显得依然较为犹豫，部分投资者可能会选择短期获利了结，导致短线市场波动。44444', 1, '/img/favicon.png', '<p>猪年开市后A股渐入佳境，不少投资者开始按捺不住了，这也让刚从春节假期回归工作岗位的卖方分析师们忙碌起来。　　某TMT行业分析师向《每日经济新闻》记者表示：“昨天收到很多客户的咨询电话，都问这个时间还能买什么。”这位分析师给出的回答显得谨慎有加，其表示，“从前期超跌+近期涨幅较小的角度挖掘。”　　历史以来，每轮反弹该买什么都是让投资者困扰的一个问题。那么现在是跟风“折叠屏”、5G等硬科技？还是买“万金油”券商板块？目前，看看哪些板块还处于滞涨、超跌的状态，或许是投资人眼下更需要做的功课。　　折叠屏主题究竟还能火多久？　　疲弱了一年的A股最近开始逆袭了！中金公司策略团队日前指出，截至2月13日收盘，刨去几个异常市场，中国股市以本币计算今年初至今领先全球，这是多重因素共振下的上涨。　　当然，在火爆的行情之下，分析师在朋友圈秀行情、K线图的多了，被客户咨询、要求路演的也多了。　　某TMT行业分析师认为，在大涨之后，目前更应该从前期超跌+近期涨幅较小的角度挖掘标的。这样偏谨慎的态度在当下的市况中不无道理。有策略分析师认为，目前市场信心还在恢复过程中，在短线快速上涨之后，市场心态显得依然较为犹豫，部分投资者可能会选择短期获利了结，导致短线市场波动。44444</p>', '财经');
INSERT INTO `topic` VALUES (87, 2, 2, '2月15日第3次测试', '2019-02-15 11:58:49', '2019-02-15 11:58:49', 6, 0, 0, 1, 'PRIVATE', '雷霆和鹈鹕一战，威少今天30投18中，狂砍44分+14篮板+11助攻，再次完成三双。赛前，威少在雷霆队的得分一共18203分，加上这44分，威少18247分，成功超越了加里佩顿（18207分），成为雷霆队队史第一得分王，这是包括雷霆前身超音速时期的队史数据。威少昔日好兄弟，杜兰特的超音速和雷霆生涯一共得到了17566分，排在雷霆队史第三。加上这一个三双，威少已经连续11场完成三双，NBA历史第一纪录继续扩大，NBA历史第二名是张伯伦：连续9场三双', 0, '/img/favicon.png', '<p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; text-indent: 32px; background-color: rgb(255, 255, 255);\">雷霆和鹈鹕一战，威少今天30投18中，狂砍44分+14篮板+11助攻，再次完成三双。赛前，威少在雷霆队的得分一共18203分，加上这44分，威少18247分，成功超越了加里佩顿（18207分），成为雷霆队队史第一得分王，这是包括雷霆前身超音速时期的队史数据。</span></p><p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; text-indent: 32px; background-color: rgb(255, 255, 255);\">威少昔日好兄弟，杜兰特的超音速和雷霆生涯一共得到了17566分，排在雷霆队史第三。加上这一个三双，威少已经连续11场完成三双，NBA历史第一纪录继续扩大，NBA历史第二名是张伯伦：连续9场三双</span></p>', '体育');
INSERT INTO `topic` VALUES (88, 2, 2, '2月21日测试', '2019-02-21 14:22:41', '2019-02-21 14:22:41', 5, 0, 0, 1, 'PRIVATE', '春节假期刚过，紧接着2019年艺考校考拉开帷幕。虽然艺考改革对考生文化成绩要求提高以及考试难度增加，但这并没有影响考生报考热情，今年多所院校报考人数创下新高。根据统计，从2014年至2019年，北京电影学院报考人数持续增长，六年间，增加4万多人。报考人数方面，2019年北京电影学院报考总人次达59059，同比增长31.02%，创历史新高。2019年中央戏剧学院报考总人次达67946，同比增长31%，同样为历年报考人数之最。此外，上海戏剧学院、中国传媒大学(分数线,专业设置)2019艺考报名人数分别为45884人、5万人，均创下历史新高。招生计划方面，2019年北京电影学院和上海戏剧学院，招生计划较往年分别增加30人和20人。2019年中国传媒大学招生计划增加较多，为90人。而中央戏剧学院2019年招生计划有所下调，减少25人。　　在报名人数不断增长的情况下，除了中央戏剧学院招生计划较去年有所下调外，其余三所学校招生计划均有所增加，但由于报考人数涨幅过大，从2018年与2019年院校的整体报录比可以看出，2019年各校艺考竞争形势均较去年更为严峻。2018年中央戏剧学院整体报录比为86：1，2019年达到119：1，超过了北京电影学院的114：1。　　除了学校整体报考难度较往年有所加大，各学校热门专业的竞争也更为激烈。', 0, '/img/favicon.png', '<p><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">春节假期刚过，紧接着2019年艺考校考拉开帷幕。虽然艺考改革对考生文化成绩要求提高以及考试难度增加，但这并没有影响考生报考热情，今年多所院校报考人数创下新高。根据统计，从2014年至2019年，北京电影学院报考人数持续增长，六年间，增加4万多人。</span></p><p><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">报考人数方面，2019年北京电影学院报考总人次达59059，同比增长31.02%，创历史新高。2019年中央戏剧学院报考总人次达67946，同比增长31%，同样为历年报考人数之最。此外，上海戏剧学院、</span><a href=\"http://kaoshi.edu.sina.com.cn/college/c/50203.shtml\" target=\"_blank\" style=\"text-decoration-line: none; outline: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; color: rgb(0, 113, 255); font-size: 18px; letter-spacing: 1px; white-space: normal;\">中国传媒大学</a><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">(</span><a href=\"http://kaoshi.edu.sina.com.cn/college/c/score/50203.shtml\" target=\"_blank\" style=\"text-decoration-line: none; outline: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; color: rgb(0, 113, 255); font-size: 18px; letter-spacing: 1px; white-space: normal;\">分数线</a><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">,</span><a href=\"http://kaoshi.edu.sina.com.cn/college/c/major/50203.shtml\" target=\"_blank\" style=\"text-decoration-line: none; outline: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; color: rgb(0, 113, 255); font-size: 18px; letter-spacing: 1px; white-space: normal;\">专业设置</a><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\">)2019艺考报名人数分别为45884人、5万人，均创下历史新高。</span></span></p><p><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"></span></span></p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">招生计划方面，2019年北京电影学院和上海戏剧学院，招生计划较往年分别增加30人和20人。2019年中国传媒大学招生计划增加较多，为90人。而中央戏剧学院2019年招生计划有所下调，减少25人。</p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">　　在报名人数不断增长的情况下，除了中央戏剧学院招生计划较去年有所下调外，其余三所学校招生计划均有所增加，但由于报考人数涨幅过大，从2018年与2019年院校的整体报录比可以看出，2019年各校艺考竞争形势均较去年更为严峻。2018年中央戏剧学院整体报录比为86：1，2019年达到119：1，超过了北京电影学院的114：1。</p><p style=\"margin-top: 0px; margin-bottom: 30px; padding: 0px; font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px; white-space: normal;\">　　除了学校整体报考难度较往年有所加大，各学校热门专业的竞争也更为激烈。</p><p><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><span style=\"font-family: &quot;Hiragino Sans GB&quot;, &quot;Microsoft Yahei&quot;, 微软雅黑, Simsun, 宋体, Arial; font-size: 18px; letter-spacing: 1px;\"><br/></span></span><br/></p>', '娱乐');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `user_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1:普通用户 2:普通管理员 3:超级管理员',
  `locked` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0:未锁定 1:锁定',
  `credit` int(11) NOT NULL COMMENT '积分',
  `photo` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像地址',
  `grade` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '年级',
  `academy` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学院',
  `last_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ip地址',
  `last_visit` date NULL DEFAULT NULL,
  `signature` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `backPicture` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `back_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `validcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'ydc', '45', 3, 0, 547, 'userImage/68e83bea-727d-41dc-919d-c42b19d37803.png', '大二', '计算机', '0:0:0:0:0:0:0:1', '2019-03-04', '舔狗舔到最后什么都有', NULL, '', 'userBg/c38414de-8982-45fb-b67a-3793a24b0fb4.png', NULL);
INSERT INTO `user` VALUES (4, 'untilyouydc', '123456a', 2, 0, 887, 'userImage/ec299cf8-f134-4001-b1ed-7f2f5a0c2063.png', 'kk', 'we', '0:0:0:0:0:0:0:1', '2019-03-04', '舔狗舔到最后什么都有', NULL, '15087581161@163.com', 'userBg/617c606d-19bd-4629-bfff-0193c9bed5e3.png', '0146');
INSERT INTO `user` VALUES (5, 'dtw', '123', 1, 0, 12, '', 'kk', 'we', '222', '2019-01-08', NULL, NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (6, 'dwww', '123', 1, 1, 12, '', 'kk', 'we', '222', '2019-01-08', NULL, NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (7, 'dwwwtw', '123', 1, 1, 12, 'dff', 'kk', 'we', '222', '2019-01-08', NULL, NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (8, 'kdwww', '123', 1, 1, 12, 'dff', 'kk', 'we', '222', '2019-01-08', NULL, NULL, '', NULL, NULL);
INSERT INTO `user` VALUES (9, 'ydcc', '123456a', 1, 0, 5, '/img/coding.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-09', NULL, NULL, '15087581162@163.com', NULL, NULL);
INSERT INTO `user` VALUES (10, 'ydcct', '123456a', 1, 0, 5, '/img/coding.jpg', ' ', 'genxing', '0:0:0:0:0:0:0:1', '2019-02-09', NULL, NULL, '15087581163@163.com', NULL, NULL);
INSERT INTO `user` VALUES (11, 'ydcctr', '123456a', 1, 0, 5, '/img/coding.jpg', ' ', 'genxing', '0:0:0:0:0:0:0:1', '2019-02-09', NULL, NULL, '15087581166@163.com', NULL, NULL);
INSERT INTO `user` VALUES (12, 'dww1234', '123456a', 1, 0, 5, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '2435997588@163.com', NULL, NULL);
INSERT INTO `user` VALUES (13, 'dwydc', '123456a', 0, 1, 5, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '150875811669@163.com', NULL, NULL);
INSERT INTO `user` VALUES (14, 'dwydcc', '123456a', 0, 1, 5, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '150875811667@163.com', NULL, NULL);
INSERT INTO `user` VALUES (15, 'wade', '123456a', 1, 0, 10, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '1508758116677@163.com', NULL, NULL);
INSERT INTO `user` VALUES (16, 'wade2', '123456a', 1, 0, 5, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '150875811663@163.com', NULL, NULL);
INSERT INTO `user` VALUES (17, 'wadw2', '1234567a', 1, 0, 5, '/img/code.jpg', ' ', '计算机', '0:0:0:0:0:0:0:1', '2019-02-10', NULL, NULL, '150875811366@163.com', NULL, NULL);
INSERT INTO `user` VALUES (18, 'dwydcc12', '123', 0, 0, 5, '/img/code.jpg', '', '', '0:0:0:0:0:0:0:1', '2019-02-14', NULL, NULL, '1508758116222@163.com', NULL, NULL);
INSERT INTO `user` VALUES (19, '123ydc', '123', 0, 1, 10, '/img/code.jpg', '', '', '0:0:0:0:0:0:0:1', '2019-02-14', NULL, NULL, '150875811262@163.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
