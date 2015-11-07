-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-11-2015 a las 20:50:11
-- Versión del servidor: 5.6.25
-- Versión de PHP: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bicitoolstaidy`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conexiones`
--

CREATE TABLE IF NOT EXISTS `conexiones` (
  `id_conexion` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fechaConexion` datetime NOT NULL,
  `tipoConexion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporal`
--

CREATE TABLE IF NOT EXISTS `temporal` (
  `columnpk` int(11) NOT NULL,
  `estring` varchar(45) DEFAULT NULL,
  `estring2` varchar(45) DEFAULT NULL,
  `edate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL,
  `numeroIdentificacion` int(11) NOT NULL,
  `tipoIdentificacion` int(11) NOT NULL,
  `tipoPerfil` int(11) NOT NULL,
  `genero` int(11) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `fechaNacimiento` varchar(10) DEFAULT NULL,
  `direccionCasa` varchar(100) NOT NULL,
  `direccionTrabajo` varchar(100) DEFAULT NULL,
  `telefonoFijo` varchar(10) DEFAULT NULL,
  `telefonoMovil` varchar(10) NOT NULL,
  `facebookUser` varchar(100) DEFAULT NULL,
  `facebookToken` varchar(100) DEFAULT NULL,
  `twitterUser` varchar(100) DEFAULT NULL,
  `twitterToken` varchar(100) DEFAULT NULL,
  `usuario` varchar(10) NOT NULL,
  `contrasenia` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE IF NOT EXISTS `vendedor` (
  `id_establecimiento` int(11) NOT NULL,
  `nombre_establecimiento` varchar(100) NOT NULL,
  `direccion_establecimiento` varchar(100) NOT NULL,
  `telefono_establecimiento` varchar(10) NOT NULL,
  `celular_establecimiento` varchar(10) NOT NULL,
  `correo_establecimiento` varchar(50) NOT NULL,
  `nit_establecimiento` varchar(20) NOT NULL,
  `foto_establecimiento` varchar(100) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conexiones`
--
ALTER TABLE `conexiones`
  ADD PRIMARY KEY (`id_conexion`);

--
-- Indices de la tabla `temporal`
--
ALTER TABLE `temporal`
  ADD PRIMARY KEY (`columnpk`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numeroIdentificacion` (`numeroIdentificacion`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id_establecimiento`),
  ADD UNIQUE KEY `id_usuario` (`id_usuario`),
  ADD UNIQUE KEY `nit_establecimiento` (`nit_establecimiento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `conexiones`
--
ALTER TABLE `conexiones`
  MODIFY `id_conexion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id_establecimiento` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
