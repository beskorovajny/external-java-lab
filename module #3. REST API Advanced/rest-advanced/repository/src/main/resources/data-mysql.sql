INSERT INTO EXTERNAL_LAB.TAG (ID, NAME )
VALUES ( DEFAULT, 'java'),
       (DEFAULT , 'scala'),
       ( DEFAULT, 'c'),
       (DEFAULT , 'c-sharp'),
       ( DEFAULT, 'kotlin'),
       (DEFAULT , 'visual basic');
INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE  (ID, NAME, DESCRIPTION , PRICE , DURATION , CREATE_DATE )
VALUES
    (DEFAULT , 'jvm', 'jvm based languages', 55.0, 2, '2023-03-23 15:58:05.284'),
    (DEFAULT , 'microsoft', 'monopoly', 55.0, 2, '2023-03-23 15:59:05.284'),
    (DEFAULT , 'mixed', 'all-in-one', 55.0, 2, '2023-03-23 16:00:05.284'),
    (DEFAULT , 'android', 'not familiar', 55.0, 2, '2023-03-23 17:58:05.284');

INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE_HAS_TAG  (gift_certificate_id, tag_id )
VALUES
    (1, 1),
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
