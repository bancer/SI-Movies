SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `movies` ;
CREATE SCHEMA IF NOT EXISTS `movies` DEFAULT CHARACTER SET utf8 ;
USE `movies` ;

-- -----------------------------------------------------
-- Table `movies`.`Movie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Movie` ;

CREATE TABLE IF NOT EXISTS `movies`.`Movie` (
  `id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `year` YEAR NULL,
  `timeline` ENUM('IN_THEATERS', 'OPENING', 'COMING_SOON', 'OTHER') NOT NULL,
  `runtime` SMALLINT(3) NOT NULL,
  `mpaa_rating` ENUM('G','NC-17','PG','PG-13','R','Unrated') NOT NULL,
  `users_rating_score` FLOAT NULL,
  `studio` VARCHAR(60) NULL,
  `critics_consensus` TEXT NULL,
  `synopsis` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Genre` ;

CREATE TABLE IF NOT EXISTS `movies`.`Genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `name` ENUM('Action & Adventure', 'Adult', 'Animation', 'Art House & International','Classics','Comedy','Cult Movies','Documentary','Drama','Faith & Spirituality','Gay & Lesbian','Horror','Kids & Family','Musical & Performing Arts','Mystery & Suspense','Romance','Science Fiction & Fantasy','Special Interest','Sports & Fitness','Television','Western','Anime & Manga') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_Name_Movie` (`movie_id` ASC, `name` ASC),
  INDEX `fk_Genre_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Genre_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`CriticRating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`CriticRating` ;

CREATE TABLE IF NOT EXISTS `movies`.`CriticRating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('CRITICS', 'AUDIENCE') NOT NULL,
  `score` TINYINT(3) NOT NULL,
  `rating` ENUM('Certified Fresh','Fresh','Rotten','Spilled','Upright') NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_type_movie` (`movie_id` ASC, `type` ASC),
  INDEX `fk_CriticRating_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `CriticRating_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Poster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Poster` ;

CREATE TABLE IF NOT EXISTS `movies`.`Poster` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('THUMBNAIL', 'PROFILE', 'DETAILED', 'ORIGINAL') NOT NULL,
  `url` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index3` (`movie_id` ASC, `type` ASC),
  INDEX `fk_Poster_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Poster_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Actor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Actor` ;

CREATE TABLE IF NOT EXISTS `movies`.`Actor` (
  `id` INT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
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
  `name` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Character_Cast1_idx` (`actor_id` ASC),
  INDEX `fk_Character_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Character_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Character_Cast1`
    FOREIGN KEY (`actor_id`)
    REFERENCES `movies`.`Actor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Director`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Director` ;

CREATE TABLE IF NOT EXISTS `movies`.`Director` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_name` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`AlternateID`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`AlternateID` ;

CREATE TABLE IF NOT EXISTS `movies`.`AlternateID` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `value` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_AlternateID_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `AlternateID_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Link` ;

CREATE TABLE IF NOT EXISTS `movies`.`Link` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `type` ENUM('SELF', 'ALTERNATE', 'CAST', 'CLIPS', 'REVIEWS', 'SIMILAR') NOT NULL,
  `url` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index3` (`movie_id` ASC, `type` ASC),
  INDEX `fk_Link_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Link_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
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
  `thumbnail` VARCHAR(130) NOT NULL,
  `link` VARCHAR(130) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_Movie_Link` (`movie_id` ASC, `link` ASC),
  INDEX `fk_Clip_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Clip_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Review` ;

CREATE TABLE IF NOT EXISTS `movies`.`Review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `critic` VARCHAR(50) NOT NULL,
  `date` DATE NULL,
  `freshness` ENUM('fresh','none','rotten') NOT NULL,
  `publication` VARCHAR(255) NULL,
  `quote` TEXT NULL,
  `link` VARCHAR(255) NULL,
  `country` CHAR(2) NOT NULL DEFAULT 'US',
  PRIMARY KEY (`id`),
  INDEX `fk_Review_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Review_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`User` ;

CREATE TABLE IF NOT EXISTS `movies`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  `user_name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_name` (`user_name` ASC))
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
  CONSTRAINT `UserRating_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rating_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `movies`.`User` (`id`)
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
  `type` ENUM('THEATER', 'DVD') NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_country_movie` (`movie_id` ASC, `type` ASC),
  INDEX `fk_Release_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `Release_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`Cache`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`Cache` ;

CREATE TABLE IF NOT EXISTS `movies`.`Cache` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `hash` INT NOT NULL,
  `request` VARCHAR(30) NOT NULL,
  `response` TEXT NOT NULL,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_hash` (`hash` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movies`.`MovieDirector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movies`.`MovieDirector` ;

CREATE TABLE IF NOT EXISTS `movies`.`MovieDirector` (
  `director_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  PRIMARY KEY (`director_id`, `movie_id`),
  INDEX `fk_table1_Director1_idx` (`director_id` ASC),
  INDEX `fk_table1_Movie1_idx` (`movie_id` ASC),
  CONSTRAINT `MovieDirector_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movies`.`Movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_Director1`
    FOREIGN KEY (`director_id`)
    REFERENCES `movies`.`Director` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `movies` ;

-- -----------------------------------------------------
-- procedure UpdateMovieUsersRatingScoreProcedure
-- -----------------------------------------------------

USE `movies`;
DROP procedure IF EXISTS `movies`.`UpdateMovieUsersRatingScoreProcedure`;

DELIMITER $$
USE `movies`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateMovieUsersRatingScoreProcedure`(IN movieid INT)
BEGIN
	DECLARE ratingAvg FLOAT;
	SET ratingAvg = (
		SELECT AVG(`value`) FROM UserRating 
		WHERE movie_id = movieid
	);
	UPDATE Movie 
	SET users_rating_score = ratingAvg
	WHERE id = movieid;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `movies`;

DELIMITER $$

USE `movies`$$
DROP TRIGGER IF EXISTS `movies`.`UserRatingInsertTrigger` $$
USE `movies`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `movies`.`UserRatingInsertTrigger`
AFTER INSERT ON `movies`.`UserRating`
FOR EACH ROW
CALL UpdateMovieUsersRatingScoreProcedure(NEW.movie_id)$$


USE `movies`$$
DROP TRIGGER IF EXISTS `movies`.`UserRatingUpdateTrigger` $$
USE `movies`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `movies`.`UserRatingUpdateTrigger`
AFTER UPDATE ON `movies`.`UserRating`
FOR EACH ROW
CALL UpdateMovieUsersRatingScoreProcedure(NEW.movie_id)$$


DELIMITER ;
