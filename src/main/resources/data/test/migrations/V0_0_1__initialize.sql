/*
 * Engine: h2
 * Version: 0.0.1
 * Description: Initial database structure and data.
 */

/*
 * Structure
 */
-- CREATE TABLE PERSON (
--   id BIGINT GENERATED BY DEFAULT AS IDENTITY,
--   first_name varchar(255) not null,
--   last_name varchar(255) not null
-- );
--
-- create sequence person_sequence start with 1 increment by 1;
--
-- insert into PERSON (first_name, last_name) values ('Dave', 'Syer');

CREATE TABLE res_customer (
  res_cust_id            BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1
  INCREMENT BY 1 )                 NOT NULL,
  res_base_creation_date TIMESTAMP NOT NULL,
  res_base_creator       VARCHAR(64
                         ),
  res_base_modifier      VARCHAR(64
                         ),
  res_base_modify_date   TIMESTAMP,
  res_base_version       BIGINT    NOT NULL,
  res_cust_firstname     VARCHAR(64
                         )         NOT NULL,
  PRIMARY KEY (res_cust_id
  )
);
CREATE TABLE res_reservation (
  res_rsrv_id            BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1
  INCREMENT BY 1 )                 NOT NULL,
  res_base_creation_date TIMESTAMP NOT NULL,
  res_base_creator       VARCHAR(64),
  res_base_modifier      VARCHAR(64),
  res_base_modify_date   TIMESTAMP,
  res_base_version       BIGINT    NOT NULL,
  res_rsrv_from          TIMESTAMP,
  res_rsrv_to            TIMESTAMP,
  res_cust_id            BIGINT    NOT NULL,
  res_tabl_id            BIGINT    NOT NULL,
  PRIMARY KEY (res_rsrv_id)
);
CREATE TABLE res_table (
  res_tabl_id            BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1
  INCREMENT BY 1 )                   NOT NULL,
  res_base_creation_date TIMESTAMP   NOT NULL,
  res_base_creator       VARCHAR(64),
  res_base_modifier      VARCHAR(64),
  res_base_modify_date   TIMESTAMP,
  res_base_version       BIGINT      NOT NULL,
  res_tabl_name          VARCHAR(64) NOT NULL,
  PRIMARY KEY (res_tabl_id)
);
CREATE INDEX RES_TABLE_NAME_INDEX
  ON res_table (res_tabl_name);
ALTER TABLE res_table
  ADD CONSTRAINT RES_TABLE_NAME_UNIQUE UNIQUE (res_tabl_name);
ALTER TABLE res_reservation
  ADD CONSTRAINT RES_CUST_RSRV_ID FOREIGN KEY (res_cust_id) REFERENCES res_customer;
ALTER TABLE res_reservation
  ADD CONSTRAINT RES_TABL_RSRV_ID FOREIGN KEY (res_tabl_id) REFERENCES res_table;

/*
 * Data
 */
INSERT INTO res_customer VALUES
  (1, '2018-03-14 09:11:58.261000', NULL, NULL, NULL, 1, 'Customer1'),
  (2, '2018-03-15 09:11:58.261000', NULL, NULL, NULL, 1, 'Customer2'),
  (3, '2018-03-15 09:11:58.261000', NULL, NULL, NULL, 1, 'Customer3');

INSERT INTO res_table (res_tabl_id, res_base_creation_date, res_base_version, res_tabl_name)
VALUES
  ('1', '2018-03-14 09:11:58.261000', '1', 'Table1'),
  ('2', '2018-03-15 09:11:58.261000', '1', 'Table2'),
  ('3', '2018-03-16 09:11:58.261000', '1', 'Table3');

INSERT INTO res_reservation (res_rsrv_id, res_base_creation_date, res_base_version,
                             res_rsrv_from, res_rsrv_to, res_cust_id, res_tabl_id)
VALUES
  ('1', '2018-03-14 09:11:58.261000', '1', '2050-03-16 09:00:00.000000', '2050-03-16 10:00:00.000000', '1', '1'),
  ('2', '2018-03-14 09:11:58.261000', '1', '2050-03-16 16:00:00.000000', '2050-03-16 17:00:00.000000', '2', '1'),
  ('3', '2018-03-14 09:11:58.261000', '1', '2050-03-16 16:00:00.000000', '2050-03-16 17:00:00.000000', '2', '3');