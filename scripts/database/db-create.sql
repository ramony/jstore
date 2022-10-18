CREATE DATABASE `jstoredb9`

CREATE TABLE `listing` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `page_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `listing_UN` (`page_url`)
) ENGINE=InnoDB AUTO_INCREMENT=23203 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `detail_type` varchar(10) NOT NULL,
  `detail_id` varchar(50) NOT NULL,
  `detail_title` varchar(1000) NOT NULL,
  `detail_url` varchar(2000) NOT NULL,
  `read_flag` int(11) NOT NULL,
  `local_flag` int(11) NOT NULL,
  `page_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `detail_UN` (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22792 DEFAULT CHARSET=utf8mb4;