CREATE TABLE `instruments` (
  `userID` varchar(10) NOT NULL,
  `instrument1` varchar(10) DEFAULT NULL,
  `skillLevel1` int DEFAULT NULL,
  `instrument2` varchar(10) DEFAULT NULL,
  `skillLevel2` int DEFAULT NULL,
  `instrument3` varchar(10) DEFAULT NULL,
  `skillLevel3` int DEFAULT NULL,
  `instrument4` varchar(10) DEFAULT NULL,
  `skillLevel4` int DEFAULT NULL,
  `instrument5` varchar(10) DEFAULT NULL,
  `skillLevel5` int DEFAULT NULL,
  PRIMARY KEY (`userID`)
);