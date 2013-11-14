START TRANSACTION;
INSERT INTO Movie (id, title, year, timeline, runtime, mpaa_rating, 
	users_rating_score, studio, critics_consensus, synopsis) 
	VALUES (771250004, 'The Hunger Games: Catching Fire', '2013', 
	'OTHER', 146, 'PG-13', 0.0, 'Lionsgate Films', null, 
	'THE HUNGER GAMES: CATCHING FIRE begins as Katniss Everdeen has returned home safe after winning the 74th Annual Hunger Games along with fellow tribute Peeta Mellark. Winning means that they must turn around and leave their family and close friends, embarking on a "Victor\'s Tour" of the districts. Along the way Katniss senses that a rebellion is simmering, but the Capitol is still very much in control as President Snow prepares the 75th Annual Hunger Games (The Quarter Quell) - a competition that could change Panem forever. (c) Lionsgate'
	);
INSERT INTO `Release` (movie_id, type, release_date) 
	VALUES (771250004, 'THEATER', '2013-11-22'), 
		   (771250004, 'DVD', '1001-01-01');
INSERT INTO `Poster` (movie_id, type, url) 
	VALUES (771250004, 'THUMBNAIL', 
	'http://content7.flixster.com/movie/11/17/38/11173861_mob.jpg'), 
	(771250004, 'PROFILE', 
	'http://content7.flixster.com/movie/11/17/38/11173861_pro.jpg'), 
	(771250004, 'DETAILED', 
	'http://content7.flixster.com/movie/11/17/38/11173861_det.jpg'), 
	(771250004, 'ORIGINAL', 
	'http://content7.flixster.com/movie/11/17/38/11173861_ori.jpg');
INSERT INTO `CriticRating` (movie_id, type, score, rating) 
	VALUES (771250004, 'CRITICS', 92, 'Fresh'), 
		   (771250004, 'AUDIENCE', 99, null);
INSERT INTO `AlternateID` (movie_id, name, value) 
	VALUES (771250004, 'IMDB', '1951264');
INSERT INTO `Link` (movie_id, type, url) 
	VALUES (771250004, 'SELF', 
	'http://api.rottentomatoes.com/api/public/v1.0/movies/771250004.json'), 
	(771250004, 'ALTERNATE', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/'), 
	(771250004, 'CAST', 
	'http://api.rottentomatoes.com/api/public/v1.0/movies/771250004/cast.json'), 
	(771250004, 'CLIPS', 
	'http://api.rottentomatoes.com/api/public/v1.0/movies/771250004/clips.json'), 
	(771250004, 'REVIEWS', 
	'http://api.rottentomatoes.com/api/public/v1.0/movies/771250004/reviews.json'), 
	(771250004, 'SIMILAR', 
	'http://api.rottentomatoes.com/api/public/v1.0/movies/771250004/similar.json');
INSERT INTO `Genre` (movie_id, name) 
	VALUES (771250004, 'Action & Adventure');
INSERT INTO `Genre` (movie_id, name) 
	VALUES (771250004, 'Science Fiction & Fantasy');
SELECT 
    Director.id, Director.name
FROM
    Director AS Director
WHERE
    Director.name = 'Francis Lawrence'
LIMIT 1;
INSERT INTO `MovieDirector` (director_id, movie_id) 
	VALUES (2135, 771250004);
INSERT INTO `Review` (movie_id, critic, date, freshness, publication, quote, link, country) 
	VALUES (771250004, 'Cath Clarke', '2013-11-12', 'fresh', 'Time Out', 
	'Like Katniss ducking a poison-tip arrow, the keepers of Suzanne Collins\'s trilogy of fantasy novels have dodged the perils of the sloppy second franchise film.', 
	'http://www.timeout.com/london/film/the-hunger-games-catching-fire', 
	'us');
INSERT INTO `Review` (movie_id, critic, date, freshness, publication, quote, link, country) 
	VALUES (771250004, 'Todd McCarthy', '2013-11-12', 'fresh', 'Hollywood Reporter', 
	'A safe, serviceable, carefully crafted action drama in which the subversive seeds planted in the first story take welcome root.', 
	'http://www.hollywoodreporter.com/movie/hunger-games-catching-fire/review/655231', 
	'us');
INSERT INTO `Review` (movie_id, critic, date, freshness, publication, quote, link, country) 
	VALUES (771250004, 'Peter Debruge', '2013-11-12', 'fresh', 'Variety', 
	'"Catching Fire" makes for rousing entertainment in its own right, leaving fans riled and ready to storm the castle.', 
	'http://variety.com/2013/film/reviews/film-review-the-hunger-games-catching-fire-1200820552/', 
	'us');
INSERT INTO `Review` (movie_id, critic, date, freshness, publication, quote, link, country) 
	VALUES (771250004, 'Alonso Duralde', '2013-11-11', 'fresh', 'The Wrap', 
	'Dismiss it as a popcorn movie if you must, but at least they\'ve bothered to serve it with real butter and truffle salt.', 
	'http://www.thewrap.com/hunger-games-catching-fire-review-jennifer-lawrence-katniss-everdeen', 
	'us');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 152, 
	'http://content.internetvideoarchive.com/content/photos/7424/779912_066.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11177818');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 143, 
	'http://content.internetvideoarchive.com/content/photos/8533/913505_019.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11184178');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 145, '', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11177799');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 152, 
	'http://content.internetvideoarchive.com/content/photos/8420/779912_093.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11184258');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 38, 
	'http://content.internetvideoarchive.com/content/photos/8553/360953_025.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11184804');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 40, 
	'http://content.internetvideoarchive.com/content/photos/8552/693742_017.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11184805');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 121, 
	'http://content.internetvideoarchive.com/content/photos/8505/263447_079.jpg', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11183271');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 152, '', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11180974');
