USE external_lab;

INSERT INTO EXTERNAL_LAB.GIFT_CERTIFICATE (id, name, description, price, duration, create_date)
VALUES (DEFAULT, 'jvm', 'jvm based languages', 255.0, 2, '2023-03-23 15:58:05.284'),
       (DEFAULT, 'microsoft', 'monopoly', 155.0, 2, '2023-03-23 15:59:05.284'),
       (DEFAULT, 'mixed', 'all-in-one', 55.0, 2, '2023-03-23 16:00:05.284'),
       (DEFAULT, 'android', 'not familiar', 25.0, 2, '2023-03-23 17:58:05.284');

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

insert into external_lab.users (email, first_name, last_name) values ('mrawstronpg@homestead.com', 'Muhammad', 'Rawstron');
insert into external_lab.users (email, first_name, last_name) values ('kwoolliamsph@biblegateway.com', 'Korney', 'Woolliams');
insert into external_lab.users (email, first_name, last_name) values ('fspellardpi@oracle.com', 'Fae', 'Spellard');
insert into external_lab.users (email, first_name, last_name) values ('rtofftspj@intel.com', 'Rozella', 'Toffts');
insert into external_lab.users (email, first_name, last_name) values ('rmclagainpk@hud.gov', 'Ramon', 'McLagain');
insert into external_lab.users (email, first_name, last_name) values ('rgoddmanpl@google.it', 'Rance', 'Goddman');
insert into external_lab.users (email, first_name, last_name) values ('kfarakerpm@bbc.co.uk', 'Kettie', 'Faraker');
insert into external_lab.users (email, first_name, last_name) values ('achesselpn@lycos.com', 'Antoine', 'Chessel');
insert into external_lab.users (email, first_name, last_name) values ('fdorkinspo@ocn.ne.jp', 'Frannie', 'Dorkins');
insert into external_lab.users (email, first_name, last_name) values ('jgiblettpp@gnu.org', 'Jeno', 'Giblett');
insert into external_lab.users (email, first_name, last_name) values ('corbellpq@archive.org', 'Conroy', 'Orbell');
insert into external_lab.users (email, first_name, last_name) values ('lsimnettpr@hud.gov', 'Linet', 'Simnett');
insert into external_lab.users (email, first_name, last_name) values ('ssillarsps@furl.net', 'Shirleen', 'Sillars');
insert into external_lab.users (email, first_name, last_name) values ('wmacveighpt@japanpost.jp', 'Wallas', 'Macveigh');
insert into external_lab.users (email, first_name, last_name) values ('brootespu@youtu.be', 'Bobby', 'Rootes');
insert into external_lab.users (email, first_name, last_name) values ('hzanucioliipv@telegraph.co.uk', 'Hubey', 'Zanuciolii');
insert into external_lab.users (email, first_name, last_name) values ('acocktonpw@opera.com', 'Arlyn', 'Cockton');
insert into external_lab.users (email, first_name, last_name) values ('egrundypx@disqus.com', 'Elora', 'Grundy');
insert into external_lab.users (email, first_name, last_name) values ('htebbittpy@redcross.org', 'Harriet', 'Tebbitt');
insert into external_lab.users (email, first_name, last_name) values ('sduerpz@1und1.de', 'Sammie', 'Duer');
insert into external_lab.users (email, first_name, last_name) values ('mberringtonq0@twitpic.com', 'Micki', 'Berrington');
insert into external_lab.users (email, first_name, last_name) values ('kdraxfordq1@vimeo.com', 'Kaiser', 'Draxford');
insert into external_lab.users (email, first_name, last_name) values ('mbedhamq2@sphinn.com', 'Matt', 'Bedham');
insert into external_lab.users (email, first_name, last_name) values ('ftorettaq3@imdb.com', 'Fannie', 'Toretta');
insert into external_lab.users (email, first_name, last_name) values ('palekseicikq4@printfriendly.com', 'Philly', 'Alekseicik');
insert into external_lab.users (email, first_name, last_name) values ('cdupreyq5@nasa.gov', 'Curran', 'Duprey');
insert into external_lab.users (email, first_name, last_name) values ('zwickenq6@rakuten.co.jp', 'Zachariah', 'Wicken');
insert into external_lab.users (email, first_name, last_name) values ('ecluteq7@flickr.com', 'Enrique', 'Clute');
insert into external_lab.users (email, first_name, last_name) values ('dredolfiq8@vkontakte.ru', 'Darrelle', 'Redolfi');
insert into external_lab.users (email, first_name, last_name) values ('pdeftieq9@mlb.com', 'Pat', 'Deftie');
insert into external_lab.users (email, first_name, last_name) values ('bbidewelqa@europa.eu', 'Brandyn', 'Bidewel');
insert into external_lab.users (email, first_name, last_name) values ('nconybearqb@pen.io', 'Nahum', 'Conybear');
insert into external_lab.users (email, first_name, last_name) values ('fmostinqc@csmonitor.com', 'Fancie', 'Mostin');
insert into external_lab.users (email, first_name, last_name) values ('pwiginqd@bigcartel.com', 'Poppy', 'Wigin');
insert into external_lab.users (email, first_name, last_name) values ('zbrosioqe@time.com', 'Zandra', 'Brosio');
insert into external_lab.users (email, first_name, last_name) values ('fwaitlandqf@t-online.de', 'Francine', 'Waitland');
insert into external_lab.users (email, first_name, last_name) values ('ppatmanqg@time.com', 'Paulo', 'Patman');
insert into external_lab.users (email, first_name, last_name) values ('apeschetqh@surveymonkey.com', 'Annemarie', 'Peschet');
insert into external_lab.users (email, first_name, last_name) values ('silyuninqi@nature.com', 'Sergent', 'Ilyunin');
insert into external_lab.users (email, first_name, last_name) values ('abatthewqj@boston.com', 'Alastair', 'Batthew');
insert into external_lab.users (email, first_name, last_name) values ('jprimmerqk@dion.ne.jp', 'Jacobo', 'Primmer');
insert into external_lab.users (email, first_name, last_name) values ('areckql@businessinsider.com', 'Ashely', 'Reck');
insert into external_lab.users (email, first_name, last_name) values ('saynscombeqm@aol.com', 'Susy', 'Aynscombe');
insert into external_lab.users (email, first_name, last_name) values ('zwhebleqn@go.com', 'Zebadiah', 'Wheble');
insert into external_lab.users (email, first_name, last_name) values ('gdisburyqo@wordpress.com', 'Grange', 'Disbury');
insert into external_lab.users (email, first_name, last_name) values ('mclemontsqp@cbslocal.com', 'Margret', 'Clemonts');
insert into external_lab.users (email, first_name, last_name) values ('tfihellyqq@nymag.com', 'Tamas', 'Fihelly');
insert into external_lab.users (email, first_name, last_name) values ('breinbeckqr@so-net.ne.jp', 'Broddy', 'Reinbeck');
insert into external_lab.users (email, first_name, last_name) values ('mzannuttiqs@samsung.com', 'Morie', 'Zannutti');
insert into external_lab.users (email, first_name, last_name) values ('ntopingqt@home.pl', 'Neila', 'Toping');
insert into external_lab.users (email, first_name, last_name) values ('mtorrequ@fema.gov', 'Marissa', 'Torre');
insert into external_lab.users (email, first_name, last_name) values ('lroddenqv@theguardian.com', 'Lorilyn', 'Rodden');
insert into external_lab.users (email, first_name, last_name) values ('ofineyqw@earthlink.net', 'Olvan', 'Finey');
insert into external_lab.users (email, first_name, last_name) values ('mhardwellqx@etsy.com', 'Mandi', 'Hardwell');
insert into external_lab.users (email, first_name, last_name) values ('tseilerqy@cbslocal.com', 'Travus', 'Seiler');
insert into external_lab.users (email, first_name, last_name) values ('cbubbqz@flavors.me', 'Calida', 'Bubb');
insert into external_lab.users (email, first_name, last_name) values ('challiganr0@telegraph.co.uk', 'Charissa', 'Halligan');
insert into external_lab.users (email, first_name, last_name) values ('galflatr1@ocn.ne.jp', 'Glynis', 'Alflat');
insert into external_lab.users (email, first_name, last_name) values ('mbrunr2@amazon.co.jp', 'Morie', 'Brun');
insert into external_lab.users (email, first_name, last_name) values ('swigelsworthr3@is.gd', 'Shurlocke', 'Wigelsworth');
insert into external_lab.users (email, first_name, last_name) values ('pfriser4@webmd.com', 'Pansy', 'Frise');
insert into external_lab.users (email, first_name, last_name) values ('csingyardr5@phoca.cz', 'Clair', 'Singyard');
insert into external_lab.users (email, first_name, last_name) values ('fferrandr6@washingtonpost.com', 'Frasco', 'Ferrand');
insert into external_lab.users (email, first_name, last_name) values ('lpleveyr7@wix.com', 'Lonnard', 'Plevey');
insert into external_lab.users (email, first_name, last_name) values ('ssemanr8@seesaa.net', 'Sukey', 'Seman');
insert into external_lab.users (email, first_name, last_name) values ('dimpletonr9@icio.us', 'Darleen', 'Impleton');
insert into external_lab.users (email, first_name, last_name) values ('gtandyra@shutterfly.com', 'Giulia', 'Tandy');
insert into external_lab.users (email, first_name, last_name) values ('creglarrb@diigo.com', 'Cordula', 'Reglar');
insert into external_lab.users (email, first_name, last_name) values ('mguilbertrc@cpanel.net', 'Mannie', 'Guilbert');
insert into external_lab.users (email, first_name, last_name) values ('ddashwoodrd@ifeng.com', 'Devan', 'Dashwood');
insert into external_lab.users (email, first_name, last_name) values ('bscrowtonre@ask.com', 'Barth', 'Scrowton');
insert into external_lab.users (email, first_name, last_name) values ('jshackellrf@slashdot.org', 'Jeremiah', 'Shackell');
insert into external_lab.users (email, first_name, last_name) values ('telanrg@rakuten.co.jp', 'Tori', 'Elan');
insert into external_lab.users (email, first_name, last_name) values ('wansillrh@marketwatch.com', 'Way', 'Ansill');
insert into external_lab.users (email, first_name, last_name) values ('mlemarquisri@digg.com', 'Michaella', 'Le Marquis');
insert into external_lab.users (email, first_name, last_name) values ('sdevennierj@skyrock.com', 'Sheelagh', 'Devennie');
insert into external_lab.users (email, first_name, last_name) values ('bbabbagerk@ft.com', 'Bettina', 'Babbage');
insert into external_lab.users (email, first_name, last_name) values ('dreinbechrl@fastcompany.com', 'Darcey', 'Reinbech');
insert into external_lab.users (email, first_name, last_name) values ('ckristoffersenrm@nsw.gov.au', 'Collin', 'Kristoffersen');
insert into external_lab.users (email, first_name, last_name) values ('aalphegern@nba.com', 'Alan', 'Alphege');
insert into external_lab.users (email, first_name, last_name) values ('wvogellerro@netvibes.com', 'Way', 'Vogeller');
insert into external_lab.users (email, first_name, last_name) values ('isquelchrp@merriam-webster.com', 'Ingeborg', 'Squelch');
insert into external_lab.users (email, first_name, last_name) values ('kmelmethrq@sphinn.com', 'Kanya', 'Melmeth');
insert into external_lab.users (email, first_name, last_name) values ('abroginirr@imdb.com', 'Ashlen', 'Brogini');


INSERT INTO EXTERNAL_LAB.RECEIPT (ID, PRICE, CREATE_DATE, USER_ID)
VALUES (DEFAULT, 210.0, '2023-03-23 15:58:05.284', 1),
       (DEFAULT, 255.0, '2023-04-23 15:58:05.284', 1),
       (DEFAULT, 256.0, '2023-04-29 15:58:05.284', 2),
       (DEFAULT, 128.0, '2023-05-01 15:58:05.284', 3),
       (DEFAULT, 50.0, '2023-03-13 15:58:05.284', 4),
       (DEFAULT, 111.0, '2023-03-22 15:58:05.284', 3);
INSERT INTO EXTERNAL_LAB.RECEIPT_HAS_GIFT_CERTIFICATE (RECEIPT_ID, GIFT_CERTIFICATE_ID)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 2),
       (4, 3),
       (4, 1),
       (4, 2),
       (5, 3),
       (6, 1),
       (6, 2),
       (6, 3),
       (6, 4);