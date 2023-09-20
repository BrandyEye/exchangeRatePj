----------------------------------------------------------------------
# exchangeRatePj
demo exchangeRate 匯率demo
-----------------------------------------------------------------------
java 1.8
springboot 2.7.15
db mysql 8.0.34
-----------------------------------------------------------------------
table schema exchangerate
CREATE TABLE `exchangerate` (
  `sn` int NOT NULL AUTO_INCREMENT,
  `USD_NTD` decimal(15,8) NOT NULL,
  `RMB_NTD` decimal(15,8) NOT NULL,
  `USD_RMB` decimal(15,8) NOT NULL,
  `date` varchar(10) NOT NULL,
  PRIMARY KEY (`sn`)
)
----------------------------------------------------------------------


