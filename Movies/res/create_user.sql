CREATE USER 'moviehunter'@'localhost' 
	IDENTIFIED BY 'gfoubvTDPNW85308*%_#!';
GRANT SELECT, INSERT, UPDATE, DELETE, LOCK TABLES, EXECUTE 
	ON `movies`.* 
	TO 'moviehunter'@'localhost' 
	IDENTIFIED BY 'gfoubvTDPNW85308*%_#!';