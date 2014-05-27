
  # --- !Ups

INSERT INTO user (name,email,password,salt,created) VALUES ('alice','alice@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('ben','ben@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('carl','carl@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('danielle','danielle@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('earl','earl@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('fred','fred@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('gina','gina@company.com','password','salt','2014-01-01 00:00:00');
INSERT INTO user (name,email,password,salt,created) VALUES ('harry','harry@company.com','password','salt','2014-01-01 00:00:00');

  # --- !Downs

DELETE FROM user;
