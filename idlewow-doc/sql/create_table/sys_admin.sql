CREATE TABLE `sys_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(200) NOT NULL DEFAULT '' COMMENT '密码',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  INDEX `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统管理员表';