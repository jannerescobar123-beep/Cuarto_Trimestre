CREATE DATABASE cultivamarket;
USE cultivamarket;

-- Tabla de Agricultores
CREATE TABLE Agricultores (
    AgricultorID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Region VARCHAR(50),
    AñoIngreso INT,
    tarjeta BLOB COMMENT 'numero de una tarjeta de crdito'
);

-- Tabla de Productos
CREATE TABLE Productos (
    ProductoID INT PRIMARY KEY,
    NombreProducto VARCHAR(100),
    Categoria VARCHAR(50),
    Precio DECIMAL(10, 2) COMMENT 'Precio por kilogramo en Euros',
    AñoCosecha INT,
    AgricultorID INT,
    FOREIGN KEY (AgricultorID) REFERENCES Agricultores(AgricultorID)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Tabla de Ventas
CREATE TABLE Ventas (
    VentaID INT PRIMARY KEY,
    ProductoID INT,
    CantidadVendida DECIMAL(10, 2) COMMENT 'Cantidad vendida en kilogramos',
    FechaVenta DATE,
    PrecioVenta DECIMAL(10, 2),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

# Poblar tablas
-- Agricultores
INSERT INTO Agricultores (AgricultorID, Nombre, Region, AñoIngreso, tarjeta) VALUES
(1, 'Alejandro Alzate Garcia', 'Norte', 2000, AES_ENCRYPT('1002566450','ADSO3231660')),
(2, 'Alejandro Tamayo Zapata', 'Sur', 2005, AES_ENCRYPT('1005134256','ADSO3231660')),
(3, 'Erik Jhoan Mayorga Cortes', 'Este', 2010, AES_ENCRYPT('1092458022','ADSO3231660')),
(4, 'Hector Daniel Gonzalez Sanchez', 'Oeste', 2015, AES_ENCRYPT('1038646923','ADSO3231660')),
(5, 'Janner Andrey Carvajal Escobar', 'Centro', 2012, AES_ENCRYPT('1034288667','ADSO3231660')),
(6, 'Johan Leandro Vega Morales', 'Norte', 2011, AES_ENCRYPT('1091204147','ADSO3231660')),
(7, 'Jostin David Acevedo Vargas', 'Sur', 2008, AES_ENCRYPT('1090273927','ADSO3231660')),
(8, 'Juan Alejandro Foronda Guerrero', 'Este', 2014, AES_ENCRYPT('1005279080','ADSO3231660')),
(9, 'Juan Diego Herrera Villegas', 'Oeste', 2007, AES_ENCRYPT('1192791948','ADSO3231660')),
(10, 'Juan Esteban Cardona Batero', 'Centro', 2009, AES_ENCRYPT('1094974604','ADSO3231660'));


-- Productos
INSERT INTO Productos (ProductoID, NombreProducto, Categoria, Precio, AñoCosecha, AgricultorID) VALUES
(1, 'Tomate', 'Verdura', 1.20, 2023, 1),
(2, 'Lechuga', 'Verdura', 0.80, 2022, 2),
(3, 'Papa', 'Tubérculo', 0.50, 2021, 3),
(4, 'Zanahoria', 'Verdura', 1.10, 2021, 4),
(5, 'Maíz', 'Grano', 1.50, 2020, 5),
(6, 'Trigo', 'Grano', 2.00, 2023, 6),
(7, 'Cebolla', 'Verdura', 1.30, 2022, 7),
(8, 'Ajo', 'Verdura', 2.568, 2021, 8),
(9, 'Frijol', 'Grano', 1.60, 2020, 9),
(10, 'Cilantro', 'Hierba', 0.90, 2023, 10),
(11, '  Culantro  ', 'TuVerCulo', 4.90, 2020, 10);

-- Ventas
INSERT INTO Ventas (VentaID, ProductoID, CantidadVendida, FechaVenta, PrecioVenta) VALUES
(1, 1, 500.00, '2025-05-01', 600.00),
(2, 2, 300.00, '2025-05-03', 240.00),
(3, 3, 600.00, '2025-05-05', 300.00),
(4, 4, 450.00, '2025-05-06', 495.00),
(5, 5, 700.00, '2025-05-07', 1050.00),
(6, 6, 550.00, '2025-05-10', 1100.00),
(7, 7, 400.00, '2025-05-12', 520.00),
(8, 8, 350.00, '2025-05-14', 875.00),
(9, 9, 650.00, '2025-05-15', 1040.00),
(10, 10, 300.00, '2025-05-16', 270.00),
(11, 1, 480.00, '2025-06-01', 576.00),
(12, 2, 250.00, '2025-06-03', 200.00),
(13, 3, 550.00, '2025-06-05', 275.00),
(14, 4, 420.00, '2025-06-06', 462.00),
(15, 5, 650.00, '2021-06-07', 975.00),
(16, 6, 500.00, '2025-06-10', 1000.00),
(17, 7, 380.00, '2025-06-12', 494.00),
(18, 8, 330.00, '2025-06-14', 825.00),
(19, 9, 600.00, '2025-06-15', 960.00),
(20, 10, 290.00, '2025-06-16', 261.00),
(21, 1, 450.00, '2025-07-01', 540.00),
(22, 2, 270.00, '2025-07-03', 216.00),
(23, 3, 530.00, '2025-07-05', 265.00),
(24, 4, 410.00, '2025-07-06', 451.00),
(25, 5, 620.00, '2025-07-07', 930.00),
(26, 6, 480.00, '2025-07-10', 960.00),
(27, 7, 390.00, '2025-07-12', 507.00),
(28, 8, 340.00, '2025-07-14', 850.00),
(29, 9, 580.00, '2025-07-15', 928.00),
(30, 10, 280.00, '2025-07-16', 252.00),
(31, 1, 490.00, '2023-11-01', 588.00),
(32, 2, 310.00, '2025-08-03', 248.00),
(33, 3, 560.00, '2025-08-05', 280.00),
(34, 4, 460.00, '2025-08-06', 506.00),
(35, 5, 640.00, '2025-08-07', 960.00),
(36, 6, 510.00, '2025-08-10', 1020.00),
(37, 7, 370.00, '2025-08-12', 481.00),
(38, 8, 320.00, '2025-08-14', 800.00),
(39, 9, 610.00, '2025-08-15', 976.00),
(40, 10, 295.00, '2024-10-16', 265.50);


-- Preguntas:

-- Obtener el precio de cada producto agrícola con un descuento del 10%.
-- Utilice la función ROUND para redondear el resultado a 2 decimales.
SELECT NombreProducto,Precio, round (Precio - Precio * 10 /100,2) as descuento
from Productos;

-- Obtener la longitud del nombre de cada agricultor.
-- Utilice la función CHAR_LENGTH.
select Nombre, CHAR_LENGTH(Nombre) as longitud_nombre
from Agricultores;


-- Concatenar el nombre y la región de cada agricultor, separados por un guion.
-- Utilice la función CONCAT_WS.
select CONCAT_WS(Nombre, Region,' - ') as nombre_region from Agricultores;


-- Localizar la primera aparición de la letra 'a' en el nombre de cada agricultor.
-- Utilice la función INSTR.

-- Obtener los 3 primeros caracteres del nombre de cada producto.
-- Utilice la función LEFT.


-- Obtener la cantidad total de productos vendidos por cada agricultor.

-- Cuente cuantos días han pasado desde la ultima venta realizada hasta hoy
-- Utilice las funciones NOW y DATEDIFF.

Obtener el precio promedio de los productos por categoría.

Obtener el nombre del agricultor que vendió el producto más caro.

Formatear la fecha de venta en el formato 'día/mes/año'.

Obtener el nombre del mes en que se realizaron más ventas.

Obtener la región del agricultor que tiene el nombre más largo.

Reemplazar todas las ocurrencias de la letra 'e' en el nombre del producto por la letra 'a'.

Obtener los nombres de los productos que no tienen ventas registradas.

Obtener la suma total de las ventas de productos cosechados en el año 2024.

Obtener el nombre del agricultor con más productos cosechados.

Obtener el total de ventas realizadas en el último trimestre del año pasado.
Utilice la función QUARTER y YEAR.

Calcular cuántos días han pasado desde la última cosecha registrada en la tabla de productos.

Obtener el nombre del agricultor cuya región tiene el nombre más corto.
Utilice la función LENGTH y ORDER BY.

Obtener el precio del producto más caro redondeado a 2 decimales.

Obtener el número total de ventas realizadas durante el mes de enero de cualquier año.
Utilice la función MONTH y COUNT.

Obtener el nombre del agricultor cuya última cosecha ocurrió en un año impar.
Utilice la función MOD.

Obtener la hora exacta de la última venta realizada.
Utilice la función TIME_FORMAT.

Obtener la cantidad vendida y el agricultor responsable de las tres ventas más grandes.
Utilice la función ORDER BY y LIMIT.

Obtener el promedio de precios de los productos cuyo nombre empieza con la letra 'M'.
Utilice la función AVG y LIKE.

Obtener la fecha de venta más antigua registrada en la base de datos.
Utilice la función MIN.

Calcular la suma total de las ventas realizadas este año.
Utilice las funciones YEAR y SUM.

Obtener la longitud del nombre de los productos en kilogramos vendidos.
Utilice la función LENGTH.

Obtener el nombre del agricultor con la venta más barata.
Utilice la función MIN.

Repetir el nombre de cada producto tres veces en la salida.
Utilice la función REPEAT.

Obtener la fecha y hora actual.
Utilice la función NOW.

Obtener la suma de todas las cantidades vendidas y la cantidad distinta de productos vendidos.
Utilice las funciones SUM y COUNT DISTINCT.

Obtener el nombre de los productos cuya longitud sea mayor a 10 caracteres.
Utilice la función CHAR_LENGTH.

Obtener el nombre del mes con más productos vendidos.
Utilice las funciones MONTHNAME y SUM.

Obtener la cantidad de productos vendidos por cada agricultor en el año 2025.
Utilice las funciones SUM y YEAR.

Obtener los nombres de los productos cuyo precio sea mayor a la media de los precios.
Utilice las funciones AVG y WHERE.

Obtener el nombre del producto más vendido en cada categoría.
Utilice la función MAX y GROUP BY.


Una consulta que indique si el agricultor ingresó antes o después del año 2010
Use if

Marcar si un producto tiene un precio superior a $1.20
Use if

Clasifique los productos como "Caro" o "Barato" dependiendo de si su precio es mayor a $1.00.

Indicar si la cantidad vendida fue superior a 500 kg ('Venta Grande', 'Venta Pequeña')

Verificar si la cosecha fue 'Reciente' o 'Antigua' (después del 2023)

Identificar si la venta tiene 'Precio Alto' o 'Precio Bajo' si el precio es mayor al promedio del producto

