insert into users (username, password, enabled) values ('roger', '$2a$12$HBW2uom9ibQ6tSf8YSFJhevSAPqS7PcfO6ongGY1C4TL2LXHwPSvK', 1);
insert into users (username, password, enabled) values ('ria', '$2a$12$oR9pHPfCcOog8SfSgbvN3epA80jgQsLhsUjFKyHZWXIEkGH.JkPFS', 1);

insert into authorities (username, authority) values ('roger', 'ROLE_READ');
insert into authorities (username, authority) values ('ria', 'ROLE_READ');
insert into authorities (username, authority) values ('ria', 'ROLE_ADMIN');

