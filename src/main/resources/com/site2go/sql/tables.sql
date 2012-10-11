create table site (
  id int not null auto_increment primary key,
  name varchar(255),
  domain varchar(255),
  default_layout_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);

create table page (
  id int not null auto_increment primary key,
  slug varchar(255),
  title varchar(255),
  meta_title varchar(255),
  meta_description varchar(255),
  meta_keywords varchar(255),
  site_id int,
  layout_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);

create table layout (
  id int not null auto_increment primary key,
  name varchar(255),
  slug varchar(255),
  template clob,
  site_id int,
  modified_date timestamp not null,
  created_date timestamp not null
);

create table user (
  id int not null auto_increment primary key,
  email varchar(255),
  password varchar(255),
  super_admin int(1),
  modified_date timestamp not null,
  created_date timestamp not null
);

create table site_users (
  site_id int not null,
  user_id int not null,
  primary key(site_id, user_id)
);
