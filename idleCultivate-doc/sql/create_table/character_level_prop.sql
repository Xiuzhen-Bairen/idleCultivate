CREATE TABLE `character_level_prop` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job` int(11) NOT NULL DEFAULT '0' COMMENT '职业',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '角色等级',
  `hp` int(11) NOT NULL DEFAULT '0' COMMENT '血量',
  `strength` int(11) NOT NULL DEFAULT '0' COMMENT '力量',
  `agility` int(11) NOT NULL DEFAULT '0' COMMENT '敏捷',
  `intellect` int(11) NOT NULL DEFAULT '0' COMMENT '智力',
  `stamina` int(11) NOT NULL DEFAULT '0' COMMENT '耐力',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
	UNIQUE INDEX `idx_job_level` (job, level)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='等级属性配置表';