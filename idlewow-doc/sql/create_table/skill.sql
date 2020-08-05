CREATE TABLE `skill` (
  `skill_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '功法id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '功法名称',
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '功法类别',
  `level` int(11) NOT NULL DEFAULT 1 COMMENT '功法等级',
  `value` int(11) NOT NULL DEFAULT 0 COMMENT '功法属性',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`skill_id`),
  INDEX `idx_skill_id` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='功法表';
