CREATE TABLE shopping.role
(
    id bigserial primary key NOT NULL,
    code character varying(255) NOT NULL
);

CREATE TABLE shopping.user_role
(
    id serial primary key NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES shopping.user (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT role_id FOREIGN KEY (role_id)
    REFERENCES shopping.role (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO shopping.role
VALUES (1, 'ADMIN'), (2, 'USER');

ALTER TABLE shopping.user
    ADD COLUMN email character varying(255) UNIQUE;