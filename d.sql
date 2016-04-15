-- Nettoyage de la BD

DROP SEQUENCE SeqClient;
DROP SEQUENCE SeqReservation;

DELETE FROM Client;

DELETE FROM TableRepas;

DELETE FROM Carte;

DELETE FROM Service;

DELETE FROM Reservation;

DELETE FROM Occupe;
