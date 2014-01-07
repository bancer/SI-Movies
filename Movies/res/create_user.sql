CREATE USER 'moviehunter'@'localhost' 
	IDENTIFIED BY '1234';
GRANT SELECT, INSERT, UPDATE, DELETE, LOCK TABLES, EXECUTE 
	ON `movies`.* 
	TO 'moviehunter'@'localhost' 
	IDENTIFIED BY '1234';