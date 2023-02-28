create table users(
                      id int not null AUTO_INCREMENT,
                      username varchar(50) not null,
                      password varchar(50) not null,
                      enabled boolean not null,
                      primary key(id)
);

create table authorities (
                             id int not null AUTO_INCREMENT,
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             primary key(id)
);

create table customer(
                         id int not null AUTO_INCREMENT,
                         email varchar(50) not null,
                         pwd varchar(200) not null,
                         role varchar(45) not null,
                         primary key(id)
);
