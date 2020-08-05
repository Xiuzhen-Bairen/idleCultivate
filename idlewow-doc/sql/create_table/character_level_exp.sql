CREATE TABLE `character_level_exp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '等级',
  `exp_speed` int(11) NOT NULL DEFAULT '1' COMMENT '当前等级经验获取速度',
  `exp` int(11) NOT NULL DEFAULT '0' COMMENT '升级所需经验',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  	UNIQUE INDEX `idx_level` (level)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='等级经验表';