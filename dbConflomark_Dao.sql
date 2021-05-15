--CREAR BASE DE DATOS dbCONFLOMARK
CREATE DATABASE dbCONFLOMARK
GO

--USAR BASE DE DATOS dbCONFLOMARK
USE dbCONFLOMARK
GO


--CREAR TABLA CLIENTE
CREATE TABLE CLIENTE (
	IDCLI INT IDENTITY(1,1) NOT NULL,
	NOMCLI VARCHAR(50) NOT NULL,
	APECLI VARCHAR(50) NOT NULL,
	DIRCLI VARCHAR(80) NOT NULL,
	CELCLI CHAR(9) NOT NULL,
	CODUBI CHAR(6) NOT NULL,
	ESTCLI CHAR(1) NOT NULL,
	CONSTRAINT CLIENTE_pk PRIMARY KEY(IDCLI)
);

-- INSERTAR REGISTROS DE CLIENTE
INSERT INTO CLIENTE
(NOMCLI, APECLI, DIRCLI, CELCLI, CODUBI, ESTCLI)
VALUES
('Juan Gabriel', 'Condori Jara', 'Nuevo Hualcara Mz:K Lt:3', '940460321', '010101', 'A'),
('Sergio Danilo', 'Flores Tadeo', 'San Vicente Mz:J Lt:5', '948695767', '010102', 'A'),
('Josue Miguel', 'Alarcon Asto', 'Santa Rosa Mz:T Lt:7', '998475645', '010103', 'A'),
('Maria Daniela', 'Díaz Romero', 'Imperial Mz:C Lt:9', '903489256', '010104', 'A'),
('Diego Alvaro', 'Galvez Quispe', 'Las Malvinas Mz:V Lt:2', '919803481', '010105', 'A'),
('Lucia Dayana', 'Cuzcano Cuya', 'Santa Rosa Mz:E Lt:4', '989347622', '010106', 'A'),
('Victor Raúl', 'Quispe Manturano', 'San Luis Mz:G Lt:6', '998143568', '010107', 'A'),
('Sofia Carmen', 'Huamán Vásquez', 'San Antonio Mz:R Lt:3', '932457821', '010108', 'A'),
('Kevin Rodrigo', 'Guerra Cáceres', 'Nuevo Imperial Mz:F Lt:6', '998436512', '010109', 'A'),
('Brayan Enrique', 'Vicente Manrique', 'Mala Mz:N Lt:8', '956734563', '010110', 'A');
GO


--CREAR TABLA PRODUCTO
CREATE TABLE PRODUCTO (
	IDPRO INT IDENTITY(1,1) NOT NULL,
	NOMPRO VARCHAR(50),
	MARPRO VARCHAR(50),
	CODFAM CHAR(6),
	DESPRO VARCHAR(80),
	PREPRO DECIMAL(3,2),
	ESTPRO CHAR(1),
	CONSTRAINT PRODUCTO_pk PRIMARY KEY(IDPRO)
);

-- INSERTAR REGISTROS DE PRODUCTO
INSERT INTO PRODUCTO
(NOMPRO, MARPRO, CODFAM, DESPRO, PREPRO, ESTPRO)
VALUES
('Doritos', 'Doritos', '010701', 'Doritos sabor queso', '3.50', 'A'),
('Aceite', 'Primor', '010101', 'Aceite de cocina', '5.50', 'A'),
('Arroz', 'Tondero', '010102', 'Arroz blanco', '4.50', 'A'),
('Azúcar', 'Dulfina', '010212', 'Azúcar morena', '3.50', 'A'),
('Gaseosa', 'Coca cola', '010802', 'Gaseosa sin azúcar', '9.00', 'A'),
('Gaseosa', 'Inka cola', '010802', 'Gaseosa sabor original', '9.50', 'A'),
('Galleta', 'Picaras', '010702', 'Galleta Sabor fresa', '1.50', 'A'),
('Chocolate', 'Vizzio', '010703', 'Chocolate con almendras', '4.00', 'A'),
('Mantequilla', 'Manty', '010302', 'Mantequilla clásica', '3.50', 'A'),
('Yogurt', 'Gloria', '010305', 'Yogurt sabor fresa', '7.00', 'A');
GO


-- CREAR TABLA FAMILIA
CREATE TABLE FAMILIA (
	CODFAM CHAR(6),
	NOMFAM VARCHAR(50),
	SUBFAM VARCHAR(50),
	CONSTRAINT FAMILIA_pk PRIMARY KEY(CODFAM)
);

