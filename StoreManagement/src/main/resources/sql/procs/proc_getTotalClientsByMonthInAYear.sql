USE `db_storeManager_dev`;
DROP procedure IF EXISTS `getTotalClientsByMonthInAYear`;

DELIMITER $$
USE `db_storeManager_dev`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalClientsByMonthInAYear`(year bigint(20))
BEGIN
    DROP TEMPORARY TABLE if exists tmp ;
    create TEMPORARY table tmp
    (SELECT Count(cli.id) as countClients , 
           MONTH(cli.createdOn) as month, 
           YEAR(cli.createdOn) as year, 
           st.id as storeId, 
           st.name as storeName, 
           st.description as storeDesc
    FROM db_storeManager_dev.tb_client as cli 
        INNER JOIN tb_client_store as cliSt on cli.id = cliSt.client_id
        LEFT JOIN tb_store as st on cliSt.store_id = st.id
    Group by MONTH(cli.createdOn), YEAR(cli.createdOn)
    
    );
    SELECT case when month = 1 then countClients else 0 end as countClientsJan,
           case when month = 2 then countClients else 0 end as countClientsFeb,
           case when month = 3 then countClients else 0 end as countClientsMar,
           case when month = 4 then countClients else 0 end as countClientsApr,
           case when month = 5 then countClients else 0 end as countClientsMay,
           case when month = 6 then countClients else 0 end as countClientsJun,
           case when month = 7 then countClients else 0 end as countClientsJul,
           case when month = 8 then countClients else 0 end as countClientsAug,
           case when month = 9 then countClients else 0 end as countClientsSet,
           case when month = 10 then countClients else 0 end as countClientsOct,
           case when month = 11 then countClients else 0 end as countClientsNov,
           case when month = 12 then countClients else 0 end as countClientsDez,
           storeId,
           storeName,
           storeDesc
    from tmp as t
    where t.year = year;

    DROP TEMPORARY TABLE tmp ;
END$$

DELIMITER ;



