DELIMITER $$
CREATE PROCEDURE `ClearWeekOldCacheProcedure` ()
BEGIN
	SET SQL_SAFE_UPDATES=0;
	DELETE FROM `Cache`
	WHERE `time` < (NOW() - INTERVAL 7 DAY);
END$$
DELIMITER ;