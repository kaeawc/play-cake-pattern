
  # --- !Ups

INSERT INTO user (name,email,password,salt) VALUES ('alice','alice@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('ben','ben@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('carl','carl@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('danielle','danielle@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('earl','earl@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('fred','fred@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('gina','gina@company.com','password','salt');
INSERT INTO user (name,email,password,salt) VALUES ('harry','harry@company.com','password','salt');

  # --- !Downs

DELETE FROM user;