create table if not exists accident (
  id serial PRIMARY KEY,
  name varchar(100),
  text varchar(10000),
  address varchar(100),
  accident_type_id int references accident_type(id)
);

create table if not exists accident_type (
  id serial PRIMARY KEY,
  type_id int,
  type_name varchar(50)
);

create table if not exists accident_rule (
  id serial PRIMARY KEY,
  accident_id int references accident(id),
  rule_id int,
  rule_name varchar(50)
)