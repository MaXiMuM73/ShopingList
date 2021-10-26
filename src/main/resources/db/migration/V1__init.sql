CREATE TABLE shopping.user
(
    id bigserial primary key NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) ,
    creation_date timestamptz NOT NULL,
    CONSTRAINT user_name_unique UNIQUE (username)
);

CREATE TABLE shopping.list
(
    id bigserial primary key NOT NULL,
    title character varying(255),
    content character varying(255),
    creation_date timestamptz NOT NULL,
    user_id bigint,
    CONSTRAINT user_id FOREIGN KEY (user_id)
        REFERENCES shopping.user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);