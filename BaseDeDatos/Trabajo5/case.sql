CREATE TABLE libros (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  autor VARCHAR(50) NOT NULL,
  genero VARCHAR(30),
  anio_publicacion INT,
  copias_disponibles INT,
  precio DECIMAL(6,2),
  es_bestseller BOOLEAN,
  idioma VARCHAR(20),
  dias_prestamo INT,
  ultima_revision DATE
);

-- Inserción de 20 registros de muestra
INSERT INTO libros (titulo, autor, genero, anio_publicacion, copias_disponibles, precio, es_bestseller, idioma, dias_prestamo, ultima_revision) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 'Ficción', 1967, 5, 25.99, TRUE, 'Español', 14, '2023-12-15'),
('To Kill a Mockingbird', 'Harper Lee', 'Ficción', 1960, 3, 15.50, TRUE, 'Inglés', 14, '2024-01-20'),
('1984', 'George Orwell', 'Ficción', 1949, 0, 12.99, TRUE, 'Inglés', 14, '2023-11-05'),
('El principito', 'Antoine de Saint-Exupéry', 'Ficción', 1943, 7, 10.99, TRUE, 'Español', 14, '2024-02-28'),
('Breve historia del tiempo', 'Stephen Hawking', 'No ficción', 1988, 2, 30.00, FALSE, 'Español', 21, '2023-10-10'),
('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Ficción', 1605, 1, 35.99, FALSE, 'Español', 14, '2023-09-01'),
('El código Da Vinci', 'Dan Brown', 'Ficción', 2003, 4, 20.50, TRUE, 'Español', 14, '2024-03-15'),
('La metamorfosis', 'Franz Kafka', 'Ficción', 1915, 3, 11.99, FALSE, 'Español', 14, '2023-08-20'),
('El arte de la guerra', 'Sun Tzu', 'No ficción', -500, 6, 14.99, FALSE, 'Español', 21, '2024-01-05'),
('Orgullo y prejuicio', 'Jane Austen', 'Ficción', 1813, 2, 18.50, TRUE, 'Inglés', 14, '2023-12-01'),
('El nombre de la rosa', 'Umberto Eco', 'Ficción', 1980, 1, 28.99, FALSE, 'Español', 14, '2023-11-15'),
('Los juegos del hambre', 'Suzanne Collins', 'Ficción', 2008, 5, 22.99, TRUE, 'Español', 14, '2024-02-10'),
('El alquimista', 'Paulo Coelho', 'Ficción', 1988, 4, 19.99, TRUE, 'Español', 14, '2024-03-01'),
('Sapiens: De animales a dioses', 'Yuval Noah Harari', 'No ficción', 2011, 3, 32.50, TRUE, 'Español', 21, '2023-10-25'),
('Crónica de una muerte anunciada', 'Gabriel García Márquez', 'Ficción', 1981, 2, 16.99, FALSE, 'Español', 14, '2023-09-30'),
('El gran Gatsby', 'F. Scott Fitzgerald', 'Ficción', 1925, 0, 13.50, TRUE, 'Inglés', 14, '2023-07-15'),
('Mujercitas', 'Louisa May Alcott', 'Ficción', 1868, 3, 17.99, FALSE, 'Español', 14, '2024-01-10'),
('El señor de los anillos', 'J.R.R. Tolkien', 'Ficción', 1954, 1, 45.00, TRUE, 'Español', 21, '2023-12-20'),
('Breve historia de casi todo', 'Bill Bryson', 'No ficción', 2003, 2, 29.99, FALSE, 'Español', 21, '2024-02-05'),
('Rayuela', 'Julio Cortázar', 'Ficción', 1963, 1, 23.50, FALSE, 'Español', 14, '2023-11-30');


-- Clasificar los libros por antigüedad
create view libros_ntiguedad as
SELECT 
  titulo, 
  anio_publicacion,
  CASE
    WHEN anio_publicacion < 1900 THEN 'Muy antiguo'
    WHEN anio_publicacion BETWEEN 1900 AND 1949 THEN 'Antiguo'
    WHEN anio_publicacion BETWEEN 1950 AND 1999 THEN 'Moderno'
    ELSE 'Contemporaneo'
  END AS clasificacion_antiguedad
FROM libros;

-- Determinar la disponibilidad de los libros
select titulo,copias_disponibles
case 
when copias_disponibles = 0 THEN 'No dispo0nible'
when copias_disponibles between 1 and 3 then 'Disponibilidad limitada'
else 'Ampliamente disponible' end as disponibilidad_libros


-- Categorizar libros por precio
create view libros_precio as
select titulo, precio,
case 
when precio between 10.99 and 14.99 then 'Economico'
when precio between 15.5 and 23.5 then 'Precio medio'
when precio between 25.99 and 32.5 then 'Precio alto'
else 'Premium' end as libros_por_precio from libros;


--Clasificar libros por popularidad (basado en si es bestseller y copias disponibles)
create view popularidad_libros as 
select titulo,
case 
when es_bestseller = true then 1
else 0
end as es_bestseller,copias_disponibles,
case 
when es_bestseller = false and copias_disponibles <= 2 then 'interes moderado'
when es_bestseller = false and copias_disponibles >= 3 then 'interes bajo'
when es_bestseller = true and copias_disponibles <= 2 then 'muy popular'
else 'popular' end as popularidad from libros;	

-- Determinar el idioma principal y secundario
create view idioma_principal_secundario as
select titulo,idioma,
case 
when idioma = 'Inglés' then 'Inglés(Secundario)'
else 'Español(Principal)' end as Clasificacion_de_idioma from libros;

-- Asignar categoría por género y antigüedad
create view categoria_por_genero as
select titulo,genero,anio_publicacion,
case 
when genero = 'Ficción' and anio_publicacion between 1605 and 1949 then 'Ficción clasica'
when genero = 'Ficción' and anio_publicacion between 1950 and 2008 then 'Ficción moderna'
when genero = 'No ficción' and anio_publicacion < 1989 then 'No ficción clasica'
else 'No ficción contemporánea' end as categoria_especial from libros;


-- Determinar el período de préstamo recomendado
create view periodo_prestamo as
select titulo, dias_prestamo,
case 
when dias_prestamo <=14 then 'Prestamo estandar'
else 'Prestamo extendido' end as tipo_prestamo from libros;


-- Clasificar libros por década de publicación
create view decada_publicacion as
select titulo,anio_publicacion,
case 
when anio_publicacion <= 1949 then 'Antes de 1950'
when anio_publicacion between 1950 and 1959 then 'Década de 1950'
when anio_publicacion between 1960 and 1969 then 'Década de 1960'
when anio_publicacion between 1980 and 1989 then 'Década de 1980'
when anio_publicacion between 2000 and 2009 then 'Década de 2000'
else 'Década de 2010 en adelante' end as decada_de_publicacion from libros;

-- Determinar necesidad de revisión




-- Clasificar por longitud del título
create view longitud_por_titulo as
SELECT titulo,
  CASE
    WHEN LENGTH(titulo) <= 7 THEN 'Título corto'
    WHEN LENGTH(titulo) BETWEEN 8 AND 20 THEN 'Título medio'
    ELSE 'Título largo'
  END AS longitud_titulo
FROM libros;





