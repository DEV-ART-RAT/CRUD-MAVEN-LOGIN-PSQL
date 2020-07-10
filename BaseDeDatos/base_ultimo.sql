
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

---
 


CREATE TABLE public.alumnoxmateria(
	c_alumnoxmateria integer primary key,
	cod_materia varchar(6) not null  ,
	c_expediente integer not null  ,
	s_nota float not null,
	s_estado varchar(10) not null,
	i_annio_materia integer not null,
	s_ciclo varchar(2) not null	
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
	s_institucion int not null,
	s_nombrePadre varchar(50) not null,
	s_nombreMadre varchar(50) not null
);
ALTER TABLE public.alumnoxmateria ADD CONSTRAINT fk_cod_materia FOREIGN KEY(cod_materia) REFERENCES public.materia(cod_materia);
ALTER TABLE public.alumnoxmateria ADD CONSTRAINT fk_c_expediente FOREIGN KEY(c_expediente) REFERENCES public.expediente(c_expediente);

create table public.centroescolar(
	s_institucion int primary key,
	s_nombre  varchar(50) not null,
	s_descripcion  varchar(500) not null,
	s_dpto  int not null,
	s_municipio  int not null
);

ALTER TABLE public.expediente ADD CONSTRAINT fk_c_institucion FOREIGN KEY(s_institucion) REFERENCES public.centroescolar(s_institucion);
