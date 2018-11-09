ALTER TABLE print_setting ADD printer_type  tinyint(4)  COMMENT '0表示AO打印机，1表示商米内置打印机';
ALTER TABLE print_setting ADD pos_type tinyint(4) COMMENT '0表示通联pos，1表示不使用pos机';

