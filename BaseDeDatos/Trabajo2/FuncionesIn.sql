-- =========================================
-- CREAR BASE DE DATOS
-- =========================================

CREATE DATABASE FuncionesIncorporadas;

-- =========================================
-- CONECTARSE A LA BASE DE DATOS temporal
-- (En pgAdmin o DBeaver selecciona la BD)
-- =========================================


-- =========================================
-- CREACIÓN DE TABLA
-- =========================================

CREATE TABLE persona (
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL
);


-- =========================================
-- INSERTAR DATOS DE EJEMPLO
-- =========================================

INSERT INTO persona (nombre, genero)
SELECT
    'Persona_' || numero,
    CASE
        WHEN numero % 2 = 0 THEN 'Masculino'
        ELSE 'Femenino'
    END
FROM generate_series(1, 1000) AS numero;


-- =========================================
-- CONSULTAR DATOS
-- =========================================

SELECT * FROM persona;


-- =========================================
-- VER TAMAÑO DE TODAS LAS BASES DE DATOS
-- =========================================

SELECT 
    datname AS "Base de datos",
    pg_size_pretty(pg_database_size(datname)) AS "Tamaño"
FROM pg_database
ORDER BY pg_database_size(datname) DESC;


-- =========================================
-- VER TAMAÑO DE CADA TABLA
-- DE LA BASE DE DATOS ACTUAL
-- =========================================

SELECT
    relname AS "Tabla",
    pg_size_pretty(pg_total_relation_size(relid)) AS "Tamaño"
FROM pg_catalog.pg_statio_user_tables
ORDER BY pg_total_relation_size(relid) DESC;


-- =========================================
-- VER TAMAÑO DETALLADO DE LAS TABLAS
-- =========================================

SELECT
    relname AS "Tabla",
    n_live_tup AS "Registros",
    pg_size_pretty(pg_relation_size(relid)) AS "Datos",
    pg_size_pretty(pg_indexes_size(relid)) AS "Índices",
    pg_size_pretty(pg_total_relation_size(relid)) AS "Total"
FROM pg_stat_user_tables
ORDER BY pg_total_relation_size(relid) DESC;