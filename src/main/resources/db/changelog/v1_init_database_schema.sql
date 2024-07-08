-- liquibase formatted sql

-- changeset Admin:1720450890589-1
CREATE TABLE hall
(
    id       UUID         NOT NULL,
    name     VARCHAR(32)  NOT NULL,
    capacity INTEGER      NOT NULL,
    seats    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_hall PRIMARY KEY (id)
);

