INSERT INTO tb_user_roles (`role`,`isEnabled`) VALUES ('ROLE_ADMIN', true);
INSERT INTO tb_user_roles (`role`,`isEnabled`) VALUES ('ROLE_USER', true);
INSERT INTO tb_user_roles (`role`,`isEnabled`) VALUES ('ROLE_MANAGER', true);


--ecommerce
INSERT INTO tb_ec_plan (`isEnabled`,`description`,`name`,`value`)VALUES(true,'basic plan','plan.basic',1);
INSERT INTO tb_ec_plan (`isEnabled`,`description`,`name`,`value`)VALUES(true,'intermediate plan','plan.intermediate',1);
INSERT INTO tb_ec_plan (`isEnabled`,`description`,`name`,`value`)VALUES(true,'full plan','plan.full',1);
INSERT INTO tb_ec_account (`isEnabled`,`name`,`plan`) VALUES(true,'Morana',1);


INSERT INTO tb_users (`isEnabled`,`isActive`, `password`,`userName`, `userRole`, `name`, `account`) VALUES (true,true,"$2a$10$KEA5eHefNnUuDjNaHs9mhuP5nK1gG6wjei1mXmxVXLiyWt.WihxRC","master",1,"Master",1);

--INSERT INTO tb_store (`isEnabled`,`description`,`name`,`telephone`) VALUES(true,'xpto-desc','xpto','11-11112222');
--INSERT INTO tb_user_store (`user_id`, `store_id`) VALUES (1,1);


INSERT INTO tb_status(`isEnabled`,`description`) VALUES(true,"status.notSubmitted");
INSERT INTO tb_status(`isEnabled`,`description`) VALUES(true,"status.scheduled");
INSERT INTO tb_status(`isEnabled`,`description`) VALUES(true,"status.processing");
INSERT INTO tb_status(`isEnabled`,`description`) VALUES(true,"status.submitted");
