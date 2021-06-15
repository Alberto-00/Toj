DROP database if exists toj_sitoweb;
CREATE DATABASE toj_sitoweb;
USE toj_sitoweb;

CREATE TABLE Categoria(
	ID_categoria INT UNSIGNED PRIMARY KEY,
    Nome VARCHAR(20) NOT NULL
); 

CREATE TABLE Articolo(
	ID_articolo INT UNSIGNED PRIMARY KEY,
    Prezzo DOUBLE(5,2) default 0.00,
    Sesso CHAR(1) NOT NULL,
    Descrizione TEXT,
    sconto DOUBLE default 0,
    data_inserimento DATE NOT NULL,
    ID_categoria INT UNSIGNED NOT NULL,
    Nome text NOT NULL,
    foreign key (ID_categoria) references Categoria (ID_categoria)
    ON UPDATE CASCADE
    ON DELETE CASCADE
); 

CREATE TABLE pathImg(
    pathName VARCHAR(500) primary key,
    ID_articolo INT UNSIGNED,
    foreign key (ID_articolo) references Articolo (ID_articolo)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Colore(
	cod_esadecimale VARCHAR(10) PRIMARY KEY,
    Nome VARCHAR(20) NOT NULL
); 

CREATE TABLE Tinta(
	cod_esadecimale VARCHAR(10),
    ID_articolo INT UNSIGNED,
    foreign key (cod_esadecimale) references Colore (cod_esadecimale)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (ID_articolo) references Articolo (ID_articolo)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
	PRIMARY KEY(ID_articolo, cod_esadecimale)
); 

CREATE TABLE Taglia(
	id_nome VARCHAR(5) PRIMARY KEY
); 

CREATE TABLE Size(
	id_nome VARCHAR(5),
    ID_articolo INT UNSIGNED,
    Quantita INT UNSIGNED default 0,
    foreign key (id_nome) references Taglia (id_nome)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (ID_articolo) references Articolo (ID_articolo)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(ID_articolo, id_nome)
); 

CREATE TABLE Account_User(
	Email VARCHAR(100) PRIMARY KEY,
    Password_User VARCHAR(50) NOT NULL,
    Admin_user BOOLEAN default 0 NOT NULL
);

CREATE TABLE Ordine(
	ID_ordine VARCHAR(10) PRIMARY KEY,
	data_acquisto DATE NOT NULL,
    pacchetto_regalo BOOLEAN DEFAULT false NOT NULL,
    data_spedizione DATE NOT NULL,
    Descrizione TEXT,
    Email VARCHAR(100),
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Cod_sconto(
	codice VARCHAR(10) PRIMARY KEY,
    data_scadenza DATE NOT NULL,
	sconto DOUBLE default 0 NOT NULL
);

CREATE TABLE Applicato(
	codice VARCHAR(10),
    ID_ordine VARCHAR(100),
    foreign key (codice) references Cod_sconto (codice)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (ID_ordine) references Ordine (ID_ordine)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY (codice)
);

CREATE TABLE Posseduto(
	codice VARCHAR(10),
    Email VARCHAR(100),
    foreign key (codice) references Cod_sconto (codice)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY (codice)
);

CREATE TABLE Composizione(
	ID_articolo INT UNSIGNED,
    ID_ordine VARCHAR(10),
    Quantita_articolo INT UNSIGNED DEFAULT 0,
	foreign key (ID_articolo) references Articolo (ID_articolo)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (ID_ordine) references Ordine (ID_ordine)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(ID_articolo, ID_ordine)
);

CREATE TABLE Dati_cliente(
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(20) NOT NULL,
    ddn DATE NOT NULL,
    Telefono VARCHAR(20) NOT NULL UNIQUE,
    Via VARCHAR(100) NOT NULL,
    N_civico VARCHAR(10) NOT NULL,
    CAP CHAR(10) NOT NULL,
    Email CHAR(100),
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);



