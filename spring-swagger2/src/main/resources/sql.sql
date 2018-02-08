CREATE TABLE `user_info` (
`id`  integer NOT NULL AUTO_INCREMENT ,
`user_Name`  varchar(255) NULL COMMENT '用户名' ,
`pass_word`  varchar(255) NULL COMMENT '密码' ,
`phone`  varchar(255) NULL COMMENT '手机号' ,
`address`  varchar(255) NULL COMMENT '地址' ,
`createTime`  date NULL COMMENT '时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `id` (`id`) 
)
;
