create table if not exists accident (
  id serial PRIMARY KEY,
  name varchar(100),
  text varchar(10000) not null,
  address varchar(100) not null
);