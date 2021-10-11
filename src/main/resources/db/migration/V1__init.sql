CREATE SEQUENCE IF NOT EXISTS shopping.user_sequence START 1;

CREATE SEQUENCE IF NOT EXISTS shopping.list_sequence START 1;

CREATE TABLE IF NOT EXISTS shopping.user
(
    id bigint NOT NULL DEFAULT nextval('shopping.user_sequence'),
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    creation_date timestamp without time zone NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_name_unique UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS shopping.list
(
    id bigint NOT NULL DEFAULT nextval('shopping.list_sequence'),
    title character varying(255) COLLATE pg_catalog."default",
    content character varying(255) COLLATE pg_catalog."default",
    creation_date timestamp without time zone NOT NULL,
    user_id bigint,
    CONSTRAINT list_pkey PRIMARY KEY (id),
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES shopping.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO shopping.user (username, password, creation_date)
VALUES ('user', 'user', now()), ('user2', 'user2', now());

INSERT INTO shopping.list (title, content, creation_date, user_id)
VALUES ('user title', 'user content', now(), 1), ('user2 title', 'user2 content', now(), 2);