BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS player;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE player (
    player_id integer UNIQUE,
    first_name varchar(25) NOT NULL,
    last_name varchar(30) NOT NULL,
    CONSTRAINT PK_player PRIMARY KEY (player_id)
);

COMMIT TRANSACTION;
