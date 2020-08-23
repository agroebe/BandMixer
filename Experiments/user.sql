CREATE TABLE `user` (
  `userID` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `joinDate` date DEFAULT NULL,
  `bio` varchar(100) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phoneNumber` int DEFAULT NULL,
  PRIMARY KEY (`userID`,`password`)
);