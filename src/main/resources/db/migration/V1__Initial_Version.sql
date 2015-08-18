drop database LANGUAGE_EXCHANGE;

/* DB 생성 */

Create DATABASE LANGUAGE_EXCHANGE DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/* User 생성 및 DB권한 주기 */

Grant all privileges on LANGUAGE_EXCHANGE.* to alpha@'localhost' identified by '1q2w3e4r';

/* Table 생성 */

use LANGUAGE_EXCHANGE;

Create Table USERS(
 userEmail varchar(50) PRIMARY KEY,
 userName varchar(50),
 userPassword varchar(16),
 userGender varchar(16),
 userAge int,
 oAuth varchar(16),
 userNative varchar(50),
 userPracticing varchar(50),
 userIntro varchar(50),
 userCreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 userUpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP
);

insert into USERS (userEmail,userName,userPassword,userGender,userAge,oAuth,userCreateDate,userUpdateDate) values("test@gmail.com","테스터","1234qwer","male","99",null,  default, default);
