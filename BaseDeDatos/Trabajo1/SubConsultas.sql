tema CREATE TABLE productos (
    id_producto INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(50),
    precio DECIMAL(10,2)
);

CREATE TABLE pedidos (
    id_pedido INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fecha DATE,
    id_cliente INT,
    id_producto INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);
INSERT INTO productos (nombre, categoria, precio) VALUES
('Smartphone X', 'Telefonía', 59999),
('Tablet Y', 'Computación', 34999),
('Auriculares Z', 'Audio', 9999),
('Laptop Pro', 'Computación', 120000),
('Mouse Gamer', 'Accesorios', 15000),
('Teclado Mecánico', 'Accesorios', 22000),
('Monitor HD', 'Pantallas', 80000),
('Impresora Eco', 'Oficina', 45000),
('Smartwatch Fit', 'Wearables', 30000),
('Parlante Bluetooth', 'Audio', 18000);
INSERT INTO pedidos (fecha, id_cliente, id_producto) VALUES
('2023-11-20', 1, 1),
('2023-11-21', 2, 2),
('2023-11-22', 1, 3),
('2023-11-23', 3, 4),
('2023-11-24', 2, 5),
('2023-11-25', 1, 6),
('2023-11-26', 4, 7),
('2023-11-27', 5, 8),
('2023-11-28', 3, 9),
('2023-11-29', 2, 10),
('2023-11-30', 1, 2),
('2023-12-01', 4, 3),
('2023-12-02', 5, 4),
('2023-12-03', 2, 1),
('2023-12-04', 3, 5),
('2023-12-05', 1, 7),
('2023-12-06', 4, 8),
('2023-12-07', 5, 9),
('2023-12-08', 2, 6),
('2023-12-09', 3, 10);


select * from productos p 

-- Obtener todos los productos cuyo precio es mayor que el precio del producto más caro dividido 3.
create view precioMayorDividido3 as
SELECT * FROM productos
WHERE precio > (
SELECT MAX(precio) / 3
FROM productos
);

-- Obtener el nombre y precio de los productos que han sido pedidos por el cliente con id_cliente = 1.
create view pedidosDeCliente1 as
SELECT nombre, precio
FROM productos
WHERE id_producto IN (
SELECT id_producto
FROM pedidos
WHERE id_cliente = 1
);

-- Obtener el precio promedio de los productos en cada categoría.

create view promedioPorCategoria as
select categoria, avg(precio) as promedio_precio
from productos
group by categoria;


-- Encontrar el precio del producto más barato en la categoría 'Computación'.
create view masBaratoDeComputacion as
select  min(precio) as precio_barato
from productos 
where categoria = 'Computación';

-- Obtener los detalles de los pedidos realizados en la fecha más reciente.
create view pedidosFechaMasReciente as
select * 
from pedidos
where fecha = (
select max(fecha)
from pedidos 
);

-- Listar los productos que no han sido pedidos por ningún cliente.7
create view pedidoPorNingunCliente as
select * from productos
where id_producto not in (
select id_producto from pedidos
);
-- Encontrar la cantidad total gastada por cada cliente.
create view totalGastadoPorCliente as
SELECT id_cliente, SUM(precio)
FROM pedidos
JOIN productos ON pedidos.id_producto = productos.id_producto
GROUP BY id_cliente;

-- Obtener el nombre del producto más caro.
create view productoMasCaro as
SELECT nombre
FROM productos
WHERE precio = (
    SELECT MAX(precio)
    FROM productos
);
-- Obtener el precio de los productos que han sido pedidos más de una vez.
create view  pedidoMasDeUnaVez as
SELECT p.nombre, p.precio
FROM productos p
JOIN pedidos pe 
ON p.id_producto = pe.id_producto
GROUP BY p.id_producto, p.nombre, p.precio
HAVING COUNT(*) > 1;


-- 10. Encontrar el cliente que ha realizado el mayor número de pedidos.

CREATE VIEW cliente_mas_pedidos AS
SELECT id_cliente, COUNT(*) AS total_pedidos
FROM pedidos
GROUP BY id_cliente
HAVING COUNT(*) = (
    SELECT MAX(total)
    FROM (
        SELECT COUNT(*) AS total
        FROM pedidos
        GROUP BY id_cliente
    ) AS subconsulta
);

-- 11. Obtener los productos que tienen un precio mayor al precio promedio de los productos.

CREATE VIEW productos_mayor_promedio AS
SELECT *
FROM productos
WHERE precio > (
    SELECT AVG(precio)
    FROM productos
);

-- 12. Listar los productos que han sido pedidos en la fecha más antigua.

CREATE VIEW productos_fecha_antigua AS
SELECT p.*
FROM productos p
JOIN pedidos pe
ON p.id_producto = pe.id_producto
WHERE pe.fecha = (
    SELECT MIN(fecha)
    FROM pedidos
);

-- 13. Obtener el nombre y precio de los productos que han sido pedidos por más de 3 clientes diferentes.

CREATE VIEW productos_mas_3_clientes AS
SELECT p.nombre, p.precio
FROM productos p
JOIN pedidos pe
ON p.id_producto = pe.id_producto
GROUP BY p.id_producto, p.nombre, p.precio
HAVING COUNT(DISTINCT pe.id_cliente) > 3;
	
-- 14. Encontrar los productos en la categoría 'Audio' que han sido pedidos al menos una vez.

CREATE VIEW productos_audio_pedidos AS
SELECT *
FROM productos
WHERE categoria = 'Audio'
AND id_producto IN (
    SELECT id_producto
    FROM pedidos
);

-- 15. Obtener los productos que fueron pedidos al menos una vez.

