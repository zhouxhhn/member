
drop TABLE  if exists sales_agency;
CREATE TABLE `sales_agency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20)  NOT NULL COMMENT '经销商帐号',
  `name` varchar(64) NOT NULL COMMENT '经销商名称',
  `grade` varchar(64) NOT NULL COMMENT '经销商等级',
  `address` text COMMENT '通讯地址',
  `phone` varchar(20) COMMENT '联系电话',
  `contacts` varchar(20) COMMENT '联系人',
  `balance` decimal(10,2) DEFAULT '0.00'  COMMENT '帐户余额',
  `discount` decimal(12,2) NOT NULL  COMMENT '结算折扣',
  `license_img_url` varchar(200)  NULL  COMMENT '营业执照图片地址',
  `license_img_secret` varchar(64)  NULL  COMMENT '营业执照图片密钥',
  `remark` text COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sales_agency_index_code` (`code`)
) COMMENT='经销商表';

drop TABLE  if exists sales_agency_delivery;
CREATE TABLE `sales_agency_delivery` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  shop_id bigint(20) NOT NULL COMMENT '门店id',
  receiver_name varchar(50) NOT NULL COMMENT '收货人',
  cellphone varchar(50) NOT NULL COMMENT '手机号码',
  address varchar(200) NOT NULL COMMENT '详细地址',
  full_address varchar(200) NOT NULL COMMENT '省市区+详细地址',
  province_code bigint(20)  COMMENT '省code',
  city_code bigint(20)  COMMENT '市code',
  district_code bigint(20)  COMMENT '区code',
  label varchar(50)  COMMENT '标签',
  default_address tinyint(1)  DEFAULT '0' COMMENT '默认地址:0代表不是默认地址，1代表默认地址',
  status tinyint(1)  NOT NULL DEFAULT '0' COMMENT '状态:0代表有效，1代表失效(删除)',
  create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT='门店配送信息表';
drop TABLE  if exists sales_agency_balance;
CREATE TABLE `sales_agency_balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` decimal(12,2)  NOT NULL DEFAULT '0.00'  COMMENT '金额',
  `trade_platform` varchar(64)  COMMENT '交易平台',
  `serial_num` varchar(64)  COMMENT '流水号',
  `business_type` varchar(64) NOT NULL COMMENT '业务类型',
  `order_num` varchar(64)  COMMENT '客户订单号',
  `code` varchar(20) NOT NULL   COMMENT '经销商帐号',
  `operator` varchar(64)  COMMENT '操作人',
  `total_balance` decimal(12,2)  NOT NULL DEFAULT '0.00'  COMMENT '期末余额',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='经销商余额表';

drop TABLE  if exists sales_shop;
CREATE TABLE `sales_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20)  NOT NULL COMMENT '门店code',
  `source_id` varchar(20) NULL COMMENT '门店sourceId',
  `name` varchar(64) NOT NULL COMMENT '门店名称',
  `address` text NOT NULL COMMENT '门店地址',
  `phone` varchar(20) NULL COMMENT '电话',
  `agency_code` varchar(64) NOT NULL COMMENT '经销商帐号',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `sales_shop_index_code` (`code`)
) COMMENT='门店表';

drop TABLE  if exists sales_user;
CREATE TABLE `sales_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64)  NOT NULL COMMENT '员工帐号',
  `empno` varchar(64) NOT NULL COMMENT '员工工号',
  `name` varchar(64)  NOT NULL COMMENT '姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `shop_code` varchar(20) COMMENT '门店code',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sales_user_unique_code` (`code`,`shop_code`)
) COMMENT='门店员工表';

drop TABLE  if exists sales_agency_account_history;
CREATE TABLE `sales_agency_account_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agency_id` bigint(20) NOT NULL COMMENT '经销商id',
  `order_no`  varchar(50)  NOT NULL COMMENT '单号',
  `serial_no`  varchar(50)  NULL COMMENT '交易流水号',
  `points` decimal(12,2) DEFAULT NULL COMMENT '积分',
  `terminal_points` decimal(12,2) DEFAULT NULL COMMENT '期末积分',
  `balance` decimal(12,2) DEFAULT NULL COMMENT '余额',
  `terminal_balance` decimal(12,2) DEFAULT NULL COMMENT '期末余额',
  `operator` varchar(50)  NOT NULL COMMENT '操作人',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型',
  `small_type` tinyint(1) DEFAULT NULL COMMENT '每种类型中的小类型',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_agency_account_history_sales_agency` (`agency_id`),
  unique index inx_agency_account_history_sales_agency (order_no),
  CONSTRAINT `fk_agency_account_history_sales_agency` FOREIGN KEY (`agency_id`) REFERENCES `sales_agency` (`id`)
) COMMENT='经销商帐户历史表';


drop TABLE  if exists sales_agency_payment_history;
CREATE TABLE `sales_agency_payment_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agency_id` bigint(20) NOT NULL COMMENT '经销商id',
  `order_no`  varchar(50)  NOT NULL COMMENT '订单号',
  `payment_no`  varchar(50)  NOT NULL COMMENT '支付流水号',
  `amount` decimal(12,2)  NOT NULL COMMENT '金额',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='经销商采购支付历史表';

--密码被被始化为123456
INSERT  INTO `sales_user`(code,empno,name,password,type,status)
VALUES('admin',' ','超级管理员','$2a$10$zk/3aoMMGzdE5Iqdu/gSget3pOSo7Jwbu28vHRSWDvlFlAMXv9M0O',1,0);


--初始化经销商端信息
INSERT  INTO `sales_agency`(id,code,name,grade,address,phone,contacts,discount)
VALUES (1,'A00001','跃期(上海)家居有限公司','A','潍坊路179号世纪汇广场LG2-026+028斯品空间','18565204980','张扬','0.7');

INSERT  INTO `sales_agency`(id,code,name,grade,address,phone,contacts,discount)
VALUES (2,'A00002','广州斯品家居文化发展有限公司','A','天河区马场路36号太阳新天地购物中心505-2','18565204980','张扬','0.7');


--初始化门店信息
INSERT  INTO `sales_shop`(code,source_id,name,address,phone,agency_code)
VALUES ('S000001','6','广州新天地门店','天河区马场路36号太阳新天地购物中心505-2','020-89281601','A00002');

INSERT  INTO `sales_shop`(code,source_id,name,address,phone,agency_code)
VALUES ('S000002','7','上海新天地门店','上海市黄浦区湖滨道150号企业新天地购物中心B1','021-53097991','A00001');

INSERT  INTO `sales_shop`(code,source_id,name,address,phone,agency_code)
VALUES ('S000004','10','上海世纪汇门店','潍坊路179号世纪汇广场LG2-026+028斯品空间','021-50783698','A00001');





