CREATE TABLE users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(20),
  salary DECIMAL
);

INSERT INTO users (name, salary) VALUES ('Ivan', 1500);
INSERT INTO users (name, salary) VALUES ('Miron', 1000);