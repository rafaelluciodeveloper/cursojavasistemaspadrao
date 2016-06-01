-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01-Jun-2016 às 17:50
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `agendatelefonica`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefone` varchar(30) NOT NULL,
  `observacoes` text NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`codigo`, `nome`, `endereco`, `email`, `telefone`, `observacoes`) VALUES
(1, 'RAFAEL DA SILVA LUCIO', 'RUA DR JOSE PAULINO ALBUQUERQUE SARMENTO 47 - PONTA GROSSA - MACEIO/AL', 'RAFAELLUCIO.DEVELOPER@GMAIL.COM', '(082)98818-5395', 'Ligar Sempre Dia 20 de Cada Mês.'),
(2, 'RODRIGO MAXWELL DA SILVA LUCIO', 'RUA DR JOSE PAULINO ALBUQUERQUE SARMENTO 48 - PONTA GROSSA - MACEIO/AL', 'DIGOMAXWELL@GMAIL.COM', '(082)98829-2812', 'Ligar Sempre Dia 15 de Cada Mês.'),
(3, 'ISRAEL ANTONIO LUCIO', 'RUA 11 DE JULHO 48', 'israel@hotmail.com', '082999739504', 'Ligar Sempre dia 10 de cada Mês.');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
