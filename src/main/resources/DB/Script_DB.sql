CREATE SEQUENCE client_id_seq;

CREATE TABLE public."client"
(
    id serial PRIMARY KEY,
    tipo_documento character varying(2) not null,
    numero_documento character varying(20) not null,
    primer_nombre character varying(20) not null,
    segundo_nombre character varying(20) not null,
    primer_apellido character varying(20) not null,
    segundo_apellido character varying(20) not null,
    telefono character varying(20) not null,
    direccion character varying(32) not null,
    ciudad_residencia character varying(20) not null
);

ALTER TABLE IF EXISTS public."client"
    OWNER to julianfas20;
    
INSERT INTO client VALUES (1,'C','23445322','Julian','Felipe', 'Avila', 'Santa', '7621548', 'Callejón Carla Vila, Piso 7', 'Bogota');
INSERT INTO client VALUES (2,'P','23445321','Luis','Alexander', 'Rodriguez', '', '7621541', 'Transversal 13 A # 41 - 42 sur', 'Bogota');
INSERT INTO client VALUES (3,'C','23445323','Andrea','', 'Roa', 'Palacios', '7621542', '2445 Frank Mall', 'Bogota');
INSERT INTO client VALUES (4,'C','23445324','Joyce','Marcela', 'Gonzalez', 'De Quesada', '7621543', '279 Treutel Court Apt. 470', 'Bogota');
INSERT INTO client VALUES (5,'P','1000821793','Martin','Santiago', 'Arce', 'Contreras', '7621544', '47466 Leffler Station Suite 015', 'Bogota');

ALTER SEQUENCE client_id_seq RESTART WITH 6;