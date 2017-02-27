-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 26 Février 2017 à 16:40
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `banque2`
--
CREATE DATABASE IF NOT EXISTS `banque2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `banque2`;

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

DROP TABLE IF EXISTS `administrateurs`;
CREATE TABLE IF NOT EXISTS `administrateurs` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `secureKey` int(11) NOT NULL,
  `prefixe` varchar(1) NOT NULL DEFAULT 'a',
  PRIMARY KEY (`identifiant`),
  KEY `identifiant` (`identifiant`)
) ENGINE=MyISAM AUTO_INCREMENT=1009 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateurs`
--

INSERT INTO `administrateurs` (`identifiant`, `nom`, `prenom`, `mdp`, `secureKey`, `prefixe`) VALUES
(1000, 'nom', 'prenom', 'sadas', 1234, 'a'),
(1001, 'Dup', 'Jean', 'j55vNuXGsZzlzjBYFy1k61me6FIyaTfgOy9tXK7U62bK68jonXH4OFATukKUHvtl', 1944, 'a'),
(1002, 'Dup', 'Jean', 'BM69SPEjvnWcArAQpB6H2DCKDv+fWKmYAYICFNYnsfDrDHzW0UJ/9ZtKgfGYQvNG', 5512, 'a'),
(1003, 'Dup', 'Jean', 'eJDoQD2gqaaWUG3h2afbcGTqIHg7e2UjKdKd7jdOJf//N0baqO6Wh3ToSnB3ObAm', 2740, 'a'),
(1004, 'Idriss', 'Jean', 'YMZ0mDjJNrHSe5Jay+xnlsFByAw2wxZTlLwHesy7H5kxIKdJZzY45h9vogJ4aryO', 1320, 'a'),
(1005, 'Dup', 'Jean', 'w6VxIbih4bqNh6+jbPVQ82kaHDLeRL3fAvEhlYoLajalKDWjBwb5e0qNss4MpBo8', 1087, 'a'),
(1006, 'Dup', 'Jean', 'HnlXpxTxqeOkf0byMs81YNPyD4P/d/L1fQ6TCrNs0/wFggtz/li8sdGJuQ/rUVBy', 4407, 'a'),
(1007, 'Dup', 'Jean', 'KvZMHNxknhrUTDCiybFEcgDfIyteTaWpKW6GJsl9V/5KfZR/V0OK4emPCMkNEXeQ', 7974, 'a'),
(1008, 'Dup', 'Jean', 'dRcBHW05j1mUoCgSmVkkfTuGCHOAaHLBG1a4ptYw1l1yG6RDya0vJZHkkRx3VfWT', 2618, 'a');

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE IF NOT EXISTS `carte` (
  `numCarte` varchar(16) NOT NULL,
  `dateExp` date NOT NULL,
  `crypto` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `idCompte` int(11) NOT NULL,
  PRIMARY KEY (`numCarte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `carte`
--

INSERT INTO `carte` (`numCarte`, `dateExp`, `crypto`, `nom`, `prenom`, `idCompte`) VALUES
('6666121852784807', '2018-02-01', 54, 'Dup', 'Jean', 4),
('666637979544334', '2018-02-01', 67, 'Dup', 'Jean', 1),
('666654823457204', '2018-02-01', 463, 'Dup', 'Jean', 3),
('6666719829994067', '2018-02-01', 361, 'Dup', 'Jean', 2);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `courriel` varchar(100) NOT NULL,
  `dateNaissance` varchar(10) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `adresse` varchar(600) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `prefixe` varchar(1) NOT NULL DEFAULT 'c',
  PRIMARY KEY (`identifiant`),
  KEY `identifiant` (`identifiant`)
) ENGINE=MyISAM AUTO_INCREMENT=9020 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`identifiant`, `nom`, `prenom`, `courriel`, `dateNaissance`, `telephone`, `adresse`, `mdp`, `prefixe`) VALUES
(9008, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', '+Qk6Ob8kVXvkKVzrZvAmaS4cbLV6uiRGbKE+yZ52uFbjuCoBg6UxgFfIg8Dwo3cH', 'c'),
(9009, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'XQhJAyB1J7BmWyPJC2vofuHgQ7j2KengLKyU++2iXILNURyAymjw19DsWyLb7Zi+', 'c'),
(9010, 'utilisateur', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'gUx+NI6EjvyEXqT4v/VqKHXi7BdpS9IGeH/kNR9/x5pUrhmv8Rx/YFvTKzywE2jg', 'c'),
(9019, 'Dup', 'Jean', 'jdup@j.com', '2017-02-26', '512-234-4567', '12 rue v Montreal', 'TwtlYiBcA7Qws0+5YjJMhr5dbyIJMT44n0KsXHJS8FeoXcSz1bm3hJhOo5/qhii3', 'c'),
(9013, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'OX1mGV89Y/D84vv22l/VPhsiRaoxsK1Bs0rnp6xbK0dvRwkgODRreuoUHU7w8Ig2', 'c'),
(9014, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'knsMxnPtGnaBmBH5uc8xrdG8exXSWqOQ38+ZEXe02lVEkFs0ektKsxGDFLqOOCi+', 'c'),
(9015, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'rdnIKMQ8jydxhQXsHjmQqwVQ7IpoGSxnHbtu8tjyVa371KL+UwjzGYK6wNjHAs8F', 'c'),
(9016, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', '/BrXfEuTrSZyJpgHX4M4tbvff2Kao90ad+iTlvF18orSfuwUSU7gmmSdE3APi0ee', 'c'),
(9017, 'Dup', 'Jean', 'jdup@j.com', '2017-01-08', '512-234-4567', '12 rue v Montreal', 'Arx8sZDfuZIGS2ppcst7eJoUTRfe2RaYvEGz+jB5yqLDePto8hlrgf73MXuKzDSB', 'c');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `idCompte` int(5) NOT NULL AUTO_INCREMENT,
  `idBanque` varchar(4) NOT NULL DEFAULT '666-',
  `type` varchar(6) NOT NULL,
  `Solde` float NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `idBanque`, `type`, `Solde`, `idClient`) VALUES
(1, '666-', 'Debit', 1234, 9008),
(2, '666-', 'Credit', 1234, 9008),
(3, '666-', 'Debit', 1234, 9017),
(4, '666-', 'Credit', 1234, 9017);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE IF NOT EXISTS `transaction` (
  `idTransaction` int(11) NOT NULL AUTO_INCREMENT,
  `idCompteClient` int(11) NOT NULL,
  `idCompteDestinataire` int(11) NOT NULL,
  `montant` float NOT NULL,
  `date` date NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`idTransaction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


-- --------------------------------------------------------

--
-- Structure de la table `preautorisation`
--

DROP TABLE IF EXISTS `preautorisation`;
CREATE TABLE IF NOT EXISTS `preautorisation` (
  `preauth_id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_id` VARCHAR (16) NOT NULL ,
  `credit_expiration` date NOT NULL,
  `credit_nom` varchar(25) NOT NULL,
  `credit_prenom` varchar(25) NOT NULL,
  `credit_cvs` varchar(3) NOT NULL,
  `source_id` varchar(11) ,
  `montant` DOUBLE NOT NULL,
  `preauth_status` varchar(25) NOT NULL,
  PRIMARY KEY (`preauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
