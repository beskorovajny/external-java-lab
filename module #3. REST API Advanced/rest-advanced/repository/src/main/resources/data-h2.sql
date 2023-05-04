INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE (id, name, description, price, duration, create_date)
VALUES (DEFAULT, 'jvm', 'jvm based languages', 55.0, 2, '2023-03-23 15:58:05.284'),
       (DEFAULT, 'microsoft', 'monopoly', 55.0, 2, '2023-03-23 15:59:05.284'),
       (DEFAULT, 'mixed', 'all-in-one', 55.0, 2, '2023-03-23 16:00:05.284'),
       (DEFAULT, 'android', 'not familiar', 55.0, 2, '2023-03-23 17:58:05.284');

INSERT INTO EXTERNAL_LAB.TAG (id, name)
VALUES (DEFAULT, 'java'),
       (DEFAULT, 'scala'),
       (DEFAULT, 'c'),
       (DEFAULT, 'c-sharp'),
       (DEFAULT, 'kotlin'),
       (DEFAULT, 'visual basic');

INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE_HAS_TAG (gift_certificate_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (2, 3),
       (2, 4),
       (2, 6),
       (3, 1),
       (3, 4),
       (3, 6),
       (4, 5),
       (4, 1);
INSERT INTO EXTERNAL_LAB.USERS (id, email, first_name, last_name)
VALUES (DEFAULT, 'mwhitcomb0@ow.ly', 'Whitcomb', 'Minna'),
       (DEFAULT, 'mferriere1@hexun.com', 'Ferriere', 'Mercedes'),
       (DEFAULT, 'mpoulter2@xrea.com', 'Poulter', 'Milty'),
       (DEFAULT, 'epark3@nps.gov', 'Park', 'Emilio'),
       (DEFAULT, 'dmunnings4@buzzfeed.com', 'Munnings', 'Diena'),
       (DEFAULT, 'bbrickell5@angelfire.com', 'Brickell', 'Byrom'),
       (DEFAULT, 'ratterley6@oracle.com', 'Atterley', 'Raimondo'),
       (DEFAULT, 'smcgivena7@livejournal.com', 'McGivena', 'Selma'),
       (DEFAULT, 'tshillinglaw8@t-online.de', 'Shillinglaw', 'Tulley'),
       (DEFAULT, 'hjanik9@tinypic.com', 'Janik', 'Hakim');