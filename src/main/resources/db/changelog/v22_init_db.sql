-- liquibase formatted sql

-- changeset Admin:1720691439497-1
CREATE TABLE hall
(
    id    UUID         NOT NULL,
    name  VARCHAR(32)  NOT NULL,
    seats VARCHAR(255) NOT NULL,
    CONSTRAINT pk_hall PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-2
CREATE TABLE movie
(
    id          UUID        NOT NULL,
    title       VARCHAR(32) NOT NULL,
    duration    numeric     NOT NULL,
    description VARCHAR(512),
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-3
CREATE TABLE place
(
    id           UUID    NOT NULL,
    place_number INTEGER NOT NULL,
    status       SMALLINT,
    row_id       UUID    NOT NULL,
    CONSTRAINT pk_place PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-4
CREATE TABLE row
(
    id         UUID    NOT NULL,
    row_number INTEGER NOT NULL,
    session_id UUID    NOT NULL,
    CONSTRAINT pk_row PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-5
CREATE TABLE session
(
    id         UUID    NOT NULL,
    movie_id   UUID    NOT NULL,
    hall_id    UUID    NOT NULL,
    start_time time WITHOUT TIME ZONE NOT NULL,
    end_time   time WITHOUT TIME ZONE NOT NULL,
    start_date date    NOT NULL,
    price      INTEGER NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-6
CREATE TABLE ticket
(
    id           UUID         NOT NULL,
    user_id      UUID,
    booking_code VARCHAR(255) NOT NULL,
    session_id   UUID,
    place_id     UUID,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-7
CREATE TABLE user_table
(
    id      UUID        NOT NULL,
    phone   VARCHAR(11) NOT NULL,
    chat_id BIGINT      NOT NULL,
    CONSTRAINT pk_user_table PRIMARY KEY (id)
);

-- changeset Admin:1720691439497-8
ALTER TABLE user_table
    ADD CONSTRAINT uc_user_table_phone UNIQUE (phone);

-- changeset Admin:1720691439497-9
ALTER TABLE place
    ADD CONSTRAINT FK_PLACE_ON_ROWID FOREIGN KEY (row_id) REFERENCES row (id);

-- changeset Admin:1720691439497-10
ALTER TABLE row
    ADD CONSTRAINT FK_ROW_ON_SESSION FOREIGN KEY (session_id) REFERENCES session (id);

-- changeset Admin:1720691439497-11
ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_HALL FOREIGN KEY (hall_id) REFERENCES hall (id);

-- changeset Admin:1720691439497-12
ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset Admin:1720691439497-13
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (id);

-- changeset Admin:1720691439497-14
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_SESSION FOREIGN KEY (session_id) REFERENCES session (id);

-- changeset Admin:1720691439497-15
ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES user_table (id);

