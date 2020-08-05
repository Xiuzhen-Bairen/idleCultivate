CREATE TABLE `user_account` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `password` varchar(200) NOT NULL DEFAULT '' COMMENT '密码',
  `mail` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
  `real_name` varchar(20) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `id_no` varchar(20) NOT NULL DEFAULT '' COMMENT '身份证号',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '账号状态 0-正常 1-冻结',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `register_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '注册IP',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  INDEX `idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号表';