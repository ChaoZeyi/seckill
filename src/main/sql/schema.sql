-- 为什么要写该.sql脚本？
-- 权当是对数据库操作的一个记录，在企业开发中，可能会存在多个版本，使用该脚本可以清楚的看出每个版本进行了哪些更新，并且对于其他开发人员
-- 更友好

create DATABASE seckill;

use seckill;

-- 商品库存表

create table repo(
  product_id BIGINT NOT NULL Auto_increment comment '商品id',
  product_name varchar(120) not null comment '商品名称',
  product_num int not null COMMENT '商品库存',
  start_time TIMESTAMP not null comment '秒杀开始时间',
  end_time TIMESTAMP not null comment '秒杀结束时间',
  create_time TIMESTAMP not null DEFAULT current_timestamp comment '秒杀行为创建时间',
  PRIMARY KEY (product_id),
  key idx_start_time (start_time),
  key idx_end_time (end_time),
  key idx_create_time(create_time)
) ENGINE Innodb AUTO_INCREMENT 1000 default CHARSET = utf8 COMMENT '商品库存表';

INSERT into repo(product_name, product_num, start_time, end_time)
  values('1000元秒杀iphone7', 100, '2017-12-12 00:00:00', '2017-12-13 00:00:00');
INSERT into repo(product_name, product_num, start_time, end_time)
  VALUES
    ('500元秒杀ipad2', 200, '2017-12-12 00:00:00', '2017-12-13 00:00:00'),
    ('300元秒杀小米4', 300, '2017-12-12 00:00:00', '2017-12-13 00:00:00'),
    ('200元秒杀红米note', 400, '2017-12-12 00:00:00', '2017-12-13 00:00:00');

-- 秒杀结果明细表

create table killed_info(
  product_id BIGINT not NULL comment '商品id',
  user_phone BIGINT NOT NULL COMMENT '用户手机号',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '秒杀状态，-1代表失败；0代表成功；1代表已付款',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '秒杀行为创建时间',
  PRIMARY KEY (product_id, user_phone),    /*联合主键，保证一个用户对同一商品只能秒杀一次*/
  KEY idx_create_time(create_time)
) ENGINE Innodb DEFAULT CHARSET = utf8 COMMENT '秒杀明细表';

