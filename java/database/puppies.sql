BEGIN TRANSACTION;

DROP TABLE IF EXISTS puppy;
DROP TABLE IF EXISTS shelter;

DROP SEQUENCE IF EXISTS seq_puppy_id;
DROP SEQUENCE IF EXISTS seq_shelter_id;

CREATE SEQUENCE seq_shelter_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_puppy_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE shelter (
	shelter_id serial,
	shelter_name varchar(40) NOT NULL,
	shelter_address varchar(60) NOT NULL,
	shelter_city varchar(40) NOT NULL,
	shelter_state varchar(20) NOT NULL,
	shelter_zip varchar(40) NOT NULL,
	CONSTRAINT PK_shelter_id PRIMARY KEY (shelter_id)
);

CREATE TABLE puppy (
	puppy_id serial NOT NULL,
	puppy_name varchar(10) NOT NULL,
	puppy_breed varchar(40),
	puppy_age int ,
	puppy_gender varchar(10) NOT NULL,
	shelter_id int NOT NULL,
	CONSTRAINT PK_puppy_id PRIMARY KEY (puppy_id),
	CONSTRAINT FK_shelter FOREIGN KEY (shelter_id) REFERENCES shelter (shelter_id)
);



INSERT INTO shelter (shelter_name, shelter_address, shelter_city, shelter_state, shelter_zip) VALUES ('Snoopy"s Home For Dogs', '123 Brown St.', 'Pinetree Corners', 'Minnesota', '55101');
INSERT INTO shelter (shelter_name, shelter_address, shelter_city, shelter_state, shelter_zip) VALUES ('The Lassie Dog Shelter', '32 Willow St.', 'Minneapolis', 'Minnesota', '55105');
INSERT INTO shelter (shelter_name, shelter_address, shelter_city, shelter_state, shelter_zip) VALUES ('St. Paul Animal Shelter', '45645 Shelly Ave.', 'St. Paul', 'Minnesota', '55114');
INSERT INTO shelter (shelter_name, shelter_address, shelter_city, shelter_state, shelter_zip) VALUES ('Balto Animal Shelter', '5789 Green St.', 'Maplewood', 'Minnesota', '55119');
INSERT INTO shelter (shelter_name, shelter_address, shelter_city, shelter_state, shelter_zip) VALUES ('McGruff Home For Dogs', '4567 OVerTheHill Way', 'Highland', 'Minnesota', '55133');

INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Snoopy', 'Beagle', 7, 'Male', 1);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Millie', 'German Shepherd', 5, 'Female', 1);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Fido', 'Poodle Mix', 5, 'Male', 1);

INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Snuggles', 'Beagle Mix', 2, 'Female', 2);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Leila', 'Boxer Mix', 1, 'Female', 2);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Gibson', 'Labrador Mix', 1, 'Female', 2);

INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Max', 'Golden Retriever', 4, 'Male', 3);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Tango', 'Black Labrador', 4, 'Female', 3);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Sadie', 'Beagle', 4, 'Female', 3);

INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Pickles', 'Collie Mix', 2, 'Female', 4);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Millie', 'Golden Doodle', 2, 'Male', 4);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Della', 'Labrador Mix', 2, 'Male', 4);

INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Chips', 'St. Bernard', 3, 'Male', 5);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Checkers', 'Husky', 3, 'Male', 5);
INSERT INTO puppy (puppy_name, puppy_breed, puppy_age, puppy_gender, shelter_id) VALUES ('Paddy', 'Mutt', 3, 'Male', 5);


COMMIT TRANSACTION;