INSERT INTO Clip (movie_id, duration, thumbnail, link) 
	VALUES (771250004, 67, '', 
	'http://www.rottentomatoes.com/m/the_hunger_games_catching_fire/trailers/11184352');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Jennifer Lawrence'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (770800260, 'Jennifer Lawrence');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770800260, 771250004, 'Katniss Everdeen');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Josh Hutcherson'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162654356, 771250004, 'Peeta Mellark');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Liam Hemsworth'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (770833479, 'Liam Hemsworth');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770833479, 771250004, 'Gale Hawthorne');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Elizabeth Banks'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162653584, 771250004, 'Effie Trinket');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Stanley Tucci'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162661152, 771250004, 'Caesar Flickerman');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Woody Harrelson'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162654250, 771250004, 'Haymitch Abernathy');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Jena Malone'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162655292, 771250004, 'Johanna Mason');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Willow Shields'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771101778, 'Willow Shields');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771101778, 771250004, 'Primrose Everdeen');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Paula Malcolmson'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (770899889, 'Paula Malcolmson');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770899889, 771250004, 'Katniss and Primrose\'s Mother');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Lenny Kravitz'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770709278, 771250004, 'Cinna');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Donald Sutherland'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162655293, 771250004, 'President Snow');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Philip Seymour Hoffman'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162654287, 771250004, 'Plutarch Heavensbee');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Amanda Plummer'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162727116, 771250004, 'Wiress');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Lynn Cohen'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770685888, 771250004, 'Mags');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Patrick St. Esprit'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770805048, 771250004, 'Romulus Thread');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Meta Golding'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (770801622, 'Meta Golding');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770801622, 771250004, 'Enobaria');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Bruno Gunn'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771416574, 'Bruno Gunn');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771416574, 771250004, 'Brutus');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Alan Ritchson'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (770760819, 'Alan Ritchson');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (770760819, 771250004, 'Gloss');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'E. Robert Mitchell'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771416739, 'E. Robert Mitchell');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771416739, 771250004, 'Chaff');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Maria Howell'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771051625, 'Maria Howell');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771051625, 771250004, 'Seeder');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Stephanie Leigh Schlund'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771417339, 'Stephanie Leigh Schlund');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771417339, 771250004, 'Cashmere');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Sam Claflin'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771073196, 'Sam Claflin');
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (771073196, 771250004, 'Finnick');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Jeffrey Wright'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162653065, 771250004, 'Beetee');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Wes Bentley'
LIMIT 1;
INSERT INTO `Character` (actor_id, movie_id, name) 
	VALUES (162670889, 771250004, 'Seneca Crane');
SELECT 
    Cast.id, Cast.name
FROM
    Actor AS Cast
WHERE
    Cast.name = 'Jeniffer Lawrence'
LIMIT 1;
INSERT INTO `Actor` (id, name) 
	VALUES (771466993, 'Jeniffer Lawrence');
COMMIT;