-- INSERTAR REGISTROS DE FAMILIA
INSERT INTO FAMILIA
(CODFAM, NOMFAM, SUBFAM)
VALUES
('010101','Abarrotes','Aceites'),
('010102','Abarrotes','Arroz'),
('010103','Abarrotes','Pastas'),
('010104','Abarrotes','Salsas'),
('010105','Abarrotes','Conservas'),
('010106','Abarrotes','Menestras'),
('010107','Abarrotes','Purés'),
('010108','Abarrotes','Sopas'),
('010109','Abarrotes','Harinas'),
('010110','Abarrotes','Condimentos'),
('010111','Abarrotes','Especias'),
('010212','Desayuno','Azúcar'),
('010213','Desayuno','Cafés'),
('010214','Desayuno','Infusiones'),
('010215','Desayuno','Hierbas'),
('010216','Desayuno','Cereales'),
('010217','Desayuno','Mermeladas'),
('010218','Desayuno','Mieles'),
('010219','Desayuno','Panes'),
('010301','Lácteos y huevos','Huevos'),
('010302','Lácteos y huevos','Mantequillas'),
('010303','Lácteos y huevos','Margarinas'),
('010304','Lácteos y huevos','Yogurt familiar'),
('010305','Lácteos y huevos','Yogurt personal'),
('010306','Lácteos y huevos','Leches líquidas'),
('010307','Lácteos y huevos','Bebidas especiales'),
('010401','Quesos y fiambres','Quesos'),
('010402','Quesos y fiambres','Jamonadas'),
('010403','Quesos y fiambres','Salchichas'),
('010501','Helados y alimentos congelados','Helados'),
('010502','Helados y alimentos congelados','Amburguesas'),
('010601','Pizzas y platos preparados','Pizzas'),
('010602','Pizzas y platos preparados','Sandwiches'),
('010603','Pizzas y platos preparados','Empanadas'),
('010604','Pizzas y platos preparados','Pasteles'),
('010701','Dulces y snacks','Snacks'),
('010702','Dulces y snacks','Galletas'),
('010703','Dulces y snacks','Chocolates'),
('010704','Dulces y snacks','Caramelos'),
('010705','Dulces y snacks','Chicles'),
('010706','Dulces y snacks','Frutos secos'),
('010801','Bebidas','Agua'),
('010802','Bebidas','Gaseosas'),
('010803','Bebidas','Tés'),
('010804','Bebidas','Jugos'),
('010805','Bebidas','Energizantes'),
('010806','Bebidas','Rehidratantes'),
('010901','Licores','Cervezas'),
('010902','Licores','Vinos'),
('010903','Licores','Piscos'),
('010904','Licores','Whiskys'),
('010905','Licores','Ron');
GO


-- CREAR TABLA UBIGEO
CREATE TABLE UBIGEO (
	CODUBI CHAR(6),
	DEPUBI VARCHAR(50),
	PROUBI VARCHAR(50),
	DISUBI VARCHAR(50),
	CONSTRAINT UBIGEO_pk PRIMARY KEY(CODUBI)
);

