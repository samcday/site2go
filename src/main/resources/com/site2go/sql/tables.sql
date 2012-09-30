create table site (
  id int not null auto_increment primary key,
  name varchar(255),
  domain varchar(255),
  default_layout_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);

create table page (
  id int not null primary key,
  name varchar(255),
  meta_title varchar(255),
  meta_description varchar(255),
  meta_keywords varchar(255),
  layout_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);

create table layout (
  id int not null primary key,
  name varchar(255),
  site_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);