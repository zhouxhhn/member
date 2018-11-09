
--前台支付
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/payment/sum','前台订单汇总接口',id from  sales_permission_group where name='销售管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/payment/index','前台获取支付流水列表接口',id from  sales_permission_group where name='销售管理' and type = 0;


--后台支付
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/backend/payment/backendIndex','后台获取支付流水列表接口',id from  sales_permission_group where name='销售管理' and type = 1;

insert into sales_permission_role(role_id,permission_id,permission_group_id)
select 1,id,group_id from sales_permission_action where action_url='/sales/front/payment/index'
or action_url='/sales/front/payment/sum' or action_url='/sales/backend/payment/backendIndex'

