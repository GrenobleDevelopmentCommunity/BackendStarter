drop table if exists roles;
create table roles (
	id int not null primary key identity(1,1),
  roleName VARCHAR(10) not null unique
);

insert into roles (roleName)
    values ('admin'),('user');

alter table users
    add roleID int not null
    constraint DK_RoleId default 2 with values
    constraint FK_RoleId foreign key (roleID) references roles(id) on delete no action;

update users
    set roleID = 1
    where email='admin@admin.admin';