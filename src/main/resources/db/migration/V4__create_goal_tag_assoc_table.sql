CREATE TABLE goal_tag_assoc (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  goal_id bigint(20) NOT NULL,
  tag_id bigint(20) NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (goal_id) REFERENCES goal(id),
  FOREIGN KEY (tag_id) REFERENCES tag(id)
);
