/*
drop table user_role;
drop table APP_role;
drop table APP_USER;
drop table persistent_logins;
*/

create table APP_USER
(
  USER_ID           BIGINT,
  USER_NAME         VARCHAR(36) not null,
  ENCRYTED_PASSWORD VARCHAR(128) not null,
  ENABLED           Boolean not null 
) ;
--  
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



insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true);
 
insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', true);
 
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


/*
select * from app_user;
select * from user_role;
*/

/*
select * from app_user;
select * from app_role;
select * from user_role;
select * from persistent_logins;
*/
CREATE TABLE public.alumnoxmateria(
	c_alumnoxmateria integer not null primary key
	cod_materia varchar(6) not null  ,
	c_expediente integer not null  ,
	s_nota varchar(3) not null,
	s_estado varchar(10) not null
)
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

CREATE TABLE public.minicipio(
	s_minicipio int PRIMARY KEY not null,
	s_nombre varchar(50) not null
);

CREATE TABLE public.dpto(
	s_depto int PRIMARY KEY not null,
	s_nombre varchar(50) not null
);



insert into minicipio (s_minicipio, s_nombre)
values (1, 'municipio Libertad Prro');

insert into minicipio (s_minicipio, s_nombre)
values (2, 'municipio SS Prro');

insert into dpto (s_depto , s_nombre)
values (1, 'Libertad Prro');

insert into dpto (s_depto, s_nombre)
values (2, 'SS ');


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

-- drop table public.expediente

-- select * from public.expediente