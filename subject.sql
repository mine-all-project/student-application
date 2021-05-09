/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : subject

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 09/05/2021 21:59:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sys_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK4w5bjgy9j6e12hhy0yb79v9t2`(`subject_id`) USING BTREE,
  INDEX `FK9dvo4gaxjycvb9qmnpd2dnxrp`(`sys_user_id`) USING BTREE,
  CONSTRAINT `FK4w5bjgy9j6e12hhy0yb79v9t2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK9dvo4gaxjycvb9qmnpd2dnxrp` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discuss
-- ----------------------------

-- ----------------------------
-- Table structure for discuss_children
-- ----------------------------
DROP TABLE IF EXISTS `discuss_children`;
CREATE TABLE `discuss_children`  (
  `discuss_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `children_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `UK_r0a2527fy2keda810nv6tpuau`(`children_id`) USING BTREE,
  INDEX `FK92kegb1f48s07kk0ek2jq23x5`(`discuss_id`) USING BTREE,
  CONSTRAINT `FK92kegb1f48s07kk0ek2jq23x5` FOREIGN KEY (`discuss_id`) REFERENCES `discuss` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKeg5krr2k95t0ysp6ew905e1li` FOREIGN KEY (`children_id`) REFERENCES `discuss` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discuss_children
-- ----------------------------

-- ----------------------------
-- Table structure for file_data
-- ----------------------------
DROP TABLE IF EXISTS `file_data`;
CREATE TABLE `file_data`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_data
-- ----------------------------

