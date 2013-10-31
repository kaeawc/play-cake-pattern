
  # --- !Ups

INSERT INTO user (name,email,password,salt,age) VALUES ('tommy','tommy@company.com','password','salt', 26);
INSERT INTO user (name,email,password,salt,age) VALUES ('josh','josh@company.com','password','salt', 52);

  # --- !Downs

DELETE FROM user;