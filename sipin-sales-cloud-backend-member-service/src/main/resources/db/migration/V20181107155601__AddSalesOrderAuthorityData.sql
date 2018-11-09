Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/orders/exportExcel','后台导出销售订单列表数据excel',id from  sales_permission_group where name='销售管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/backend/payment/exportExcel','后台导出交易流水列表数据excel',id from  sales_permission_group where name='销售管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/purchase-order/exportExcel','后台导出采购订单列表数据excel',id from  sales_permission_group where name='采购管理' and type = 1;

--退货单管理
Insert into sales_permission_group(name,type) values('退货单管理','1');
Insert into sales_permission_group(name,type) values('退货单管理','0');

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/return-order','前台获取退货单详情',id from  sales_permission_group where name='退货单管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/return-order/audit','前台审核销售退货单',id from  sales_permission_group where name='退货单管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/return-order/cancel','前台取消退货单详情',id from  sales_permission_group where name='退货单管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/return-order/create','前台新建销售退货单',id from  sales_permission_group where name='退货单管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/front/return-order/index','获取前台销售退货单列表',id from  sales_permission_group where name='退货单管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/return-order','后台台获取退货单详情',id from  sales_permission_group where name='退货单管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/return-order/exportExcel','导出销售退货单列表数据excel',id from  sales_permission_group where name='退货单管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/backend/return-order/index','获取后台销售退货单列表',id from  sales_permission_group where name='退货单管理' and type = 1;