-- ----------------------------
-- Table structure for paper_file_info
-- ----------------------------
DROP TABLE IF EXISTS `paper_file_info`;
CREATE TABLE `paper_file_info`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文件名',
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '附件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for papers1
-- ----------------------------
DROP TABLE IF EXISTS `papers1`;
CREATE TABLE `papers1`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '正文',
  `reader_count` int NOT NULL,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `create_by_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK7mthoi9v5qwmgqraudofbfr34`(`create_by_id`) USING BTREE,
  CONSTRAINT `FK7mthoi9v5qwmgqraudofbfr34` FOREIGN KEY (`create_by_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of papers1
-- ----------------------------

-- ----------------------------
-- Table structure for papers1_file_list
-- ----------------------------
DROP TABLE IF EXISTS `papers1_file_list`;
CREATE TABLE `papers1_file_list`  (
  `papers1_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_list_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FKj7yh7t8kkspkcyh91w49b9ys3`(`file_list_id`) USING BTREE,
  INDEX `FK5q74jo5x9lxxjwd23aw02kvmp`(`papers1_id`) USING BTREE,
  CONSTRAINT `FK5q74jo5x9lxxjwd23aw02kvmp` FOREIGN KEY (`papers1_id`) REFERENCES `papers1` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj7yh7t8kkspkcyh91w49b9ys3` FOREIGN KEY (`file_list_id`) REFERENCES `paper_file_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of papers1_file_list
-- ----------------------------

-- ----------------------------
-- Table structure for papers1_tags_list
-- ----------------------------
DROP TABLE IF EXISTS `papers1_tags_list`;
CREATE TABLE `papers1_tags_list`  (
  `papers1_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tags_list_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FKq69ydrnd3qjmfkeahelqn0hr9`(`tags_list_id`) USING BTREE,
  INDEX `FK1qloi6y174xvjftphxn2sy286`(`papers1_id`) USING BTREE,
  CONSTRAINT `FK1qloi6y174xvjftphxn2sy286` FOREIGN KEY (`papers1_id`) REFERENCES `papers1` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKq69ydrnd3qjmfkeahelqn0hr9` FOREIGN KEY (`tags_list_id`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of papers1_tags_list
-- ----------------------------

-- ----------------------------
-- Table structure for request_pull
-- ----------------------------
DROP TABLE IF EXISTS `request_pull`;
CREATE TABLE `request_pull`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 0:生效 1:未生效 2:已拒绝',
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sys_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3bvi2fg4epvouqc6ir3ft6ljs`(`subject_id`) USING BTREE,
  INDEX `FK2bm4dqkep33acc973jwbado4e`(`sys_user_id`) USING BTREE,
  CONSTRAINT `FK2bm4dqkep33acc973jwbado4e` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK3bvi2fg4epvouqc6ir3ft6ljs` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of request_pull
-- ----------------------------

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `begin_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `is_share` tinyint(1) NULL DEFAULT 1 COMMENT '是否共享 0:是 1:否',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 0:立项 1:在研 2:结题 -1:驳回',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `create_by_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqhs8vvkovnc9ypxvygg1o89b1`(`create_by_id`) USING BTREE,
  CONSTRAINT `FKqhs8vvkovnc9ypxvygg1o89b1` FOREIGN KEY (`create_by_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('b5276e63-6643-456c-a228-ba44e7e1b42d', '2021-04-13 14:59:34', 0, '2021-04-13 14:59:34', '2021-04-13 14:59:34', NULL, 1, 0, '11', '001');

-- ----------------------------
-- Table structure for subject_person_list
-- ----------------------------
DROP TABLE IF EXISTS `subject_person_list`;
CREATE TABLE `subject_person_list`  (
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `person_list_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FK8p7qrn898dxcdk0uk3rdisxw1`(`person_list_id`) USING BTREE,
  INDEX `FKg3mcryni3g99fcm6huah0ssyf`(`subject_id`) USING BTREE,
  CONSTRAINT `FK8p7qrn898dxcdk0uk3rdisxw1` FOREIGN KEY (`person_list_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKg3mcryni3g99fcm6huah0ssyf` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_person_list
-- ----------------------------
INSERT INTO `subject_person_list` VALUES ('b5276e63-6643-456c-a228-ba44e7e1b42d', 'b719d4e4-b2de-4dc5-bd2a-571ff732c673');

-- ----------------------------
-- Table structure for subject_share
-- ----------------------------
DROP TABLE IF EXISTS `subject_share`;
CREATE TABLE `subject_share`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 0:生效 1:未生效',
  `share_by_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK37xxmur411gvcdntpap53w5g0`(`share_by_id`) USING BTREE,
  INDEX `FKk971qll94monadhts9n8ogy4n`(`subject_id`) USING BTREE,
  CONSTRAINT `FK37xxmur411gvcdntpap53w5g0` FOREIGN KEY (`share_by_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKk971qll94monadhts9n8ogy4n` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_share
-- ----------------------------

-- ----------------------------
-- Table structure for subject_share_share_user
-- ----------------------------
DROP TABLE IF EXISTS `subject_share_share_user`;
CREATE TABLE `subject_share_share_user`  (
  `subject_share_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `share_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FK9pw3xlrsvmakhnrfkhdavr2ad`(`share_user_id`) USING BTREE,
  INDEX `FKfaptoh40xx1ngscbyuppret2h`(`subject_share_id`) USING BTREE,
  CONSTRAINT `FK9pw3xlrsvmakhnrfkhdavr2ad` FOREIGN KEY (`share_user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfaptoh40xx1ngscbyuppret2h` FOREIGN KEY (`subject_share_id`) REFERENCES `subject_share` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_share_share_user
-- ----------------------------

-- ----------------------------
-- Table structure for subject_step
-- ----------------------------
DROP TABLE IF EXISTS `subject_step`;
CREATE TABLE `subject_step`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '阶段内容',
  `index_num` tinyint(1) NULL DEFAULT 0 COMMENT '阶段数',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态 0:正常 1:已结束',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_step
-- ----------------------------
INSERT INTO `subject_step` VALUES ('6cc0a8ea-8df1-4916-9b06-39bf8eafa2d8', '2021-04-13 14:59:34', 0, '2021-04-13 14:59:34', '11', 0, 0);

-- ----------------------------
-- Table structure for subject_step_list
-- ----------------------------
DROP TABLE IF EXISTS `subject_step_list`;
CREATE TABLE `subject_step_list`  (
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `step_list_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `UK_h8hjuvdnqn87je692psqrv31u`(`step_list_id`) USING BTREE,
  INDEX `FK1ayop8ogvnxy52n2l7wmhyua3`(`subject_id`) USING BTREE,
  CONSTRAINT `FK1ayop8ogvnxy52n2l7wmhyua3` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfc925gowin0kaxotyc7vjlhrw` FOREIGN KEY (`step_list_id`) REFERENCES `subject_step` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_step_list
-- ----------------------------
INSERT INTO `subject_step_list` VALUES ('b5276e63-6643-456c-a228-ba44e7e1b42d', '6cc0a8ea-8df1-4916-9b06-39bf8eafa2d8');

-- ----------------------------
-- Table structure for subject_step_result_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_step_result_info`;
CREATE TABLE `subject_step_result_info`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `file_name` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文件名',
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '附件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_step_result_info
-- ----------------------------

-- ----------------------------
-- Table structure for subject_step_result_infos
-- ----------------------------
DROP TABLE IF EXISTS `subject_step_result_infos`;
CREATE TABLE `subject_step_result_infos`  (
  `subject_step_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `result_infos_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `UK_gxtl76i89lj7anvtqqrbyoju2`(`result_infos_id`) USING BTREE,
  INDEX `FK7f3gaporr8uvickglk8favpmm`(`subject_step_id`) USING BTREE,
  CONSTRAINT `FK7f3gaporr8uvickglk8favpmm` FOREIGN KEY (`subject_step_id`) REFERENCES `subject_step` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKne6ewg4c35iopyd2nw64peo5m` FOREIGN KEY (`result_infos_id`) REFERENCES `subject_step_result_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_step_result_infos
-- ----------------------------

-- ----------------------------
-- Table structure for subject_tags
-- ----------------------------
DROP TABLE IF EXISTS `subject_tags`;
CREATE TABLE `subject_tags`  (
  `subject_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tags_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `UK_m7i9nf6ykd5jigq9jk6gkff3d`(`tags_id`) USING BTREE,
  INDEX `FKm4cw5jb7bwnppv1qk02j4v46k`(`subject_id`) USING BTREE,
  CONSTRAINT `FKm4cw5jb7bwnppv1qk02j4v46k` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsmh2wmaj0i5btibhnwtu7wqd1` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_tags
-- ----------------------------
INSERT INTO `subject_tags` VALUES ('b5276e63-6643-456c-a228-ba44e7e1b42d', '88d058b6-0b69-4c9b-a96f-6c9fea04d800');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `level` int NULL DEFAULT 1 COMMENT '菜单等级',
  `menu_type` int NULL DEFAULT 0 COMMENT '菜单类型 1:目录 2:菜单 3:外链 4:按钮',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级菜单id',
  `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权标识',
  `sort` int NULL DEFAULT -1 COMMENT '菜单排序号',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_sys_menus`;
CREATE TABLE `sys_role_sys_menus`  (
  `sys_role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sys_menus_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FK88ixyb7r4xwfvb5s4id1gk4kn`(`sys_menus_id`) USING BTREE,
  INDEX `FK13xavwd65ufacrjkjk1vdwubg`(`sys_role_id`) USING BTREE,
  CONSTRAINT `FK13xavwd65ufacrjkjk1vdwubg` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK88ixyb7r4xwfvb5s4id1gk4kn` FOREIGN KEY (`sys_menus_id`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_sys_menus
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `age` int NOT NULL DEFAULT 0 COMMENT '年龄',
  `is_admin` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否为超级管理员 0:是 1:否',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '用户状态标记 0:正常 1:禁用',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `role` tinyint(1) NULL DEFAULT NULL COMMENT '角色',
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `last_modified_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后操作用户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_51bvuyvihefoh4kp5syh2jpi4`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('001', '2021-03-24 16:56:10', b'0', '2021-04-12 04:51:36', 20, b'1', 'admin', '1bbd886460827015e5d605ed44252251', b'0', 'admin', 0, '162165436@qq.com', NULL);
INSERT INTO `sys_user` VALUES ('04ff7025-276d-4a15-b450-823bc0adb43a', '2021-04-13 14:59:17', b'0', '2021-04-13 14:59:17', 2, b'1', '22', NULL, b'0', '22', 1, '294046317@qq.com', NULL);
INSERT INTO `sys_user` VALUES ('b719d4e4-b2de-4dc5-bd2a-571ff732c673', '2021-04-13 14:57:30', b'0', '2021-04-13 15:01:27', 11, b'1', '用户1', '', b'0', '用户1', 2, '294046317@qq.com', NULL);

-- ----------------------------
-- Table structure for sys_user_sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_sys_roles`;
CREATE TABLE `sys_user_sys_roles`  (
  `sys_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sys_roles_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FKp0li124wdg92xspfa3gy0on4q`(`sys_roles_id`) USING BTREE,
  INDEX `FKbc2hxke5hdejnwj0iuwq5te4g`(`sys_user_id`) USING BTREE,
  CONSTRAINT `FKbc2hxke5hdejnwj0iuwq5te4g` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKp0li124wdg92xspfa3gy0on4q` FOREIGN KEY (`sys_roles_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tags
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tags`;
CREATE TABLE `sys_user_tags`  (
  `sys_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tags_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `FK1pwi25y9n3cetvntu6wkiw820`(`tags_id`) USING BTREE,
  INDEX `FK4pksxy6mexxv33gobmkcikab1`(`sys_user_id`) USING BTREE,
  CONSTRAINT `FK1pwi25y9n3cetvntu6wkiw820` FOREIGN KEY (`tags_id`) REFERENCES `tags` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK4pksxy6mexxv33gobmkcikab1` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_tags
-- ----------------------------
INSERT INTO `sys_user_tags` VALUES ('04ff7025-276d-4a15-b450-823bc0adb43a', '88d058b6-0b69-4c9b-a96f-6c9fea04d800');
INSERT INTO `sys_user_tags` VALUES ('b719d4e4-b2de-4dc5-bd2a-571ff732c673', '7ad5f518-476f-4ae8-8b11-6c23638b1cbf');
INSERT INTO `sys_user_tags` VALUES ('b719d4e4-b2de-4dc5-bd2a-571ff732c673', '9d35184b-40e8-4132-9c8c-5f6278ee33fd');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `color` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签样式',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES ('7ad5f518-476f-4ae8-8b11-6c23638b1cbf', '2021-04-13 14:56:12', 0, '2021-04-13 14:56:12', '#ff0000', '测试1');
INSERT INTO `tags` VALUES ('88d058b6-0b69-4c9b-a96f-6c9fea04d800', '2021-04-13 14:56:27', 0, '2021-04-13 14:56:27', '#00FF00', '测试');
INSERT INTO `tags` VALUES ('9d35184b-40e8-4132-9c8c-5f6278ee33fd', '2021-04-13 14:56:47', 0, '2021-04-13 14:56:47', '#0000ff', '测试3');

SET FOREIGN_KEY_CHECKS = 1;
