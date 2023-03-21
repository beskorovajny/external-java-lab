-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema external_lab
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `external_lab` ;

-- -----------------------------------------------------
-- Schema external_lab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `external_lab` DEFAULT CHARACTER SET utf8 ;
USE `external_lab` ;

-- -----------------------------------------------------
-- Table `external_lab`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `external_lab`.`tag` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `external_lab`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `external_lab`.`gift_certificate` (
                                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                                 `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `duration` INT NOT NULL,
    `create_date` DATETIME(3) NOT NULL,
    `last_update_date` DATETIME(3) NULL,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `external_lab`.`tag_has_gift_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `external_lab`.`tag_has_gift_certificate` (
                                                                         `tag_id` INT NOT NULL,
                                                                         `gift_certificate_id` INT NOT NULL,
                                                                         PRIMARY KEY (`tag_id`, `gift_certificate_id`),
    INDEX `fk_tag_has_gift_certificate_gift_certificate1_idx` (`gift_certificate_id` ASC) VISIBLE,
    INDEX `fk_tag_has_gift_certificate_tag_idx` (`tag_id` ASC) VISIBLE,
    CONSTRAINT `fk_tag_has_gift_certificate_tag`
    FOREIGN KEY (`tag_id`)
    REFERENCES `external_lab`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_tag_has_gift_certificate_gift_certificate1`
    FOREIGN KEY (`gift_certificate_id`)
    REFERENCES `external_lab`.`gift_certificate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
