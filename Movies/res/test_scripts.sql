SELECT *
FROM `CriticRating`
PROCEDURE ANALYSE ( ) ;

SELECT * FROM `Movie` PROCEDURE ANALYSE ( ) ;

SELECT *
FROM `Review`
PROCEDURE ANALYSE ( ) ;

EXPLAIN 
SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, 
Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, 
Movie.critics_consensus, Movie.synopsis 
FROM Movie AS Movie WHERE Movie.id=12000 LIMIT 1;


SELECT *
FROM `Cache`
PROCEDURE ANALYSE ( ) ;

ALTER TABLE `Character` 
CHANGE `name` `name` VARCHAR( 70 ) 
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

ALTER TABLE `Cache` 
CHANGE `request` `request` VARCHAR( 30 ) 
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

SELECT COUNT(*) FROM Movie;

EXPLAIN EXTENDED
SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, Character.id, Character.movie_id, Character.actor_id, Character.name, Cast.id, Cast.name, MovieDirector.director_id, MovieDirector.movie_id, Director.id, Director.name, Genre.id, Genre.movie_id, Genre.name, CriticRating.id, CriticRating.movie_id, CriticRating.type, CriticRating.score, CriticRating.rating, AlternateId.id, AlternateId.movie_id, AlternateId.name, AlternateId.value, Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link, Link.id, Link.movie_id, Link.type, Link.url FROM Movie AS Movie LEFT JOIN `Character` AS `Character` ON Movie.id=Character.movie_id LEFT JOIN Actor AS Cast ON Character.actor_id=Cast.id LEFT JOIN MovieDirector AS MovieDirector ON MovieDirector.movie_id=Movie.id LEFT JOIN Director AS Director ON Director.id=MovieDirector.director_id LEFT JOIN Genre AS Genre ON Genre.movie_id=Movie.id LEFT JOIN Poster AS Poster ON Poster.movie_id=Movie.id LEFT JOIN CriticRating AS CriticRating ON CriticRating.movie_id=Movie.id LEFT JOIN AlternateID AS AlternateId ON AlternateId.movie_id=Movie.id LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id LEFT JOIN Link AS Link ON Link.movie_id=Movie.id 
WHERE Movie.id=771308378 LIMIT 1;
SHOW WARNINGS;

EXPLAIN EXTENDED
SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, Character.id, Character.movie_id, Character.actor_id, Character.name, Cast.id, Cast.name, MovieDirector.director_id, MovieDirector.movie_id, Director.id, Director.name, Genre.id, Genre.movie_id, Genre.name, CriticRating.id, CriticRating.movie_id, CriticRating.type, CriticRating.score, CriticRating.rating, AlternateId.id, AlternateId.movie_id, AlternateId.name, AlternateId.value, Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link, Link.id, Link.movie_id, Link.type, Link.url FROM Movie AS Movie 
LEFT JOIN `Character` AS `Character` ON Movie.id=Character.movie_id 
LEFT JOIN Actor AS Cast ON Character.actor_id=Cast.id 
LEFT JOIN MovieDirector AS MovieDirector ON MovieDirector.movie_id=Movie.id 
LEFT JOIN Director AS Director ON Director.id=MovieDirector.director_id 
LEFT JOIN Genre AS Genre ON Genre.movie_id=Movie.id 
LEFT JOIN Poster AS Poster ON Poster.movie_id=Movie.id 
LEFT JOIN CriticRating AS CriticRating ON CriticRating.movie_id=Movie.id 
LEFT JOIN AlternateID AS AlternateId ON AlternateId.movie_id=Movie.id 
LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id
LEFT JOIN Link AS Link ON Link.movie_id=Movie.id 
WHERE Movie.id=771247277;
SHOW WARNINGS;
SHOW PROFILES;
SHOW SESSION STATUS LIKE 'ha%';

EXPLAIN EXTENDED
SELECT STRAIGHT_JOIN
	Link.id, Link.movie_id, Link.type, Link.url,
	Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, 
	Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link
FROM Link AS Link
LEFT JOIN Movie AS Movie ON Link.movie_id=Movie.id  
LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id
WHERE Movie.id=771247277;

EXPLAIN
SELECT COUNT(*) FROM Movie WHERE id=771308378;

EXPLAIN
SELECT Cache.id, Cache.hash, Cache.request, Cache.response, 
Cache.time FROM Cache AS Cache WHERE Cache.hash=1951908210;

