-- liquibase formatted sql

-- changeset Admin:1720535451775-1
CREATE TABLE hall
(
    id    UUID         NOT NULL,
    name  VARCHAR(32)  NOT NULL,
    seats VARCHAR(255) NOT NULL,
    CONSTRAINT pk_hall PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-2
CREATE TABLE movie
(
    id          UUID        NOT NULL,
    title       VARCHAR(32) NOT NULL,
    duration INTERVAL NOT NULL,
    description VARCHAR(512),
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-3
CREATE TABLE place
(
    id           UUID        NOT NULL,
    place_number VARCHAR(32) NOT NULL,
    is_occupied  BOOLEAN,
    row_id       UUID        NOT NULL,
    CONSTRAINT pk_place PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-4
CREATE TABLE row
(
    id         UUID        NOT NULL,
    row_number VARCHAR(32) NOT NULL,
    session_id UUID        NOT NULL,
    CONSTRAINT pk_row PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-5
CREATE TABLE session
(
    id         UUID    NOT NULL,
    movie_id   UUID    NOT NULL,
    hall_id    UUID    NOT NULL,
    start_time time WITHOUT TIME ZONE NOT NULL,
    end_time   time WITHOUT TIME ZONE NOT NULL,
    price      INTEGER NOT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-6
CREATE TABLE ticket
(
    id         UUID NOT NULL,
    user_id    UUID,
    session_id UUID,
    place_id   UUID,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-7
CREATE TABLE "user"
(
    id      UUID         NOT NULL,
    phone   VARCHAR(11)  NOT NULL,
    chat_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- changeset Admin:1720535451775-8
ALTER TABLE "user"
    ADD CONSTRAINT uc_user_phone UNIQUE (phone);

-- changeset Admin:1720535451775-9
ALTER TABLE place
    ADD CONSTRAINT FK_PLACE_ON_ROWID FOREIGN KEY (row_id) REFERENCES row (id);

-- changeset Admin:1720535451775-10
ALTER TABLE row
    ADD CONSTRAINT FK_ROW_ON_SESSION FOREIGN KEY (session_id) REFERENCES session (id);

-- changeset Admin:1720535451775-11
ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_HALL FOREIGN KEY (hall_id) REFERENCES hall (id);

-- changeset Admin:1720535451775-12
ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset Admin:1720535451775-13
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (id);

-- changeset Admin:1720535451775-14
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_SESSION FOREIGN KEY (session_id) REFERENCES session (id);

-- changeset Admin:1720535451775-15
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);

