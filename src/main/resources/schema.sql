DROP TABLE IF EXISTS QUOTE;

CREATE TABLE QUOTE (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       quote VARCHAR(2500) NOT NULL,
                       author VARCHAR(250) NOT NULL
);