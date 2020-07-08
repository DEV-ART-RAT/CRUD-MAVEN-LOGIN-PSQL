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


insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (1, 'Margaretha', 'Souch', '1A2121ADC', '2000-01-01', 20, '99 Sunfield Parkway', 84198162, 90981010, 'Colegio Santa Celicia', 'Margaretha Souch', 'Margaretha Souch');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (2, 'Drusy', 'MacCaughan', '4AF221ADC', '2000-01-01', 20, '59366 Bluestem Street', 55639579, 75860789, 'Liceo Salvadoreño', 'Drusy MacCaughan', 'Drusy MacCaughan');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (3, 'Travers', 'Burburough', '1A21S2A22', '2000-01-01', 20, '7148 Claremont Hill', 89107824, 38004672, 'Liceo Salvadoreño', 'Travers Burburough', 'Travers Burburough');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (4, 'Aviva', 'Bartels', 'TDG35EYD8', '2000-01-01', 20, '3 1st Drive', 25417729, 31192206, 'Colegio Nazareth', 'Aviva Bartels', 'Aviva Bartels');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (5, 'Augustine', 'Poltun', '63YD65FSL', '2000-01-01', 20, '359 Twin Pines Way', 82302000, 78400113, 'Colegio Nazareth', 'Augustine Poltun', 'Augustine Poltun');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (6, 'Laraine', 'Christofle', '9874SU82', '2000-01-01', 20, '89903 Loeprich Way', 58116937, 43916102, 'Colegio Nazareth', 'Laraine Christofle', 'Laraine Christofle');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (7, 'Hedy', 'Taks', '8UT5ED37E', '2000-01-01', 20, '318 Bunker Hill Junction', 89571895, 75082594, 'Colegio Fatima', 'Hedy Taks', 'Hedy Taks');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (8, 'Delores', 'Rentenbeck', '98UEED21K', '2000-01-01', 20, '851 Blue Bill Park Drive', 30142279, 83812764, 'Colegio Fatima', 'Delores Rentenbeck', 'Delores Rentenbeck');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (9, 'Anderson', 'McCrudden', '987EHDNS', '2000-01-01', 20, '626 Del Mar Hill', 73011193, 92739870, 'Colegio Fatima', 'Anderson McCrudden', 'Anderson McCrudden');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (10, 'Darnell', 'Borsi', '09HE653WS', '2000-01-01', 20, '12065 Jackson Drive', 36458188, 14819214, 'Colegio Santa Celicia', 'Darnell Borsi', 'Darnell Borsi');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (11, 'Margarethe', 'Lyndon', '1A2121ADC', '2000-01-01', 20, '471 Mariners Cove Crossing', 56154340, 96318397, 'Liceo Salvadoreño', 'Margarethe Lyndon', 'Margarethe Lyndon');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (12, 'Nilson', 'Terry', '4AF221ADC', '2000-01-01', 20, '456 Monterey Drive', 67156660, 92794766, 'Colegio Nazareth', 'Nilson Terry', 'Nilson Terry');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (13, 'Kimbra', 'Croson', '1A21S2A22', '2000-01-01', 20, '20761 Pierstorff Plaza', 39731607, 25666672, 'Colegio Fatima', 'Kimbra Croson', 'Kimbra Croson');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (14, 'Lizbeth', 'Perrycost', 'TDG35EYD8', '2000-01-01', 20, '0625 Canary Plaza', 21320615, 37082890, 'Colegio Nazareth', 'Lizbeth Perrycost', 'Lizbeth Perrycost');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (15, 'Shannah', 'Presslee', '63YD65FSL', '2000-01-01', 20, '75 8th Hill', 81784785, 41047462, 'Colegio Fatima', 'Shannah Presslee', 'Shannah Presslee');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (16, 'Fidelia', 'MacCard', '9874SU82', '2000-01-01', 20, '9 Debs Pass', 27493486, 76925402, 'Colegio Nazareth', 'Fidelia MacCard', 'Fidelia MacCard');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (17, 'Brocky', 'Behnke', '8UT5ED37E', '2000-01-01', 20, '198 Kennedy Parkway', 37695849, 79363554, 'Liceo Salvadoreño', 'Brocky Behnke', 'Brocky Behnke');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (18, 'Nona', 'Buxey', '98UEED21K', '2000-01-01', 20, '786 Stone Corner Circle', 82676944, 45850930, 'Liceo Salvadoreño', 'Nona Buxey', 'Nona Buxey');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (19, 'Sephira', 'Billham', '987EHDNS', '2000-01-01', 20, '83 Arrowood Way', 90798883, 14303232, 'Liceo Salvadoreño', 'Sephira Billham', 'Sephira Billham');
insert into expediente (c_expediente, s_nombre, s_apellido, s_carne, d_fnacimiento, s_edad, s_direccion, s_telefonof, s_telefonom, s_institucion, s_nombrePadre, s_nombreMadre) values (20, 'Clarie', 'Chanter', '09HE653WS', '2000-01-01', 20, '0 Laurel Pass', 86629237, 55805598, 'Colegio Fatima', 'Clarie Chanter', 'Clarie Chanter');

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
