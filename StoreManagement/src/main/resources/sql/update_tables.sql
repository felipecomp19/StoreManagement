--INSERT INTO db_storeManager_test.tb_user_roles (`role`) VALUES ('ROLE_ADMIN');
--INSERT INTO db_storeManager_test.tb_user_roles (`role`) VALUES ('ROLE_USER');

INSERT INTO tb_user_roles (`role`) VALUES ('ROLE_ADMIN');
INSERT INTO tb_user_roles (`role`) VALUES ('ROLE_USER');
INSERT INTO tb_users (`isActive`, `password`, `userName`, `userRole`, `name`) VALUES (true,"$2a$10$KEA5eHefNnUuDjNaHs9mhuP5nK1gG6wjei1mXmxVXLiyWt.WihxRC","master",3,"Master");