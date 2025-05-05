CREATE DATABASE livres
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;
USE livre;
CREATE TABLE livre (
    id INT NOT NULL AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    isbn DATE NOT NULL,
    disponible VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE utilisateur (
    id INT NOT NULL AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
   
    PRIMARY KEY (id)
);
CREATE TABLE reservation (
    id INT NOT NULL AUTO_INCREMENT,
    id_livre INT NOT NULL,
    id_utilisateur INT NOT NULL,
    date_reservation DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_livre) REFERENCES livre(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
CREATE TABLE pret (
    id INT NOT NULL AUTO_INCREMENT,
    id_livre INT NOT NULL,
    id_utilisateur INT NOT NULL,
    note INT NOT NULL CHECK (note >= 1 AND note <= 5),
    commentaire TEXT,
    date_avis DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_livre) REFERENCES livre(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);