--liquibase formatted sql

--changeset rporlov:1

CREATE TABLE game_series
(
    id                  int     NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
    title               varchar NOT NULL
);

CREATE TABLE amiibo_series
(
    id                  int     NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
    game_series_id      int     NOT NULL REFERENCES amiibo(id),
    title               varchar NOT NULL
);

CREATE TABLE amiibo
(
    id                  int     NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
    amiibo_series_id    int NOT NULL UNIQUE REFERENCES amiibo_series(id),
    game_series_id      int NOT NULL UNIQUE game_series(id),
    character           varchar NOT NULL,
    image_link          varchar NOT NULL,
    name                varchar NOT NULL,
    type                varchar NOT NULL
);

CREATE TABLE release
(
    id                  int     NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE PRIMARY KEY,
    amiibo_id           int     NOT NULL UNIQUE REFERENCES amiibo(id),
    au                  varchar NOT NULL,
    eu                  varchar NOT NULL,
    jp                  varchar NOT NULL,
    na                  varchar NOT NULL
);
