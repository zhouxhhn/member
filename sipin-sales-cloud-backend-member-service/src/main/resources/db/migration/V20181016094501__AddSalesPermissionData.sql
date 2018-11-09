--前台权限管理组
Insert into sales_permission_group(name,type) values('权限管理','0');

--前台员工管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/editUserPwd','修改个人密码接口',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/addUser','新增员工的接口',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/indexUser','获取员工列表接口',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/searchUser','获取指定具体某个管理员信息的接口',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/setRole','员工角色授权',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/setStatus','设置员工的状态接口',id from  sales_permission_group where name='员工管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/user/updateUser','修改员工的接口',id from  sales_permission_group where name='员工管理' and type = 0;

--前台权限管理
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/add','新增角色接口',id from  sales_permission_group where name='权限管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/delete','删除角色接口',id from  sales_permission_group where name='权限管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/index','获取角色列表接口',id from  sales_permission_group where name='权限管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/search','查找单个角色接口',id from  sales_permission_group where name='权限管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/setRolePermission','角色授权接口',id from  sales_permission_group where name='权限管理' and type = 0;

Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/front/roles/update','修改角色接口',id from  sales_permission_group where name='权限管理' and type = 0;

--后台
Insert into sales_permission_action(action_url,display_name,group_id)
select '/sales/user/editUserPwd','修改个人密码接口',id from  sales_permission_group where name='员工管理' and type = 1;

--把修改个人密码给所有员工
insert into sales_permission_role(role_id,permission_id,permission_group_id) select role.id,paction.id as pid,paction.group_id from sales_role role,sales_permission_action paction where paction.display_name='修改个人密码接口' ;