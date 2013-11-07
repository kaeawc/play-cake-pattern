  # --- !Ups

  alter table user
  add column age integer not null default 0;

  # --- !Downs

  alter table user
  drop column age;