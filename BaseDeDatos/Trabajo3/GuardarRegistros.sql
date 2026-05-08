CREATE TABLE socios(
    documento CHAR(8) PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

CREATE TABLE deportes(
    codigo INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(30)
);

 CREATE TABLE inscritos(
    documento CHAR(8),
    codigodeporte INT,
    anio INT NOT NULL,
    cuota CHAR(1),
    PRIMARY KEY(documento, codigodeporte, anio),
    FOREIGN KEY(documento) REFERENCES socios(documento),
    FOREIGN KEY(codigodeporte) REFERENCES deportes(codigo)
);

INSERT INTO socios VALUES
('300','Celio Perez'),
('100','Ana Garcia'),
('200','Bernardo Fuentes'),
('400','Delio Molina');

INSERT INTO deportes(nombre) VALUES
('Tenis'),
('Natacion'),
('Basquet'),
('Voley');


INSERT INTO inscritos VALUES
('300',1,2020,'s'),
('300',1,2021,'n'),
('300',2,2020,'s'),
('200',1,2020,'n'),
('200',2,2021,'s'),
('400',1,2020,'n'),
('400',1,2021,'s'),
('400',3,2021,'n');

-- 1. Cree una tabla llamada deudores que tiene el año, nombre socio, y deporte

CREATE TABLE deudores(
    anio INT,
    nombre_socio VARCHAR(30),
    deporte VARCHAR(30)
);
-- 2. Luego haga una consulta de manera que permita saber que socios y que deporte NO han pagado la cuota en el año 2020
-- PRUEBA
SELECT i.anio,
       s.nombre AS nombre_socio,
       d.nombre AS deporte
FROM inscritos i
JOIN socios s ON i.documento = s.documento
JOIN deportes d ON i.codigodeporte = d.codigo
WHERE i.anio = 2020
AND i.cuota = 'n';	

-- ----- VISTA .------
CREATE VIEW vista_deudores_2020 AS
SELECT i.anio,
       s.nombre AS nombre_socio,
       d.nombre AS deporte
FROM inscritos i
JOIN socios s ON i.documento = s.documento
JOIN deportes d ON i.codigodeporte = d.codigo
WHERE i.anio = 2020
AND i.cuota = 'n';

-- 3. Verifique que los datos son los correctos
SELECT * FROM vista_deudores_2020;

-- 4. Luego liste los deudores del año 2021
-- PRUEBA
SELECT i.anio,
       s.nombre AS nombre_socio,
       d.nombre AS deporte
FROM inscritos i
JOIN socios s ON i.documento = s.documento
JOIN deportes d ON i.codigodeporte = d.codigo
WHERE i.anio = 2021
AND i.cuota = 'n';

-- ---- VISTA ----
CREATE VIEW vista_deudores_2021 AS
SELECT i.anio,
       s.nombre AS nombre_socio,
       d.nombre AS deporte
FROM inscritos i
JOIN socios s ON i.documento = s.documento
JOIN deportes d ON i.codigodeporte = d.codigo
WHERE i.anio = 2021
AND i.cuota = 'n';

-- VER VISTA
SELECT * FROM vista_deudores_2021;















