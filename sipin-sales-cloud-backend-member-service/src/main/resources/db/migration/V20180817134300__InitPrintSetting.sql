CREATE TABLE `print_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `printer_ip` varchar(20)  NULL COMMENT '打印主机IP',
  `printer` tinyint(4)  NULL  COMMENT '打印机',
  `shop_address` varchar(200)  NULL COMMENT '店铺地址',
  `shop_phone` varchar(64)  NULL COMMENT '店铺电话',
  `ticket_tips` varchar(200)  NULL COMMENT '票尾提示',
  `pos_ip` varchar(20) NULL COMMENT 'POS机IP',
  `shop_code` varchar(20) NULL COMMENT '门店code',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='打印设置';
