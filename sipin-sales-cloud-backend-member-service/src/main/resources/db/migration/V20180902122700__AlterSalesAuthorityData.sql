

--前台配送信息管理
delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/front/delivery/addAddress' and group_id not in
 (select id from sales_permission_group where name='经销商管理')
);
delete from  sales_permission_action where action_url ='/sales/agency/front/delivery/addAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');

delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/front/delivery/deleteAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/front/delivery/deleteAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');

delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/front/delivery/index' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/front/delivery/index' and group_id not in
(select id from sales_permission_group where name='经销商管理');

delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url = '/sales/agency/front/delivery/searchAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url = '/sales/agency/front/delivery/searchAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/front/delivery/setDefaultAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/front/delivery/setDefaultAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/front/delivery/updateAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/front/delivery/updateAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');

--后台配送信息管理
delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/addAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/addAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/deleteAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/deleteAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/index' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/index' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/searchAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/searchAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');

delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/setDefaultAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/setDefaultAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');


delete from sales_permission_role where permission_id = (
select id from sales_permission_action where action_url ='/sales/agency/delivery/updateAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理')
);
delete from sales_permission_action where action_url ='/sales/agency/delivery/updateAddress' and group_id not in
(select id from sales_permission_group where name='经销商管理');

delete from sales_permission_role where role_id=1 and permission_group_id in (select id from  sales_permission_group where name='经销商管理');
insert into sales_permission_role(role_id,permission_id,permission_group_id) select 1, id,group_id from sales_permission_action where sales_permission_action.group_id in (select id from  sales_permission_group where name='经销商管理');


Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/permission/group/back/indexBackGroup','获取后台权限组列表接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/permission/group/front/indexFrontGroup','获取前台权限组列表接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/permission/group/indexAction','获取指定角色权限组的权限列表接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/add','新增角色接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/delete','删除角色接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/index','获取角色列表接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/search','查找单个角色接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/setRolePermission','角色授权接口',id from  sales_permission_group where name='权限管理' and type = 1;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/roles/update','修改角色接口',id from  sales_permission_group where name='权限管理' and type = 1;

insert into sales_permission_role(role_id,permission_id,permission_group_id) select 1, id,group_id from sales_permission_action where sales_permission_action.group_id in (select id from  sales_permission_group where name='权限管理');


