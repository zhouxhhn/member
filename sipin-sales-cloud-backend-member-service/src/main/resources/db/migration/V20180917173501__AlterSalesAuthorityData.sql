
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/agency/updateOuterCode','修改经销商外部编码',id from  sales_permission_group where name='经销商管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/shop/updateSourceId','修改门店sourceId',id from  sales_permission_group where name='门店管理' and type = 1;


insert into sales_permission_role(role_id,permission_id,permission_group_id) select 1,id,group_id from sales_permission_action where action_url='/sales/agency/updateOuterCode'
or action_url='/sales/shop/updateSourceId'