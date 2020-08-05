CREATE TABLE `map_coord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `from_map_id` varchar(20) NOT NULL DEFAULT '' COMMENT '地图名称ID',
  `from_map_name` varchar(20) NOT NULL DEFAULT '' COMMENT '起始地图名称',
  `dest_map_id` varchar(20) NOT NULL DEFAULT '' COMMENT '目标地图ID',
  `dest_map_name` varchar(20) NOT NULL DEFAULT '' COMMENT '目标地图名称',
  `shape` varchar(10) NOT NULL DEFAULT '' COMMENT '形状',
  `coord` varchar(200) NOT NULL DEFAULT '' COMMENT '坐标',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  INDEX `idx_from_map_id` (`from_map_id`),
  INDEX `idx_dest_map_id` (`dest_map_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地图坐标表';