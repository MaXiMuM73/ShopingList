CREATE TABLE IF NOT EXISTS shopping.role
(
    id bigint NOT NULL,
    code character varying(255) NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shopping.user_role
(
    id serial NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id),
    CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES shopping.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT role_id FOREIGN KEY (role_id)
    REFERENCES shopping.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO shopping.role
VALUES (1, 'ADMIN'), (2, 'USER');

ALTER TABLE shopping.user
    ADD COLUMN email character varying(255) UNIQUE;