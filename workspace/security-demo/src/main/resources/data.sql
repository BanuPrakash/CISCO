
insert into users(username, password, enabled) values ('brad', '$2a$12$HoJj5vKHl7zCvKcDnUrHbesmvgn4xGbvpvihM43FhAydpNVSR9LTu', 1);

insert into users(username, password, enabled) values ('rita', '$2a$12$0haXohQ/3onD26i1q0ZTQOx9.rENFp8/RO52nfuSnH6G6rS7GzQPu', 1);

insert into authorities(username, authority) values ('rita', 'ROLE_ADMIN');
insert into authorities(username, authority) values ('rita', 'ROLE_GUEST');
insert into authorities(username, authority) values ('brad', 'ROLE_GUEST')