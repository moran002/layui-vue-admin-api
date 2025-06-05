/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : localhost:3306
 Source Schema         : moran

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 05/06/2025 15:07:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `sort` int NOT NULL DEFAULT 0 COMMENT '字典排序',
  `label` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'name',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目录',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `sort` int NOT NULL DEFAULT 1 COMMENT '排序',
  `is_show` tinyint NOT NULL DEFAULT 1 COMMENT '是否可见',
  `type` int NOT NULL DEFAULT 1 COMMENT '类型:1:菜单,2:按钮',
  `api` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口api',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, 'Workbench', '/index', 'BasicLayout', '首页', 0, 1, 1, NULL, 'layui-icon-home', '/index/index', '2023-10-11 10:47:32', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (2, 1, 'Index', '/index/index', '/index/index/index.vue', '首页', 1, 1, 1, NULL, 'layui-icon-util', NULL, '2023-10-11 10:51:04', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (3, NULL, 'System', '/system', 'BasicLayout', '系统管理', 1, 1, 1, NULL, 'layui-icon-set', NULL, '2023-10-11 11:00:56', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (4, 3, 'SystemUser', '/system/user', '/system/user/index.vue', '用户管理', 1, 1, 1, 'system:user:query', 'layui-icon-user', NULL, '2023-10-11 11:02:35', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (5, 3, 'SystemRole', '/system/role', '/system/role/index.vue', '角色管理', 2, 1, 1, 'system:role:query', 'layui-icon-user', NULL, '2023-10-11 11:02:35', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (6, 3, 'SystemMenu', '/system/menu', '/system/menu/index.vue', '菜单管理', 3, 0, 1, 'system:menu:query', 'layui-icon-spread-left', NULL, '2023-10-11 11:02:35', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (7, 4, NULL, NULL, NULL, '编辑', 1, 1, 2, 'system:user:update', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (8, 4, NULL, NULL, NULL, '删除', 1, 1, 2, 'system:user:del', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (9, 4, NULL, NULL, NULL, '状态', 1, 1, 2, 'system:user:status', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (10, 5, NULL, NULL, NULL, '编辑', 1, 1, 2, 'system:role:update', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (11, 5, NULL, NULL, NULL, '删除', 1, 1, 2, 'system:role:del', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (12, 6, NULL, NULL, NULL, '编辑', 1, 1, 2, 'system:menu:update', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (13, 6, NULL, NULL, NULL, '新增', 1, 1, 2, 'system:menu:create', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-05-07 13:54:51', 1, 0);
INSERT INTO `sys_menu` VALUES (14, 5, NULL, NULL, NULL, '分配权限', 1, 1, 2, 'system:role:permission', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (15, 4, NULL, NULL, NULL, '创建', 1, 1, 2, 'system:user:create', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (16, 4, NULL, NULL, NULL, '重置密码', 1, 1, 2, 'system:user:password', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (17, 5, NULL, NULL, NULL, '创建', 1, 1, 2, 'system:role:create', NULL, NULL, '2023-10-12 17:05:14', 0, '2025-04-29 11:13:14', 0, 0);
INSERT INTO `sys_menu` VALUES (18, 6, NULL, NULL, NULL, '删除', 3, 1, 2, 'system:menu:del', '', NULL, '2025-05-07 14:01:06', 1, '2025-05-07 14:01:06', 1, 0);
INSERT INTO `sys_menu` VALUES (19, 3, 'SystemLog', '/system/log', '/system/log/index.vue', '日志管理', 4, 1, 1, 'system:log:query', 'layui-icon-cols', NULL, '2025-05-26 09:26:45', 1, '2025-05-26 09:29:02', 1, 0);
INSERT INTO `sys_menu` VALUES (20, 3, 'SystemDict', '/system/dict', '/system/dict/index.vue', '字典管理', 5, 1, 1, 'system:dict:query', 'layui-icon-util', NULL, '2025-05-30 13:35:49', 1, '2025-05-30 13:35:49', 1, 0);
INSERT INTO `sys_menu` VALUES (21, 20, NULL, NULL, NULL, '新增', 1, 1, 2, 'system:dict:create', '', NULL, '2025-05-30 13:36:13', 1, '2025-05-30 13:36:13', 1, 0);
INSERT INTO `sys_menu` VALUES (22, 20, NULL, NULL, NULL, '修改', 2, 1, 2, 'system:dict:update', '', NULL, '2025-05-30 13:42:08', 1, '2025-05-30 13:42:08', 1, 0);
INSERT INTO `sys_menu` VALUES (23, 20, NULL, NULL, NULL, '删除', 3, 1, 2, 'system:dict:del', '', NULL, '2025-05-30 13:42:35', 1, '2025-05-30 13:42:35', 1, 0);

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作模块类型',
  `sub_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作名',
  `biz_id` bigint NOT NULL COMMENT '操作数据模块编号',
  `action` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作内容',
  `success` bit(1) NOT NULL DEFAULT b'1' COMMENT '操作结果',
  `extra` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '拓展字段',
  `request_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方法名',
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求地址',
  `user_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户 IP',
  `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器 UA',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `menu_ids` json NULL COMMENT '菜单ID集合',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '添加一个备注', '[1, 2, 3, 4, 7, 8, 9, 15, 16, 5, 10, 11, 14, 17, 19, 20, 21, 22, 23]', '2023-10-11 11:16:10', 0, '2025-05-30 13:42:59', 1, 0);
INSERT INTO `sys_role` VALUES (2, '管理员', '角色', '[1, 2, 9, 15, 16, 5, 10, 11, 14, 17, 13]', '2023-10-12 13:27:50', 0, '2025-05-06 16:47:55', 1, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'https://i.gtimg.cn/club/item/face/img/2/16022_100.gif' COMMENT '头像',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_ids` json NULL COMMENT '角色ID集合',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_01`(`account` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'larxj9nkkTvTBzX4Ky804BMYsxDPQMFJToYdCrbnIPcUAxRtH14FwbtqOe+Q9HeeoPXdR+b19SxjKBnJ9z7UTtmg1oe8yn76IPZdWNMsBJXColp2n+K5EYVC0xpihQGYjzY658e75XzBgIbGSDtz5oW0O4IXSSmYuzxoc/mSO1U=', '管理员', 'https://i.gtimg.cn/club/item/face/img/2/16022_100.gif', '12332131', NULL, NULL, '[1]', 1, '2023-10-11 11:17:57', 0, '2025-05-06 09:36:04', 123456, 0);
INSERT INTO `sys_user` VALUES (2, 'moran', 'larxj9nkkTvTBzX4Ky804BMYsxDPQMFJToYdCrbnIPcUAxRtH14FwbtqOe+Q9HeeoPXdR+b19SxjKBnJ9z7UTtmg1oe8yn76IPZdWNMsBJXColp2n+K5EYVC0xpihQGYjzY658e75XzBgIbGSDtz5oW0O4IXSSmYuzxoc/mSO1U=', '默然', 'https://i.gtimg.cn/club/item/face/img/2/16022_100.gif', '18746176990', '1280520512@qq.com', NULL, '[3]', 1, '2024-03-25 15:51:14', 0, '2025-05-06 09:36:04', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
