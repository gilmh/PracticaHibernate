PracticaHibernate
=================

Este ejercicio trabaja sobre una base de datos empleando hibernate y cumple los siguientes puntos:

  . La Base de Datos utilizada contendrá al menos 4 entidades y relaciones 1-N y N-M
  . Se podrá llevar a cabo el alta de registros, relacionándolos como convenga
  . Se podrá llevar a cabo la modificación de los registros, relacionándolos como convenga
  . Se podrá llevar a cabo la baja de registros, comprobando las dependencias entre los mismos
  . Se podrán llevar a cabo búsquedas complejas (por más de un criterio de busqueda y utilizando
    más de un objeto) 
  . Utilizar hasta 6 entidades
  . Utilizar alguna clase que mantenga más de una relación con otras clases
  . Implementar un mecanismo de autenticación usuario/contraseña
  . Implementar una barra de estado donde mostrar un resumen de los datos que visualiza el usuario
    en cada momento (qué datos, cuantas filas, qué fila tiene seleccionada actualmente y los mensajes
    oportunos según la acción que realice)
  . La aplicación refrescará los datos mostrados de forma automática ante posibles cambios en la
    Base de Datos
  . Permitir exportar a XML los datos de alguna tabla


Para poder emplear la aplicación pedirá un usuario y contraseña que habrá que insertar, a mano, previamente en la base de datos, en mi base de datos he creado uno con los siguientes campos: usuario = admin, contraseña = admin.



Esta hecho sobre la siguiente base de datos, exportada directamente desde phpmyadmin:

  -- phpMyAdmin SQL Dump
  -- version 4.0.4.1
  -- http://www.phpmyadmin.net
  -- Servidor: 127.0.0.1
  -- Tiempo de generación: 08-02-2014 a las 10:56:21
  -- Versión del servidor: 5.6.11
  -- Versión de PHP: 5.5.3
  
  SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
  SET time_zone = "+00:00";
  
  
  /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
  /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
  /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
  /*!40101 SET NAMES utf8 */;
  
  --
  -- Base de datos: `juegohibernate`
  --
  CREATE DATABASE IF NOT EXISTS `juegohibernate` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
  USE `juegohibernate`;
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `armas`
  --
  
  CREATE TABLE IF NOT EXISTS `armas` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(20) DEFAULT NULL,
    `descripcion` varchar(20) DEFAULT NULL,
    `ataque` int(11) DEFAULT NULL,
    `id_personaje` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_personaje` (`id_personaje`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
  
  --
  -- Volcado de datos para la tabla `armas`
  --
  
  INSERT INTO `armas` (`id`, `nombre`, `descripcion`, `ataque`, `id_personaje`) VALUES
  (1, 'sss', 'ss', 222, 1),
  (2, 'ggg', 'ggg', 333, 1);
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `login`
  --
  
  CREATE TABLE IF NOT EXISTS `login` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `usuario` varchar(20) DEFAULT NULL,
    `password` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;
  
  --
  -- Volcado de datos para la tabla `login`
  --
  
  INSERT INTO `login` (`id`, `usuario`, `password`) VALUES
  (1, 'admin', 'admin');
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `mapas`
  --
  
  CREATE TABLE IF NOT EXISTS `mapas` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(20) DEFAULT NULL,
    `descripcion` varchar(30) DEFAULT NULL,
    `dimension` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;
  
  --
  -- Volcado de datos para la tabla `mapas`
  --
  
  INSERT INTO `mapas` (`id`, `nombre`, `descripcion`, `dimension`) VALUES
  (6, 'df', 'dfb', 'dfb'),
  (7, 'udfuy', 'luhvu', 'dgbdgb');
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `misiones`
  --
  
  CREATE TABLE IF NOT EXISTS `misiones` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(20) DEFAULT NULL,
    `descripcion` varchar(50) DEFAULT NULL,
    `puntos` int(11) DEFAULT NULL,
    `id_usuarios` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_usuarios` (`id_usuarios`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `personajes`
  --
  
  CREATE TABLE IF NOT EXISTS `personajes` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `nombre` varchar(20) DEFAULT NULL,
    `raza` varchar(20) DEFAULT NULL,
    `clase` varchar(20) DEFAULT NULL,
    `historia` varchar(50) DEFAULT NULL,
    `id_usuario` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_usuario` (`id_usuario`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
  
  --
  -- Volcado de datos para la tabla `personajes`
  --
  
  INSERT INTO `personajes` (`id`, `nombre`, `raza`, `clase`, `historia`, `id_usuario`) VALUES
  (1, 'xxx', 'xxx', 'xxx', 'xxx', 1),
  (2, 'hhh', 'hhh', 'hhh', 'hhh', 1);
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `usuarios`
  --
  
  CREATE TABLE IF NOT EXISTS `usuarios` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `poblacion` varchar(20) DEFAULT NULL,
    `correo` varchar(30) DEFAULT NULL,
    `nombre` varchar(20) DEFAULT NULL,
    `telefono` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;
  
  --
  -- Volcado de datos para la tabla `usuarios`
  --
  
  INSERT INTO `usuarios` (`id`, `poblacion`, `correo`, `nombre`, `telefono`) VALUES
  (1, 'aa', 'aa', 'aa', 'aa'),
  (2, 'ggggt', 'dd', 'lllll', 'dd'),
  (3, 'fgn', 'tbh', 'db', 'dfgbn');
  
  -- --------------------------------------------------------
  
  --
  -- Estructura de tabla para la tabla `usuarios_mapas`
  --
  
  CREATE TABLE IF NOT EXISTS `usuarios_mapas` (
    `id_usuario` int(10) unsigned NOT NULL,
    `id_mapa` int(10) unsigned NOT NULL,
    PRIMARY KEY (`id_usuario`,`id_mapa`),
    KEY `id_usuario` (`id_usuario`),
    KEY `id_mapa` (`id_mapa`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  --
  -- Volcado de datos para la tabla `usuarios_mapas`
  --
  
  INSERT INTO `usuarios_mapas` (`id_usuario`, `id_mapa`) VALUES
  (1, 6),
  (3, 7);
  
  --
  -- Restricciones para tablas volcadas
  --
  
  --
  -- Filtros para la tabla `armas`
  --
  ALTER TABLE `armas`
    ADD CONSTRAINT `armas_error` FOREIGN KEY (`id_personaje`) REFERENCES `personajes` (`id`) ON DELETE CASCADE ON UPDATE     CASCADE;
  
  --
  -- Filtros para la tabla `misiones`
  --
  ALTER TABLE `misiones`
    ADD CONSTRAINT `misiones_ibfk_1` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON          UPDATE CASCADE;
  
  --
  -- Filtros para la tabla `personajes`
  --
  ALTER TABLE `personajes`
    ADD CONSTRAINT `personajes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON         UPDATE CASCADE;
  
  --
  -- Filtros para la tabla `usuarios_mapas`
  --
  ALTER TABLE `usuarios_mapas`
    ADD CONSTRAINT `usuarios_mapas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON     UPDATE CASCADE,
    ADD CONSTRAINT `usuarios_mapas_ibfk_2` FOREIGN KEY (`id_mapa`) REFERENCES `mapas` (`id`) ON DELETE CASCADE ON UPDATE     CASCADE;
  
  /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
  /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
  /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

