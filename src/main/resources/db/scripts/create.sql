create table if not exists accident (
  id serial PRIMARY KEY,
  name varchar(100),
  date_of_creation timestamp(0) not null,
  image varchar(50),
  number varchar(20) not null,
  address varchar(100) not null,
  text varchar(10000) not null
);