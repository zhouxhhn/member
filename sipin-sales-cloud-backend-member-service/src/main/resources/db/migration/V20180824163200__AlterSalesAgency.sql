ALTER TABLE sales_agency ADD outer_code varchar(50);
update sales_agency set outer_code='CUST0009' where code='A00001';
update sales_agency set outer_code='CUST0011' where code='A00002';
