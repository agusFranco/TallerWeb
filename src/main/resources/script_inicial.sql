use db;

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE `establecimiento`;
TRUNCATE TABLE `zona`;
TRUNCATE TABLE `insumo`;
TRUNCATE TABLE `responsable`;
TRUNCATE TABLE `tipoDistribucion`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `zona` (`id`,`nombre`,`puntaje`) VALUES 
(1,"ZONA A", 90),
(2,"ZONA B", 50),
(3,"ZONA C", 30),
(4,"ZONA D", 20),
(5,"ZONA E", 10);

INSERT INTO `tipodistribucion` (`id`,`nombre`) VALUES
(1,'Combinada'),
(2,'Ocupacion'),
(3,'Capacidad Total'),
(4,'Zona'),
(5,'Equitativa');



INSERT INTO `responsable` (`id`,`nombre`,`apellido`,`edad`,`titulo`,`zona_id`) VALUES 
(1,"Enric","Montoya",45,"Título de Médico Especialista",1),
(2,"Jacobo","Fuertes",35,"Gobernador de la ciudad",2),
(3,"Carlos","Alonso",60,"Gobernador de la ciudad",3),
(4,"Luis","Alonso",46,"Jefe de Hospital",1),
(5,"Gustavo","Montes",41,"Jefe de Hospital",2),
(6,"Antonio","Fuertes",37,"Título de Médico Especialista",5),
(7,"Alberto","Rivera",39,"Rector de Hospital",4),
(8,"Alberto","Ibañez",56,"Jefe de Hospital",4),
(9,"Omar","Fernandez",67,"Título de Médico Especialista",4),
(10,"Jose","Alonso",78,"Rector de Hospital",5);


INSERT INTO `establecimiento` (`id`,`capacidad`,`ocupacion`,`nombre`,`responsable_id`,`ubicacion`,`zona_id`) VALUES 
(1,1000,500,"Centro Médico ALBOR",1,"61.22679, -116.461", 1),
(2,2000,300,"Instituto VITA",2,"77.66838, 135.06326",1),
(3,3000,100,"Clínica del Prado",4,"57.7117, 18.61417",2),
(4,4000,200,"Hospital Provincial",5,"-4.76349, 98.13647",2),
(5,1000,300,"Sanatorio de los Arrayos",7,"-63.69961, 132.81188",3),
(6,5000,400,"Instituto Tucumano",6,"39.71679, 177.40042",3),
(7,3000,200,"Centro Asistencial Gierson",3,"-30.33961, 31.16367",4),
(8,1000,100,"Centro de Salud Almagro",10,"-12.16646, -49.08455",4),
(9,2000,500,"Hospital General Alvarez",9,"-52.19993, -28.86915",5),
(10,4000,200,"Hospital Piñero",8,"1.64948, 106.37695",5);

INSERT INTO `insumo` (`id`,`cantidad`,`nombre`,`tipo`,`precioUnidad`) VALUES 
(1,102,"Respiradores","Tipo B",3400),
(2,203,"Medicamentos","Tipo C",120),
(3,304,"Jeringas","Tipo A",110),
(4,400,"Tapa bocas","Tipo D",50),
(5,100,"Delantales","Tipo C",69),
(6,200,"Camas","Tipo F",2500),
(7,400,"Guantes","Tipo B",55);

