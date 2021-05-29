-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-05-05 16:07:16.294

-- tables
-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int NOT NULL AUTO_INCREMENT COMMENT 'Este campo es el identificador de cada cliente registrado.',
    NOMCLI varchar(50) NULL COMMENT 'Contiene los nombres del cliente',
    APECLI varchar(50) NULL COMMENT 'Contiene los apellidos del cliente',
    DNICLI char(8) NULL COMMENT 'Contiene el número de DNI del cliente.',
    CONSTRAINT CLIENTE_pk PRIMARY KEY (IDCLI)
) COMMENT 'Esta tabla contiene toda la información de los clientes.';

-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    IDPRO int NOT NULL AUTO_INCREMENT COMMENT 'Contiene el identificador de cada producto.',
    NOMPRO varchar(50) NULL COMMENT 'Contiene la descripción del producto.',
    PREPRO decimal(10,2) NULL COMMENT 'Contiene el precio unitario en soles peruanos.',
    FECREGPRO date NOT NULL,
    CONSTRAINT PRODUCTO_pk PRIMARY KEY (IDPRO)
) COMMENT 'Contiene toada la información de los productos que se va a vender.';

-- Table: VENTA
CREATE TABLE VENTA (
    IDVEN int NOT NULL AUTO_INCREMENT COMMENT 'Contiene el identificador de cada venta teniendo en cuenta que el código esta conformado ',
    FECVEN date NULL COMMENT 'Contiene la fecha en que se realizó la venta.',
    FECENTVEN date NULL,
    IDCLI int NOT NULL,
    TOTVEN decimal(10,2) NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY (IDVEN)
) COMMENT 'Contiene los datos referentes al cliente y fecha de venta.';

-- Table: VENTA_DETALLE
CREATE TABLE VENTA_DETALLE (
    IDVENDET int NOT NULL AUTO_INCREMENT COMMENT 'Contiene el identificador de cada producto vendido.',
    CANVENDET int NOT NULL,
    PREVENDET int NOT NULL,
    IDPRO int NOT NULL,
    IDVEN int NOT NULL,
    CONSTRAINT VENTA_DETALLE_pk PRIMARY KEY (IDVENDET)
) COMMENT 'Contiene de productos vendidos a los clientes.';

-- foreign keys
-- Reference: VENTA_CLIENTE (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_CLIENTE FOREIGN KEY VENTA_CLIENTE (IDCLI)
    REFERENCES CLIENTE (IDCLI);

-- Reference: VENTA_DETALLE_PRODUCTO (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_PRODUCTO FOREIGN KEY VENTA_DETALLE_PRODUCTO (IDPRO)
    REFERENCES PRODUCTO (IDPRO);

-- Reference: VENTA_DETALLE_VENTA (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_VENTA FOREIGN KEY VENTA_DETALLE_VENTA (IDVEN)
    REFERENCES VENTA (IDVEN);

-- End of file.

