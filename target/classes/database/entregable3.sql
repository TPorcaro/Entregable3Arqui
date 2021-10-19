-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-10-2021 a las 23:55:06
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ddbbentregable2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrera`
--

CREATE TABLE `carrera` (
  `idCarrera` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carrera`
--

INSERT INTO `carrera` (`idCarrera`, `nombre`) VALUES
(1, 'Iaculis Nec Industries'),
(2, 'Leo Vivamus Incorporated'),
(3, 'Mauris Magna LLP'),
(4, 'Justo Faucibus Lectus Company'),
(5, 'Vel Associates'),
(6, 'Nunc LLP'),
(7, 'Nunc Interdum Limited'),
(8, 'Phasellus At Ltd'),
(9, 'Cras Dictum LLP'),
(10, 'Condimentum PC'),
(11, 'At Pretium Aliquet Associates'),
(12, 'Vestibulum Mauris Magna Associates'),
(13, 'Tristique Senectus Foundation'),
(14, 'Nullam Ltd'),
(15, 'Molestie Arcu Foundation'),
(16, 'Neque Non Industries'),
(17, 'Dignissim Lacus Aliquam Inc.'),
(18, 'Sed Pharetra Limited'),
(19, 'Sed Nulla Ante Consulting'),
(20, 'Sem Vitae Foundation');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `nmro_libreta` int(11) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `ciudad_residencia` varchar(255) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`nmro_libreta`, `apellido`, `ciudad_residencia`, `dni`, `edad`, `genero`, `nombre`) VALUES
(1, 'Jena Joseph', 'Yorkton', 44234521, 50, 'f', 'Rajah Lara'),
(2, 'Mara Johns', 'Omal', 44234522, 29, 'm', 'Jorden Rosario'),
(3, 'Yvonne Vazquez', 'Butte', 44234523, 24, 'f', 'Ina Pena'),
(4, 'Ciaran Benton', 'Okene', 44234524, 38, 'm', 'Signe Hampton'),
(5, 'Macon Shepard', 'Corozal', 44234525, 44, 'm', 'Carson Brewer'),
(6, 'Kenneth Strong', 'Nizhny', 44234526, 49, 'f', 'Vanna Herring'),
(7, 'Donna Graves', 'Pskov', 44234527, 26, 'm', 'Yetta Evans'),
(8, 'Gregory Kemp', 'Port Glasgow', 44234528, 18, 'f', 'Shannon Noel'),
(9, 'Sonia Malone', 'Chimbote', 44234529, 26, 'm', 'Joan Collins'),
(10, 'Ivy Orr', 'Istanbul', 44234530, 34, 'f', 'Austin Munoz'),
(11, 'Miriam Bass', 'Ratlam', 44234531, 20, 'm', 'Hedda Gentry'),
(12, 'Guy Jones', 'Olavarria', 44234532, 27, 'f', 'Kaye Watson'),
(13, 'Victor Conway', 'Barranco Minas', 44234533, 28, 'm', 'Bevis Francis'),
(14, 'Yoko Cortez', 'Cuautla', 44234534, 43, 'f', 'Dacey Miller'),
(15, 'Jason Lambert', 'Kapiti', 44234535, 32, 'm', 'Emma Fitzpatrick'),
(16, 'Ross Morse', 'Pelotas', 44234536, 31, '', 'Leilani Miller'),
(17, 'Mason Acevedo', 'North Jakarta', 44234537, 42, 'f', 'Avye Dotson'),
(18, 'Branden Dejesus', 'Puerto Montt', 44234538, 43, 'm', 'Abel Chang'),
(19, 'Isadora Cortez', 'Mattersburg', 44234539, 22, 'f', 'Dean Cain'),
(20, 'Dana Lamb', 'Olavarria', 44234540, 26, 'm', 'Justine Lyons');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `id` int(11) NOT NULL,
  `egresado` bit(1) DEFAULT NULL,
  `fecha_inicio` datetime(6) DEFAULT NULL,
  `idCarrera` int(11) DEFAULT NULL,
  `nmro_libreta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`id`, `egresado`, `fecha_inicio`, `idCarrera`, `nmro_libreta`) VALUES
(1, b'0', '2011-10-02 18:48:05.000000', 8, 14),
(2, b'0', '2015-07-02 18:48:05.000000', 3, 18),
(3, b'1', '2010-12-02 14:48:05.000000', 2, 5),
(4, b'1', '2016-04-07 18:48:05.000000', 2, 1),
(5, b'0', '2014-10-02 18:48:05.000000', 9, 18),
(6, b'1', '2016-11-10 11:48:05.000000', 2, 10),
(7, b'0', '2010-10-02 18:48:05.000000', 4, 20),
(8, b'1', '2011-10-02 18:48:05.000000', 1, 1),
(9, b'0', '2011-10-02 18:48:05.000000', 4, 12),
(10, b'1', '2011-10-02 18:48:05.000000', 1, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrera`
--
ALTER TABLE `carrera`
  ADD PRIMARY KEY (`idCarrera`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`nmro_libreta`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm566sdlwndm8fwq9dml8i2yvf` (`idCarrera`),
  ADD KEY `FKkx1o6xk79ysrgoxxn3m1gcqyp` (`nmro_libreta`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `FKkx1o6xk79ysrgoxxn3m1gcqyp` FOREIGN KEY (`nmro_libreta`) REFERENCES `estudiante` (`nmro_libreta`),
  ADD CONSTRAINT `FKm566sdlwndm8fwq9dml8i2yvf` FOREIGN KEY (`idCarrera`) REFERENCES `carrera` (`idCarrera`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
