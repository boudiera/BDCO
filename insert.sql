-- Insetions de n-uplets de test

CREATE SEQUENCE SeqClient;
CREATE SEQUENCE SeqTable;
CREATE SEQUENCE SeqReservation;
CREATE SEQUENCE SeqCarte;


-- Creation des tables
-- 3 tables exterieures
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'EXTERIEUR');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'EXTERIEUR');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'EXTERIEUR');
-- 4 tables coté fenêtre
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 6, 5, 4, 'FENETRE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 6, 5, 4, 'FENETRE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'FENETRE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'FENETRE');
-- 5 tables coté cuisine
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 6, 5, 4, 'CUISINE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 4, 4, 4, 'CUISINE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 4, 4, 4, 'CUISINE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'CUISINE');
INSERT INTO TableRepas
	VALUES (SeqTable.nextval, 2, 2, 2, 'CUISINE');


-- Creation de clients
INSERT INTO Client
	VALUES (SeqClient.nextval, 'ARNAUD', '0607080910');
INSERT INTO Client
	VALUES (SeqClient.nextval, 'CLEMENT', '0000000000');
INSERT INTO Client
	VALUES (SeqClient.nextval, 'FLORENT', '0123456789');
INSERT INTO Client
	VALUES (SeqClient.nextval, 'FLORIAN', '0605040302');
INSERT INTO Client
	VALUES (SeqClient.nextval, 'IAGO', '0987654321');
INSERT INTO Client
	VALUES (SeqClient.nextval, 'NICOLAS', '0102030405');


-- Creation de cartes
INSERT INTO Carte
	VALUES (SeqCarte.nextval);
INSERT INTO Carte
	VALUES (SeqCarte.nextval);
INSERT INTO Carte
	VALUES (SeqCarte.nextval);


-- Creation d'articles
-- Boissons
INSERT INTO Article
	VALUES ('COCA', 'BOISSON', 'AUCUNE');
INSERT INTO Article
	VALUES ('DIABOLO', 'BOISSON', 'AUCUNE');
INSERT INTO Article
	VALUES ('COTE DU RHONE', 'BOISSON', 'AUCUNE');
INSERT INTO Article
	VALUES ('ARCENS', 'BOISSON', 'ARDECHE');
INSERT INTO Article
	VALUES ('KIR CHATAIGNE', 'BOISSON', 'AUCUNE');
INSERT INTO Article
	VALUES ('CAFE', 'BOISSON', 'AUCUNE');
INSERT INTO Article
	VALUES ('BIERE', 'BOISSON', 'AUCUNE');
-- Menus
INSERT INTO Article
	VALUES ('PETITE FAIM', 'MENU', 'AUCUNE');
INSERT INTO Article
	VALUES ('GROSSE FAIM', 'MENU', 'ARDECHE');
-- Entrees
INSERT INTO Article
	VALUES ('SALADE RAVIOLES', 'ENTREE', 'ARDECHE');
INSERT INTO Article
	VALUES ('SALADE CAILLETTE', 'ENTREE', 'ARDECHE');
INSERT INTO Article
	VALUES ('SALADE CESAR', 'ENTREE', 'AUCUNE');
INSERT INTO Article
	VALUES ('FOIE GRAS', 'ENTREE', 'AUCUNE');
-- Plats
INSERT INTO Article
	VALUES ('CIVET SANGLIER', 'PLAT', 'ARDECHE');
INSERT INTO Article
	VALUES ('PAVET CABILLAUD', 'PLAT', 'MER');
INSERT INTO Article
	VALUES ('MOULES FRITES', 'PLAT', 'MER');
INSERT INTO Article
	VALUES ('GRATIN RAVIOLES', 'PLAT', 'ARDECHE');
INSERT INTO Article
	VALUES ('SALADE COMPLETE', 'PLAT', 'ARDECHE');
--Desserts
INSERT INTO Article
	VALUES ('MARRONS GLACES', 'DESSERT', 'ARDECHE');
INSERT INTO Article
	VALUES ('PROFITEROLES', 'DESSERT', 'AUCUNE');
INSERT INTO Article
	VALUES ('GLACES 2 BOULES', 'DESSERT', 'AUCUNE');
INSERT INTO Article
	VALUES ('SALADE FRUITS', 'DESSERT', 'AUCUNE');


-- Creation de services
INSERT INTO Service
	VALUES (to_date('2016-04-16', 'YYYY-MM-DD'), 'SOIR', 3);
INSERT INTO Service
	VALUES (to_date('2016-04-28', 'YYYY-MM-DD'), 'MIDI', 1);
INSERT INTO Service
	VALUES (to_date('2016-04-28', 'YYYY-MM-DD'), 'SOIR', 1);
INSERT INTO Service
	VALUES (to_date('2016-04-29', 'YYYY-MM-DD'), 'MIDI', 1);
INSERT INTO Service
	VALUES (to_date('2016-04-29', 'YYYY-MM-DD'), 'SOIR', 3);
INSERT INTO Service
	VALUES (to_date('2016-04-30', 'YYYY-MM-DD'), 'MIDI', 2);
INSERT INTO Service
	VALUES (to_date('2016-04-30', 'YYYY-MM-DD'), 'SOIR', 3);


-- Creation de reservations
INSERT INTO Reservation
	VALUES (SeqReservation.nextval, 2, 20, 00, 5, to_date('2016-04-16', 'YYYY-MM-DD'), 'SOIR',0);