CREATE VIEW productos_pedidos AS
SELECT *
FROM productos
WHERE id_producto IN (
    SELECT DISTINCT id_producto
    FROM pedidos
);

-- 16. Listar el cliente que ha gastado más en total.

CREATE VIEW cliente_mas_gasto AS
SELECT pe.id_cliente, SUM(p.precio) AS total_gastado
FROM pedidos pe
JOIN productos p
ON pe.id_producto = p.id_producto
GROUP BY pe.id_cliente
HAVING SUM(p.precio) = (
    SELECT MAX(total)
    FROM (
        SELECT SUM(p2.precio) AS total
        FROM pedidos pe2
        JOIN productos p2
        ON pe2.id_producto = p2.id_producto
        GROUP BY pe2.id_cliente
    ) AS subconsulta
);

-- 17. Obtener todos los pedidos que incluyen productos cuyo precio es superior al precio promedio de los productos.

CREATE VIEW pedidos_precio_superior_promedio AS
SELECT pe.*
FROM pedidos pe
JOIN productos p
ON pe.id_producto = p.id_producto
WHERE p.precio > (
    SELECT AVG(precio)
    FROM productos
);


-- 18. Obtener el nombre de los productos que han sido pedidos por el cliente con id_cliente = 2,
-- pero no han sido pedidos por el cliente con id_cliente = 1.

CREATE VIEW productos_cliente2_no_cliente1 AS
SELECT nombre
FROM productos
WHERE id_producto IN (
    SELECT id_producto
    FROM pedidos
    WHERE id_cliente = 2
)
AND id_producto NOT IN (
    SELECT id_producto
    FROM pedidos
    WHERE id_cliente = 1
);

-- 19. Encontrar los productos que no han sido pedidos en los últimos 30 días.

CREATE VIEW productos_no_ultimos_30_dias AS
SELECT *
FROM productos
WHERE id_producto NOT IN (
    SELECT id_producto
    FROM pedidos
    WHERE fecha >= CURRENT_DATE - INTERVAL '30 days'
);


-- 20. Obtener el promedio de precios de los productos en la categoría 'Telefonía'
-- para los pedidos realizados en el mes más reciente.

CREATE VIEW promedio_telefonia_mes_reciente AS
SELECT AVG(p.precio) AS promedio_precio
FROM productos p
JOIN pedidos pe
ON p.id_producto = pe.id_producto
WHERE p.categoria = 'Telefonía'
AND DATE_TRUNC('month', pe.fecha) = (
    SELECT DATE_TRUNC('month', MAX(fecha))
    FROM pedidos
);




-- Subconsultas IN --    MINI TALLER

-- Eliminar tablas si existen
DROP TABLE IF EXISTS clientes;

DROP TABLE IF EXISTS ciudades;

-- Crear tabla ciudades
CREATE TABLE ciudades (
    codigo SERIAL PRIMARY KEY,
    nombre VARCHAR(20)
);

-- Crear tabla clientes
CREATE TABLE clientes (
    codigo SERIAL PRIMARY KEY,
    nombre VARCHAR(30),
    domicilio VARCHAR(30),
    codigociudad SMALLINT NOT NULL,

    CONSTRAINT fk_ciudad
    FOREIGN KEY (codigociudad)
    REFERENCES ciudades(codigo)
);

-- Insertar ciudades
INSERT INTO ciudades (nombre) VALUES
('Bogotá'),
('Medellín'),
('Cali'),
('Barranquilla'),
('Cartagena'),
('Bucaramanga'),
('Pereira');

-- Insertar clientes
INSERT INTO clientes (nombre, domicilio, codigociudad) VALUES
('Ana Pérez', 'Calle 10 #15-30', 1),
('Bernardo Gómez', 'Carrera 7 #20-10', 3),
('Carolina Martínez', 'Avenida Bolívar #5-12', 2),
('Daniel Silva', 'Calle 50 #45-22', 4),
('Elizabeth Ramírez', 'Carrera 3 #18-05', 5),
('Fernando López', 'Avenida Santander #10-25', 1),
('Gabriela Castro', 'Calle 25 #32-18', 2),
('Hugo Moreno', 'Carrera 15 #40-08', 3),
('Inés Rojas', 'Avenida Boyacá #22-11', 4),
('Julián Zapata', 'Calle 12 #28-02', 5),
('Karen Vega', 'Carrera 8 #16-19', 1),
('Laura Ortiz', 'Avenida Jiménez #30-14', 2),
('Mateo Duque', 'Calle 42 #38-20', 3),
('Natalia Herrera', 'Carrera 20 #44-06', 4),
('Omar Vásquez', 'Avenida NQS #26-13', 5);


-- Necesitamos conocer los nombres de las ciudades de aquellos clientes cuyo domicilio es en "Avenida Boyacá", empleando subconsulta.
create view avenida_Boyaca as 
SELECT c.nombre AS ciudad
FROM ciudades c
INNER JOIN clientes cl 
ON c.codigo = cl.codigociudad
WHERE cl.domicilio LIKE '%Avenida Boyacá%';

-- Obtenga los nombre de las ciudades de los clientes cuyo apellido no comienza con una letra específica, empleando subconsulta
create view apellido_etraEspecifica as
SELECT c.nombre AS ciudad
FROM ciudades c
INNER JOIN clientes cl 
ON c.codigo = cl.codigociudad
WHERE cl.nombre NOT IN (
    SELECT nombre
    FROM clientes
    WHERE nombre LIKE 'A%'   -- Cambiar la letra si desea
);

--  Pruebe la subconsulta anterior separada de la consulta exterior para verificar que retorna una lista de valores de un solo campo
create view listadeValoresDeUn_soloCampo as
select codigociudad
from clientes
where nombre like 'A%';  -- Cambie la letra según la consulta anterior







