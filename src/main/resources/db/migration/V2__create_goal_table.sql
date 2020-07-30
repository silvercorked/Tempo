CREATE TABLE goal (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  parent_id bigint(20) DEFAULT NULL,
  goal varchar(100) NOT NULL,
  description varchar(1000) NOT NULL,
  progress bigint(20) NOT NULL,
  target bigint(20) NOT NULL,
  due_date DATETIME NOT NULL,
  user_id bigint(20) NOT NULL,
  recurrence_num int NOT NULL,
  recurrence_freq varchar(30) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