INSERT INTO Reservation
	VALUES (SeqReservation.nextval, 2, 20, 00, 2, to_date('2016-04-30', 'YYYY-MM-DD'), 'SOIR',0);
INSERT INTO Reservation
	VALUES (SeqReservation.nextval, 5, 20, 00, 4, to_date('2016-04-30', 'YYYY-MM-DD'), 'SOIR',0);
INSERT INTO Reservation
	VALUES (SeqReservation.nextVal, 8, 12, 30, 1, to_date('2016-04-30', 'YYYY-MM-DD'), 'MIDI',0);
INSERT INTO Reservation
	VALUES (SeqReservation.nextval, 1, 12, 10, 3, to_date('2016-04-28', 'YYYY-MM-DD'), 'MIDI',0);


-- Creation de occupe
INSERT INTO Occupe
	VALUES (1, 3);
INSERT INTO Occupe
	VALUES (2, 1);
INSERT INTO Occupe
	VALUES (3, 4);
INSERT INTO Occupe
	VALUES (4, 9);
INSERT INTO Occupe
	VALUES (4, 10);
INSERT INTO Occupe
	VALUES (5, 12);


-- Creation de PrixTotal
INSERT INTO PrixTotal
	VALUES (30.5);


-- Creation de Coute
INSERT INTO Coute
	VALUES (1, 30.5);


-- Creation de EstVoisine
-- Exterieur
INSERT INTO EstVoisine
	VALUES (1, 2);
INSERT INTO EstVoisine
	VALUES (2, 1);
INSERT INTO EstVoisine
	VALUES (1, 3);
INSERT INTO EstVoisine
	VALUES (3, 1);
INSERT INTO EstVoisine
	VALUES (2, 3);
INSERT INTO EstVoisine
	VALUES (3, 2);
-- Fenetre
INSERT INTO EstVoisine
	VALUES (4, 5);
INSERT INTO EstVoisine
	VALUES (5, 4);
INSERT INTO EstVoisine
	VALUES (4, 6);
INSERT INTO EstVoisine
	VALUES (6, 4);
INSERT INTO EstVoisine
	VALUES (5, 7);
INSERT INTO EstVoisine
	VALUES (7, 5);
INSERT INTO EstVoisine
	VALUES (6, 7);
INSERT INTO EstVoisine
	VALUES (7, 6);
-- Cuisine
INSERT INTO EstVoisine
	VALUES (8, 9);
INSERT INTO EstVoisine
	VALUES (9, 8);
INSERT INTO EstVoisine
	VALUES (8, 11);
INSERT INTO EstVoisine
	VALUES (11, 8);
INSERT INTO EstVoisine
	VALUES (9, 10);
INSERT INTO EstVoisine
	VALUES (10, 9);
INSERT INTO EstVoisine
	VALUES (10, 12);
INSERT INTO EstVoisine
	VALUES (12, 10);
INSERT INTO EstVoisine
	VALUES (11, 12);
INSERT INTO EstVoisine
	VALUES (12, 11);


-- Creation de ContientPlat
INSERT INTO ContientPlat
	VALUES ('PETITE FAIM', 'SALADE COMPLETE');
INSERT INTO ContientPlat
	VALUES ('PETITE FAIM', 'PAVET CABILLAUD');
INSERT INTO ContientPlat
	VALUES ('GROSSE FAIM', 'CIVET SANGLIER');
INSERT INTO ContientPlat
	VALUES ('GROSSE FAIM', 'MOULES FRITES');
INSERT INTO ContientPlat
	VALUES ('GROSSE FAIM', 'GRATIN RAVIOLES');


-- Creation de ContientAutreArticle
-- Petite Faim
INSERT INTO ContientAutreArticle
	VALUES ('PETITE FAIM', 'SALADE FRUITS');
-- Boissons
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'BIERE');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'ARCENS');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'KIR CHATAIGNE');
-- Entrees
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'SALADE RAVIOLES');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'SALADE CAILLETTE');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'FOIE GRAS');
-- Desserts
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'MARRONS GLACES');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'PROFITEROLES');
INSERT INTO ContientAutreArticle
	VALUES ('GROSSE FAIM', 'GLACES 2 BOULES');


-- Creation EstElement
-- Première carte
INSERT INTO EstElement
	VALUES ('PETITE FAIM', 1, 12.0);
INSERT INTO EstElement
	VALUES ('CAFE', 1, 1.50);
INSERT INTO EstElement
	VALUES ('ARCENS', 1, 2.0);
INSERT INTO EstElement
	VALUES ('COCA', 1, 2.0);
INSERT INTO EstElement
	VALUES ('FOIE GRAS', 1, 5.0);
-- Deuxième carte
INSERT INTO EstElement
	VALUES ('GROSSE FAIM', 2, 18.50);
INSERT INTO EstElement
	VALUES ('COCA', 2, 2.0);
-- Troisième carte
INSERT INTO EstElement
	VALUES ('PETITE FAIM', 3, 12.0);
INSERT INTO EstElement
	VALUES ('GROSSE FAIM', 3, 18.50);
INSERT INTO EstElement
	VALUES ('SALADE FRUITS', 1, 3.0);


-- Creation des commandes
INSERT INTO Commande
	VALUES (1, 'PETITE FAIM', 1);
INSERT INTO Commande
	VALUES (1, 'GROSSE FAIM', 1);