-- INSERTAR REGISTROS DE UBIGEO
INSERT INTO UBIGEO
(CODUBI, DEPUBI, PROUBI, DISUBI)
VALUES
('150101','LIMA','LIMA','LIMA'),
('150102','LIMA','LIMA','ANCON'),
('150103','LIMA','LIMA','ATE'),
('150104','LIMA','LIMA','BARRANCO'),
('150105','LIMA','LIMA','BRENA'),
('150106','LIMA','LIMA','CARABAYLLO'),
('150107','LIMA','LIMA','CHACLACAYO'),
('150108','LIMA','LIMA','CHORRILLOS'),
('150109','LIMA','LIMA','CIENEGUILLA'),
('150110','LIMA','LIMA','COMAS'),
('150111','LIMA','LIMA','EL AGUSTINO'),
('150112','LIMA','LIMA','INDEPENDENCIA'),
('150113','LIMA','LIMA','JESUS MARIA'),
('150114','LIMA','LIMA','LA MOLINA'),
('150115','LIMA','LIMA','LA VICTORIA'),
('150116','LIMA','LIMA','LINCE'),
('150117','LIMA','LIMA','LOS OLIVOS'),
('150118','LIMA','LIMA','LURIGANCHO'),
('150119','LIMA','LIMA','LURIN'),
('150120','LIMA','LIMA','MAGDALENA DEL MAR'),
('150121','LIMA','LIMA','MAGDALENA VIEJA'),
('150122','LIMA','LIMA','MIRAFLORES'),
('150123','LIMA','LIMA','PACHACAMAC'),
('150124','LIMA','LIMA','PUCUSANA'),
('150125','LIMA','LIMA','PUENTE PIEDRA'),
('150126','LIMA','LIMA','PUNTA HERMOSA'),
('150127','LIMA','LIMA','PUNTA NEGRA'),
('150128','LIMA','LIMA','RIMAC'),
('150129','LIMA','LIMA','SAN BARTOLO'),
('150130','LIMA','LIMA','SAN BORJA'),
('150131','LIMA','LIMA','SAN ISIDRO'),
('150132','LIMA','LIMA','SAN JUAN DE LURIGANCHO'),
('150133','LIMA','LIMA','SAN JUAN DE MIRAFLORES'),
('150134','LIMA','LIMA','SAN LUIS'),
('150135','LIMA','LIMA','SAN MARTIN DE PORRES'),
('150136','LIMA','LIMA','SAN MIGUEL'),
('150137','LIMA','LIMA','SANTA ANITA'),
('150138','LIMA','LIMA','SANTA MARIA DEL MAR'),
('150139','LIMA','LIMA','SANTA ROSA'),
('150140','LIMA','LIMA','SANTIAGO DE SURCO'),
('150141','LIMA','LIMA','SURQUILLO'),
('150142','LIMA','LIMA','VILLA EL SALVADOR'),
('150143','LIMA','LIMA','VILLA MARIA DEL TRIUNFO'),
('150201','LIMA','BARRANCA','BARRANCA'),
('150202','LIMA','BARRANCA','PARAMONGA'),
('150203','LIMA','BARRANCA','PATIVILCA'),
('150204','LIMA','BARRANCA','SUPE'),
('150205','LIMA','BARRANCA','SUPE PUERTO'),
('150301','LIMA','CAJATAMBO','CAJATAMBO'),
('150302','LIMA','CAJATAMBO','COPA'),
('150303','LIMA','CAJATAMBO','GORGOR'),
('150304','LIMA','CAJATAMBO','HUANCAPON'),
('150305','LIMA','CAJATAMBO','MANAS'),
('150401','LIMA','CANTA','CANTA'),
('150402','LIMA','CANTA','ARAHUAY'),
('150403','LIMA','CANTA','HUAMANTANGA'),
('150404','LIMA','CANTA','HUAROS'),
('150405','LIMA','CANTA','LACHAQUI'),
('150406','LIMA','CANTA','SAN BUENAVENTURA'),
('150407','LIMA','CANTA','SANTA ROSA DE QUIVES'),
('150501','LIMA','CANETE','SAN VICENTE DE CANETE'),
('150502','LIMA','CANETE','ASIA'),
('150503','LIMA','CANETE','CALANGO'),
('150504','LIMA','CANETE','CERRO AZUL'),
('150505','LIMA','CANETE','CHILCA'),
('150506','LIMA','CANETE','COAYLLO'),
('150507','LIMA','CANETE','IMPERIAL'),
('150508','LIMA','CANETE','LUNAHUANA'),
('150509','LIMA','CANETE','MALA'),
('150510','LIMA','CANETE','NUEVO IMPERIAL'),
('150511','LIMA','CANETE','PACARAN'),
('150512','LIMA','CANETE','QUILMANA'),
('150513','LIMA','CANETE','SAN ANTONIO'),
('150514','LIMA','CANETE','SAN LUIS'),
('150515','LIMA','CANETE','SANTA CRUZ DE FLORES'),
('150516','LIMA','CANETE','ZUNIGA'),
('150601','LIMA','HUARAL','HUARAL'),
('150602','LIMA','HUARAL','ATAVILLOS ALTO'),
('150603','LIMA','HUARAL','ATAVILLOS BAJO'),
('150604','LIMA','HUARAL','AUCALLAMA'),
('150605','LIMA','HUARAL','CHANCAY'),
('150606','LIMA','HUARAL','IHUARI'),
('150607','LIMA','HUARAL','LAMPIAN'),
('150608','LIMA','HUARAL','PACARAOS'),
('150609','LIMA','HUARAL','SAN MIGUEL DE ACOS'),
('150610','LIMA','HUARAL','SANTA CRUZ DE ANDAMARCA'),
('150611','LIMA','HUARAL','SUMBILCA'),
('150612','LIMA','HUARAL','VEINTISIETE DE NOVIEMBRE'),
('150701','LIMA','HUAROCHIRI','MATUCANA'),
('150702','LIMA','HUAROCHIRI','ANTIOQUIA'),
('150703','LIMA','HUAROCHIRI','CALLAHUANCA'),
('150704','LIMA','HUAROCHIRI','CARAMPOMA'),
('150705','LIMA','HUAROCHIRI','CHICLA'),
('150706','LIMA','HUAROCHIRI','CUENCA'),
('150707','LIMA','HUAROCHIRI','HUACHUPAMPA'),
('150708','LIMA','HUAROCHIRI','HUANZA'),
('150709','LIMA','HUAROCHIRI','HUAROCHIRI'),
('150710','LIMA','HUAROCHIRI','LAHUAYTAMBO'),
('150711','LIMA','HUAROCHIRI','LANGA'),
('150712','LIMA','HUAROCHIRI','LARAOS'),
('150713','LIMA','HUAROCHIRI','MARIATANA'),
('150714','LIMA','HUAROCHIRI','RICARDO PALMA'),
('150715','LIMA','HUAROCHIRI','SAN ANDRES DE TUPICOCHA'),
('150716','LIMA','HUAROCHIRI','SAN ANTONIO'),
('150717','LIMA','HUAROCHIRI','SAN BARTOLOME'),
('150718','LIMA','HUAROCHIRI','SAN DAMIAN'),
('150719','LIMA','HUAROCHIRI','SAN JUAN DE IRIS'),
('150720','LIMA','HUAROCHIRI','SAN JUAN DE TANTARANCHE'),
('150721','LIMA','HUAROCHIRI','SAN LORENZO DE QUINTI'),
('150722','LIMA','HUAROCHIRI','SAN MATEO'),
('150723','LIMA','HUAROCHIRI','SAN MATEO DE OTAO'),
('150724','LIMA','HUAROCHIRI','SAN PEDRO DE CASTA'),
('150725','LIMA','HUAROCHIRI','SAN PEDRO DE HUANCAYRE'),
('150726','LIMA','HUAROCHIRI','SANGALLAYA'),
('150727','LIMA','HUAROCHIRI','SANTA CRUZ DE COCACHACRA'),
('150728','LIMA','HUAROCHIRI','SANTA EULALIA'),
('150729','LIMA','HUAROCHIRI','SANTIAGO DE ANCHUCAYA'),
('150730','LIMA','HUAROCHIRI','SANTIAGO DE TUNA'),
('150731','LIMA','HUAROCHIRI','SANTO DOMINGO DE LOS OLLEROS'),
('150732','LIMA','HUAROCHIRI','SURCO'),
('150801','LIMA','HUAURA','HUACHO'),
('150802','LIMA','HUAURA','AMBAR'),
('150803','LIMA','HUAURA','CALETA DE CARQUIN'),
('150804','LIMA','HUAURA','CHECRAS'),
('150805','LIMA','HUAURA','HUALMAY'),
('150806','LIMA','HUAURA','HUAURA'),
('150807','LIMA','HUAURA','LEONCIO PRADO'),
('150808','LIMA','HUAURA','PACCHO'),
('150809','LIMA','HUAURA','SANTA LEONOR'),
('150810','LIMA','HUAURA','SANTA MARIA'),
('150811','LIMA','HUAURA','SAYAN'),
('150812','LIMA','HUAURA','VEGUETA'),
('150901','LIMA','OYON','OYON'),
('150902','LIMA','OYON','ANDAJES'),
('150903','LIMA','OYON','CAUJUL'),
('150904','LIMA','OYON','COCHAMARCA'),
('150905','LIMA','OYON','NAVAN'),
('150906','LIMA','OYON','PACHANGARA'),
('151001','LIMA','YAUYOS','YAUYOS'),
('151002','LIMA','YAUYOS','ALIS'),
('151003','LIMA','YAUYOS','AYAUCA'),
('151004','LIMA','YAUYOS','AYAVIRI'),
('151005','LIMA','YAUYOS','AZANGARO'),
('151006','LIMA','YAUYOS','CACRA'),
('151007','LIMA','YAUYOS','CARANIA'),
('151008','LIMA','YAUYOS','CATAHUASI'),
('151009','LIMA','YAUYOS','CHOCOS'),
('151010','LIMA','YAUYOS','COCHAS'),
('151011','LIMA','YAUYOS','COLONIA'),
('151012','LIMA','YAUYOS','HONGOS'),
('151013','LIMA','YAUYOS','HUAMPARA'),
('151014','LIMA','YAUYOS','HUANCAYA'),
('151015','LIMA','YAUYOS','HUANGASCAR'),
('151016','LIMA','YAUYOS','HUANTAN'),
('151017','LIMA','YAUYOS','HUANEC'),
('151018','LIMA','YAUYOS','LARAOS'),
('151019','LIMA','YAUYOS','LINCHA'),
('151020','LIMA','YAUYOS','MADEAN'),
('151021','LIMA','YAUYOS','MIRAFLORES'),
('151022','LIMA','YAUYOS','OMAS'),
('151023','LIMA','YAUYOS','PUTINZA'),
('151024','LIMA','YAUYOS','QUINCHES'),
('151025','LIMA','YAUYOS','QUINOCAY'),
('151026','LIMA','YAUYOS','SAN JOAQUIN'),
('151027','LIMA','YAUYOS','SAN PEDRO DE PILAS'),
('151028','LIMA','YAUYOS','TANTA'),
('151029','LIMA','YAUYOS','TAURIPAMPA'),
('151030','LIMA','YAUYOS','TOMAS'),
('151031','LIMA','YAUYOS','TUPE'),
('151032','LIMA','YAUYOS','VINAC'),
('151033','LIMA','YAUYOS','VITIS');
GO
































