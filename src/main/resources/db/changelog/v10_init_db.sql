-- liquibase formatted sql
CREATE SCHEMA cinema;
-- changeset Tuf15:1720603805549-1
CREATE TABLE cinema.hall (id UUID NOT NULL, name VARCHAR(32) NOT NULL, seats VARCHAR(255) NOT NULL, CONSTRAINT pk_hall PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-2
CREATE TABLE cinema.movie (id UUID NOT NULL, title VARCHAR(32) NOT NULL, duration INTERVAL NOT NULL, description VARCHAR(512), CONSTRAINT pk_movie PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-3
CREATE TABLE cinema.place (id UUID NOT NULL, place_number VARCHAR(32) NOT NULL, is_occupied BOOLEAN, row_id UUID NOT NULL, CONSTRAINT pk_place PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-4
CREATE TABLE cinema.row (id UUID NOT NULL, row_number VARCHAR(32) NOT NULL, session_id UUID NOT NULL, CONSTRAINT pk_row PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-5
CREATE TABLE cinema.session (id UUID NOT NULL, movie_id UUID NOT NULL, hall_id UUID NOT NULL, start_time time WITHOUT TIME ZONE NOT NULL, end_time time WITHOUT TIME ZONE NOT NULL, price INTEGER NOT NULL, CONSTRAINT pk_session PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-6
CREATE TABLE cinema.ticket (id UUID NOT NULL, user_id UUID, session_id UUID, place_id UUID, CONSTRAINT pk_ticket PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-7
CREATE TABLE cinema."user" (id UUID NOT NULL, phone VARCHAR(11) NOT NULL, chat_id VARCHAR(255) NOT NULL, CONSTRAINT pk_user PRIMARY KEY (id));

-- changeset Tuf15:1720603805549-8
ALTER TABLE cinema."user" ADD CONSTRAINT uc_user_phone UNIQUE (phone);

-- changeset Tuf15:1720603805549-9
ALTER TABLE cinema.place ADD CONSTRAINT FK_PLACE_ON_ROWID FOREIGN KEY (row_id) REFERENCES cinema.row (id);

-- changeset Tuf15:1720603805549-10
ALTER TABLE cinema.row ADD CONSTRAINT FK_ROW_ON_SESSION FOREIGN KEY (session_id) REFERENCES cinema.session (id);

-- changeset Tuf15:1720603805549-11
ALTER TABLE cinema.session ADD CONSTRAINT FK_SESSION_ON_HALL FOREIGN KEY (hall_id) REFERENCES cinema.hall (id);

-- changeset Tuf15:1720603805549-12
ALTER TABLE cinema.session ADD CONSTRAINT FK_SESSION_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES cinema.movie (id);

-- changeset Tuf15:1720603805549-13
ALTER TABLE cinema.ticket ADD CONSTRAINT FK_TICKET_ON_PLACE FOREIGN KEY (place_id) REFERENCES cinema.place (id);

-- changeset Tuf15:1720603805549-14
ALTER TABLE cinema.ticket ADD CONSTRAINT FK_TICKET_ON_SESSION FOREIGN KEY (session_id) REFERENCES cinema.session (id);

-- changeset Tuf15:1720603805549-15
ALTER TABLE cinema.ticket ADD CONSTRAINT FK_TICKET_ON_USER FOREIGN KEY (user_id) REFERENCES cinema."user" (id);

