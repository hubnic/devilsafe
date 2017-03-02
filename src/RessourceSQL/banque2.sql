-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 02 Mars 2017 à 03:14
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
-- Structure de la table `administrateurs`
--

DROP TABLE IF EXISTS `administrateurs`;
CREATE TABLE `administrateurs` (
  `identifiant` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `secureKey` int(11) NOT NULL,
  `prefixe` varchar(1) NOT NULL DEFAULT 'a'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateurs`
--

INSERT INTO `administrateurs` (`identifiant`, `nom`, `prenom`, `mdp`, `secureKey`, `prefixe`) VALUES
(1004, 'Administrateur', 'Boss', 'NK3FRsvo56mdu+twYrYob7JQFfERCoiAjn+j5MhwN9rvgad9Z+bMupMwXuA5mGiZ', 1320, 'a'),
(1064, 'Administrateur', 'Administrateur', 'ydGRrbQfVocNmt63PWSIjuHVB5/6LJI+xz3Sl6pSLo1AAhpTKjG2PcVgCHYcdKOg', 4111, 'a');

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE `carte` (
  `numCarte` varchar(16) NOT NULL,
  `dateExp` varchar(7) NOT NULL,
  `crypto` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `idCompte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `carte`
--

INSERT INTO `carte` (`numCarte`, `dateExp`, `crypto`, `nom`, `prenom`, `idCompte`) VALUES
('6666148320307027', '2018/02', 563, 'Dupont', 'Jean', 6009),
('666618426776487', '2021/04', 364, 'New', 'client', 6019),
('6666211490588577', '2021/04', 219, 'New', 'client', 6018),
('6666258093534433', '2021/04', 63, 'New', 'client', 6021),
('6666268490386617', '2018/02', 722, 'Dup', 'Jean', 6002),
('666648147628235', '2018/02', 35, 'Dupont', 'Jean', 6005),
('666672319559253', '2018/02', 845, 'Dupont', 'Jean', 6011),
('6666742621243658', '2021/04', 473, 'New', 'client', 6020),
('6666836142097970', '2018/02', 84, 'Dupont', 'Jean', 6007),
('6666918583353599', '2018/02', 781, 'Dup', 'Jean', 6003),
('6666943657236893', '2018/02', 901, 'Dupont', 'Jean', 6008);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
  `identifiant` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `courriel` varchar(100) NOT NULL,
  `dateNaissance` varchar(10) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `adresse` varchar(600) NOT NULL,
  `mdp` varchar(200) NOT NULL,
  `prefixe` varchar(1) NOT NULL DEFAULT 'c'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`identifiant`, `nom`, `prenom`, `courriel`, `dateNaissance`, `telephone`, `adresse`, `mdp`, `prefixe`) VALUES
(9030, 'New', 'client', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', '+2C0ugoyzsOKmTnQEDMiU3V2Pi5yFwnBIsB/J2tDEjajlJOQ9MjlrlppDYzjmk0+', 'c'),
(9025, 'Dupont', 'Jean', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'ToXmtoFMPziuhBsDgLUrMQNeRPu8Z/VeQrn+GWQ6kRg7x6VX+1cTyGv0x2YBDH9v', 'c'),
(9024, 'Dupont', 'Jean', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', '5XcimcZ5SoTOYDmWM+4mh+KvtBV/srakS4q2zad1mQvWzn8TpFpNCZfBkV49KZej', 'c'),
(9029, 'New', 'client', 'membre@banque2.com', '2017-03-01', '512-234-4567', '12 rue v Montreal', 'l31++5m1s0d1kHxWsiyZWOsJfgRYlbI3/iAiwlhRwIqfotyglWq07ORhS5FLSp1S', 'c');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE `compte` (
  `idCompte` int(5) NOT NULL,
  `idBanque` varchar(4) NOT NULL DEFAULT '666-',
  `type` varchar(6) NOT NULL,
  `Solde` decimal(10,0) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `idBanque`, `type`, `Solde`, `idClient`) VALUES
(6002, '666-', 'CREDIT', '0', 9023),
(6003, '666-', 'DEBIT', '400', 9023),
(6005, '666-', 'DEBIT', '50', 9024),
(6007, '666-', 'CREDIT', '0', 9025),
(6008, '666-', 'DEBIT', '1500', 9025),
(6009, '666-', 'DEBIT', '512', 9024),
(6011, '666-', 'CREDIT', '950', 9024),
(6018, '666-', 'CREDIT', '0', 9029),
(6019, '666-', 'CREDIT', '0', 9030),
(6020, '666-', 'DEBIT', '1500', 9030),
(6021, '666-', 'DEBIT', '1500', 9030);

-- --------------------------------------------------------

--
-- Structure de la table `preautorisation`
--

DROP TABLE IF EXISTS `preautorisation`;
CREATE TABLE `preautorisation` (
  `preauth_id` int(11) NOT NULL,
  `credit_id` varchar(16) NOT NULL,
  `credit_expiration` varchar(7) NOT NULL,
  `credit_nom` varchar(25) NOT NULL,
  `credit_prenom` varchar(25) NOT NULL,
  `credit_cvs` varchar(3) NOT NULL,
  `source_id` varchar(100) NOT NULL,
  `montant` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `preautorisation`
--

INSERT INTO `preautorisation` (`preauth_id`, `credit_id`, `credit_expiration`, `credit_nom`, `credit_prenom`, `credit_cvs`, `source_id`, `montant`) VALUES
(1, '6666148320307027', '2018/02', 'Dupe', 'Jean', '123', 'TEST', 20);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `idTransaction` int(11) NOT NULL,
  `idCompteClient` int(11) NOT NULL,
  `idCompteDestinataire` int(11) NOT NULL,
  `montant` float NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(25, 6011, 6005, 450, '2017-03-01 17:54:30', 'Virement en provenace du compte 6005');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  ADD PRIMARY KEY (`identifiant`),
  ADD KEY `identifiant` (`identifiant`);

--
-- Index pour la table `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`numCarte`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`identifiant`),
  ADD KEY `identifiant` (`identifiant`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`idCompte`);

--
-- Index pour la table `preautorisation`
--
ALTER TABLE `preautorisation`
  ADD PRIMARY KEY (`preauth_id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idTransaction`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `administrateurs`
--
ALTER TABLE `administrateurs`
  MODIFY `identifiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1065;
--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `identifiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9031;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6022;
--
-- AUTO_INCREMENT pour la table `preautorisation`
--
ALTER TABLE `preautorisation`
  MODIFY `preauth_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idTransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
