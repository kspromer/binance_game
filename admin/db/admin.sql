/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 127.0.0.1:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 12/04/2022 18:36:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
BEGIN;
INSERT INTO `sys_captcha` VALUES ('05a2697d-a3fb-4077-81f1-d9f97ffb3797', '4x342', '2021-09-05 20:37:20');
INSERT INTO `sys_captcha` VALUES ('0fc72295-16a6-4613-84b7-9d0829e0299a', 'xn257', '2021-09-30 23:13:33');
INSERT INTO `sys_captcha` VALUES ('12a7c2fc-78a6-43b9-8e50-844748120071', 'g85xn', '2022-03-30 16:23:14');
INSERT INTO `sys_captcha` VALUES ('16269b4e-0621-4403-8ca7-d4ddf9d45488', '6xw64', '2022-03-30 17:54:53');
INSERT INTO `sys_captcha` VALUES ('180e8700-9329-410c-8fb2-8e5ab7294c3c', '45388', '2022-03-30 17:56:50');
INSERT INTO `sys_captcha` VALUES ('193da0b9-c667-4b32-87b0-cb6058f7ee72', '2na3m', '2022-03-30 13:49:55');
INSERT INTO `sys_captcha` VALUES ('1a81cd1a-2252-46c8-88d5-6454af04df4c', 'd4pm3', '2021-09-30 22:00:38');
INSERT INTO `sys_captcha` VALUES ('2de7d031-470b-46cf-8fc5-9f23a4929157', 'yg3dg', '2021-08-11 16:36:06');
INSERT INTO `sys_captcha` VALUES ('40868cbf-3e6d-4c2a-86d9-4364912621ac', 'nw6aa', '2021-08-11 17:05:32');
INSERT INTO `sys_captcha` VALUES ('64028dec-bc56-44c3-8167-a60f475d622c', 'wcp4w', '2021-09-30 23:13:12');
INSERT INTO `sys_captcha` VALUES ('77933c7d-0898-44d4-8fff-b910920b6c95', 'gmypx', '2022-03-31 18:14:25');
INSERT INTO `sys_captcha` VALUES ('7fba8cdf-db89-48fd-8a73-cb6c4ddc84bb', 'm5en7', '2021-09-30 23:13:11');
INSERT INTO `sys_captcha` VALUES ('8c991797-962d-4d1d-8e1b-203962edf99e', 'ey8xp', '2021-09-30 23:13:10');
INSERT INTO `sys_captcha` VALUES ('8caa5aae-f2d4-4041-834b-12600d919793', 'ffeg3', '2021-08-11 17:00:06');
INSERT INTO `sys_captcha` VALUES ('8f759d88-ec2c-4eef-8246-2cfd740a3376', 'x7agd', '2022-03-30 16:05:08');
INSERT INTO `sys_captcha` VALUES ('942e5505-ace7-4353-82ca-63eea304c73f', '3654n', '2021-08-11 16:33:27');
INSERT INTO `sys_captcha` VALUES ('a086c670-fc68-4b4b-8e66-f24565415861', 'e2pep', '2021-08-11 16:34:36');
INSERT INTO `sys_captcha` VALUES ('abcd', '6pen7', '2022-03-20 17:16:56');
INSERT INTO `sys_captcha` VALUES ('abcd1', '6537m', '2022-03-20 17:21:33');
INSERT INTO `sys_captcha` VALUES ('abcddef', 'mnnw5', '2022-03-20 17:54:33');
INSERT INTO `sys_captcha` VALUES ('abcddefd', 'a4ybx', '2022-03-20 18:07:27');
INSERT INTO `sys_captcha` VALUES ('abcddefdb', 'mf2fn', '2022-03-20 18:08:39');
INSERT INTO `sys_captcha` VALUES ('af45fdde-8155-40bf-8e84-b27925d460f9', '65n3g', '2022-03-30 15:44:29');
INSERT INTO `sys_captcha` VALUES ('bb694936-3db3-4dcf-8283-38ccaecd1b8a', '63n54', '2021-08-11 17:07:11');
INSERT INTO `sys_captcha` VALUES ('bf01cb28-3874-417b-89b9-44824ec4c6fa', 'y26px', '2021-09-30 23:12:13');
INSERT INTO `sys_captcha` VALUES ('c8a2f4be-8f4c-4824-8f2f-289c184f0b40', 'x5fby', '2021-08-11 16:14:25');
INSERT INTO `sys_captcha` VALUES ('f8fd9282-432f-453d-828b-c0f5d94c6770', 'x35pp', '2022-03-30 00:16:38');
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);
INSERT INTO `sys_menu` VALUES (41, 62, '用户列表', 'binancegame/account', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (42, 41, '查看', NULL, 'binancegame:account:list,binancegame:account:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (43, 41, '新增', NULL, 'binancegame:account:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (44, 41, '修改', NULL, 'binancegame:account:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (45, 41, '删除', NULL, 'binancegame:account:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (46, 62, '用户提现', 'binancegame/accountwithdrawal', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (47, 46, '查看', NULL, 'binancegame:accountwithdrawal:list,binancegame:accountwithdrawal:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (48, 46, '新增', NULL, 'binancegame:accountwithdrawal:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (49, 46, '修改', NULL, 'binancegame:accountwithdrawal:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (50, 46, '删除', NULL, 'binancegame:accountwithdrawal:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (51, 62, '用户充值地址', 'binancegame/accountrechargeaddress', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (52, 51, '查看', NULL, 'binancegame:accountrechargeaddress:list,binancegame:accountrechargeaddress:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (53, 51, '新增', NULL, 'binancegame:accountrechargeaddress:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (54, 51, '修改', NULL, 'binancegame:accountrechargeaddress:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (55, 51, '删除', NULL, 'binancegame:accountrechargeaddress:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (56, 63, '分佣配置', 'binancegame/agentcommission', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (57, 56, '查看', NULL, 'binancegame:agentcommission:list,binancegame:agentcommission:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (58, 56, '新增', NULL, 'binancegame:agentcommission:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (59, 56, '修改', NULL, 'binancegame:agentcommission:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (60, 56, '删除', NULL, 'binancegame:agentcommission:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (61, 41, '加分', '', 'binancegame:account:addMoney', 2, '', 0);
INSERT INTO `sys_menu` VALUES (62, 0, '用户管理', '', '', 0, 'admin', 0);
INSERT INTO `sys_menu` VALUES (63, 0, '系统配置', '', '', 0, 'menu', 0);
INSERT INTO `sys_menu` VALUES (64, 62, '金额变动', 'binancegame/moneychange', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (65, 64, '查看', NULL, 'binancegame:moneychange:list,binancegame:moneychange:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (66, 64, '新增', NULL, 'binancegame:moneychange:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (67, 64, '修改', NULL, 'binancegame:moneychange:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (68, 64, '删除', NULL, 'binancegame:moneychange:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (69, 62, '用户消息', 'binancegame/message', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (70, 69, '查看', NULL, 'binancegame:message:list,binancegame:message:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (71, 69, '新增', NULL, 'binancegame:message:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (72, 69, '修改', NULL, 'binancegame:message:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (73, 69, '删除', NULL, 'binancegame:message:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (74, 63, '投注配置', 'binancegame/betconfig', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (75, 74, '查看', NULL, 'binancegame:betconfig:list,binancegame:betconfig:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (76, 74, '新增', NULL, 'binancegame:betconfig:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (77, 74, '修改', NULL, 'binancegame:betconfig:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (78, 74, '删除', NULL, 'binancegame:betconfig:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (79, 84, '投注记录', 'binancegame/betrecord', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (80, 79, '查看', NULL, 'binancegame:betrecord:list,binancegame:betrecord:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (81, 79, '新增', NULL, 'binancegame:betrecord:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (82, 79, '修改', NULL, 'binancegame:betrecord:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (83, 79, '删除', NULL, 'binancegame:betrecord:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (84, 0, '投注管理', '', '', 0, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (85, 84, 'k线', 'binancegame/klines', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (86, 85, '查看', NULL, 'binancegame:klines:list,binancegame:klines:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (87, 85, '新增', NULL, 'binancegame:klines:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (88, 85, '修改', NULL, 'binancegame:klines:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (89, 85, '删除', NULL, 'binancegame:klines:delete', 2, NULL, 6);
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_token` VALUES (1, 'a80773117c6d063ff24c634b51bffc7e', '2022-04-13 02:41:37', '2022-04-12 14:41:37');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
