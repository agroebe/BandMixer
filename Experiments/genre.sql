CREATE TABLE `genre` (
  `userID` varchar(10) NOT NULL,
  `rock` bit(2) DEFAULT NULL,
  `jazz` bit(2) DEFAULT NULL,
  `hipHop` bit(2) DEFAULT NULL,
  `blues` bit(2) DEFAULT NULL,
  `folk` bit(2) DEFAULT NULL,
  `pop` bit(2) DEFAULT NULL,
  `classical` bit(2) DEFAULT NULL,
  `r&b` bit(2) DEFAULT NULL,
  `metal` bit(2) DEFAULT NULL,
  `country` bit(2) DEFAULT NULL,
  PRIMARY KEY (`userID`)
);