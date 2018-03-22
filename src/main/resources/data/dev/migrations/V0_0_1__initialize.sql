/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure and data.
 */

/*
 * Structure
 */
CREATE TABLE `res_table` (
  `res_tabl_id`            BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `res_base_creation_date` DATETIME(6) NOT NULL,
  `res_base_creator`       VARCHAR(64)          DEFAULT NULL,
  `res_base_modifier`      VARCHAR(64)          DEFAULT NULL,
  `res_base_modify_date`   DATETIME(6)          DEFAULT NULL,
  `res_base_version`       BIGINT(20)  NOT NULL,
  `res_tabl_name`          VARCHAR(64) NOT NULL,
  PRIMARY KEY (`res_tabl_id`),
  UNIQUE KEY `RES_TABLE_NAME_UNIQUE` (`res_tabl_name`),
  KEY `RES_TABLE_NAME_INDEX` (`res_tabl_name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `res_customer` (
  `res_cust_id`            BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `res_base_creation_date` DATETIME(6) NOT NULL,
  `res_base_creator`       VARCHAR(64)          DEFAULT NULL,
  `res_base_modifier`      VARCHAR(64)          DEFAULT NULL,
  `res_base_modify_date`   DATETIME(6)          DEFAULT NULL,
  `res_base_version`       BIGINT(20)  NOT NULL,
  `res_cust_firstname`     VARCHAR(64) NOT NULL,
  PRIMARY KEY (`res_cust_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `res_reservation` (
  `res_rsrv_id`            BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `res_base_creation_date` DATETIME(6) NOT NULL,
  `res_base_creator`       VARCHAR(64)          DEFAULT NULL,
  `res_base_modifier`      VARCHAR(64)          DEFAULT NULL,
  `res_base_modify_date`   DATETIME(6)          DEFAULT NULL,
  `res_base_version`       BIGINT(20)  NOT NULL,
  `res_rsrv_from`          DATETIME(6)          DEFAULT NULL,
  `res_rsrv_to`            DATETIME(6)          DEFAULT NULL,
  `res_cust_id`            BIGINT(20)  NOT NULL,
  `res_tabl_id`            BIGINT(20)  NOT NULL,
  PRIMARY KEY (`res_rsrv_id`),
  KEY `RES_CUST_RSRV_ID` (`res_cust_id`),
  KEY `RES_TABL_RSRV_ID` (`res_tabl_id`),
  CONSTRAINT `RES_CUST_RSRV_ID` FOREIGN KEY (`res_cust_id`) REFERENCES `res_customer` (`res_cust_id`),
  CONSTRAINT `RES_TABL_RSRV_ID` FOREIGN KEY (`res_tabl_id`) REFERENCES `res_table` (`res_tabl_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*
 * Data
 */
