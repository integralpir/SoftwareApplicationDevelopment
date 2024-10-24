--liquibase formatted sql

--changeset rporlov:1

CREATE TABLE game_series
(
    title               varchar NOT NULL PRIMARY KEY
);

CREATE TABLE amiibo_series
(
    title               varchar NOT NULL PRIMARY KEY,
    game_series_title   varchar NOT NULL REFERENCES game_series(title)
);

CREATE TABLE amiibo
(
    id                  int     NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
    amiibo_series_title varchar NOT NULL REFERENCES amiibo_series(title),
    game_series_title   varchar NOT NULL REFERENCES game_series(title),
    character           varchar NOT NULL,
    image_link          varchar NOT NULL,
    name                varchar NOT NULL,
    type                varchar NOT NULL
);
