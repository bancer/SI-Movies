CREATE USER 'moviehunter'@'localhost' 
	IDENTIFIED BY '1234';
GRANT SELECT, INSERT, UPDATE, LOCK TABLES, EXECUTE 
	ON `movies`.* 
	TO 'moviehunter'@'localhost' 
	IDENTIFIED BY '1234';