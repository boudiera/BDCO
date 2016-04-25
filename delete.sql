-- Nettoyage de la BD

DROP SEQUENCE SeqClient;
DROP SEQUENCE SeqReservation;
DROP SEQUENCE SeqTable;
DROP SEQUENCE SeqCarte;



DELETE FROM Commande;

DELETE FROM EstElement;

DELETE FROM ContientAutreArticle;

DELETE FROM ContientPlat;

DELETE FROM EstVoisine;

DELETE FROM Coute;

DELETE FROM Occupe;

DELETE FROM Reservation;

DELETE FROM Service;

DELETE FROM Article;

DELETE FROM Carte;

DELETE FROM Client;

DELETE FROM TableRepas;

