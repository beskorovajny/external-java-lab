DROP SCHEMA IF EXISTS EXTERNAL_LAB;
CREATE SCHEMA IF NOT EXISTS EXTERNAL_LAB;

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
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`gift_certificate_id`)
        REFERENCES EXTERNAL_LAB.GIFT_CERTIFICATE (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

INSERT INTO EXTERNAL_LAB.TAG (ID, NAME )
VALUES ( DEFAULT, 'tag1'),
       (DEFAULT , 'tag2'),
       ( DEFAULT, 'tag3'),
       (DEFAULT , 'tag4'),
       ( DEFAULT, 'tag5'),
       (DEFAULT , 'tag6');
INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE  (ID, NAME, DESCRIPTION , PRICE , DURATION , CREATE_DATE )
VALUES
    (DEFAULT , 'certificate1', 'desc1',55.0,2,'2023-03-23 15:58:05.284'),
    (DEFAULT , 'certificate2', 'desc2',55.0,2,'2023-03-23 15:59:05.284'),
    (DEFAULT , 'certificate3', 'desc3',55.0,2,'2023-03-23 16:00:05.284'),
    (DEFAULT , 'certificate4', 'desc4',55.0,2,'2023-03-23 17:58:05.284'),
    (DEFAULT , 'certificate5', 'desc5',55.0,2,'2023-03-23 18:58:05.284'),
    (DEFAULT , 'certificate6', 'desc6',55.0,2,'2023-03-23 19:58:05.284');