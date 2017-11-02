-- This Script only works if executed in a H2 shell otherwise create the user and Database as
-- mentioned below, connect to them and open the SQL Editor to execute the Create Table statements

-- Connect to MySQL using this connection : jdbc:h2:tcp://localhost/~/fuse-demo

DROP SCHEMA fuse_demo;

CREATE SCHEMA fuse_demo;

CREATE TABLE `fuse_demo`.`Payments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `from` VARCHAR(32) NULL ,
  `to` VARCHAR(32) NULL ,
  `amount` DOUBLE NULL ,
  `currency` VARCHAR(32) NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `fuse_demo`.`ProcessedPayments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paymentIdentifier` VARCHAR(32) NULL ,
  PRIMARY KEY (`id`) );

