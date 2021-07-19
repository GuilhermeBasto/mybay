CREATE TABLE mybay.User
(
userId INT ,
name VARCHAR(255),
password VARCHAR(255),
email VARCHAR(255),
country VARCHAR(255),
PRIMARY KEY (userId)

);

CREATE TABLE mybay.Item
(
itemId INT,
name VARCHAR(255),
category VARCHAR(255),
originCountry VARCHAR(255),
userId INT,
date Date,
photograph mediumBlob,
PRIMARY KEY (itemId),
FOREIGN KEY (userId) REFERENCES User(userId)
);
