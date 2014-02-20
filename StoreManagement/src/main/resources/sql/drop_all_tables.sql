--Base de desenv
drop table db_storeManager_dev.tb_client_store;
drop table db_storeManager_dev.tb_store;
drop table db_storeManager_dev.tb_mailingList_client;
drop table db_storeManager_dev.tb_client;
drop table db_storeManager_dev.tb_address;
drop table db_storeManager_dev.tb_campaign;
drop table db_storeManager_dev.tb_mailingList;
drop table db_storeManager_dev.tb_clientType;
drop table db_storeManager_dev.tb_audit;
drop table db_storeManager_dev.tb_users;
drop table db_storeManager_dev.tb_user_roles;

--Base de test
drop table db_storeManager_test.tb_client_store;
drop table db_storeManager_test.tb_store;
drop table db_storeManager_test.tb_mailingList_client;
drop table db_storeManager_test.tb_client;
drop table db_storeManager_test.tb_address;
drop table db_storeManager_test.tb_campaign;
drop table db_storeManager_test.tb_mailingList;
drop table db_storeManager_test.tb_clientType;
drop table db_storeManager_test.tb_audit;
drop table db_storeManager_test.tb_users;
drop table db_storeManager_test.tb_user_roles;


CREATE SCHEMA 'db_storeManager_test' DEFAULT CHARACTER SET latin1 ;