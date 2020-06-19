CREATE TABLE tag (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tag varchar(15) NOT NULL,
  description varchar(1000) NOT NULL,
  user_id bigint(20) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
