create table person
(
    id       serial primary key   not null,
    login    varchar(2000) unique not null,
    password varchar(2000)        not null
);

insert into person (login, password)
values ('parsentev', '123');
insert into person (login, password)
values ('ban', '123');
insert into person (login, password)
values ('ivan', '123');