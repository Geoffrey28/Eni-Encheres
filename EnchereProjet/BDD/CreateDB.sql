USE KILOUTOU_DB

CREATE TABLE Client
(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	nom varchar(30) NOT NULL,
	prenom varchar(30) NOT NULL,
	telephone varchar(10) NOT NULL,
	email varchar(50) NOT NULL UNIQUE,
	password varchar(80) NOT NULL,
	typePermis varchar(4) NOT NULL
);

CREATE TABLE Adresse
(
	id_Personne int NOT NULL,
	numero int NOT NULL,
	rue varchar(50) NOT NULL,
	codepostal int NOT NULL,
	ville varchar(30) NOT NULL
);

ALTER TABLE Adresse WITH CHECK ADD
	CONSTRAINT FK_Adresse_idPersonne FOREIGN KEY (id_Personne)
		REFERENCES Client(id);

CREATE TABLE Voiture
(
	id int NOT NULL IDENTITY(1,1) PRIMARY KEY,
	modele varchar(20) NOT NULL,
	marque varchar(20) NOT NULL,
	immatriculation varchar(7) NOT NULL,
	puissance int NOT NULL,
	statut char(2) NOT NULL,
	prixLocation int NOT NULL,
	motorisation char(2) NOT NULL,
	nbrPortes int NOT NULL CONSTRAINT CK_nbrPortes CHECK (nbrPortes < 6)
);

SELECT * FROM Client
SELECT * FROM Adresse