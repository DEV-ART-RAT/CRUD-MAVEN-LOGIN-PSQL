create table APP_USER
(
  USER_ID           BIGINT,
  USER_NAME         VARCHAR(36) not null,
  ENCRYTED_PASSWORD VARCHAR(128) not null,
  ENABLED           Boolean not null,
  USER_EXP_ID			BIGINT not null
	 
) ;
 
alter table APP_USER
  add constraint APP_USER_PK primary key (USER_ID);
 
alter table APP_USER
  add constraint APP_USER_UK unique (USER_NAME);
 
-- Create table
create table APP_ROLE
(
  ROLE_ID   BIGINT not null,
  ROLE_NAME VARCHAR(30) not null
) ;
--  
alter table APP_ROLE
  add constraint APP_ROLE_PK primary key (ROLE_ID);
 
alter table APP_ROLE
  add constraint APP_ROLE_UK unique (ROLE_NAME);
-- Create table
create table USER_ROLE
(
  ID      BIGINT,
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);
--  
alter table USER_ROLE
  add constraint USER_ROLE_PK primary key (ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references APP_USER (USER_ID);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references APP_ROLE (ROLE_ID);
 
-- Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (
 
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
     
);

CREATE TABLE public.usuarioPersona(
	c_id BIGINT PRIMARY KEY not null,
	s_nombre varchar(50) not null,
	s_apellido varchar(50) not null,
	--s_carne varchar(9) not null,
	d_fnacimiento varchar(15) not null,
	s_edad varchar(3) not null,
	s_direccion varchar(100) not null,
	
	s_depto int not null,
	s_minicipio int not null
);



CREATE TABLE public.dpto(
	s_depto int PRIMARY KEY not null,
	s_nombre varchar(50) not null
);

alter table APP_USER
  add constraint APP_USER_FK1 foreign key (USER_EXP_ID)
  references usuarioPersona (c_id);

CREATE TABLE public.minicipio(
	s_minicipio int PRIMARY KEY not null,
	s_nombre varchar(50) not null,
	s_depto int not null
);
ALTER TABLE public.minicipio ADD CONSTRAINT fk_c_municipio FOREIGN KEY(s_depto) 
REFERENCES public.dpto(s_depto);
--drop table minicipio

insert into dpto (s_depto , s_nombre)
values (1, 'Libertad');
insert into dpto (s_depto, s_nombre)
values (2, 'SS ');

insert into minicipio (s_minicipio, s_nombre,s_depto)
values (1, 'municipio Libertad',1);
insert into minicipio (s_minicipio, s_nombre,s_depto)
values (2, 'municipio SS',2);
insert into minicipio (s_minicipio, s_nombre,s_depto)
values (3, 'municipio Libertad 2',1);
insert into minicipio (s_minicipio, s_nombre,s_depto)
values (4, 'municipio SS 2',2);



insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (2, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (3, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (5, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (6, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (7, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (8, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (9, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (10, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);
insert into usuarioPersona (c_id, s_nombre, s_apellido, d_fnacimiento,s_edad,s_direccion,s_depto,s_minicipio )
values (11, 'dbuser1', '$2aCrFu', '$2aCrFu','2Fu','$2aCrFu',1,1);


insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED,USER_EXP_ID)
values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,2);
insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED,USER_EXP_ID)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true,3);
 
---
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');
 
---
 
insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);
insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 1, 2);
insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);



CREATE TABLE public.alumnoxmateria(
	c_alumnoxmateria integer primary key,
	cod_materia varchar(6) not null  ,
	c_expediente integer not null  ,
	s_nota varchar(3) not null,
	s_estado varchar(10) not null
);
--drop TABLE public.alumnoxmateria

CREATE TABLE public.materia(
	cod_materia varchar(6) not null  PRIMARY KEY,
	nombre_materia varchar(50) not null,
	descripcion_materia varchar(100) not null,
	estado_materia varchar(10) not null
);
--drop TABLE public.materia

CREATE TABLE public.expediente(
	c_expediente int PRIMARY KEY,
	s_nombre varchar(50) not null,
	s_apellido varchar(50) not null,
	s_carne varchar(9) not null,
	d_fnacimiento varchar(15) not null,
	s_edad varchar(3) not null,
	s_direccion varchar(100) not null,
	s_telefonof varchar(8) not null,
	s_telefonom varchar(8) not null,
	s_institucion varchar(50) not null,
	s_nombrePadre varchar(50) not null,
	s_nombreMadre varchar(50) not null
);
ALTER TABLE public.alumnoxmateria ADD CONSTRAINT fk_cod_materia FOREIGN KEY(cod_materia) REFERENCES public.materia(cod_materia);
ALTER TABLE public.alumnoxmateria ADD CONSTRAINT fk_c_expediente FOREIGN KEY(c_expediente) REFERENCES public.expediente(c_expediente);


insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020MA', 'Matematica', 'Matematica Avanzada', 'Activo');
insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020IN', 'Ingles', 'Ingles Basico', 'Activo');
insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020LE', 'Lenguaje', 'Lenguaje y literatura', 'Activo');
insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020SE', 'Seminario', 'Seminario de orientacion', 'Activo');
insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020CI', 'Ciencias', 'Ciencias Naturales', 'Activo');
insert into materia (cod_materia, nombre_materia, descripcion_materia, estado_materia) values ('2020SO', 'Sociales', 'Ciencias Sociales', 'Activo');

insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (1, 'Trix', 'Fernie', '1A2121ADC', '2000-01-01', 20, '78 Oak Court', 13002568, 76455648, 'Colegio Fatima', 'Trix Fernie', 'Trix Fernie');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (2, 'Lyndsey', 'Ianilli', '4AF221ADC', '1999-02-10', 21, '52211 Delaware Crossing', 71305248, 60860305, 'Colegio Fatima', 'Lyndsey Ianilli', 'Lyndsey Ianilli');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (3, 'Abbe', 'Swarbrick', '1A21S2A22', '2003-03-01', 17, '8714 Cascade Parkway', 80219404, 38673605, 'Colegio Fatima', 'Abbe Swarbrick', 'Abbe Swarbrick');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (4, 'Layton', 'Varrow', 'TDG35EYD8', '2005-08-02', 15, '94 Burning Wood Avenue', 23051930, 97276567, 'Colegio Nazareth', 'Layton Varrow', 'Layton Varrow');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (5, 'Leila', 'Kilvington', '63YD65FSL', '2000-01-01', 20, '38347 Birchwood Way', 43199378, 71455937, 'Colegio Santa Celicia', 'Leila Kilvington', 'Leila Kilvington');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (6, 'Clementia', 'Laskey', '9874SU82', '1999-02-10', 21, '6 2nd Trail', 38913417, 72420618, 'Colegio Nazareth', 'Clementia Laskey', 'Clementia Laskey');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (7, 'Prissie', 'Skipperbottom', '8UT5ED37E', '2003-03-01', 17, '18710 Huxley Terrace', 73337062, 33507051, 'Liceo Salvadoreño', 'Prissie Skipperbottom', 'Prissie Skipperbottom');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (8, 'El', 'Baselio', '98UEED21K', '2005-08-02', 15, '69 Oriole Hill', 52113776, 48493840, 'Colegio Nazareth', 'El Baselio', 'El Baselio');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (9, 'Belia', 'Canas', '987EHDNS', '2000-01-01', 20, '0 Bunker Hill Center', 43620096, 75837541, 'Liceo Salvadoreño', 'Belia Canas', 'Belia Canas');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (10, 'Susannah', 'Wise', '09HE653WS', '1999-02-10', 21, '03 Annamark Way', 62459159, 25142012, 'Colegio Fatima', 'Susannah Wise', 'Susannah Wise');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (11, 'Linette', 'Vicent', '1A2121ADC', '2003-03-01', 17, '20 Laurel Trail', 25065971, 40552202, 'Colegio Fatima', 'Linette Vicent', 'Linette Vicent');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (12, 'Jeramey', 'Pietzker', '4AF221ADC', '2005-08-02', 15, '37 Orin Parkway', 87936700, 59275892, 'Colegio Santa Celicia', 'Jeramey Pietzker', 'Jeramey Pietzker');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (13, 'Farra', 'Rodenhurst', '1A21S2A22', '2000-01-01', 20, '100 Cambridge Trail', 93321775, 73541294, 'Colegio Fatima', 'Farra Rodenhurst', 'Farra Rodenhurst');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (14, 'Basile', 'Kmicicki', 'TDG35EYD8', '1999-02-10', 21, '460 Canary Junction', 63340987, 56189396, 'Colegio Santa Celicia', 'Basile Kmicicki', 'Basile Kmicicki');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (15, 'Wrennie', 'Master', '63YD65FSL', '2003-03-01', 17, '62940 Cottonwood Place', 41172565, 86776579, 'Colegio Nazareth', 'Wrennie Master', 'Wrennie Master');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (16, 'Glennie', 'Cosans', '9874SU82', '2005-08-02', 15, '699 Fairfield Pass', 16993786, 49543705, 'Colegio Nazareth', 'Glennie Cosans', 'Glennie Cosans');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (17, 'Saxe', 'Avey', '8UT5ED37E', '2000-01-01', 20, '932 Eastwood Plaza', 91992119, 20342250, 'Colegio Fatima', 'Saxe Avey', 'Saxe Avey');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (18, 'Janot', 'Tailour', '98UEED21K', '1999-02-10', 21, '35 Merry Crossing', 26660540, 76867285, 'Liceo Salvadoreño', 'Janot Tailour', 'Janot Tailour');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (19, 'Fayette', 'Upcott', '987EHDNS', '2003-03-01', 17, '6753 Tennessee Court', 70188792, 79625374, 'Liceo Salvadoreño', 'Fayette Upcott', 'Fayette Upcott');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (20, 'Brice', 'Kee', '09HE653WS', '2005-08-02', 15, '8 Old Shore Parkway', 35644196, 25121303, 'Colegio Nazareth', 'Brice Kee', 'Brice Kee');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (21, 'Estevan', 'Stubbeley', '1A2121ADC', '2000-01-01', 20, '9869 Monterey Lane', 65268769, 37386399, 'Colegio Fatima', 'Estevan Stubbeley', 'Estevan Stubbeley');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (22, 'Yevette', 'Gabbott', '4AF221ADC', '1999-02-10', 21, '0363 Reinke Terrace', 76417800, 23500576, 'Colegio Fatima', 'Yevette Gabbott', 'Yevette Gabbott');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (23, 'Shirley', 'Ginity', '1A21S2A22', '2003-03-01', 17, '489 Ridgeway Road', 96885871, 13039551, 'Colegio Santa Celicia', 'Shirley Ginity', 'Shirley Ginity');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (24, 'Winonah', 'McCrainor', 'TDG35EYD8', '2005-08-02', 15, '77552 Hanover Court', 15763722, 85129777, 'Liceo Salvadoreño', 'Winonah McCrainor', 'Winonah McCrainor');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (25, 'Geoffrey', 'Scamaden', '63YD65FSL', '2000-01-01', 20, '8 Sachtjen Trail', 30361775, 62724941, 'Colegio Fatima', 'Geoffrey Scamaden', 'Geoffrey Scamaden');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (26, 'Nannette', 'Colthard', '9874SU82', '1999-02-10', 21, '9948 Hallows Alley', 49012332, 30010334, 'Colegio Fatima', 'Nannette Colthard', 'Nannette Colthard');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (27, 'Berrie', 'Bradberry', '8UT5ED37E', '2003-03-01', 17, '2664 Pond Circle', 44164746, 13206294, 'Liceo Salvadoreño', 'Berrie Bradberry', 'Berrie Bradberry');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (28, 'Jodie', 'Franc', '98UEED21K', '2005-08-02', 15, '326 Susan Drive', 87320702, 66382409, 'Colegio Fatima', 'Jodie Franc', 'Jodie Franc');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (29, 'Kayle', 'Entreis', '987EHDNS', '2000-01-01', 20, '94 School Park', 68336291, 62756175, 'Colegio Santa Celicia', 'Kayle Entreis', 'Kayle Entreis');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (30, 'Claudell', 'Geraudel', '09HE653WS', '1999-02-10', 21, '78 Norway Maple Place', 78026394, 87127107, 'Colegio Santa Celicia', 'Claudell Geraudel', 'Claudell Geraudel');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (31, 'Elton', 'Lundie', '1A2121ADC', '2003-03-01', 17, '22447 Hanover Court', 80983987, 51295724, 'Colegio Santa Celicia', 'Elton Lundie', 'Elton Lundie');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (32, 'Ahmed', 'Studeart', '4AF221ADC', '2005-08-02', 15, '89593 Fallview Parkway', 24900502, 45792370, 'Colegio Fatima', 'Ahmed Studeart', 'Ahmed Studeart');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (33, 'Gerty', 'Baterip', '1A21S2A22', '2000-01-01', 20, '04435 Elka Court', 14784047, 11392150, 'Liceo Salvadoreño', 'Gerty Baterip', 'Gerty Baterip');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (34, 'Cassy', 'Woolner', 'TDG35EYD8', '1999-02-10', 21, '46 Fordem Pass', 77006914, 55410904, 'Colegio Santa Celicia', 'Cassy Woolner', 'Cassy Woolner');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (35, 'Ernestus', 'Barkus', '63YD65FSL', '2003-03-01', 17, '117 Montana Point', 69170232, 13599209, 'Liceo Salvadoreño', 'Ernestus Barkus', 'Ernestus Barkus');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (36, 'Seumas', 'Durbyn', '9874SU82', '2005-08-02', 15, '6 Knutson Parkway', 27417221, 87392912, 'Colegio Santa Celicia', 'Seumas Durbyn', 'Seumas Durbyn');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (37, 'Dorris', 'Filgate', '8UT5ED37E', '2000-01-01', 20, '73148 7th Place', 28269429, 48913490, 'Liceo Salvadoreño', 'Dorris Filgate', 'Dorris Filgate');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (38, 'Devlen', 'Cobbled', '98UEED21K', '1999-02-10', 21, '9151 Arizona Crossing', 34488904, 64762230, 'Liceo Salvadoreño', 'Devlen Cobbled', 'Devlen Cobbled');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (39, 'Trish', 'Hammel', '987EHDNS', '2003-03-01', 17, '55945 Mockingbird Lane', 20157467, 68353192, 'Liceo Salvadoreño', 'Trish Hammel', 'Trish Hammel');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (40, 'Katherine', 'Buddell', '09HE653WS', '2005-08-02', 15, '11 Glacier Hill Drive', 59322458, 59855414, 'Colegio Fatima', 'Katherine Buddell', 'Katherine Buddell');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (41, 'Dianna', 'Keppie', '1A2121ADC', '2000-01-01', 20, '65795 Starling Lane', 44607672, 42958488, 'Liceo Salvadoreño', 'Dianna Keppie', 'Dianna Keppie');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (42, 'Lurette', 'Symper', '4AF221ADC', '1999-02-10', 21, '9518 Sloan Road', 81122865, 21287265, 'Colegio Fatima', 'Lurette Symper', 'Lurette Symper');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (43, 'Carly', 'Fulloway', '1A21S2A22', '2003-03-01', 17, '2588 Spaight Parkway', 89295480, 32192144, 'Colegio Santa Celicia', 'Carly Fulloway', 'Carly Fulloway');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (44, 'Derick', 'Chart', 'TDG35EYD8', '2005-08-02', 15, '9356 Jay Pass', 72835475, 18401710, 'Liceo Salvadoreño', 'Derick Chart', 'Derick Chart');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (45, 'Doretta', 'MacGauhy', '63YD65FSL', '2000-01-01', 20, '3347 Sycamore Lane', 14553647, 13857884, 'Colegio Fatima', 'Doretta MacGauhy', 'Doretta MacGauhy');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (46, 'Anallise', 'Killner', '9874SU82', '1999-02-10', 21, '7573 Barby Court', 49238934, 90567333, 'Colegio Nazareth', 'Anallise Killner', 'Anallise Killner');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (47, 'Aleece', 'Blowes', '8UT5ED37E', '2003-03-01', 17, '1553 Colorado Circle', 79740201, 40732066, 'Colegio Nazareth', 'Aleece Blowes', 'Aleece Blowes');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (48, 'Tilda', 'Daviot', '98UEED21K', '2005-08-02', 15, '37290 Arkansas Lane', 21599910, 22574007, 'Colegio Fatima', 'Tilda Daviot', 'Tilda Daviot');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (49, 'Lynnelle', 'Dawidman', '987EHDNS', '2000-01-01', 20, '7 Hooker Avenue', 62338038, 50469276, 'Colegio Fatima', 'Lynnelle Dawidman', 'Lynnelle Dawidman');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (50, 'Melloney', 'Ladloe', '09HE653WS', '1999-02-10', 21, '9 Dorton Drive', 96565613, 60597381, 'Colegio Fatima', 'Melloney Ladloe', 'Melloney Ladloe');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (51, 'Clemmy', 'Challiner', '1A2121ADC', '2003-03-01', 17, '50442 Doe Crossing Junction', 20419894, 82815859, 'Colegio Nazareth', 'Clemmy Challiner', 'Clemmy Challiner');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (52, 'Gery', 'Ferreras', '4AF221ADC', '2005-08-02', 15, '6301 Prairie Rose Place', 72271704, 17783348, 'Colegio Santa Celicia', 'Gery Ferreras', 'Gery Ferreras');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (53, 'Lonnie', 'Di Giacomo', '1A21S2A22', '2000-01-01', 20, '7 Caliangt Lane', 72615751, 83787048, 'Colegio Nazareth', 'Lonnie Di Giacomo', 'Lonnie Di Giacomo');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (54, 'Rodie', 'Gladding', 'TDG35EYD8', '1999-02-10', 21, '2 Mockingbird Pass', 33304205, 91723367, 'Colegio Nazareth', 'Rodie Gladding', 'Rodie Gladding');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (55, 'Phylis', 'Burgwin', '63YD65FSL', '2003-03-01', 17, '891 Chive Lane', 12557390, 72982800, 'Colegio Fatima', 'Phylis Burgwin', 'Phylis Burgwin');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (56, 'Chrisy', 'Inch', '9874SU82', '2005-08-02', 15, '5745 Maywood Parkway', 16597337, 46221786, 'Colegio Nazareth', 'Chrisy Inch', 'Chrisy Inch');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (57, 'Anni', 'Swindell', '8UT5ED37E', '2000-01-01', 20, '4 Towne Trail', 64858415, 20757885, 'Colegio Nazareth', 'Anni Swindell', 'Anni Swindell');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (58, 'Dexter', 'Wisam', '98UEED21K', '1999-02-10', 21, '69319 Bellgrove Center', 24743274, 30585642, 'Colegio Fatima', 'Dexter Wisam', 'Dexter Wisam');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (59, 'Olympe', 'Levitt', '987EHDNS', '2003-03-01', 17, '6162 Graedel Road', 97677989, 64489530, 'Colegio Nazareth', 'Olympe Levitt', 'Olympe Levitt');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (60, 'Felipa', 'Krugmann', '09HE653WS', '2005-08-02', 15, '3 Truax Pass', 65631104, 93517720, 'Colegio Fatima', 'Felipa Krugmann', 'Felipa Krugmann');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (61, 'Junia', 'Bendare', '1A2121ADC', '2000-01-01', 20, '123 Fairview Junction', 45277167, 91368418, 'Colegio Fatima', 'Junia Bendare', 'Junia Bendare');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (62, 'Lindy', 'Gounard', '4AF221ADC', '1999-02-10', 21, '2289 Reinke Center', 51655086, 97917424, 'Colegio Nazareth', 'Lindy Gounard', 'Lindy Gounard');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (63, 'Gunar', 'Paddingdon', '1A21S2A22', '2003-03-01', 17, '43 Little Fleur Road', 16210462, 33911041, 'Colegio Nazareth', 'Gunar Paddingdon', 'Gunar Paddingdon');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (64, 'Ibrahim', 'Saffer', 'TDG35EYD8', '2005-08-02', 15, '30 Gulseth Park', 68090143, 52396164, 'Colegio Nazareth', 'Ibrahim Saffer', 'Ibrahim Saffer');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (65, 'Marianna', 'Ferruzzi', '63YD65FSL', '2000-01-01', 20, '7 Jenifer Crossing', 28767250, 65209312, 'Colegio Fatima', 'Marianna Ferruzzi', 'Marianna Ferruzzi');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (66, 'Leroy', 'Blemings', '9874SU82', '1999-02-10', 21, '319 Michigan Place', 32523302, 30712609, 'Colegio Fatima', 'Leroy Blemings', 'Leroy Blemings');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (67, 'Dayna', 'Templeton', '8UT5ED37E', '2003-03-01', 17, '09 Longview Lane', 44261110, 31105931, 'Colegio Nazareth', 'Dayna Templeton', 'Dayna Templeton');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (68, 'Maiga', 'Morbey', '98UEED21K', '2005-08-02', 15, '8 Hoepker Court', 93133023, 53498176, 'Colegio Santa Celicia', 'Maiga Morbey', 'Maiga Morbey');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (69, 'Rennie', 'Jee', '987EHDNS', '2000-01-01', 20, '2 Bay Avenue', 26636471, 31480150, 'Colegio Nazareth', 'Rennie Jee', 'Rennie Jee');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (70, 'Maurice', 'Houndesome', '09HE653WS', '1999-02-10', 21, '001 Melody Place', 56043598, 35399086, 'Colegio Nazareth', 'Maurice Houndesome', 'Maurice Houndesome');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (71, 'Alvin', 'Davley', '1A2121ADC', '2003-03-01', 17, '1 Pleasure Lane', 69295764, 13163868, 'Colegio Fatima', 'Alvin Davley', 'Alvin Davley');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (72, 'Madalena', 'Gymlett', '4AF221ADC', '2005-08-02', 15, '1 Talisman Terrace', 19195725, 87011156, 'Colegio Nazareth', 'Madalena Gymlett', 'Madalena Gymlett');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (73, 'Jan', 'O''Loughane', '1A21S2A22', '2000-01-01', 20, '3 Dixon Road', 64769526, 96239054, 'Liceo Salvadoreño', 'Jan O''Loughane', 'Jan O''Loughane');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (74, 'Granville', 'Stapforth', 'TDG35EYD8', '1999-02-10', 21, '190 Nelson Street', 60585321, 20299180, 'Colegio Nazareth', 'Granville Stapforth', 'Granville Stapforth');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (75, 'Berti', 'Fardo', '63YD65FSL', '2003-03-01', 17, '300 Dwight Circle', 63864242, 77754749, 'Liceo Salvadoreño', 'Berti Fardo', 'Berti Fardo');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (76, 'Ave', 'Harrhy', '9874SU82', '2005-08-02', 15, '68 Almo Lane', 35675593, 14139561, 'Colegio Santa Celicia', 'Ave Harrhy', 'Ave Harrhy');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (77, 'Reagen', 'Scargle', '8UT5ED37E', '2000-01-01', 20, '18133 Drewry Parkway', 20480581, 26360475, 'Colegio Santa Celicia', 'Reagen Scargle', 'Reagen Scargle');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (78, 'Nollie', 'Bezants', '98UEED21K', '1999-02-10', 21, '05 Calypso Road', 84510897, 59993547, 'Colegio Fatima', 'Nollie Bezants', 'Nollie Bezants');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (79, 'Cody', 'Hubball', '987EHDNS', '2003-03-01', 17, '7933 Homewood Terrace', 86987598, 86160257, 'Colegio Santa Celicia', 'Cody Hubball', 'Cody Hubball');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (80, 'Rodrigo', 'Gunnell', '09HE653WS', '2005-08-02', 15, '24 Gale Road', 85549076, 33306462, 'Liceo Salvadoreño', 'Rodrigo Gunnell', 'Rodrigo Gunnell');

insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (1, '2020IN', 16, 8.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (2, '2020MA', 12, 9.1, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (3, '2020LE', 3, 6.3, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (4, '2020MA', 8, 8.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (5, '2020SE', 19, 7.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (6, '2020MA', 2, 9.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (7, '2020LE', 2, 9.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (8, '2020MA', 11, 7.1, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (9, '2020MA', 5, 7.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (10, '2020MA', 13, 6.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (11, '2020SE', 9, 6.3, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (12, '2020LE', 4, 7.9, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (13, '2020LE', 15, 8.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (14, '2020LE', 2, 9.5, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (15, '2020CI', 3, 6.8, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (16, '2020IN', 6, 7.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (17, '2020CI', 4, 6.3, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (18, '2020CI', 6, 9.3, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (19, '2020LE', 16, 7.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (20, '2020IN', 5, 8.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (21, '2020CI', 6, 6.9, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (22, '2020CI', 20, 6.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (23, '2020SE', 9, 9.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (24, '2020IN', 13, 6.1, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (25, '2020SE', 20, 8.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (26, '2020SO', 17, 7.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (27, '2020CI', 11, 7.0, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (28, '2020CI', 20, 7.5, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (29, '2020CI', 1, 8.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (30, '2020IN', 20, 6.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (31, '2020SO', 14, 8.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (32, '2020LE', 16, 6.9, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (33, '2020IN', 20, 9.0, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (34, '2020SE', 20, 8.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (35, '2020SE', 14, 8.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (36, '2020LE', 18, 9.4, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (37, '2020IN', 17, 6.5, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (38, '2020SE', 15, 6.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (39, '2020CI', 19, 6.1, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (40, '2020CI', 9, 6.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (41, '2020CI', 19, 7.6, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (42, '2020LE', 15, 9.0, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (43, '2020LE', 4, 9.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (44, '2020SO', 7, 9.1, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (45, '2020MA', 1, 7.5, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (46, '2020SO', 12, 7.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (47, '2020LE', 13, 7.7, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (48, '2020SE', 11, 8.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (49, '2020MA', 5, 9.2, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (50, '2020SE', 7, 7.9, 'Aprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (51, '2020SE', 12, 0.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (52, '2020CI', 8, 1.0, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (53, '2020SE', 8, 4.3, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (54, '2020CI', 3, 3.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (55, '2020IN', 17, 5.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (56, '2020IN', 15, 0.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (57, '2020LE', 6, 5.6, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (58, '2020LE', 17, 4.3, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (59, '2020CI', 16, 4.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (60, '2020CI', 2, 5.5, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (61, '2020MA', 15, 3.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (62, '2020LE', 19, 0.5, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (63, '2020LE', 5, 5.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (64, '2020LE', 13, 0.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (65, '2020IN', 4, 3.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (66, '2020SO', 6, 2.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (67, '2020SO', 7, 5.3, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (68, '2020MA', 18, 2.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (69, '2020CI', 20, 2.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (70, '2020SO', 3, 3.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (71, '2020LE', 11, 4.5, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (72, '2020SO', 17, 0.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (73, '2020LE', 5, 4.7, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (74, '2020SE', 16, 4.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (75, '2020IN', 15, 0.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (76, '2020LE', 17, 2.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (77, '2020SO', 9, 1.9, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (78, '2020CI', 3, 0.6, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (79, '2020MA', 10, 5.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (80, '2020SO', 3, 2.6, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (81, '2020LE', 17, 0.9, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (82, '2020SO', 9, 2.9, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (83, '2020LE', 8, 4.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (84, '2020SO', 3, 2.0, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (85, '2020IN', 7, 4.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (86, '2020SO', 3, 4.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (87, '2020CI', 10, 4.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (88, '2020CI', 10, 3.9, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (89, '2020LE', 16, 3.0, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (90, '2020CI', 10, 5.9, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (91, '2020IN', 10, 3.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (92, '2020SE', 12, 3.7, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (93, '2020MA', 13, 3.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (94, '2020CI', 9, 1.2, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (95, '2020CI', 19, 2.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (96, '2020IN', 15, 4.5, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (97, '2020CI', 3, 1.4, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (98, '2020SO', 4, 3.8, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (99, '2020SO', 4, 1.1, 'Reprobado');
insert into alumnoxmateria (c_alumnoxmateria, cod_materia, c_expediente, s_nota, s_estado) values (100, '2020LE', 17, 4.1, 'Reprobado');
