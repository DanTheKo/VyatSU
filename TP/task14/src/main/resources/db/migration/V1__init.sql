-- -- SET search_path TO boot;
-- CREATE TABLE products (id serial, title varchar(100), price int);
--
-- INSERT INTO products (title, price) VALUES ('Bread', 40), ('Milk', 80);
--
-- CREATE TABLE users (
--     username VARCHAR(50) NOT NULL,
--     password VARCHAR(100) NOT NULL,
--     enabled boolean NOT NULL,
--     PRIMARY KEY (username)
-- );
--
-- INSERT INTO users
-- VALUES
-- ('user1', '{noop}123', true),
-- ('user2', '{noop}123', true);
--
-- CREATE TABLE authorities (
--     username varchar(50) NOT NULL,
--     authority varchar(50) NOT NULL,
--
--     CONSTRAINT authorities_idx UNIQUE (username, authority),
--
--     CONSTRAINT authorities_ibfk_1
--     FOREIGN KEY (username)
--     REFERENCES users (username)
-- );
--
-- INSERT INTO authorities
-- VALUES
-- ('user1', 'ROLE_ADMIN'),
-- ('user1', 'ROLE_USER'),
-- ('user2', 'ROLE_USER');

CREATE TABLE User_
(
    username  VARCHAR(30) NOT NULL PRIMARY KEY,
    real_name VARCHAR(30) NOT NULL,
    password  VARCHAR     NOT NULL
);

alter table User_
    owner to postgres;

INSERT INTO User_
VALUES ('user1', 'Админ', '1'),
       ('user2', 'Даниил', '1');
CREATE TABLE Authority
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk
        FOREIGN KEY (username)
            REFERENCES user_ (username)
);

INSERT INTO Authority
VALUES ('user1', 'ROLE_ADMIN'),
       ('user1', 'ROLE_USER'),
       ('user2', 'ROLE_USER');

create table Post
(
    post_id  bigserial primary key,
    post_text  varchar(50) not null,
    created_at timestamp not null,
    username   varchar(30)  references User_ (username)
);

alter table Post
    owner to postgres;

INSERT INTO Post
VALUES (default, 'Текст 1', '2023-05-05 10:25:31', 'user1'),
       (default, 'Текст 2', '2023-05-15 12:14:05', 'user1'),
       (default, 'Текст 3', '2023-05-30 13:02:14', 'user2');