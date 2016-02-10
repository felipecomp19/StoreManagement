SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `db_storeManager_prod` DEFAULT CHARACTER SET latin1 ;
USE `db_storeManager_prod` ;

-- -----------------------------------------------------
-- Table `tb_address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `cep` VARCHAR(20) NULL DEFAULT NULL ,
  `city` VARCHAR(50) NULL DEFAULT NULL ,
  `complement` VARCHAR(255) NULL DEFAULT NULL ,
  `neighbourhood` VARCHAR(50) NULL DEFAULT NULL ,
  `number` INT(11) NULL DEFAULT NULL ,
  `state` VARCHAR(50) NULL DEFAULT NULL ,
  `street` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_ec_plan`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_ec_plan` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `value` DECIMAL(19,2) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_ec_account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_ec_account` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `plan` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_2af228c2603143f4b8578f3ba2a` (`plan` ASC) ,
  CONSTRAINT `FK_2af228c2603143f4b8578f3ba2a`
    FOREIGN KEY (`plan` )
    REFERENCES `tb_ec_plan` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_user_roles`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_user_roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `role` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `isActive` TINYINT(1) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `userName` VARCHAR(255) NOT NULL ,
  `userRole` BIGINT(20) NOT NULL ,
  `account` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_c5131202fc6d4efc96316e02050` (`userRole` ASC) ,
  INDEX `FK_32469ad47b2740fcbdf4215ca1b` (`account` ASC) ,
  CONSTRAINT `FK_32469ad47b2740fcbdf4215ca1b`
    FOREIGN KEY (`account` )
    REFERENCES `tb_ec_account` (`id` ),
  CONSTRAINT `FK_c5131202fc6d4efc96316e02050`
    FOREIGN KEY (`userRole` )
    REFERENCES `tb_user_roles` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_audit`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_audit` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `operation` VARCHAR(255) NULL DEFAULT NULL ,
  `date` DATETIME NULL DEFAULT NULL ,
  `user` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_144400c98da14e3280b576af76b` (`user` ASC) ,
  CONSTRAINT `FK_144400c98da14e3280b576af76b`
    FOREIGN KEY (`user` )
    REFERENCES `tb_users` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_status`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_status` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_mailingList`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_mailingList` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `createdOn` DATETIME NULL DEFAULT NULL ,
  `defaultFromEmail` VARCHAR(255) NULL DEFAULT NULL ,
  `defaultFromName` VARCHAR(255) NULL DEFAULT NULL ,
  `defaultSubject` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_campaign`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_campaign` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `createdOns` DATETIME NULL DEFAULT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `emailFileName` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `submitted` TINYINT(1) NULL DEFAULT NULL ,
  `submittedDate` DATETIME NULL DEFAULT NULL ,
  `mailingList_id` BIGINT(20) NOT NULL ,
  `status_id` BIGINT(20) NOT NULL ,
  `subject` VARCHAR(250),
  PRIMARY KEY (`id`) ,
  INDEX `FK_53757229be094f28a00b78f7f68` (`mailingList_id` ASC) ,
  INDEX `FK_ab7fc7cebdff476f8383e306e0b` (`status_id` ASC) ,
  CONSTRAINT `FK_ab7fc7cebdff476f8383e306e0b`
    FOREIGN KEY (`status_id` )
    REFERENCES `tb_status` (`id` ),
  CONSTRAINT `FK_53757229be094f28a00b78f7f68`
    FOREIGN KEY (`mailingList_id` )
    REFERENCES `tb_mailingList` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_tb_clientType`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_clientType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `UK_3d871e509fab4849a67b8b74861` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

alter table tb_client add especialActionsespecialActions VARCHAR(100);
alter table tb_campaign add subject VARCHAR(250);

-- -----------------------------------------------------
-- Table `tb_client`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_client` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `cellPhone` VARCHAR(255) NULL DEFAULT NULL ,
  `cpf` VARCHAR(15) NOT NULL ,
  `createdOn` DATETIME NULL DEFAULT NULL ,
  `day_birthday` INT(11) NULL DEFAULT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `month_birthday` INT(11) NULL DEFAULT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `telephone` VARCHAR(255) NULL DEFAULT NULL ,
  `address` BIGINT(20) NULL DEFAULT NULL ,
  `clientType` BIGINT(20) NOT NULL ,
  `especialActions` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_a6ac7e4dda784ee696a10797a73` (`address` ASC) ,
  INDEX `FK_8f1c45512c704c49926e60d28a0` (`clientType` ASC) ,
  CONSTRAINT `FK_8f1c45512c704c49926e60d28a0`
    FOREIGN KEY (`clientType` )
    REFERENCES `tb_clientType` (`id` ),
  CONSTRAINT `FK_a6ac7e4dda784ee696a10797a73`
    FOREIGN KEY (`address` )
    REFERENCES `tb_address` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_store`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_store` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `description` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  `telephone` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_client_store`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_client_store` (
  `client_id` BIGINT(20) NOT NULL ,
  `store_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`client_id`, `store_id`) ,
  INDEX `FK_82ed7eaa44224ece80db2646f55` (`store_id` ASC) ,
  INDEX `FK_5212a013243346ac83938185666` (`client_id` ASC) ,
  CONSTRAINT `FK_5212a013243346ac83938185666`
    FOREIGN KEY (`client_id` )
    REFERENCES `tb_client` (`id` ),
  CONSTRAINT `FK_82ed7eaa44224ece80db2646f55`
    FOREIGN KEY (`store_id` )
    REFERENCES `tb_store` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_ec_payment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_ec_payment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `date` DATETIME NULL DEFAULT NULL ,
  `value` DECIMAL(19,2) NULL DEFAULT NULL ,
  `account` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_13ee26e4c0e74b4b852dcbf630e` (`account` ASC) ,
  CONSTRAINT `FK_13ee26e4c0e74b4b852dcbf630e`
    FOREIGN KEY (`account` )
    REFERENCES `tb_ec_account` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_employees`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_employees` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `store` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_a2a463b0f4514f3da827b207f2c` (`store` ASC) ,
  CONSTRAINT `FK_a2a463b0f4514f3da827b207f2c`
    FOREIGN KEY (`store` )
    REFERENCES `tb_store` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_imagen`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_imagen` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `fileName` VARCHAR(255) NULL DEFAULT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_indicators`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_indicators` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `isEnabled` TINYINT(1) NULL DEFAULT NULL ,
  `goal` DECIMAL(19,2) NULL DEFAULT NULL ,
  `month` INT(11) NULL DEFAULT NULL ,
  `numberOfAttendances` INT(11) NOT NULL ,
  `numberOfItemsSold` INT(11) NOT NULL ,
  `numberOfSales` INT(11) NOT NULL ,
  `valueOfSales` DECIMAL(19,2) NULL DEFAULT NULL ,
  `workedDays` INT(11) NULL DEFAULT NULL ,
  `year` INT(11) NULL DEFAULT NULL ,
  `employee` BIGINT(20) NOT NULL ,
  `storeName` VARCHAR(255) NULL DEFAULT NULL ,
  `achievementOfGoals` DECIMAL(19,2) NULL DEFAULT NULL ,
  `averageSalesPerDay` DECIMAL(19,2) NULL DEFAULT NULL ,
  `averageTicket` DECIMAL(19,2) NULL DEFAULT NULL ,
  `averageValueOfTheProduct` DECIMAL(19,2) NULL DEFAULT NULL ,
  `conversionRate` DECIMAL(19,2) NULL DEFAULT NULL ,
  `itemsPerSale` DECIMAL(19,2) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `FK_067ff3cbc6f24eefaac23a77434` (`employee` ASC) ,
  CONSTRAINT `FK_067ff3cbc6f24eefaac23a77434`
    FOREIGN KEY (`employee` )
    REFERENCES `tb_employees` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_mailingList_client`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_mailingList_client` (
  `mailingList_id` BIGINT(20) NOT NULL ,
  `client_id` BIGINT(20) NOT NULL ,
  INDEX `FK_3ff27d3d166046879c131fb95ca` (`client_id` ASC) ,
  INDEX `FK_184d9add385242a0bb1ca3cac4f` (`mailingList_id` ASC) ,
  CONSTRAINT `FK_184d9add385242a0bb1ca3cac4f`
    FOREIGN KEY (`mailingList_id` )
    REFERENCES `tb_mailingList` (`id` ),
  CONSTRAINT `FK_3ff27d3d166046879c131fb95ca`
    FOREIGN KEY (`client_id` )
    REFERENCES `tb_client` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tb_user_store`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `tb_user_store` (
  `user_id` BIGINT(20) NOT NULL ,
  `store_id` BIGINT(20) NOT NULL ,
  INDEX `FK_4a080715216c47bb9232b1679d7` (`store_id` ASC) ,
  INDEX `FK_9aa2220c8b29472291cfe6f928c` (`user_id` ASC) ,
  CONSTRAINT `FK_4a080715216c47bb9232b1679d7`
    FOREIGN KEY (`store_id` )
    REFERENCES `tb_store` (`id` ),
  CONSTRAINT `FK_9aa2220c8b29472291cfe6f928c`
    FOREIGN KEY (`user_id` )
    REFERENCES `tb_users` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- procedure getTotalClientsByMonthInAYear
-- -----------------------------------------------------

DELIMITER $$
USE `db_storeManager_prod`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTotalClientsByMonthInAYear`(year bigint(20))
BEGIN
    DROP TEMPORARY TABLE if exists tmp ;
create TEMPORARY table tmp
(SELECT case when MONTH(cli.createdOn) = 1 then Count(cli.id) else 0 end as countClientsJan,
        case when MONTH(cli.createdOn) = 2 then Count(cli.id) else 0 end as countClientsFeb,
        case when MONTH(cli.createdOn) = 3 then Count(cli.id) else 0 end as countClientsMar,
        case when MONTH(cli.createdOn) = 4 then Count(cli.id) else 0 end as countClientsApr,
        case when MONTH(cli.createdOn) = 5 then Count(cli.id) else 0 end as countClientsMay,
        case when MONTH(cli.createdOn) = 6 then Count(cli.id) else 0 end as countClientsJun,
        case when MONTH(cli.createdOn) = 7 then Count(cli.id) else 0 end as countClientsJul,
        case when MONTH(cli.createdOn) = 8 then Count(cli.id) else 0 end as countClientsAug,
        case when MONTH(cli.createdOn) = 9 then Count(cli.id) else 0 end as countClientsSet,
        case when MONTH(cli.createdOn) = 10 then Count(cli.id) else 0 end as countClientsOct,
        case when MONTH(cli.createdOn) = 11 then Count(cli.id) else 0 end as countClientsNov,
        case when MONTH(cli.createdOn) = 12 then Count(cli.id) else 0 end as countClientsDez,
        YEAR(cli.createdOn) as year,   
        st.id as storeId,
        st.name as storeName,
        st.description as storeDesc
FROM tb_client as cli 
    INNER JOIN tb_client_store as cliSt on cli.id = cliSt.client_id
    LEFT JOIN tb_store as st on cliSt.store_id = st.id
Group by MONTH(cli.createdOn), YEAR(cli.createdOn), st.id);
select sum(countClientsJan) as countClientsJan, 
    sum(countClientsFeb) as countClientsFeb, 
    sum(countClientsMar) as countClientsMar,
    sum(countClientsApr) as countClientsApr,
    sum(countClientsMay) as countClientsMay,
    sum(countClientsJun) as countClientsJun,
    sum(countClientsJul) as countClientsJul,
    sum(countClientsAug) as countClientsAug,
    sum(countClientsSet) as countClientsSet,
    sum(countClientsOct) as countClientsOct,
    sum(countClientsNov) as countClientsNov,
    sum(countClientsDez) as countClientsDez,
    year,   
    storeId,
    storeName,
    storeDesc
FROM tmp as t
WHERE t.year = year
group by storeId;
DROP TEMPORARY TABLE tmp ;
END$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
