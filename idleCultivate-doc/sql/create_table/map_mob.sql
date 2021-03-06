CREATE TABLE `map_mob` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `map_id` varchar(20) NOT NULL DEFAULT '' COMMENT '地图id',
  `map_name` varchar(20) NOT NULL DEFAULT '' COMMENT '地图名称',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '怪物名称',
  `faction` tinyint(4) NOT NULL DEFAULT 0 COMMENT '阵营',
  `mob_class` tinyint(4) NOT NULL DEFAULT 0 COMMENT '怪物类型 0-未指定 1-野兽 2-小动物 3-恶魔 4-龙类 5-元素生物 6-巨人 7-人型生物 8-机械 9-亡灵',
  `mob_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '怪物种类 0-普通 1-稀有 2-精英 3-世界首领',
  `level` int(11) NOT NULL DEFAULT 0 COMMENT '等级',
  `hp` int(11) NOT NULL DEFAULT 0 COMMENT '生命值',
  `damage` int(11) NOT NULL DEFAULT 0 COMMENT '伤害',
  `armour` int(11) NOT NULL DEFAULT 0 COMMENT '护甲',
  `create_user` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_user` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地图怪物表';