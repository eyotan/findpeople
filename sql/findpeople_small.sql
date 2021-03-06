﻿-- DROP DATABASE peoples;
CREATE DATABASE findpeoples
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE peoples
(
    id uuid NOT NULL,
    surname varchar(64) NOT NULL,
    name varchar(64) NOT NULL,     
    parentname varchar(64) NOT NULL,
    CONSTRAINT peoples_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

GRANT ALL ON TABLE peoples TO postgresreaduser;

COMMENT ON TABLE peoples
    IS 'Люди в БД';

COMMENT ON COLUMN peoples.id
    IS 'Уникальный номер';

COMMENT ON COLUMN peoples.surname
    IS 'Фамилия';

COMMENT ON COLUMN peoples.name
    IS 'Имя';

COMMENT ON COLUMN peoples.parentname
    IS 'Отчество';

CREATE INDEX surname_name
ON peoples (surname, name);

CREATE TABLE city
(
    id uuid NOT NULL,
    cityname varchar(64) NOT NULL,
    CONSTRAINT city_fkey FOREIGN KEY (id)
        REFERENCES peoples (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

GRANT ALL ON TABLE city TO postgresreaduser;

COMMENT ON TABLE city
    IS 'Город людей в БД';

COMMENT ON COLUMN city.id
    IS 'Уникальный номер';

COMMENT ON COLUMN city.cityname
    IS 'Название города';

CREATE TABLE cars
(
    id uuid NOT NULL,
    car varchar(64) NOT NULL,
    CONSTRAINT cars_fkey FOREIGN KEY (id)
        REFERENCES peoples (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

GRANT ALL ON TABLE cars TO postgresreaduser;

COMMENT ON TABLE cars
    IS 'Авто людей в БД';

COMMENT ON COLUMN cars.id
    IS 'Уникальный номер';

COMMENT ON COLUMN cars.car
    IS 'Модель авто';

CREATE TABLE admins
(
    id uuid NOT NULL,
    login varchar(64) NOT NULL UNIQUE,
    pass char(32) NOT NULL,
    CONSTRAINT admins_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

GRANT ALL ON TABLE admins TO postgresreaduser;

COMMENT ON TABLE admins
    IS 'Админы БД';

COMMENT ON COLUMN admins.id
    IS 'Уникальный номер пользователя';

COMMENT ON COLUMN admins.login
    IS 'Логин пользователя';

COMMENT ON COLUMN admins.pass
    IS 'Пароль пользователя';
