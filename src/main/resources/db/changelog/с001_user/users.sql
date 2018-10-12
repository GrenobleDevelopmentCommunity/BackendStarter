CREATE TABLE users
(
    id int NOT NULL IDENTITY,
    email varchar(256) NOT NULL,
    password text NOT NULL
)
CREATE UNIQUE INDEX users_id_uindex ON users (id)
CREATE UNIQUE INDEX users_email_uindex ON users (email)

insert INTO users values ('admin@admin.admin', 'admin');