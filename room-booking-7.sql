CREATE DATABASE  IF NOT EXISTS accounts;
USE accounts;

DROP DATABASE accounts;

DROP TABLE IF EXISTS location;
CREATE TABLE location (
	locID int(10) NOT NULL,
	locName varchar(255) DEFAULT NULL,
	PRIMARY KEY (locID)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userID int(10) NOT NULL AUTO_INCREMENT,
  username varchar(255) DEFAULT NULL,
  userPassword varchar(255) DEFAULT NULL,
  userLocID int(10) NOT NULL,
  isAdmin boolean DEFAULT FALSE,
  PRIMARY KEY (userID),
  CONSTRAINT fk_userLocID_userID FOREIGN KEY (userLocID) REFERENCES location(locID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms (
	roomID int(10) NOT NULL,
	isAvailable BOOLEAN NOT NULL,
	roomLocID int(10) NOT NULL,
	PRIMARY KEY(roomID, roomLocID),
	CONSTRAINT fk_roomLocID_locID FOREIGN KEY (roomLocID) REFERENCES location(locID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS bookings;
CREATE TABLE bookings (
	bookingID int(10) NOT NULL,
	title varchar(50) NOT NULL,
	bookingRoomID int(10) NOT NULL,
	startDate timestamp NOT NULL,
    endDate timestamp NOT NULL,
	description varchar(500) NOT NULL,
	locationID int NOT NULL,
    bookingUserID int(10) NOT NULL,
	PRIMARY KEY(bookingID),
	CONSTRAINT fk_bookingRoomID_roomID FOREIGN KEY (bookingRoomID) REFERENCES rooms(roomID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_bookingUserID_userID FOREIGN KEY (bookingUserID) REFERENCES user(userID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS specialRequests;
CREATE TABLE specialRequests (
	specialReqStatus boolean DEFAULT FALSE,
    specialReqBookingID int(10) NOT NULL,
    CONSTRAINT fk_specialReqBookingID_bookingID FOREIGN KEY (specialReqBookingID) REFERENCES bookings(bookingID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO location
( locID, locName )
VALUES 
( 1, "Delhi"), (2, "Bangalore");

INSERT INTO user
( userID, username, userPassword, userLocID, isAdmin )
VALUES 
( 1, "Aashna", "pass", 1, true), ( 2, "Nikhil", "pass", 2, false);

INSERT INTO rooms
( roomID, isAvailable, roomLocID )
VALUES
( 2, true, 2);

INSERT INTO bookings
( bookingID, title, bookingRoomID, startDate, endDate, description, locationID, bookingUserID)
VALUES
( 1 , "New Booking", 1, "Sun, 28 Jan 2018 04:32:04 GMT", "Sun, 28 Jan 2018 04:47:04 GMT", "A small meeting", 1, 1);