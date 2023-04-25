-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-04-2023 a las 06:39:03
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cartonesa`
--

-- --------------------------------------------------------
create DATABASE cartonesa;
use cartonesa;
--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `areanombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`id`, `areanombre`) VALUES
(1, '00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authority`
--

CREATE TABLE `authority` (
  `id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `authority`
--

INSERT INTO `authority` (`id`, `authority`) VALUES
(1, 'ROLL_ADMIN'),
(2, 'ROLL_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `causa`
--

CREATE TABLE `causa` (
  `idcausa` int(11) NOT NULL,
  `descripcioncausa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maquina`
--

CREATE TABLE `maquina` (
  `id` int(11) NOT NULL,
  `maquinanombre` varchar(255) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `maquina`
--

INSERT INTO `maquina` (`id`, `maquinanombre`, `area_id`) VALUES
(1, '00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordencompra`
--

CREATE TABLE `ordencompra` (
  `idordencomp` int(11) NOT NULL,
  `cantprod` decimal(19,2) DEFAULT NULL,
  `cotizacion` varchar(255) DEFAULT NULL,
  `estadooc` varchar(255) DEFAULT NULL,
  `fechaenvoc` date DEFAULT NULL,
  `fechaingreso` date DEFAULT NULL,
  `fechareq` date DEFAULT NULL,
  `numsolcompra` int(11) NOT NULL,
  `producto` varchar(255) DEFAULT NULL,
  `idmaq` int(11) DEFAULT NULL,
  `idunidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ordencompra`
--

INSERT INTO `ordencompra` (`idordencomp`, `cantprod`, `cotizacion`, `estadooc`, `fechaenvoc`, `fechaingreso`, `fechareq`, `numsolcompra`, `producto`, `idmaq`, `idunidad`) VALUES
(1, '0.00', '0', '0', '1999-01-01', '1999-01-01', '1999-01-01', 0, '0', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordentrabajo`
--

CREATE TABLE `ordentrabajo` (
  `idordentrab` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estadoot` varchar(255) DEFAULT NULL,
  `falla` varchar(255) DEFAULT NULL,
  `fecharegistro` datetime NOT NULL,
  `horastrab` decimal(19,2) DEFAULT NULL,
  `tiempos` varchar(255) DEFAULT NULL,
  `tipoorden` varchar(255) DEFAULT NULL,
  `idcausa` int(11) DEFAULT NULL,
  `idmaq` int(11) DEFAULT NULL,
  `idordencomp` int(11) DEFAULT NULL,
  `idsubmaq` int(11) DEFAULT NULL,
  `idtecn` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secuenc_ot`
--

CREATE TABLE `secuenc_ot` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `secuenc_ot`
--

INSERT INTO `secuenc_ot` (`next_val`) VALUES
(20000000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `submaquina`
--

CREATE TABLE `submaquina` (
  `id` int(11) NOT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `maquina_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnico`
--

CREATE TABLE `tecnico` (
  `idtecn` int(11) NOT NULL,
  `codigotrab` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idtiptrab` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipotrabajo`
--

CREATE TABLE `tipotrabajo` (
  `idtiptrab` int(11) NOT NULL,
  `tipotrab` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidadmedida`
--

CREATE TABLE `unidadmedida` (
  `idunidad` int(11) NOT NULL,
  `unidad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `unidadmedida`
--

INSERT INTO `unidadmedida` (`idunidad`, `unidad`) VALUES
(1, '00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `enabled`, `password`, `user_name`, `authority_id`) VALUES
(1, b'1', '$2a$04$n5vXRxW/F6GQQwqUKV8j/OWml4eyf/cFcVnY7FJ4fmjcdEq68JmE2', 'administrador', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `causa`
--
ALTER TABLE `causa`
  ADD PRIMARY KEY (`idcausa`);

--
-- Indices de la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmpe1kum68uwuecy56umntuvcy` (`area_id`);

--
-- Indices de la tabla `ordencompra`
--
ALTER TABLE `ordencompra`
  ADD PRIMARY KEY (`idordencomp`),
  ADD KEY `FKq5ttk5pf8qk2x7hmqhaophjl` (`idmaq`),
  ADD KEY `FKgrq13sg5y54krldid5ekldlmc` (`idunidad`);

--
-- Indices de la tabla `ordentrabajo`
--
ALTER TABLE `ordentrabajo`
  ADD PRIMARY KEY (`idordentrab`),
  ADD KEY `FK97kfr48f337881g3hw92it87v` (`idcausa`),
  ADD KEY `FK30u8ygbw5pg0y3x7o1qkatbqi` (`idmaq`),
  ADD KEY `FK7pogx0fqgjjq0v0o36gn11tnu` (`idordencomp`),
  ADD KEY `FKpev9tjre69u3ogx5fh98if6h8` (`idsubmaq`),
  ADD KEY `FK2kp8vn1foy0jgk4q89plah4jq` (`idtecn`);

--
-- Indices de la tabla `submaquina`
--
ALTER TABLE `submaquina`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqq5lx9iyxy3qhu00a626r2bbm` (`maquina_id`);

--
-- Indices de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD PRIMARY KEY (`idtecn`),
  ADD KEY `FKc3bqeadlnharojwlay33ty4cm` (`idtiptrab`);

--
-- Indices de la tabla `tipotrabajo`
--
ALTER TABLE `tipotrabajo`
  ADD PRIMARY KEY (`idtiptrab`);

--
-- Indices de la tabla `unidadmedida`
--
ALTER TABLE `unidadmedida`
  ADD PRIMARY KEY (`idunidad`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKokrgxdbtf7tirfx1d1qtg9n24` (`authority_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `area`
--
ALTER TABLE `area`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `authority`
--
ALTER TABLE `authority`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `causa`
--
ALTER TABLE `causa`
  MODIFY `idcausa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `maquina`
--
ALTER TABLE `maquina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `ordencompra`
--
ALTER TABLE `ordencompra`
  MODIFY `idordencomp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `submaquina`
--
ALTER TABLE `submaquina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  MODIFY `idtecn` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipotrabajo`
--
ALTER TABLE `tipotrabajo`
  MODIFY `idtiptrab` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `unidadmedida`
--
ALTER TABLE `unidadmedida`
  MODIFY `idunidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `maquina`
--
ALTER TABLE `maquina`
  ADD CONSTRAINT `FKmpe1kum68uwuecy56umntuvcy` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`);

--
-- Filtros para la tabla `ordencompra`
--
ALTER TABLE `ordencompra`
  ADD CONSTRAINT `FKgrq13sg5y54krldid5ekldlmc` FOREIGN KEY (`idunidad`) REFERENCES `unidadmedida` (`idunidad`),
  ADD CONSTRAINT `FKq5ttk5pf8qk2x7hmqhaophjl` FOREIGN KEY (`idmaq`) REFERENCES `maquina` (`id`);

--
-- Filtros para la tabla `ordentrabajo`
--
ALTER TABLE `ordentrabajo`
  ADD CONSTRAINT `FK2kp8vn1foy0jgk4q89plah4jq` FOREIGN KEY (`idtecn`) REFERENCES `tecnico` (`idtecn`),
  ADD CONSTRAINT `FK30u8ygbw5pg0y3x7o1qkatbqi` FOREIGN KEY (`idmaq`) REFERENCES `maquina` (`id`),
  ADD CONSTRAINT `FK7pogx0fqgjjq0v0o36gn11tnu` FOREIGN KEY (`idordencomp`) REFERENCES `ordencompra` (`idordencomp`),
  ADD CONSTRAINT `FK97kfr48f337881g3hw92it87v` FOREIGN KEY (`idcausa`) REFERENCES `causa` (`idcausa`),
  ADD CONSTRAINT `FKpev9tjre69u3ogx5fh98if6h8` FOREIGN KEY (`idsubmaq`) REFERENCES `submaquina` (`id`);

--
-- Filtros para la tabla `submaquina`
--
ALTER TABLE `submaquina`
  ADD CONSTRAINT `FKqq5lx9iyxy3qhu00a626r2bbm` FOREIGN KEY (`maquina_id`) REFERENCES `maquina` (`id`);

--
-- Filtros para la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD CONSTRAINT `FKc3bqeadlnharojwlay33ty4cm` FOREIGN KEY (`idtiptrab`) REFERENCES `tipotrabajo` (`idtiptrab`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKokrgxdbtf7tirfx1d1qtg9n24` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
