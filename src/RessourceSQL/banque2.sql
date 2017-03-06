-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 05 Mars 2017 à 22:40
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
  `nom` varchar(25) CHARACTER SET latin1 NOT NULL,
  `prenom` varchar(25) CHARACTER SET latin1 NOT NULL,
  `courriel` varchar(100) NOT NULL,
  `mdp` varchar(200) CHARACTER SET latin1 NOT NULL,
  `secureKey` int(11) NOT NULL,
  `prefixe` varchar(1) CHARACTER SET latin1 NOT NULL DEFAULT 'a',
  `recovery` int(5) DEFAULT NULL,
  PRIMARY KEY (`identifiant`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB AUTO_INCREMENT=1069 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `administrateurs`
--

INSERT INTO `administrateurs` (`identifiant`, `nom`, `prenom`, `courriel`, `mdp`, `secureKey`, `prefixe`, `recovery`) VALUES
(1004, 'Administrateur', 'Boss', 'administrateur@banque2.ca', 'NK3FRsvo56mdu+twYrYob7JQFfERCoiAjn+j5MhwN9rvgad9Z+bMupMwXuA5mGiZ', 1320, 'a', 53428),
(1064, 'Administrateur', 'Administrateur', 'administrateur@banque2.ca', 'ydGRrbQfVocNmt63PWSIjuHVB5/6LJI+xz3Sl6pSLo1AAhpTKjG2PcVgCHYcdKOg', 4111, 'a', 0),
(1065, 'Administrateur', 'Administrateur', 'administrateur@banque2.ca', 'zR0+QAvbDtD8hj6f5Es2Ul7Shi2ndEhDtoC+93n4z3cAxpjW3O1X2Dfq+BY+Wmkh', 3851, 'a', 0),
(1066, 'admin2', 'moi', 'administrateur@banque2.ca', '+Dwwcm4IV7TqtjtVIVR6DtfSdFvMdAK86QB6zxH4zuYG4rlCMkb87ZTihnm8+QhD', 3130, 'a', 0),
(1067, 'Administrateur', 'Administrateur', 'administrateur@banque2.ca', '9FVnSQjpHm2qSfAXW1xVoPqJk45PhS/ej/GG/YlpX6zZlnABxMdFbKnHBRDhV39C', 3693, 'a', 0),
(1068, 'Administrateur123', 'Administrateur123', 'admin@banque2.ca', 'Wbdk50Lk3xtAlTlDTNXSO5WFnCwWSzUx9C6MsqxKb4Y/Ix2YTgtdmURL+aSWfTak', 3518, 'a', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE IF NOT EXISTS `carte` (
  `numCarte` varchar(16) NOT NULL,
  `dateExp` varchar(7) NOT NULL,
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
('6666148320307027', '2018/02', 563, 'Dupont', 'Jean', 6009),
('666618426776487', '2021/04', 364, 'New', 'client', 6019),
('6666211490588577', '2021/04', 219, 'New', 'client', 6018),
('666624089592821', '2021/04', 824, 'bachou', 'lyessou', 6031),
('6666258093534433', '2021/04', 63, 'New', 'client', 6021),
('6666268490386617', '2018/02', 722, 'Dup', 'Jean', 6002),
('6666300212167981', '2021/04', 126, 'Dupont', 'Jean', 6022),
('6666334981645019', '2021/04', 484, 'bachou', 'lyessou', 6047),
('666648147628235', '2018/02', 35, 'Dupont', 'Jean', 6005),
('6666514988682962', '2021/04', 977, 'bachou', 'lyessou', 6048),
('6666619825512684', '2021/04', 491, 'Dupont123', 'Jean123', 6049),
('666672319559253', '2018/02', 845, 'Dupont', 'Jean', 6011),
('6666742621243658', '2021/04', 473, 'New', 'client', 6020),
('6666810239756822', '2021/04', 690, 'bachou', 'lyessou', 6030),
('6666836142097970', '2018/02', 84, 'Dupont', 'Jean', 6007),
('6666842778965275', '2021/04', 769, 'Dupont', 'Jean', 6023),
('6666918583353599', '2018/02', 781, 'Dup', 'Jean', 6003),
('6666934691551686', '2021/04', 191, 'Dupont123', 'Jean123', 6050),
('6666943657236893', '2018/02', 901, 'Dupont', 'Jean', 6008);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(25) CHARACTER SET latin1 NOT NULL,
  `prenom` varchar(25) CHARACTER SET latin1 NOT NULL,
  `courriel` varchar(100) CHARACTER SET latin1 NOT NULL,
  `dateNaissance` varchar(10) CHARACTER SET latin1 NOT NULL,
  `telephone` varchar(12) CHARACTER SET latin1 NOT NULL,
  `adresse` varchar(600) CHARACTER SET latin1 NOT NULL,
  `mdp` varchar(200) CHARACTER SET latin1 NOT NULL,
  `prefixe` varchar(1) CHARACTER SET latin1 NOT NULL DEFAULT 'c',
  `recovery` int(5) DEFAULT NULL,
  PRIMARY KEY (`identifiant`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB AUTO_INCREMENT=9035 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`identifiant`, `nom`, `prenom`, `courriel`, `dateNaissance`, `telephone`, `adresse`, `mdp`, `prefixe`, `recovery`) VALUES
(9024, 'Dupont', 'Jean', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', '5XcimcZ5SoTOYDmWM+4mh+KvtBV/srakS4q2zad1mQvWzn8TpFpNCZfBkV49KZej', 'c', 12345),
(9025, 'Dupont', 'Jean', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'ToXmtoFMPziuhBsDgLUrMQNeRPu8Z/VeQrn+GWQ6kRg7x6VX+1cTyGv0x2YBDH9v', 'c', 0),
(9029, 'New', 'client', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'l31++5m1s0d1kHxWsiyZWOsJfgRYlbI3/iAiwlhRwIqfotyglWq07ORhS5FLSp1S', 'c', 0),
(9030, 'New', 'client', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', '+2C0ugoyzsOKmTnQEDMiU3V2Pi5yFwnBIsB/J2tDEjajlJOQ9MjlrlppDYzjmk0+', 'c', 0),
(9031, 'Dupont', 'Jean', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'pV1DKzao+OKo8F2zbY9gGHJEUCryqcmjEWNu6mv1UR6LCz9ePn/7ZUMee2VkhDY9', 'c', 0),
(9034, 'Dupont123', 'Jean123', 'membre@banque2.ca', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'DZwOMZR6MHb3tu4BSbdbXap/k+a0pEbPy+gLKhYfOzOdyBVyWVP7Ofuv+4PkcfX1', 'c', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=6051 DEFAULT CHARSET=utf8;

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
(6023, '666-', 'DEBIT', 1500, 9031),
(6030, '666-', 'CREDIT', 99, 9033),
(6031, '666-', 'DEBIT', 250, 9033),
(6047, '666-', 'DEBIT', 123, 9033),
(6048, '666-', 'DEBIT', 23423, 9033),
(6049, '666-', 'CREDIT', 0, 9034),
(6050, '666-', 'DEBIT', 1500, 9034);

-- --------------------------------------------------------

--
-- Structure de la table `preautorisation`
--

DROP TABLE IF EXISTS `preautorisation`;
CREATE TABLE IF NOT EXISTS `preautorisation` (
  `preauth_id` int(11) NOT NULL AUTO_INCREMENT,
  `credit_id` varchar(16) NOT NULL,
  `credit_expiration` varchar(7) NOT NULL,
  `credit_nom` varchar(25) NOT NULL,
  `credit_prenom` varchar(25) NOT NULL,
  `credit_cvs` varchar(3) NOT NULL,
  `source_id` varchar(100) NOT NULL,
  `montant` double NOT NULL,
  `preauth_status` varchar(15) NOT NULL,
  PRIMARY KEY (`preauth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `preautorisation`
--

INSERT INTO `preautorisation` (`preauth_id`, `credit_id`, `credit_expiration`, `credit_nom`, `credit_prenom`, `credit_cvs`, `source_id`, `montant`, `preauth_status`) VALUES
(1, '6666148320307027', '2018/02', 'Dupe', 'Jean', '123', 'TEST', 20, 'CANCELED'),
(3, '6666148320307027', '2018/02', 'Dupe', 'Jean', '123', 'TEST', 345, 'CREATED'),
(5, '6666148320307027', '2018/02', 'Dupe', 'Jean', '123', 'TEST', 24.45, 'CANCELED'),
(6, '6666148320307027', '2018/02', 'Dupe', 'Jean', '123', 'TEST', 24.80, 'EXECUTED');

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
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`idTransaction`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`idTransaction`, `idCompteClient`, `idCompteDestinataire`, `montant`, `date`, `description`) VALUES
(1, 6003, 6002, 50, '2017-03-01 00:00:00', 'Ceci est mon test de transaction'),
(2, 6003, 6002, 50, '2017-03-01 00:00:00', 'Ceci est mon test de transaction'),
(3, 6003, 6002, 50, '2017-03-01 13:04:28', 'Ceci est mon test de transaction'),
(4, 6005, 6009, -100, '2017-03-01 14:16:03', 'Virement vers compte 6009'),
(5, 6009, 6005, 100, '2017-03-01 14:16:03', 'Virement en provenace du compte 6005'),
(6, 6005, 6009, -100, '2017-03-01 15:21:05', 'Virement vers compte 6009'),
(7, 6009, 6005, 100, '2017-03-01 15:21:05', 'Virement en provenace du compte 6005'),
(8, 6005, 6009, -100, '2017-03-01 15:22:53', 'Virement vers compte 6009'),
(9, 6009, 6005, 100, '2017-03-01 15:22:54', 'Virement en provenace du compte 6005'),
(10, 6005, 6005, -100, '2017-03-01 16:41:51', 'Virement vers compte 6005'),
(11, 6005, 6005, 100, '2017-03-01 16:41:51', 'Virement en provenace du compte 6005'),
(12, 6005, 6009, -200, '2017-03-01 16:42:29', 'Virement vers compte 6009'),
(13, 6009, 6005, 200, '2017-03-01 16:42:29', 'Virement en provenace du compte 6005'),
(14, 6005, 6011, -100, '2017-03-01 17:43:43', 'Virement vers compte 6011'),
(15, 6011, 6005, 100, '2017-03-01 17:43:43', 'Virement en provenace du compte 6005'),
(16, 6005, 6011, -100, '2017-03-01 17:49:18', 'Virement vers compte 6011'),
(17, 6005, 6011, -100, '2017-03-01 17:49:18', 'Virement vers compte 6011'),
(18, 6011, 6005, 100, '2017-03-01 17:49:18', 'Virement en provenace du compte 6005'),
(19, 6011, 6005, 100, '2017-03-01 17:49:18', 'Virement en provenace du compte 6005'),
(20, 6005, 6011, -100, '2017-03-01 17:51:01', 'Virement vers compte 6011'),
(21, 6011, 6005, 100, '2017-03-01 17:51:01', 'Virement en provenace du compte 6005'),
(22, 6005, 6011, -100, '2017-03-01 17:52:23', 'Virement vers compte 6011'),
(23, 6011, 6005, 100, '2017-03-01 17:52:23', 'Virement en provenace du compte 6005'),
(24, 6005, 6011, -450, '2017-03-01 17:54:30', 'Virement vers compte 6011'),
(25, 6011, 6005, 450, '2017-03-01 17:54:30', 'Virement en provenace du compte 6005'),
(26, 6009, 6005, -12, '2017-03-01 22:15:26', 'Virement vers compte 6005'),
(27, 6005, 6009, 12, '2017-03-01 22:15:27', 'Virement en provenace du compte 6009'),
(28, 6009, 6005, -100, '2017-03-01 22:15:46', 'Virement vers compte 6005'),
(29, 6005, 6009, 100, '2017-03-01 22:15:46', 'Virement en provenace du compte 6009'),
(30, 6005, 6011, -100.2, '2017-03-01 23:05:06', 'Rembourser du compte credit : 6011'),
(31, 6011, 6005, -100.2, '2017-03-01 23:05:06', 'Remboursement du compte credit effectué par le compte 6005'),
(32, 6009, 6011, -100.2, '2017-03-01 23:05:26', 'Rembourser du compte credit : 6011'),
(33, 6011, 6009, -100.2, '2017-03-01 23:05:26', 'Remboursement du compte credit effectué par le compte 6009'),
(34, 6009, 6011, -100.2, '2017-03-01 23:05:50', 'Rembourser du compte credit : 6011'),
(35, 6011, 6009, -100.2, '2017-03-01 23:05:51', 'Remboursement du compte credit effectué par le compte 6009'),
(36, 6009, 6011, -1234, '2017-03-02 12:35:59', 'Rembourser du compte credit : 6011'),
(37, 6011, 6009, -1234, '2017-03-02 12:35:59', 'Remboursement du compte credit effectué par le compte 6009'),
(38, 6009, 6011, -150, '2017-03-02 17:24:35', 'Rembourser du compte credit : 6011'),
(39, 6011, 6009, -150, '2017-03-02 17:24:35', 'Remboursement du compte credit effectué par le compte 6009'),
(40, 6005, 6011, -12, '2017-03-02 17:34:08', 'Rembourser du compte credit : 6011'),
(41, 6011, 6005, -12, '2017-03-02 17:34:09', 'Remboursement du compte credit effectué par le compte 6005'),
(42, 6008, 6007, -400, '2017-03-02 17:56:03', 'Rembourser du compte credit : 6007'),
(43, 6007, 6008, -400, '2017-03-02 17:56:03', 'Remboursement du compte credit effectué par le compte 6008'),
(45, 6030, 6027, -3, '2017-03-04 23:16:45', 'Remboursement du compte credit effectué par le compte 6027'),
(46, 6031, 6032, -50, '2017-03-04 23:20:49', 'Virement vers compte 6032'),
(48, 6031, 6030, -100, '2017-03-04 23:22:10', 'Virement vers compte 6030'),
(49, 6030, 6031, 100, '2017-03-04 23:22:10', 'Virement en provenace du compte 6031');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
