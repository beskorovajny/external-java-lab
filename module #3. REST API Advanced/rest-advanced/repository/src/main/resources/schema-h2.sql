/*
DROP SCHEMA IF EXISTS EXTERNAL_LAB CASCADE;
CREATE SCHEMA IF NOT EXISTS EXTERNAL_LAB;
SET SCHEMA EXTERNAL_LAB;

CREATE TABLE IF NOT EXISTS EXTERNAL_LAB.TAG
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS EXTERNAL_LAB.GIFT_CERTIFICATE
(
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `name`             VARCHAR(45)    NOT NULL UNIQUE ,
    `description`      VARCHAR(255)   NOT NULL,
    `price`            DECIMAL(10, 2) NOT NULL,
    `duration`         INT            NOT NULL,
    `create_date`      DATETIME(3)    NOT NULL,
    `last_update_date` DATETIME(3)    NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS EXTERNAL_LAB.TAG_HAS_GIFT_CERTIFICATE
(
    `tag_id`              INT NOT NULL,
    `gift_certificate_id` INT NOT NULL,
    PRIMARY KEY (`tag_id`, `gift_certificate_id`),
    FOREIGN KEY (`tag_id`)
        REFERENCES EXTERNAL_LAB.TAG (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE ,
    FOREIGN KEY (`gift_certificate_id`)
        REFERENCES EXTERNAL_LAB.GIFT_CERTIFICATE (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);*/
