/*
 Navicat Premium Data Transfer

 Source Server         : localhost-8.0
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : case_sys

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 18/05/2022 13:07:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `user_id` int NULL DEFAULT NULL COMMENT '患者ID',
  `status` int NULL DEFAULT 0 COMMENT '是否匿名',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `creat_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `cases_type` int NULL DEFAULT 0 COMMENT '类型',
  `start_hospitalized` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入院诊断',
  `end_hospitalized` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出院诊断',
  `start_reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入院病情',
  `end_reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出院病情',
  `record` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过敏记录',
  `blood_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '血型',
  `surgical_record` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手术记录',
  `autograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名信息',
  `create_by_id` int NULL DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `cases_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cases
-- ----------------------------
INSERT INTO `cases` VALUES (6, '测试医嘱', 19, 1, '<p>恶趣味翁恶趣味</p><blockquote>人情味儿恶趣味</blockquote><p><img src=\"/images/20220402-142848-AE9318B2FBB5.jpg\" style=\"max-width:100%;\"><br></p>', '2022-04-02 06:28:50', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (7, '测试病例', 19, 0, NULL, '2022-04-02 06:29:32', 1, '测试', '测试', '测试', '测试', '测试', '测试', '测试', '测试', NULL);
INSERT INTO `cases` VALUES (8, '3123', 19, 0, '<p>32131</p><p><img src=\"/images/20220402-142959-1EC18B0FEC01.jpg\" style=\"max-width:100%;\"><br></p>', '2022-04-02 06:30:01', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (9, '胃炎', 22, 1, '<p>忌口，忌辛辣食物</p>', '2022-04-16 05:07:09', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (10, '胃镜', 22, 1, '<p>。。。。</p>', '2022-04-16 05:08:37', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (11, '胃炎', 22, 1, NULL, '2022-04-16 05:10:55', 1, '胃炎', '胃穿孔', '胃阵痛', '好转', '无', '无', '无', '医生', NULL);
INSERT INTO `cases` VALUES (13, '分泌物', 22, 0, '<p>尿量 200ml</p>', '2022-04-16 05:15:27', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (14, '体温', 22, 1, '<p>1月1日36°</p><p>1月2日35.7°</p>', '2022-04-16 05:18:49', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (15, '体温', 22, 1, '<p>35度</p>', '2022-04-16 05:20:41', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (16, '分泌物', 22, 1, '<p>尿量100ml</p>', '2022-04-16 05:21:37', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (17, '胃镜检查', 23, 1, '<p><img src=\"/images/20220416-132723-1DE4F41FD5D6.jpg\" style=\"max-width:100%;\"><br></p><p>有明显炎症</p>', '2022-04-16 05:27:48', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (18, 'g', 22, 1, '<p><img src=\"/images/20220416-133503-98FF3F84CB2C.jpg\" style=\"max-width:100%;\"><br></p><p>清淡饮食</p>', '2022-04-16 05:35:15', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (19, 'dfs', 24, 1, '<p>rqegtt</p>', '2022-04-16 05:36:06', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (20, 'rg', 23, 0, '<p>qerr</p>', '2022-04-16 05:36:21', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (21, 'hj', 24, 0, '<p>bxv</p>', '2022-04-16 10:52:25', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (22, 'e', 23, 0, '<p><img src=\"/images/20220416-185311-76EF951FB91A.jpg\" style=\"max-width:100%;\"></p><p>regt<br></p>', '2022-04-16 10:53:20', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (23, '111', 22, 1, '<p>eee</p>', '2022-05-14 02:24:26', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (24, '肠胃絮乱', 30, 1, '<p>奥美拉素肠溶片</p>', '2022-05-16 13:19:00', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (25, '3345', 22, 1, '<p>5657</p>', '2022-05-17 07:00:21', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (26, '1111', 19, 0, '<p>111</p>', '2022-05-17 13:57:16', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (27, '1', 19, 0, '<p>11111</p>', '2022-05-17 16:05:33', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (28, '1111', 19, 0, '<p>1111111111111111111111111111111111</p>', '2022-05-17 16:08:29', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cases` VALUES (29, '1111111', 23, 0, '<p>11111111111111</p>', '2022-05-17 16:22:17', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 26);
INSERT INTO `cases` VALUES (30, '111', 28, 0, '<p>1111111111111111</p>', '2022-05-17 16:34:08', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 26);
INSERT INTO `cases` VALUES (31, '111', 27, 0, '<p>111111111111111111</p>', '2022-05-17 16:38:01', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 26);

-- ----------------------------
-- Table structure for cost
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '费用ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `money` int NULL DEFAULT NULL COMMENT '金额',
  `creat_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES (2, '棉签', '医疗器械', 2, '2022-04-02 06:25:01');
INSERT INTO `cost` VALUES (3, '三七', '中药材', 7, '2022-04-16 05:12:46');

-- ----------------------------
-- Table structure for leaves
-- ----------------------------
DROP TABLE IF EXISTS `leaves`;
CREATE TABLE `leaves`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言内容',
  `leaves_time` datetime NULL DEFAULT NULL COMMENT '留言时间',
  `reply_con` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `leaves_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leaves
-- ----------------------------
INSERT INTO `leaves` VALUES (1, 20, '111', '2022-04-17 14:16:23', NULL, NULL);
INSERT INTO `leaves` VALUES (2, 21, '333', '2022-04-17 14:29:45', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `head_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/Y-Admin/images/avatar.png' COMMENT '头像',
  `role` int NULL DEFAULT 0 COMMENT '身份【0-患者，1-护士，2-医生，3-管理员】',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `identity_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (14, 'admin', '1', '阿发', 0, '13125256699', '/Y-Admin/images/avatar.png', 3, NULL, NULL, NULL);
INSERT INTO `user` VALUES (19, NULL, NULL, '琳琳', 1, '13125257788', '/Y-Admin/images/avatar.png', 0, '测试地址', '360521199802020030', 22);
INSERT INTO `user` VALUES (20, '护士', '1', '护士', 0, '13125257788', '/images/20220416-133703-C9C2924F5FAD.jpg', 1, '测试地址', '360521199503030020', 22);
INSERT INTO `user` VALUES (21, '医生', '1', '医生', 1, '13125256699', '/Y-Admin/images/avatar.png', 2, '韩国苦涩的鼓励收到了', '360521199603030020', 22);
INSERT INTO `user` VALUES (22, NULL, NULL, '露露', 0, '12345678908', '/Y-Admin/images/avatar.png', 0, '广西桂林临桂区', '111111111111111111', 12);
INSERT INTO `user` VALUES (23, NULL, NULL, '当时', 1, '14532668368', '/Y-Admin/images/avatar.png', 0, '共和国是否会', '434677933254657623', 34);
INSERT INTO `user` VALUES (24, NULL, NULL, 'ggn', 0, '14567677813', '/Y-Admin/images/avatar.png', 0, 'fhgjgngf', '454535565667246784', 25);
INSERT INTO `user` VALUES (25, '李护士', '1', '李护士', 0, '18749387246', '/Y-Admin/images/avatar.png', 1, '国家的共和国的', '354546566772244455', 27);
INSERT INTO `user` VALUES (26, '2', '1', '王医生', 1, '18976579387', '/Y-Admin/images/avatar.png', 2, '规范和旧金山', '486868768763616797', 47);
INSERT INTO `user` VALUES (27, NULL, NULL, '36', 1, '11111111464', '/Y-Admin/images/avatar.png', 0, 'htwyetrj', '265657888844444573', 34);
INSERT INTO `user` VALUES (28, NULL, NULL, 'gfh', 0, '12356368426', '/Y-Admin/images/avatar.png', 0, 'gxjshthtyyt', '246689345145796532', 24);
INSERT INTO `user` VALUES (29, 'ys', '1', '丰富', 0, '13345565678', '/Y-Admin/images/avatar.png', 2, '对方德国法国和', '456778467975324674', 34);
INSERT INTO `user` VALUES (30, NULL, NULL, '张三', 1, '13578042579', '/Y-Admin/images/avatar.png', 0, '广西桂林市金华县', '423358964257985336', 15);

SET FOREIGN_KEY_CHECKS = 1;
