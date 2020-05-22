use db;

TRUNCATE TABLE `establecimiento`;

INSERT INTO `establecimiento` (`id`,`capacidad`,`ocupacion`,`nombre`,`responsable`,`ubicacion`,`zona`) 
VALUES 
(1,1000,500,"Aliquet Industries","Armando","61.22679, -116.461","ZONA C"),
(2,2000,300,"Donec Nibh Quisque Limited","Armando","77.66838, 135.06326","ZONA A"),
(3,3000,100,"Non LLC","Julian","57.7117, 18.61417","ZONA C"),
(4,4000,200,"Urna Foundation","Agustin","-4.76349, 98.13647","ZONA A"),
(5,1000,300,"Torquent Consulting","Agustin","-63.69961, 132.81188","ZONA C"),
(6,5000,400,"Metus PC","Sebastian","39.71679, 177.40042","ZONA D"),
(7,3000,200,"Aenean Incorporated","Sebastian","-30.33961, 31.16367","ZONA C"),
(8,1000,100,"Rhoncus Company","Armando","-12.16646, -49.08455","ZONA C"),
(9,2000,500,"Rhoncus Id Mollis Ltd","Agustin","-52.19993, -28.86915","ZONA C"),
(10,4000,200,"Sem Consulting","Julian","1.64948, 106.37695","ZONA B");