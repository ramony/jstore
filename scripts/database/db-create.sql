CREATE DATABASE `jstoredb`

-- jstoredb.listing definition

CREATE TABLE `listing` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `page_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `listing_UN` (`page_url`)
) ENGINE=InnoDB AUTO_INCREMENT=76790 DEFAULT CHARSET=utf8mb4;
-- jstoredb.detail definition

CREATE TABLE `detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `detail_type` varchar(10) NOT NULL,
  `detail_id` bigint(20) NOT NULL,
  `detail_title` varchar(1000) NOT NULL,
  `detail_url` varchar(2000) NOT NULL,
  `read_flag` int(11) NOT NULL,
  `local_flag` int(11) NOT NULL,
  `page_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `keyword` varchar(100) NOT NULL DEFAULT 'NONE',
  `tag_id` int(10) unsigned NOT NULL DEFAULT '0',
  `score` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `detail_UN` (`detail_type`,`detail_id`),
  KEY `detail_keyword_IDX` (`keyword`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77554 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;