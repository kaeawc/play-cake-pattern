
  # --- !Ups

CREATE TABLE user (
  id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name             VARCHAR(255) NOT NULL,
  email            VARCHAR(255) NOT NULL,
  password         VARCHAR(255) NOT NULL,
  salt             VARCHAR(255) NOT NULL,
  active           BOOLEAN   NOT NULL DEFAULT 1
);

CREATE INDEX USERACTIVEINDEX ON user(active);
CREATE INDEX USEREMAILINDEX ON user(email);

  # --- !Downs

DROP TABLE user;