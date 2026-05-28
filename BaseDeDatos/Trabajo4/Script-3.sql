CREATE TABLE empleados (
    documento VARCHAR(8) PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30),
    sexo CHAR(1),
    fechaingreso DATE,
    fechanacimiento DATE,
    sueldo NUMERIC(12,2),
    hijos SMALLINT
);

INSERT INTO empleados (documento, nombre, apellido, fechaingreso, sexo, fechanacimiento, sueldo, hijos) 
VALUES
('100', 'Ana', 'Alvarez', '2000-01-15', 'F', '1975-05-10', 1000000, 2),
('200', 'Beto', 'Bermúdez', '2001-02-20', 'M', '1980-08-15', 2000000, 1),
('300', 'Carmen', 'Cruz', '2002-03-25', 'F', '1975-11-30', 3000000, 3),
('400', 'David', 'Díaz', '2003-04-30', 'M', '1981-02-20', 4000000, 0),
('500', 'Elena', 'Espinosa', '2004-05-05', 'F', '1982-07-25', 5000000, 4),
('600', 'Fernando', 'Fernández', '2005-06-10', 'M', '1979-12-10', 6000000, 5),
('700', 'Gabriela', 'García', '2006-07-15', 'F', '1983-03-05', 7000000, 6),
('800', 'Héctor', 'Hernández', '2007-08-20', 'M', '1984-06-12', 8000000, 1),
('900', 'Isabel', 'Ibarra', '2008-09-25', 'F', '1985-09-18', 9000000, 2),
('1000', 'Jorge', 'Jiménez', '2009-10-30', 'M', '1986-11-22', 10000000, 3);



-- La empresa paga un subsidio adicional por hijos a cargo. para un sueldo básico menor o igual a $5.000.000 el subsidio familiar por hijo es de $300.000, 
--para un sueldo superior, el monto es de $150.000 por hijo. Haga una vista llamada liquida_Nomina_hijos donde se muestre el nombre y apellido del colaborador, 
--el sueldo básico, la cantidad de hijos a cargo, el valor del salario por hijo, el valor total del Subsidio familiar y el sueldo final con el salario familiar incluido.
-- De todos los colaboradores con hijos a cargo.


-- NOTA: En PostgreSQL el IF no funciona directamente dentro de un SELECT como en MySQL.IF solo se usa dentro de funciones o bloques
CREATE VIEW liquida_nomina_hijos AS
SELECT 
    CONCAT(nombre, ' ', apellido) AS "Colaborador",
    sueldo AS "Sueldo básico",
    hijos AS "Hijos a cargo",

    CASE 
        WHEN sueldo <= 5000000 THEN 300000
        ELSE 150000
    END AS "Salario por hijo",

    CASE 
        WHEN sueldo <= 5000000 THEN 300000 * hijos
        ELSE 150000 * hijos
    END AS "Subsidio familiar",

    CASE 
        WHEN sueldo <= 5000000 
            THEN sueldo + (300000 * hijos)
        ELSE sueldo + (150000 * hijos)
    END AS "Sueldo final"

FROM empleados
WHERE hijos > 0
ORDER BY nombre;

-- 1. Contar Pañoletas y Pañuelos para diciembre
create view pañuelos_pañoletas as
SELECT 
    COUNT(
        CASE 
            WHEN sexo = 'F' THEN 1
        END
    ) AS "Pañoletas",

    COUNT(
        CASE 
            WHEN sexo = 'M' THEN 1
        END
    ) AS "Pañuelos"

FROM empleados;


-- 2. Contar años de servicio para colaboradores que ingresaron en enero
create view anio_servicio as
SELECT 
    nombre,
    apellido,
    DATE_PART('year', AGE(CURRENT_DATE, fechaingreso)) 
    AS "Años de servicio"

FROM empleados
WHERE EXTRACT(MONTH FROM fechaingreso) = 1;


-- 3. Calcular subsidio familiar y sueldo final para todos los colaboradores
SELECT 
    nombre,
    apellido,
    sueldo,

    CASE
        WHEN sueldo <= 5000000 THEN hijos * 300000
        ELSE hijos * 150000
    END AS salario_familiar,

    CASE
        WHEN sueldo <= 5000000 
            THEN sueldo + (hijos * 300000)
        ELSE sueldo + (hijos * 150000)
    END AS sueldo_final

FROM empleados;

































