
  # --- !Ups

INSERT INTO user (name,email,password,salt,created) VALUES ('tommy','tommy@company.com','password','salt', '2014-04-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('josh','josh@company.com','password','salt', '2014-04-01 00:00:00');

  # --- !Downs

DELETE FROM user;
