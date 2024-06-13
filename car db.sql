create database AutosDb;

use AutosDb;

create table Car(
	id INT PRIMARY KEY auto_increment,
    plate VARCHAR(20) NOT NULL,
    brand VARCHAR(50),
    model VARCHAR(50),
    color VARCHAR(30),
    ownerName VARCHAR(100)
);
