DROP database if exists `WordUp`;
drop table if exists `wordup`.`user`;
drop table if exists `wordup`.`lesson`;
drop table if exists `wordup`.`card`;
drop table if exists `wordup`.`quiz_attempt`;
drop table if exists `wordup`.`user_response`;

CREATE DATABASE `wordup`;

CREATE TABLE `wordup`.`user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `profession` varchar(25) DEFAULT NULL,
  `email` varchar(50) unique NOT NULL,
  `password` varchar(256) NOT NULL,
  `pwd` varchar(32),
  `salt` varchar(256),
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wordup`.`lesson` (
  `lessonID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `file` varchar(255) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `subject2` varchar(50) DEFAULT NULL,
  `subject3` varchar(50) DEFAULT NULL,
  `level` varchar(12) DEFAULT NULL,
  `creatorID` int(11) DEFAULT NULL, 
  `dateCreated` DATE,
  PRIMARY KEY (`lessonID`),
  FOREIGN KEY(creatorID) references user(userID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wordup`.`card` (
  `cardID` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) DEFAULT NULL,
  `description` varchar(999) DEFAULT NULL,
  `lessonID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cardID`),
  KEY `lessonID` (`lessonID`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`lessonID`) REFERENCES `lesson` (`lessonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wordup`.`attempt` (
  `attemptID` int(11) NOT NULL AUTO_INCREMENT,
  `lessonID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `timestamp` datetime,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`attemptID`),
  KEY `lessonID` (`lessonID`),
  KEY `userID` (`userID`),
  CONSTRAINT `attempt_ibfk_1` FOREIGN KEY (`lessonID`) REFERENCES `lesson` (`lessonID`),
  CONSTRAINT `attempt_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wordup`.`response` (
  `responseID` int(11) NOT NULL AUTO_INCREMENT,
  `cardID` int(11) DEFAULT NULL,
  `attemptID` int(11) DEFAULT NULL,
  `userResponse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`responseID`),
  KEY `attemptID` (`attemptID`),
  CONSTRAINT `response_ibfk_1` FOREIGN KEY (`attemptID`) REFERENCES `attempt` (`attemptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `wordup`.`user` (firstName, lastName, `type`, profession, email, `password`) values ('Word Girl', 'Master', 0, 'admin', 'admin@gmail.com', 'admin')
 
