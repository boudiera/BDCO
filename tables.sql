-- Creation des differentes tables

CREATE TABLE PrixTotal (
    Valeur FLOAT,
    CONSTRAINT valeur_pk PRIMARY KEY (Valeur),
    CONSTRAINT valeur_c CHECK (Valeur >= 0)
);

CREATE TABLE Client (
    CodeClient INTEGER,
    NomClient VARCHAR(20),
    NumTel VARCHAR(10),
    CONSTRAINT codeClient_pk PRIMARY KEY (CodeClient)
);

CREATE TABLE TableRepas (
    CodeTable INTEGER,
    NbPlace0 INTEGER,
    NbPlace1 INTEGER,
    NbPlace2 INTEGER,
    Localisation VARCHAR(20),
    CONSTRAINT codeTable_pk PRIMARY KEY (CodeTable),
    CONSTRAINT nbPlace0_c CHECK (NbPlace0 >= NbPlace1),
    CONSTRAINT nbPlace1_c CHECK (NbPlace1 >= NbPlace2),
    CONSTRAINT nbPlace2_c CHECK (NbPlace2 >= 0)
);

CREATE TABLE Carte (
    CodeCarte INTEGER,
    CONSTRAINT codeCarte_pk PRIMARY KEY (CodeCarte)
);

CREATE TABLE Article (
    NomArticle VARCHAR(50),
    TypeArticle VARCHAR(20),
    NomSpecialite VARCHAR(20),
    CONSTRAINT nomArticle_pk PRIMARY KEY (NomArticle),
    CONSTRAINT typeArticle_c CHECK (TypeArticle IN ('MENU', 'ENTREE', 'PLAT', 'DESSERT', 'BOISSON'))
);

CREATE TABLE Service (
    Jour DATE,
    NomService VARCHAR(5),
    CodeCarte INTEGER,
    CONSTRAINT service_pk PRIMARY KEY (Jour, NomService),
    CONSTRAINT nomService_c CHECK (NomService IN ('MIDI', 'SOIR')),
    CONSTRAINT codeCarte_fk FOREIGN KEY (CodeCarte) REFERENCES Carte (CodeCarte)
);

CREATE TABLE Reservation (
    CodeReservation INTEGER,
    NbPersonnes INTEGER,
    Heure INT,
    Minutes INT,
    CodeClient INTEGER,
    Jour DATE,
    NomService VARCHAR(5),
    Prix INT CHECK(PRIX >= 0)
    CONSTRAINT codeReservation_pk PRIMARY KEY (CodeReservation),
    CONSTRAINT nbPersonnes_c CHECK (NbPersonnes > 0),
    CONSTRAINT codeClient_fk FOREIGN KEY (CodeClient) REFERENCES Client (CodeClient),
    CONSTRAINT service_fk FOREIGN KEY (Jour, NomService) REFERENCES Service (Jour, NomService) ON DELETE CASCADE
);

CREATE TABLE Coute (
    CodeReservation INTEGER,
    PrixTotal FLOAT,
    CONSTRAINT coute_pk PRIMARY KEY (CodeReservation),
    CONSTRAINT codeReservation_fk FOREIGN KEY (CodeReservation) REFERENCES Reservation (CodeReservation) ON DELETE CASCADE,
    CONSTRAINT prixTotal_fk FOREIGN KEY (PrixTotal) REFERENCES PrixTotal(Valeur)
);

CREATE TABLE Occupe (
    CodeReservation INTEGER,
    CodeTable INTEGER,
    CONSTRAINT occupe_pk PRIMARY KEY (CodeReservation, CodeTable),
    CONSTRAINT codeReservationOccupe_fk FOREIGN KEY (CodeReservation) REFERENCES Reservation (CodeReservation) ON DELETE CASCADE,
	CONSTRAINT codeTable_fk FOREIGN KEY (CodeTable) REFERENCES TableRepas (CodeTable) ON DELETE CASCADE
);

CREATE TABLE EstVoisine (
    CodeTable INTEGER,
    CodeTableVoisine INTEGER,
    CONSTRAINT estVoisine_pk PRIMARY KEY (CodeTable, CodeTableVoisine),
    CONSTRAINT codeTableVoisine1_fk FOREIGN KEY (CodeTable) REFERENCES TableRepas (CodeTable) ON DELETE CASCADE,
    CONSTRAINT codeTableVoisine2_fk FOREIGN KEY (CodeTableVoisine) REFERENCES TableRepas (CodeTable) ON DELETE CASCADE
);

CREATE TABLE ContientPlat (
    NomArticleMenu VARCHAR(50),
    NomArticlePlat VARCHAR(50),
    CONSTRAINT contientPlat_pk PRIMARY KEY (NomArticleMenu, NomArticlePlat),
    CONSTRAINT nomArticleMenu_fk FOREIGN KEY (NomArticleMenu) REFERENCES Article (NomArticle) ON DELETE CASCADE,
    CONSTRAINT nomArticlePlat_fk FOREIGN KEY (NomArticlePlat) REFERENCES Article (NomArticle) ON DELETE CASCADE
);

CREATE TABLE ContientAutreArticle (
    NomArticleMenu VARCHAR(50),
    NomArticleAutre VARCHAR(50),
    CONSTRAINT contientAutre_pk PRIMARY KEY (NomArticleMenu, NomArticleAutre),
    CONSTRAINT nomArticleMenuAutre_fk FOREIGN KEY (NomArticleMenu) REFERENCES Article (NomArticle) ON DELETE CASCADE,
    CONSTRAINT nomArticleAutre_fk FOREIGN KEY (NomArticleAutre) REFERENCES Article (NomArticle) ON DELETE CASCADE
);

CREATE TABLE EstElement (
    NomArticle VARCHAR(50),
    CodeCarte INTEGER,
    PrixActuel FLOAT,
    CONSTRAINT estElement_pk PRIMARY KEY (NomArticle, CodeCarte),
    CONSTRAINT nomArticleElement_fk FOREIGN KEY (NomArticle) REFERENCES Article (NomArticle) ON DELETE CASCADE,
    CONSTRAINT codeCarteElement_fk FOREIGN KEY (CodeCarte) REFERENCES Carte (CodeCarte) ON DELETE CASCADE,
    CONSTRAINT prixActuel_c CHECK (PrixActuel >= 0)
);

CREATE TABLE Commande (
    CodeReservation INTEGER,
    NomArticle VARCHAR(50),
    Quantite INTEGER,
    CONSTRAINT commande_pk PRIMARY KEY (CodeReservation, NomArticle),
    CONSTRAINT codeReservationCommande_fk FOREIGN KEY (CodeReservation) REFERENCES Reservation (CodeReservation) ON DELETE CASCADE,
    CONSTRAINT nomArticleCommande_fk FOREIGN KEY (NomArticle) REFERENCES Article (NomArticle) ON DELETE CASCADE,
    CONSTRAINT quantite_c CHECK (Quantite > 0)
);
