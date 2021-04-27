DROP database if exists toj_sitoweb;
CREATE DATABASE toj_sitoweb;
USE toj_sitoweb;

CREATE TABLE Articolo(
	ID_articolo INT UNSIGNED auto_increment PRIMARY KEY,
	Colore VARCHAR(15) NOT NULL,
    Prezzo DOUBLE(5,2) default 0.00,
    Quantita INT UNSIGNED default 0,
    Tipo VARCHAR(50) NOT NULL,
    Taglia VARCHAR(4) NOT NULL,
    Sesso CHAR(1) NOT NULL,
    Descrizione TEXT,
    sconto DOUBLE default 0
); 

CREATE TABLE Account_User(
	Email VARCHAR(100) PRIMARY KEY,
    Password_User VARCHAR(50) NOT NULL,
    Admin_user BOOLEAN default 0 NOT NULL
);

CREATE TABLE Ordine(
	ID_ordine VARCHAR(10) PRIMARY KEY,
	data_acquisto DATE NOT NULL,
    pacchetto_regalo BOOLEAN DEFAULT false,
    data_spedizione DATE NOT NULL,
    Descrizione TEXT,
    Email VARCHAR(100) NOT NULL,
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Cod_sconto(
	codice VARCHAR(10) PRIMARY KEY,
    data_scadenza DATE NOT NULL,
	sconto DOUBLE default 0 NOT NULL,
    ID_ordine VARCHAR(10) NOT NULL,
    foreign key (ID_ordine) references Ordine (ID_ordine)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Composizione(
	ID_articolo INT UNSIGNED auto_increment NOT NULL,
    ID_ordine VARCHAR(10) NOT NULL,
    Quantita_articolo INT UNSIGNED DEFAULT 0,
	foreign key (ID_articolo) references Articolo (ID_articolo)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    foreign key (ID_ordine) references Ordine (ID_ordine)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(ID_articolo, ID_ordine)
);

CREATE TABLE Carta_elettronica(
	Codice_carta VARCHAR(25) PRIMARY KEY,
    Descrizione VARCHAR(30) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Dati_cliente(
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(20) NOT NULL,
    ddn DATE NOT NULL,
    Telefono VARCHAR(20) NOT NULL UNIQUE,
    Via VARCHAR(100) NOT NULL,
    N_civico VARCHAR(10) NOT NULL,
    CAP CHAR(10) NOT NULL,
    Email CHAR(100) NOT NULL,
    foreign key (Email) references Account_User (Email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

