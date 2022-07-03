/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 100410
 Source Host           : localhost:3306
 Source Schema         : lsc

 Target Server Type    : MySQL
 Target Server Version : 100410
 File Encoding         : 65001

 Date: 04/07/2022 01:18:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `tip` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `submitter` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `r_date` timestamp(0) NULL DEFAULT current_timestamp(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `rid` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `fk`(`tip`) USING BTREE,
  CONSTRAINT `fk` FOREIGN KEY (`tip`) REFERENCES `topic` (`tid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('72712754583742e5ba1b332016de5696', '', 'ma7044020', '2022-07-02 00:00:37', '296926f4547e452a931ce1ce404643c3');
INSERT INTO `reply` VALUES ('70385b6837e1453a94b6a0bc779fb0e1', '大大大', 'ma7044020', '2022-06-27 16:38:52', '3417e5037cab499c8d982a9b2c4f69a6');
INSERT INTO `reply` VALUES ('b1aa42582ed84967b80de9939a0ddf11', 'gffff', 'ma7044020', '2022-07-01 23:37:56', '44447133b1a74416aec9c44bd8ea3da0');
INSERT INTO `reply` VALUES ('72712754583742e5ba1b332016de5696', '????', 'ma7044020', '2022-07-03 00:28:23', '654382048a064e75acafb25623ac7688');
INSERT INTO `reply` VALUES ('72712754583742e5ba1b332016de5696', 'sb', 'ma7044020', '2022-06-30 14:38:45', '6f80ecef53d546e29efbbf762cd81060');
INSERT INTO `reply` VALUES ('309dec988c6b4bf6a9b170dd09904987', '对对对', 'ma7044020', '2022-07-02 23:49:54', '805d047e56f1402d99a8d04a6ab491c0');
INSERT INTO `reply` VALUES ('19596691db7442c092210eac4c416d2b', '范德萨发生', 'ma954854650', '2022-06-28 09:04:47', 'bbc2a117324a4b96992f01db91bb6920');
INSERT INTO `reply` VALUES ('d2e97a438f6a4cc5a474a0f94fa01952', '对对对', 'ma954854650', '2022-07-01 23:33:28', 'bc0a2544ef4144a1b31021c684295332');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic`  (
  `tid` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `t_date` timestamp(0) NULL DEFAULT current_timestamp(0),
  `submitter` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('11', 'oracle如何热备份', 'rewqq', '2022-06-27 01:31:39', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('12', 'fdas', 'rew', '2022-06-27 11:42:40', 'ma7044020', '娱乐');
INSERT INTO `topic` VALUES ('13', 'fds', 'fdsa', '2022-06-27 12:32:55', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('19596691db7442c092210eac4c416d2b', 'ewq', 'eee', '2022-06-27 16:05:18', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('2', '谈谈springboot的优点', '很多', '2022-06-19 20:11:43', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('309dec988c6b4bf6a9b170dd09904987', '如何实现依赖注入', '.....', '2022-06-27 12:47:57', 'ma954854650', '技术');
INSERT INTO `topic` VALUES ('70385b6837e1453a94b6a0bc779fb0e1', 'rrrrr', 'rerw', '2022-06-27 16:38:33', 'ma7044020', '娱乐');
INSERT INTO `topic` VALUES ('72712754583742e5ba1b332016de5696', 'fdsf', 'fds', '2022-06-28 10:02:42', 'ma954854650', '技术');
INSERT INTO `topic` VALUES ('9a4218c58daa4e92a90b06fc164b6643', 'fdsa', 'fd', '2022-06-27 12:47:57', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('a7cd92e376cd48968ed6de4850962d80', '如何评价xxx', 'eeee', '2022-06-27 16:18:53', 'ma954854650', '娱乐');
INSERT INTO `topic` VALUES ('b1aa42582ed84967b80de9939a0ddf11', 'ffds', 'fdff', '2022-06-28 10:02:42', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('d2e97a438f6a4cc5a474a0f94fa01952', '如何xxx', '恩恩额恩', '2022-06-28 10:02:42', 'ma954854650', '技术');
INSERT INTO `topic` VALUES ('daeda4a7101d4ab5817c39dbc81c4d0c', 'fd', 'ffffff', '2022-06-27 11:42:40', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('f6da25a7d2a34ab78ef8cbfdbee74516', '', '', '2022-06-28 10:02:42', 'ma7044020', '技术');
INSERT INTO `topic` VALUES ('fb9a971528314c468bd920cc8d650d3b', 'ewq', 'eqw', '2022-06-27 12:47:57', 'ma7044020', '技术');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `showname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `signdate` timestamp(0) NOT NULL DEFAULT current_timestamp(0),
  `photopath` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `exp` int(30) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('ma7044020', '7044020jkl', '954854650@qq.com', '踩到猫儿', '2022-05-30 15:02:26', '\\static\\img\\profile_photo\\20210330215445.jpg', 500);
INSERT INTO `users` VALUES ('ma954854650', '7044020jkl', '2996792808@qq.com', '双岛', '2022-06-19 21:21:29', '\\static\\img\\profile_photo\\wallhaven-725m2e.jpg', 410);

SET FOREIGN_KEY_CHECKS = 1;
