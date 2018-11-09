
--密码被被始化为123456
INSERT  INTO `sales_user`(code,empno,name,password,type,status)
VALUES('M0000001','admin','超级管理员','$2a$10$zk/3aoMMGzdE5Iqdu/gSget3pOSo7Jwbu28vHRSWDvlFlAMXv9M0O',1,0);

insert into sales_user_role(user_id,role_id) select  suser.id,srole.id from `sales_user` suser,sales_role srole where suser.empno='admin' and srole.name='超级管理角色';

delete from sales_user_role where user_id = (select id from sales_user where code='admin');
delete from sales_user where code='admin';








