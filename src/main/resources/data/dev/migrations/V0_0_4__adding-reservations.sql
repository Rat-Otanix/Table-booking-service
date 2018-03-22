/*
 * Engine: MySQL
 * Version: 0.0.4
 * Description: Adding some reservations record.
 */

/*
 * Structure
 */

/*
 * Data
 */
INSERT INTO `reservation`.`res_reservation` (`res_rsrv_id`, `res_base_creation_date`, `res_base_version`,
                                             `res_rsrv_from`, `res_rsrv_to`, `res_cust_id`, `res_tabl_id`)
VALUES
  ('1', '2018-03-14 09:11:58.261000', '1', '2050-03-16 09:00:00.000000', '2050-03-16 10:00:00.000000', '1', '1'),
  ('2', '2018-03-14 09:11:58.261000', '1', '2050-03-16 16:00:00.000000', '2050-03-16 17:00:00.000000', '2', '1'),
  ('3', '2018-03-14 09:11:58.261000', '1', '2050-03-16 16:00:00.000000', '2050-03-16 17:00:00.000000', '2', '3');
;