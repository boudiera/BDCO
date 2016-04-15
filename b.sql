-- Insetions de n-uplets de test

CREATE SEQUENCE SeqClient;
CREATE SEQUENCE SeqReservation;

INSERT INTO Client
	VALUES (0, 'Clément', '0675962922');

INSERT INTO TableRepas
	VALUES (0, 4, 3, 2, 'Fenetre');

INSERT INTO Carte
	VALUES (1);

INSERT INTO Service
	VALUES (to_date('2016-12-19', 'YYYY-MM-DD'), 'SOIR', 1);

INSERT INTO Reservation
	VALUES (8, 2, 20, 30, 0, to_date('2016-12-19', 'YYYY-MM-DD'), 'SOIR');
INSERT INTO Reservation
	VALUES (7, 2, 20, 30, 0, to_date('2016-12-19', 'YYYY-MM-DD'), 'SOIR');

INSERT INTO Occupe
	VALUES (8, 0);
