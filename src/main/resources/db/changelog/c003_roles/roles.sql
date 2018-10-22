drop table if exists roles;
CREATE TABLE backend.dbo.roles
(
    id int PRIMARY KEY NOT NULL IDENTITY,
    role_name varchar(10) NOT NULL
)
CREATE UNIQUE INDEX roles_id_uindex ON backend.dbo.roles (id)
CREATE UNIQUE INDEX roles_role_name_uindex ON backend.dbo.roles (role_name)

insert into roles (role_name)
    values ('admin'),('user');

alter table users
    add role_id int not null
    constraint DK_RoleId default 2 with values
    constraint FK_RoleId foreign key (role_id) references roles(id) on delete no action;