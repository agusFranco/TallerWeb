use db;

TRUNCATE TABLE `establecimiento`;

INSERT INTO `establecimiento` (`id`,`capacidad`,`ocupacion`,`nombre`,`responsable`,`ubicacion`,`zona_id`) 
VALUES 
(1,1000,500,"Aliquet Industries","Armando","61.22679, -116.461", 1),
(2,2000,300,"Donec Nibh Quisque Limited","Armando","77.66838, 135.06326",1),
(3,3000,100,"Non LLC","Julian","57.7117, 18.61417",2),
(4,4000,200,"Urna Foundation","Agustin","-4.76349, 98.13647",2),
(5,1000,300,"Torquent Consulting","Agustin","-63.69961, 132.81188",3),
(6,5000,400,"Metus PC","Sebastian","39.71679, 177.40042",3),
(7,3000,200,"Aenean Incorporated","Sebastian","-30.33961, 31.16367",4),
(8,1000,100,"Rhoncus Company","Armando","-12.16646, -49.08455",4),
(9,2000,500,"Rhoncus Id Mollis Ltd","Agustin","-52.19993, -28.86915",5),
(10,4000,200,"Sem Consulting","Julian","1.64948, 106.37695",5);