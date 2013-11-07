SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `movies` ;
CREATE SCHEMA IF NOT EXISTS `movies` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `movies` ;

-- -----------------------------------------------------
-- Table `movies`.`Director`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Director` ;

CREATE TABLE IF NOT EXISTS `movies`.`Director` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_name` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Movie` ;

CREATE TABLE IF NOT EXISTS `movies`.`Movie` (
  `id` INT NOT NULL,
  `director_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `year` YEAR NOT NULL,
  `timeline` ENUM('in theaters', 'opening', 'coming soon', 'other') NOT NULL,
  `runtime` INT NOT NULL,
  `mpaa_rating` CHAR NULL,
  `users_rating_score` FLOAT NULL,
  `studio` VARCHAR(255) NULL,
  `critics_consensus` TEXT NULL,
  `synopsis` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Movie_Director1_idx` (`director_id` ASC),
  CONSTRAINT `fk_Movie_Director1`
    FOREIGN KEY (`director_id`)
    REFERENCES `movies`.`Director` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Genre` ;

CREATE TABLE IF NOT EXISTS `movies`.`Genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `name` ENUM('Action & Adventure', 'Comedy', 'Drama') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Genre_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `idx_Name_Movie` (`movie_id` ASC, `name` ASC),
  CONSTRAINT `fk_Genre_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`CriticRating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`CriticRating` ;

CREATE TABLE IF NOT EXISTS `movies`.`CriticRating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('Critics', 'Audience') NOT NULL,
  `score` INT NOT NULL,
  `rating` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CriticRating_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `idx_type_movie` (`movie_id` ASC, `type` ASC),
  CONSTRAINT `fk_CriticRating_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Poster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Poster` ;

CREATE TABLE IF NOT EXISTS `movies`.`Poster` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('thumbnail', 'profile', 'detailed', 'original') NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Poster_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `index3` (`movie_id` ASC, `type` ASC),
  CONSTRAINT `fk_Poster_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Actor` ;

CREATE TABLE IF NOT EXISTS `movies`.`Actor` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Character`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Character` ;

CREATE TABLE IF NOT EXISTS `movies`.`Character` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `actor_id` INT NULL,
  `movie_id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Character_Cast1_idx` (`actor_id` ASC),
  INDEX `fk_Character_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_Character_Cast1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `movies`.`Actor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Character_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`AlternateID`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`AlternateID` ;

CREATE TABLE IF NOT EXISTS `movies`.`AlternateID` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `value` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_AlternateID_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_AlternateID_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Link` ;

CREATE TABLE IF NOT EXISTS `movies`.`Link` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('self', 'alternate', 'cast', 'clips', 'reviews', 'similar', 'canonical') NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Link_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `index3` (`movie_id` ASC, `type` ASC),
  CONSTRAINT `fk_Link_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Clip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Clip` ;

CREATE TABLE IF NOT EXISTS `movies`.`Clip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `duration` INT NULL,
  `thumbnail` VARCHAR(255) NULL,
  `link` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Clip_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `idx_Movie_Link` (`movie_id` ASC, `link` ASC),
  CONSTRAINT `fk_Clip_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Review` ;

CREATE TABLE IF NOT EXISTS `movies`.`Review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `critic` VARCHAR(255) NULL,
  `date` DATE NULL,
  `freshness` VARCHAR(45) NULL,
  `publication` VARCHAR(255) NULL,
  `quote` TEXT NULL,
  `link` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Review_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_Review_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`User` ;

CREATE TABLE IF NOT EXISTS `movies`.`User` (
  `id` INT NOT NULL,
  `address` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  `user_name` VARCHAR(255) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`OpenID`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`OpenID` ;

CREATE TABLE IF NOT EXISTS `movies`.`OpenID` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `identifier` VARCHAR(255) NOT NULL,
  `provider` ENUM('Google', 'Yahoo!', 'OpenID', 'LiveJournal', 'MyOpenID', 'WordPress', 'Other') NOT NULL,
  INDEX `fk_OpenID_User1_idx` (`user_id` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_identifier_provider` (`identifier` ASC, `provider` ASC),
  CONSTRAINT `fk_OpenID_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `movies`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`UserRating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`UserRating` ;

CREATE TABLE IF NOT EXISTS `movies`.`UserRating` (
  `user_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `value` TINYINT NOT NULL,
  PRIMARY KEY (`movie_id`, `user_id`),
  INDEX `fk_Rating_User1_idx` (`user_id` ASC),
  INDEX `fk_Rating_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `fk_Rating_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `movies`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rating_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Release`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Release` ;

CREATE TABLE IF NOT EXISTS `movies`.`Release` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `country` CHAR(2) NOT NULL,
  `type` ENUM('theater', 'dvd') NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Release_Movie1_idx` (`movie_id` ASC),
  UNIQUE INDEX `idx_country_movie` (`movie_id` ASC, `country` ASC, `type` ASC),
  CONSTRAINT `fk_Release_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Cache`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Cache` ;

CREATE TABLE IF NOT EXISTS `movies`.`Cache` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `hash` INT NOT NULL,
  `request` VARCHAR(255) NOT NULL,
  `response` TEXT NOT NULL,
  `time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_hash` (`hash` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
