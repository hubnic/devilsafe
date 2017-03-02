-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 02 Mars 2017 à 23:15
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

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `idCompte` int(5) NOT NULL AUTO_INCREMENT,
  `idBanque` varchar(4) NOT NULL DEFAULT '666-',
  `type` varchar(6) NOT NULL,
  `Solde` double NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=6024 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `idBanque`, `type`, `Solde`, `idClient`) VALUES
(6002, '666-', 'CREDIT', 0, 9023),
(6003, '666-', 'DEBIT', 400, 9023),
(6005, '666-', 'DEBIT', 50, 9024),
(6007, '666-', 'CREDIT', 800, 9025),
(6008, '666-', 'DEBIT', 1100, 9025),
(6009, '666-', 'DEBIT', 50, 9024),
(6011, '666-', 'CREDIT', 38, 9024),
(6018, '666-', 'CREDIT', 0, 9029),
(6019, '666-', 'CREDIT', 0, 9030),
(6020, '666-', 'DEBIT', 1500, 9030),
(6021, '666-', 'DEBIT', 1500, 9030),
(6022, '666-', 'CREDIT', 0, 9031),
(6023, '666-', 'DEBIT', 1500, 9031);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
