create table item
(
    id     int(20) auto_increment comment '物品id'
        primary key,
    name        varchar(20) default ''                not null comment '物品名称',
    type        varchar(20) default ''                not null comment '物品类别',
    level       int         default 1                 not null comment '物品等级',
    value       int         default 0                 not null comment '物品属性',
    description varchar(20) default ''                not null comment '物品描述',
    pile        tinyint(1)                            not null comment '可否堆叠',
    max_count   int         default 1                 not null comment '堆叠最大值',
    sell        tinyint(1)                            not null comment '可否出售',
    create_user varchar(20) default ''                not null comment '创建人',
    update_user varchar(20) default ''                not null comment '修改人',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime                              null on update CURRENT_TIMESTAMP comment '修改时间',
    is_delete   tinyint     default 0                 not null comment '是否删除 0-否 1-是',
    version     int         default 0                 not null comment '版本号'
)
    comment '物品表';

create index idx_id
    on item (id);