EXPLAIN EXTENDED
SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, Character.id, Character.movie_id, Character.actor_id, Character.name, Cast.id, Cast.name, MovieDirector.director_id, MovieDirector.movie_id, Director.id, Director.name, Genre.id, Genre.movie_id, Genre.name, CriticRating.id, CriticRating.movie_id, CriticRating.type, CriticRating.score, CriticRating.rating, AlternateId.id, AlternateId.movie_id, AlternateId.name, AlternateId.value, Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link, Link.id, Link.movie_id, Link.type, Link.url FROM Movie AS Movie LEFT JOIN `Character` AS `Character` ON Movie.id=Character.movie_id LEFT JOIN Actor AS Cast ON Character.actor_id=Cast.id LEFT JOIN MovieDirector AS MovieDirector ON MovieDirector.movie_id=Movie.id LEFT JOIN Director AS Director ON Director.id=MovieDirector.director_id LEFT JOIN Genre AS Genre ON Genre.movie_id=Movie.id LEFT JOIN Poster AS Poster ON Poster.movie_id=Movie.id LEFT JOIN CriticRating AS CriticRating ON CriticRating.movie_id=Movie.id LEFT JOIN AlternateID AS AlternateId ON AlternateId.movie_id=Movie.id LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id LEFT JOIN Link AS Link ON Link.movie_id=Movie.id 
WHERE Movie.title LIKE "%A L%";
SHOW WARNINGS;




SELECT
     `Movie`.id, `Movie`.title,
     `Character`.id, `Character`.movie_id, `Character`.actor_id,
	 `Character`.`name`
 FROM `Movie` AS `Movie`
 LEFT JOIN `Character` AS `Character` ON `Movie`.id=`Character`.movie_id
 WHERE `Movie`.id=771308378;

SELECT * 
 FROM `Movie` AS `Movie`
 LEFT JOIN `Character` AS `Character` ON `Movie`.`id`=`Character`.`movie_id`
 WHERE `Movie`.`id`=771308378;

SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, Character.id, Character.movie_id, Character.actor_id, Character.name, Cast.id, Cast.name, MovieDirector.director_id, MovieDirector.movie_id, Director.id, Director.name, Genre.id, Genre.movie_id, Genre.name, CriticRating.id, CriticRating.movie_id, CriticRating.type, CriticRating.score, CriticRating.rating, AlternateId.id, AlternateId.movie_id, AlternateId.name, AlternateId.value, Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link, Link.id, Link.movie_id, Link.type, Link.url FROM Movie AS Movie
 LEFT JOIN `Character` AS `Character` ON Movie.id=Character.movie_id LEFT JOIN Actor AS Cast ON Character.actor_id=Cast.id LEFT JOIN MovieDirector AS MovieDirector ON MovieDirector.movie_id=Movie.id LEFT JOIN Director AS Director ON Director.id=MovieDirector.director_id LEFT JOIN Genre AS Genre ON Genre.movie_id=Movie.id LEFT JOIN Poster AS Poster ON Poster.movie_id=Movie.id LEFT JOIN CriticRating AS CriticRating ON CriticRating.movie_id=Movie.id LEFT JOIN AlternateID AS AlternateId ON AlternateId.movie_id=Movie.id LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id LEFT JOIN Link AS Link ON Link.movie_id=Movie.id WHERE Movie.id=771308378 LIMIT 1;

INSERT INTO `movies`.`UserRating` (`user_id`, `movie_id`, `value`) 
VALUES ('3', '9408', '2');

SELECT * FROM Movie WHERE id=9408;

EXPLAIN EXTENDED
SELECT Movie.id, Movie.title, Movie.year, Movie.timeline, Movie.runtime, Movie.mpaa_rating, Movie.users_rating_score, Movie.studio, Movie.critics_consensus, Movie.synopsis, Character.id, Character.movie_id, Character.actor_id, Character.name, Cast.id, Cast.name, MovieDirector.director_id, MovieDirector.movie_id, Director.id, Director.name, Genre.id, Genre.movie_id, Genre.name, CriticRating.id, CriticRating.movie_id, CriticRating.type, CriticRating.score, CriticRating.rating, AlternateId.id, AlternateId.movie_id, AlternateId.name, AlternateId.value, Clip.id, Clip.movie_id, Clip.duration, Clip.thumbnail, Clip.link, Link.id, Link.movie_id, Link.type, Link.url FROM Movie AS Movie 
LEFT JOIN `Character` AS `Character` ON Movie.id=Character.movie_id 
LEFT JOIN Actor AS Cast ON Character.actor_id=Cast.id 
LEFT JOIN MovieDirector AS MovieDirector ON MovieDirector.movie_id=Movie.id 
LEFT JOIN Director AS Director ON Director.id=MovieDirector.director_id 
LEFT JOIN Genre AS Genre ON Genre.movie_id=Movie.id 
LEFT JOIN Poster AS Poster ON Poster.movie_id=Movie.id 
LEFT JOIN CriticRating AS CriticRating ON CriticRating.movie_id=Movie.id 
LEFT JOIN AlternateID AS AlternateId ON AlternateId.movie_id=Movie.id 
LEFT JOIN Clip AS Clip ON Clip.movie_id=Movie.id
LEFT JOIN Link AS Link ON Link.movie_id=Movie.id 
WHERE Movie.title LIKE "%ala%";

EXPLAIN
SELECT 
    Director.id, Director.name
FROM
    Director AS Director
WHERE
    Director.name = 'Francis Lawrence'
LIMIT 1;

EXPLAIN
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Jennifer Lawrence'
LIMIT 1;