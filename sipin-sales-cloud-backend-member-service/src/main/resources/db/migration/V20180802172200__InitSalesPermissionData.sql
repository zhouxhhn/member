--初始化permission_group和permission_action的表数据
delete from sales_permission_group;

--0代表前台，1代表后台
Insert into sales_permission_group(name,type) values('销售管理','0');
Insert into sales_permission_group(name,type) values('采购管理','0');
Insert into sales_permission_group(name,type) values('库存管理','0');
Insert into sales_permission_group(name,type) values('配送信息管理','0');
Insert into sales_permission_group(name,type) values('经销商管理','0');
Insert into sales_permission_group(name,type) values('员工管理','0');
Insert into sales_permission_group(name,type) values('商品管理','0');

Insert into sales_permission_group(name,type) values('销售管理','1');
Insert into sales_permission_group(name,type) values('采购管理','1');
Insert into sales_permission_group(name,type) values('库存管理','1');
Insert into sales_permission_group(name,type) values('门店管理','1');
Insert into sales_permission_group(name,type) values('经销商管理','1');
Insert into sales_permission_group(name,type) values('员工管理','1');
Insert into sales_permission_group(name,type) values('权限管理','1');
Insert into sales_permission_group(name,type) values('配送信息管理','1');
Insert into sales_permission_group(name,type) values('商品管理','1');

delete from sales_permission_action;


--经销商前台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/getAgencyInfo','返回该用户的经销商信息',id from  sales_permission_group where name='经销商管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/purchasePayment','经销商采购订单支付接口',id from  sales_permission_group where name='经销商管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/getPaymentNo','根据采购订单号查支付流水号接口',id from  sales_permission_group where name='经销商管理' and type = 0;


--经销商后台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/indexAgency','获取经销商列表',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/searchAgency','查找单个经销商',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/addAgency','新增经销商',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/updateAgency','修改经销商',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/deleteAgency','删除经销商',id from  sales_permission_group where name='经销商管理' and type = 1;


--门店管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/indexShop','获取指定经销商门店列表',id from  sales_permission_group where name='门店管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/searchShop','获取单个门店信息',id from  sales_permission_group where name='门店管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/addShop','新增门店',id from  sales_permission_group where name='门店管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/updateShop','修改门店',id from  sales_permission_group where name='门店管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/deleteShop','删除门店',id from  sales_permission_group where name='门店管理' and type = 1;


--门店员工前台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/indexUser', '获取该用户同门店的所有店员',id from  sales_permission_group where name='员工管理' and type=0;

--门店员工后台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/indexUser', '获取指定门店的管理员列表接口',id from  sales_permission_group where name='员工管理' and type=1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/searchUser', '获取员工信息',id from  sales_permission_group where name='员工管理' and type=1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/addUser','新增门店管理员的接口',id from  sales_permission_group where name='员工管理' and type=1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/updateUser','修改员工',id from  sales_permission_group where name='员工管理' and type=1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/setStatus','修改员工状态',id from  sales_permission_group where name='员工管理' and type=1;



--采购管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/list','前台获取采购订单列表',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order','前台获取采购单详情',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/detail','前台获取采购订单详情',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/create', '前台新增采购',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/update','前台更新备注',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/cancel','前台取消采购订单',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/delete','前台删除采购订单',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/purchase-order/purchasePayment','前台经销商采购订单支付确认接口',id from  sales_permission_group where name='采购管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/get-crsf-token','前台获取CsrkToken',id from  sales_permission_group where name='采购管理' and type = 0;

--后台采购管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/purchase-order/list','后台获取采购订单列表',id from  sales_permission_group where name='采购管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/purchase-order','后台获取采购单详情',id from  sales_permission_group where name='采购管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/purchase-order/detail','后台获取采购订单详情',id from  sales_permission_group where name='采购管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/purchase-order/update','后台更新备注',id from  sales_permission_group where name='采购管理' and type = 1;


--前台商品管理
 Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/warehouse','获取仓库列表',id from  sales_permission_group where name='商品管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/search-all-sku','获取所有SKU(含库存)列表',id from  sales_permission_group where name='商品管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/search-all-sku-no-stock','获取所有SKU(不包含库存)列表',id from  sales_permission_group where name='商品管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/search-sku-warehouse-stock','获取指定仓库商品库存列表',id from  sales_permission_group where name='商品管理' and type = 0;


--后台商品管理
 Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/warehouse','获取仓库列表',id from  sales_permission_group where name='商品管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/search-all-sku','获取所有SKU(含库存)列表',id from  sales_permission_group where name='商品管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/search-all-sku-no-stock','获取所有SKU(不包含库存)列表',id from  sales_permission_group where name='商品管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/search-sku-warehouse-stock','获取指定仓库商品库存列表',id from  sales_permission_group where name='商品管理' and type = 1;


--前台配送信息管理
 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/addAddress','前台新增地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/deleteAddress','前台删除地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/index','前台查询该门店的所有地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/searchAddress','前台查询单个地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/setDefaultAddress','前台设置默认地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/updateAddress','前台修改地址接口',id from  sales_permission_group where name='配送信息管理' and type = 0;

--后台配送信息管理
 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/addAddress','后台新增地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/deleteAddress','后台删除地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/index','后台查询该门店的所有地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/searchAddress','后台查询单个地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/setDefaultAddress','后台设置默认地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;

 Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/updateAddress','后台修改地址接口',id from  sales_permission_group where name='配送信息管理' and type = 1;


--初始化超级管理员角色
delete from sales_role;
delete from sales_user_role;
delete from sales_permission_role;

insert into `sales_role`(name,code,children_system,status,type,creator) select "超级管理角色","R00001",1,0,0,suser.id from `sales_user` suser where suser.code='admin';
insert into sales_user_role(user_id,role_id) select  suser.id,srole.id from `sales_user` suser,sales_role srole where suser.code='admin' and srole.name='超级管理角色';
insert into sales_permission_role(role_id,permission_id,permission_group_id) select 0, id,group_id from sales_permission_action;
update sales_permission_role set role_id = (select srole.id from `sales_role` srole where srole.name='超级管理角色');





