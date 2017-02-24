-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 24 Février 2017 à 02:43
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
(1000, 'nom', 'prenom', 'sadas', 1234, 'a'),
(1001, 'Dup', 'Jean', 'j55vNuXGsZzlzjBYFy1k61me6FIyaTfgOy9tXK7U62bK68jonXH4OFATukKUHvtl', 1944, 'a'),
(1002, 'Dup', 'Jean', 'BM69SPEjvnWcArAQpB6H2DCKDv+fWKmYAYICFNYnsfDrDHzW0UJ/9ZtKgfGYQvNG', 5512, 'a'),
(1003, 'Dup', 'Jean', 'eJDoQD2gqaaWUG3h2afbcGTqIHg7e2UjKdKd7jdOJf//N0baqO6Wh3ToSnB3ObAm', 2740, 'a'),
(1004, 'Idriss', 'Jean', 'YMZ0mDjJNrHSe5Jay+xnlsFByAw2wxZTlLwHesy7H5kxIKdJZzY45h9vogJ4aryO', 1320, 'a');

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE `carte` (
  `numCarte` varchar(16) NOT NULL,
  `dateExp` date NOT NULL,
  `crypto` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `idCompte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `carte`
--

INSERT INTO `carte` (`numCarte`, `dateExp`, `crypto`, `nom`, `prenom`, `idCompte`) VALUES
('66668659753319', '2018-02-01', 390, 'test', 'test', 10000);

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
(9000, 'wqe', 'qwe', 'qwe', 'qwe', 'qweqw', 'qweqw', 'qweqwe', 'c'),
(9001, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', '5fxZUjxz/wFP6WtdSy+QBlOq1jIhnIdVd2I38FrJr527sWd+ZXDbkRM+ugmDrD2q', 'c'),
(9002, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'cSLF1OmnHrMe+DgPmBipHPeSdg/zeuTtpnlUp9FKuJr3jmTSSFBwEZRaJ+b1PBF5', 'c'),
(9003, 'test', 'eeeee', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'xkbbQHRuPXwKaWKp2oFhSlzfaPcVhvQcWNxraeKXJusghhxc87x6MO+pXnosUvNT', 'c'),
(9004, 'test', 'eeeee', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', '4JKtZtC+qEidg6qOq1zoSJKVb2EnDX2bKmwhZHAG61t/oRzXdyh8+ZDvdavOSgzC', 'c'),
(9005, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'FDIF0r7MxunUyLXDMZoswPiQGjU5aLIjcX6LD43HKqzOCzuLxzQ7ZFQDVumf0UrF', 'c'),
(9006, 'Idriss', 'Aissou', 'jdup@j.com', '2017-02-20', '512-234-4567', '12 rue v Montreal', 'QSbq4itjB67ZEt5g2O7fm9PH5v1GlxjRQNdtV/6sWBWHnnwv+dX69yWDlJ1/sRlJ', 'c'),
(9007, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'z3QD/lfFz6o/ilRb2Kx8iatJCBW7M3337MXGI8BHqkVyQxUyTUXLSoHGPmCUrz05', 'c'),
(9008, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', '+Qk6Ob8kVXvkKVzrZvAmaS4cbLV6uiRGbKE+yZ52uFbjuCoBg6UxgFfIg8Dwo3cH', 'c'),
(9009, 'Dup', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'XQhJAyB1J7BmWyPJC2vofuHgQ7j2KengLKyU++2iXILNURyAymjw19DsWyLb7Zi+', 'c'),
(9010, 'utilisateur', 'Jean', 'jdup@j.com', '01-08-89', '512-234-4567', '12 rue v Montreal', 'gUx+NI6EjvyEXqT4v/VqKHXi7BdpS9IGeH/kNR9/x5pUrhmv8Rx/YFvTKzywE2jg', 'c'),
(9011, 'test', 'test', 'jdup@j.com', '2017-01-02', '512-234-4567', '12 rue v Montreal', 'sHVyMOHbV0/TcfkIfxP09xr/RwMVcjXODvyyugg3/KclR4eRigCk5flxbh7PI8jj', 'c');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE `compte` (
  `idCompte` int(5) NOT NULL,
  `idBanque` varchar(4) NOT NULL DEFAULT '666-',
  `type` varchar(6) NOT NULL,
  `Solde` float NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `idBanque`, `type`, `Solde`, `idClient`) VALUES
(10000, '666-', 'Debit', 1234, 9011);

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
  `date` date NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`idTransaction`, `idCompteClient`, `idCompteDestinataire`, `montant`, `date`, `description`) VALUES
(1, 1, 9001, 250, '2017-02-18', 'Ceci est mon test');

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
  MODIFY `identifiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;
--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `identifiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9012;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10001;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idTransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
