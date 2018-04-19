SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fruit
-- ----------------------------
DROP TABLE IF EXISTS fruit;
-- CREATE TABLE `fruit` (
--   `id` int(4) NOT NULL AUTO_INCREMENT,
--   `name` varchar(20) DEFAULT NULL,
--   `price` decimal(20,0) DEFAULT NULL,
--   `weight` double(8,0) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) DEFAULT NULL,
  pass varchar(20) DEFAULT NULL,
  age int(3) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  sex int(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
);