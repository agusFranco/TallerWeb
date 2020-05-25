use db;

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE `zona`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `zona` (`id`,`nombre`,`puntaje`) 
VALUES 
(1,"ZONA A", 90),
(2,"ZONA B", 50),
(3,"ZONA C", 30),
(4,"ZONA D", 20),
(5,"ZONA E", 10),