delete from sales_permission_group where name='库存管理';
delete from sales_permission_group where name='配送信息管理';

--前台销售管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member-coupon','获取会员优惠券',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member-coupon/bind','会员绑定优惠券',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member-consignee/create','创建会员地址',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member-consignee/region','销售订单获取地区表',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member-consignee/update','更新会员地址',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/member/info','获取会员消息',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/cancelOrder','取消订单的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/confirmComplete','确认完成订单的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/confirmPayment','确认收银的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/create','前台创建销售订单',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/detail','获取指定销售订单的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/editNotes','修改备注的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/getPaymentCode','获取支付交易码的接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/index','获取销售订单列表',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/list','前台获取采购订单列表',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/orders/wholeDiscount','整单优惠的接口',id from  sales_permission_group where name='销售管理' and type = 0;


--后台销售管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/orders/index','后台获取销售订单列表',id from  sales_permission_group where name='销售管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/orders/detail','获取指定销售订单的接口',id from  sales_permission_group where name='销售管理' and type = 1;


--经销商前台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/addAddress','新增地址接口',id from  sales_permission_group where name='经销商管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/front/delivery/index','查询该门店的所有地址接口',id from  sales_permission_group where name='经销商管理' and type = 0;


--经销商后台管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/addAddress','新增地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/deleteAddress','删除地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/index','查询该门店的所有地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/searchAddress','查询单个地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/setDefaultAddress','设置默认地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/delivery/updateAddress','修改地址接口',id from  sales_permission_group where name='经销商管理' and type = 1;


insert into sales_permission_role(role_id,permission_id,permission_group_id) select 1, id,group_id from sales_permission_action where sales_permission_action.group_id in (select id from  sales_permission_group where name='销售管理');
insert into sales_permission_role(role_id,permission_id,permission_group_id) select 1, id,group_id from sales_permission_action where sales_permission_action.group_id in (select id from  sales_permission_group where name='经销商管理');
update sales_permission_role set role_id = (select srole.id from `sales_role` srole where srole.name='超级管理角色');




