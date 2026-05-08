CREATE TABLE productos (
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


select * from pedidos

-- Obtener todos los productos cuyo precio es mayor que el precio del producto más caro dividido 3.
SELECT * FROM productos
WHERE precio > (
SELECT MAX(precio) / 3
FROM productos
);

-- Obtener el nombre y precio de los productos que han sido pedidos por el cliente con id_cliente = 1.
SELECT nombre, precio
FROM productos
WHERE id_producto IN (
SELECT id_producto
FROM pedidos
WHERE id_cliente = 1
);

-- Obtener el precio promedio de los productos en cada categoría.

select categoria, avg(precio) as promedio_precio
from productos
group by categoria;


-- Encontrar el precio del producto más barato en la categoría 'Computación'.
select  min(precio) as precio_barato
from productos 
where categoria = 'Computación';

-- Obtener los detalles de los pedidos realizados en la fecha más reciente.

select * 
from pedidos
where fecha = (
select max(fecha)
from pedidos 
);

-- Listar los productos que no han sido pedidos por ningún cliente.
select * from productos
where id_producto not in (
select id_producto from pedidos
);
-- Encontrar la cantidad total gastada por cada cliente.

SELECT id_cliente, SUM(precio)
FROM pedidos
JOIN productos ON pedidos.id_producto = productos.id_producto
GROUP BY id_cliente;








