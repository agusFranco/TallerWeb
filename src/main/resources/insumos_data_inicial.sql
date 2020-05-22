use db;

TRUNCATE TABLE `insumo`;

INSERT INTO `insumo` (`id`,`cantidad`,`nombre`,`tipo`) 
VALUES 
(1,100,"Respiradores","Tipo B"),
(2,200,"Medicamentos","Tipo C"),
(3,300,"Jeringas","Tipo A"),
(4,400,"Tapa bocas","Tipo D"),
(5,100,"Delantales","Tipo C"),
(6,200,"Camas","Tipo F"),
(7,400,"Guantes","Tipo B");