-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ACEX_IESMH
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ACEX_IESMH
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ACEX_IESMH` DEFAULT CHARACTER SET utf8 ;
USE `ACEX_IESMH` ;

-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`departamentos` (
  `id_depar` INT NOT NULL AUTO_INCREMENT,
  `codigo` CHAR(3) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `jefe` INT UNSIGNED NULL,
  PRIMARY KEY (`id_depar`),
  UNIQUE INDEX `id_depar_UNIQUE` (`id_depar` ASC),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_departamentos_profesores_idx` (`jefe` ASC),
  CONSTRAINT `fk_departamentos_profesores`
    FOREIGN KEY (`jefe`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`profesores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`profesores` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dni` CHAR(9) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(80) NOT NULL,
  `password` CHAR(32) NULL,
  `nivel` ENUM('Superusuario', 'Administrador', 'Equipodirectivo', 'Profesor') NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `dep_id` INT NOT NULL,
  `foto` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC),
  INDEX `fk_profesores_departamentos1_idx` (`dep_id` ASC),
  CONSTRAINT `fk_profesores_departamentos1`
    FOREIGN KEY (`dep_id`)
    REFERENCES `ACEX_IESMH`.`departamentos` (`id_depar`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`actividades` (
  `id_actividad` INT NOT NULL AUTO_INCREMENT,
  `solicitante` INT UNSIGNED NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `tipo` ENUM('extraescolar', 'complementaria') NOT NULL,
  `fini` DATE NOT NULL,
  `ffin` DATE NOT NULL,
  `hini` TIME NOT NULL,
  `hfin` TIME NOT NULL,
  `prevista` TINYINT NOT NULL,
  `transporte_req` TINYINT NOT NULL,
  `coment_transporte` VARCHAR(75) NULL DEFAULT NULL,
  `alojamiento_req` TINYINT NOT NULL,
  `coment_alojamiento` VARCHAR(75) NULL DEFAULT NULL,
  `comentarios` VARCHAR(100) NULL DEFAULT NULL,
  `estado` ENUM('Solicitada', 'Denegada', 'Aprobada', 'Realizada') NOT NULL,
  `coment_estado` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id_actividad`),
  UNIQUE INDEX `id_actividad_UNIQUE` (`id_actividad` ASC),
  INDEX `fk_actividades_profesores1_idx` (`solicitante` ASC),
  CONSTRAINT `fk_actividades_profesores1`
    FOREIGN KEY (`solicitante`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`cursos` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `cod_curso` CHAR(5) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `etapa` ENUM('ESO', 'Bachillerato', 'FPGS', 'FPGM', 'FPB', 'FPCE') NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_curso`),
  UNIQUE INDEX `id_curso_UNIQUE` (`id_curso` ASC),
  UNIQUE INDEX `cod_curso_UNIQUE` (`cod_curso` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`grupos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `curso` INT NOT NULL,
  `cod_grupo` CHAR(8) NOT NULL,
  `num_alumnos` INT NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`id_grupo`, `curso`),
  UNIQUE INDEX `cod_curso_UNIQUE` (`cod_grupo` ASC),
  INDEX `fk_grupos_cursos1_idx` (`curso` ASC),
  CONSTRAINT `fk_grupos_cursos1`
    FOREIGN KEY (`curso`)
    REFERENCES `ACEX_IESMH`.`cursos` (`id_curso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`programadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`programadas` (
  `id_programada` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `solicitante` INT UNSIGNED NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `tipo` ENUM('extraescolar', 'complementaria') NOT NULL,
  `fini` DATE NOT NULL,
  `ffin` DATE NOT NULL,
  `hini` TIME NOT NULL,
  `hfin` TIME NOT NULL,
  `prevista` TINYINT NOT NULL,
  `transporte_req` TINYINT NOT NULL,
  `coment_transporte` VARCHAR(75) NULL DEFAULT NULL,
  `alojamiento_req` TINYINT NOT NULL,
  `coment_alojamiento` VARCHAR(75) NULL DEFAULT NULL,
  `comentarios` VARCHAR(100) NULL DEFAULT NULL,
  `estado` ENUM('Solicitada', 'Denegada', 'Aprobada', 'Realizada') NOT NULL,
  `coment_estado` VARCHAR(100) NULL DEFAULT NULL,
  `empresa_transporte` VARCHAR(45) NULL,
  `precio_transporte` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id_programada`, `id_actividad`),
  UNIQUE INDEX `id_actividad_UNIQUE` (`id_programada` ASC),
  INDEX `fk_programadas_actividades1_idx` (`id_actividad` ASC),
  INDEX `fk_programadas_profesores1_idx` (`solicitante` ASC),
  CONSTRAINT `fk_programadas_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`),
  CONSTRAINT `fk_programadas_profesores1`
    FOREIGN KEY (`solicitante`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`imagenes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`imagenes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(200) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `programadas_id_programada` INT NOT NULL,
  PRIMARY KEY (`id`, `programadas_id_programada`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `url_UNIQUE` (`url` ASC),
  INDEX `fk_imagenes_programadas1_idx` (`programadas_id_programada` ASC),
  CONSTRAINT `fk_imagenes_programadas1`
    FOREIGN KEY (`programadas_id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`mediostransporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`mediostransporte` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`responsables`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`responsables` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `id_responsable` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `id_actividad`, `id_responsable`),
  INDEX `fk_table1_profesores1_idx` (`id_responsable` ASC),
  UNIQUE INDEX `id_responsable_UNIQUE` (`id_responsable` ASC, `id_actividad` ASC),
  CONSTRAINT `fk_table1_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_profesores1`
    FOREIGN KEY (`id_responsable`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`participantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`participantes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `id_participante` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `id_actividad`, `id_participante`),
  INDEX `fk_participantes_profesores1_idx` (`id_participante` ASC),
  UNIQUE INDEX `id_participante_UNIQUE` (`id_participante` ASC, `id_actividad` ASC),
  CONSTRAINT `fk_participantes_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_profesores1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`transporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`transporte` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `id_transporte` INT NOT NULL,
  PRIMARY KEY (`id`, `id_actividad`, `id_transporte`),
  INDEX `fk_transporte_mediostransporte1_idx` (`id_transporte` ASC),
  UNIQUE INDEX `id_transporte_UNIQUE` (`id_transporte` ASC, `id_actividad` ASC),
  CONSTRAINT `fk_transporte_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transporte_mediostransporte1`
    FOREIGN KEY (`id_transporte`)
    REFERENCES `ACEX_IESMH`.`mediostransporte` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`cursos_participa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`cursos_participa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `id_curso` INT NOT NULL,
  PRIMARY KEY (`id`, `id_curso`, `id_actividad`),
  INDEX `fk_cursos_participa_actividades1_idx` (`id_actividad` ASC),
  UNIQUE INDEX `id_curso_UNIQUE` (`id_curso` ASC, `id_actividad` ASC),
  CONSTRAINT `fk_cursos_participa_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursos_participa_cursos1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `ACEX_IESMH`.`cursos` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`grupos_participa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`grupos_participa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_actividad` INT NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`id`, `id_actividad`, `id_grupo`),
  INDEX `fk_grupos_participa_grupos1_idx` (`id_grupo` ASC),
  UNIQUE INDEX `id_grupo_UNIQUE` (`id_grupo` ASC, `id_actividad` ASC),
  CONSTRAINT `fk_grupos_participa_actividades1`
    FOREIGN KEY (`id_actividad`)
    REFERENCES `ACEX_IESMH`.`actividades` (`id_actividad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupos_participa_grupos1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `ACEX_IESMH`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`transporte_programado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`transporte_programado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_programada` INT NOT NULL,
  `id_transporte` INT NOT NULL,
  PRIMARY KEY (`id`, `id_transporte`, `id_programada`),
  INDEX `fk_transporte_programado_programadas1_idx` (`id_programada` ASC),
  UNIQUE INDEX `id_programada_UNIQUE` (`id_programada` ASC, `id_transporte` ASC),
  CONSTRAINT `fk_transporte_programado_programadas1`
    FOREIGN KEY (`id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transporte_programado_mediostransporte1`
    FOREIGN KEY (`id_transporte`)
    REFERENCES `ACEX_IESMH`.`mediostransporte` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`cursos_programadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`cursos_programadas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_programada` INT NOT NULL,
  `id_curso` INT NOT NULL,
  PRIMARY KEY (`id`, `id_programada`, `id_curso`),
  INDEX `fk_cursos_programadas_cursos1_idx` (`id_curso` ASC),
  UNIQUE INDEX `id_curso_UNIQUE` (`id_curso` ASC, `id_programada` ASC),
  CONSTRAINT `fk_cursos_programadas_programadas1`
    FOREIGN KEY (`id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cursos_programadas_cursos1`
    FOREIGN KEY (`id_curso`)
    REFERENCES `ACEX_IESMH`.`cursos` (`id_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`grupos_programadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`grupos_programadas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_programada` INT NOT NULL,
  `id_grupo` INT NOT NULL,
  PRIMARY KEY (`id`, `id_grupo`, `id_programada`),
  INDEX `fk_grupos_programadas_programadas1_idx` (`id_programada` ASC),
  UNIQUE INDEX `id_grupo_UNIQUE` (`id_grupo` ASC, `id_programada` ASC),
  CONSTRAINT `fk_grupos_programadas_programadas1`
    FOREIGN KEY (`id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupos_programadas_grupos1`
    FOREIGN KEY (`id_grupo`)
    REFERENCES `ACEX_IESMH`.`grupos` (`id_grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`participantes_programada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`participantes_programada` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_programada` INT NOT NULL,
  `id_participante` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `id_participante`, `id_programada`),
  INDEX `fk_participantes_programada_programadas1_idx` (`id_programada` ASC),
  UNIQUE INDEX `id_participante_UNIQUE` (`id_participante` ASC, `id_programada` ASC),
  CONSTRAINT `fk_participantes_programada_programadas1`
    FOREIGN KEY (`id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_programada_profesores1`
    FOREIGN KEY (`id_participante`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ACEX_IESMH`.`responsables_programada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ACEX_IESMH`.`responsables_programada` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_programada` INT NOT NULL,
  `id_responsable` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `id_responsable`, `id_programada`),
  INDEX `fk_responsables_programada_programadas1_idx` (`id_programada` ASC),
  UNIQUE INDEX `id_responsable_UNIQUE` (`id_responsable` ASC, `id_programada` ASC),
  CONSTRAINT `fk_responsables_programada_programadas1`
    FOREIGN KEY (`id_programada`)
    REFERENCES `ACEX_IESMH`.`programadas` (`id_programada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_responsables_programada_profesores1`
    FOREIGN KEY (`id_responsable`)
    REFERENCES `ACEX_IESMH`.`profesores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
