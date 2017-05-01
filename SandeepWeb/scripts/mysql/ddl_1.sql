CREATE TABLE `auth` (
  `email` varchar(36) NOT NULL,
  `password` varchar(36) DEFAULT NULL,
  `session_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;