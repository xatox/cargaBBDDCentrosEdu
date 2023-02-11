--
-- Base de datos: `CentrosEducacion`
--

--
-- Borrado de todas las tablas
--

DROP TABLE IF EXISTS `CentrosEducacion`.`ComunidadesAutonomas`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Provincias`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Denominaciones`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Naturalezas`;
DROP TABLE IF EXISTS `CentrosEducacion`.`TiposCentros`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Centros`;

DROP TABLE IF EXISTS `CentrosEducacion`.`Familias`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Grados`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Modalidades`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Ensenanzas`;
DROP TABLE IF EXISTS `CentrosEducacion`.`Centros_Ensenanzas`;

DROP DATABASE IF EXISTS `CentrosEducacion`;


-- --------------------------------------------------------

--
-- Schema `CentrosEducacion`
--

CREATE SCHEMA `CentrosEducacion` ;

USE `CentrosEducacion`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ComunidadesAutonomas`
--

CREATE TABLE IF NOT EXISTS `ComunidadesAutonomas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(300) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Provincias`
--

CREATE TABLE IF NOT EXISTS `Provincias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(300) COLLATE latin1_spanish_ci NOT NULL,
  `idComunidad` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Denominaciones`
--

CREATE TABLE IF NOT EXISTS `Denominaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Naturalezas`
--

CREATE TABLE IF NOT EXISTS `Naturalezas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(300) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TiposCentros`
--

CREATE TABLE IF NOT EXISTS `TiposCentros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Centros`
--

CREATE TABLE IF NOT EXISTS `Centros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(300) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(300) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fax` varchar(300) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(300) COLLATE latin1_spanish_ci DEFAULT NULL,
  `web` varchar(300) COLLATE latin1_spanish_ci DEFAULT NULL,
  `municipio` varchar(350) COLLATE latin1_spanish_ci DEFAULT NULL,
  `localidad` varchar(350) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion` varchar(400) COLLATE latin1_spanish_ci DEFAULT NULL,
  `codigoPostal` varchar(7) COLLATE latin1_spanish_ci DEFAULT NULL,
  `concertado` tinyint(1) DEFAULT NULL,
  `idProvincia` int(11) NOT NULL,
  `idNaturaleza` int(11) NOT NULL,
  `idTipoCentro` int(11) NOT NULL,
  `idDenominacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Familias`
--

CREATE TABLE IF NOT EXISTS `Familias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Grados`
--

CREATE TABLE IF NOT EXISTS `Grados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Modalidades`
--

CREATE TABLE IF NOT EXISTS `Modalidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ensenanzas`
--

CREATE TABLE IF NOT EXISTS `Ensenanzas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(400) COLLATE latin1_spanish_ci NOT NULL,
  `idFamilia` int(11) DEFAULT NULL,
  `idGrado` int(11) DEFAULT NULL,
  `idModalidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Centros_Ensenanzas`
--

CREATE TABLE IF NOT EXISTS `Centros_Ensenanzas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCentro` int(11) NOT NULL,
  `idEnsenanza` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);