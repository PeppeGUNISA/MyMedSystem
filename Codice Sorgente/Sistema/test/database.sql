-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.5.8-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database mymedsystemdb
DROP DATABASE IF EXISTS `mymedsystemdb`;
CREATE DATABASE IF NOT EXISTS `mymedsystemdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mymedsystemdb`;

-- Dump della struttura di tabella mymedsystemdb.associazione
DROP TABLE IF EXISTS `associazione`;
CREATE TABLE IF NOT EXISTS `associazione` (
  `usernamepaziente` varchar(24) NOT NULL,
  `usernamemedico` varchar(45) NOT NULL,
  PRIMARY KEY (`usernamepaziente`),
  UNIQUE KEY `usernamepaziente_UNIQUE` (`usernamepaziente`),
  KEY `medico_idx` (`usernamemedico`),
  CONSTRAINT `medico` FOREIGN KEY (`usernamemedico`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `paziente` FOREIGN KEY (`usernamepaziente`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.associazione: ~0 rows (circa)
DELETE FROM `associazione`;
/*!40000 ALTER TABLE `associazione` DISABLE KEYS */;
INSERT INTO `associazione` (`usernamepaziente`, `usernamemedico`) VALUES
	('Muchad90', 'medOreste');
/*!40000 ALTER TABLE `associazione` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.offerta
DROP TABLE IF EXISTS `offerta`;
CREATE TABLE IF NOT EXISTS `offerta` (
  `IDprestazione` varchar(10) NOT NULL,
  `usernamelaboratorio` varchar(24) NOT NULL,
  PRIMARY KEY (`IDprestazione`,`usernamelaboratorio`),
  KEY `usernamelaboratorioofferta_idx` (`usernamelaboratorio`),
  CONSTRAINT `IDprestazioneofferta` FOREIGN KEY (`IDprestazione`) REFERENCES `prestazione` (`IDprestazione`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usernamelaboratorioofferta` FOREIGN KEY (`usernamelaboratorio`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.offerta: ~4 rows (circa)
DELETE FROM `offerta`;
/*!40000 ALTER TABLE `offerta` DISABLE KEYS */;
INSERT INTO `offerta` (`IDprestazione`, `usernamelaboratorio`) VALUES
	('90.62.2', 'LabPotente'),
	('90.63.4', 'LabPotente'),
	('90.90.2', 'LabPotente'),
	('90.91.5', 'LabPotente');
/*!40000 ALTER TABLE `offerta` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.orario
DROP TABLE IF EXISTS `orario`;
CREATE TABLE IF NOT EXISTS `orario` (
  `username` varchar(24) NOT NULL,
  `orarioapertura` time NOT NULL DEFAULT '00:00:00',
  `orariochiusura` time NOT NULL DEFAULT '00:00:00',
  `giornoapertura` char(7) NOT NULL DEFAULT '0000000',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  CONSTRAINT `orario` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.orario: ~2 rows (circa)
DELETE FROM `orario`;
/*!40000 ALTER TABLE `orario` DISABLE KEYS */;
INSERT INTO `orario` (`username`, `orarioapertura`, `orariochiusura`, `giornoapertura`) VALUES
	('LabPotente', '08:00:00', '18:00:00', '1111100'),
	('medOreste', '08:00:00', '12:00:00', '1010100');
/*!40000 ALTER TABLE `orario` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.prenotazione
DROP TABLE IF EXISTS `prenotazione`;
CREATE TABLE IF NOT EXISTS `prenotazione` (
  `IDprenotazione` int(11) NOT NULL AUTO_INCREMENT,
  `luogo` varchar(50) NOT NULL,
  `dataorario` datetime NOT NULL,
  `IDprestazione` varchar(10) NOT NULL,
  `usernamepaziente` varchar(24) NOT NULL,
  PRIMARY KEY (`IDprenotazione`),
  UNIQUE KEY `IDprenotazione_UNIQUE` (`IDprenotazione`),
  KEY `IDprestazioneprenotazione_idx` (`IDprestazione`),
  KEY `pazienteprenotazione_idx` (`usernamepaziente`),
  CONSTRAINT `pazienteprenotazione` FOREIGN KEY (`usernamepaziente`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prestazioneprenotazione` FOREIGN KEY (`IDprestazione`) REFERENCES `prestazione` (`IDprestazione`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.prenotazione: ~0 rows (circa)
DELETE FROM `prenotazione`;
/*!40000 ALTER TABLE `prenotazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `prenotazione` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.prestazione
DROP TABLE IF EXISTS `prestazione`;
CREATE TABLE IF NOT EXISTS `prestazione` (
  `IDprestazione` varchar(10) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `prezzo` decimal(8,2) NOT NULL,
  PRIMARY KEY (`IDprestazione`),
  UNIQUE KEY `IDprestazione_UNIQUE` (`IDprestazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.prestazione: ~5 rows (circa)
DELETE FROM `prestazione`;
/*!40000 ALTER TABLE `prestazione` DISABLE KEYS */;
INSERT INTO `prestazione` (`IDprestazione`, `descrizione`, `prezzo`) VALUES
	('90.62.2', 'EMOCROMO:  HB, GR, GB, HCT, PLT, IND. DERIV., F. L.', 4.98),
	('90.63.4', 'ESAME MICROSCOPICO DEL SANGUE PERIFERICO', 3.72),
	('90.90', 'TEST PRESTAZIONE', 8.00),
	('90.90.2', 'TEST AGGIUNTA PRESTAZIONE', 20.00),
	('90.91.5', 'E. COLI PATOGENI DA COLTURA IDENTIFICAZIONE BIOCHIMICA', 8.16);
/*!40000 ALTER TABLE `prestazione` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.recapito
DROP TABLE IF EXISTS `recapito`;
CREATE TABLE IF NOT EXISTS `recapito` (
  `username` varchar(24) NOT NULL,
  `stato` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `citta` varchar(50) NOT NULL,
  `CAP` varchar(5) NOT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) NOT NULL,
  `cellulare` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  CONSTRAINT `recapito` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.recapito: ~5 rows (circa)
DELETE FROM `recapito`;
/*!40000 ALTER TABLE `recapito` DISABLE KEYS */;
INSERT INTO `recapito` (`username`, `stato`, `provincia`, `citta`, `CAP`, `indirizzo`, `telefono`, `cellulare`) VALUES
	('blabla20', 'Italia', 'Lecce', 'Lecce', '02020', 'Via Piazza qualcosa, 92', '020015465', NULL),
	('LabPotente', 'Italia', 'Caserta', 'Caserta', '81100', 'Via Galileo Galilei, 20', '08235471111', NULL),
	('medOreste', 'Italia', 'Napoli', 'Pompei', '80045', 'Via Aldo Moro, 1', '0815332111', NULL),
	('Muchad90', 'Italia', 'Lecce', 'Muro Leccese', '73036', 'Via Antonio Beccadelli, 138', '03521207018', NULL),
	('Proom1958', 'Italia', 'Pavia', 'Badia Pavese', '27010', 'Piazzetta Concordia, 17', '03814133617', NULL);
/*!40000 ALTER TABLE `recapito` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.referto
DROP TABLE IF EXISTS `referto`;
CREATE TABLE IF NOT EXISTS `referto` (
  `IDreferto` varchar(30) NOT NULL,
  `note` varchar(500) DEFAULT NULL,
  `file` varchar(500) NOT NULL COMMENT 'path del referto',
  `usernamepaziente` varchar(24) DEFAULT NULL,
  `usernamelaboratorio` varchar(24) NOT NULL,
  `IDprestazione` varchar(10) DEFAULT NULL,
  `datainserimento` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`IDreferto`,`usernamelaboratorio`),
  UNIQUE KEY `IDreferto_UNIQUE` (`IDreferto`),
  UNIQUE KEY `file_UNIQUE` (`file`),
  KEY `paziente_idx` (`usernamepaziente`),
  KEY `laboratorio_idx` (`usernamelaboratorio`),
  KEY `prestazionereferto_idx` (`IDprestazione`),
  CONSTRAINT `laboratorioreferto` FOREIGN KEY (`usernamelaboratorio`) REFERENCES `utente` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pazientereferto` FOREIGN KEY (`usernamepaziente`) REFERENCES `utente` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `prestazionereferto` FOREIGN KEY (`IDprestazione`) REFERENCES `prestazione` (`IDprestazione`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.referto: ~3 rows (circa)
DELETE FROM `referto`;
/*!40000 ALTER TABLE `referto` DISABLE KEYS */;
INSERT INTO `referto` (`IDreferto`, `note`, `file`, `usernamepaziente`, `usernamelaboratorio`, `IDprestazione`, `datainserimento`) VALUES
	('12', '', 'C:\\referti\\LabPotente\\12.pdf', 'Muchad90', 'LabPotente', '90.63.4', '2021-01-05'),
	('16', NULL, 'referti\\LabPotente\\16.pdf', 'Muchad90', 'LabPotente', '90.91.5', '2021-01-19'),
	('17', 'Il paziente purtroppo ha l\'elicobatterio RIP', 'referti\\LabPotente\\17.pdf', 'Muchad90', 'LabPotente', '90.91.5', '2021-01-19');
/*!40000 ALTER TABLE `referto` ENABLE KEYS */;

-- Dump della struttura di tabella mymedsystemdb.utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE IF NOT EXISTS `utente` (
  `username` varchar(24) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(100) NOT NULL,
  `CFPIVA` varchar(16) NOT NULL,
  `ruolo` enum('paziente','medico','laboratorio','operatoreAsl') NOT NULL DEFAULT 'paziente',
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `luogonascita` varchar(100) DEFAULT NULL,
  `datanascita` date DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `CF-PIVA_UNIQUE` (`CFPIVA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella mymedsystemdb.utente: ~6 rows (circa)
DELETE FROM `utente`;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` (`username`, `password`, `email`, `CFPIVA`, `ruolo`, `nome`, `cognome`, `luogonascita`, `datanascita`) VALUES
	('blabla20', 'imBlue280', 'g.ali20@dominio.it', 'LGHGPP51B01E506T', 'paziente', 'Giuseppe', 'Alighieri', 'Lecce', '1990-06-05'),
	('Giuseppone', 'Montesantone1', 'GiuseppeMontesanto@boh.it', 'MNTGPP60E03F839S', 'operatoreAsl', 'Giuseppe', 'Montesanto', 'Napoli', '1960-05-03'),
	('LabPotente', 'panskjwk2S', 'labpowa@outlook.it', '10002938453', 'laboratorio', 'Laboratorio di Ferdinando Vitale', NULL, NULL, NULL),
	('medOreste', 'aonskf2jIpo', 'OresteLongo@outlook.it', 'LNGRST49S23B963Y', 'medico', 'Oreste', 'Longo', 'Caserta', '1949-11-23'),
	('Muchad90', 'wov2ieR9ah', 'UranioLongo@dayrep.com', 'RNULNG62M01E506L', 'paziente', 'Uranio', 'Longo', 'Leccese', '1962-08-01'),
	('Proom1958', 'iwooh4Aeg', 'ViolaZetticci@teleworm.us', 'ZTTVLI59M49H953M', 'paziente', 'Viola', 'Zettici', 'San Leucio', '1959-08-09');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
