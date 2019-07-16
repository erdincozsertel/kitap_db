CREATE TABLE `book_db`.`categories`(
    `categoryId` INT(11) NOT NULL AUTO_INCREMENT,
    `categoryName` VARCHAR(15) NOT NULL,
    PRIMARY KEY(`categoryId`)
) ENGINE = InnoDB; 

CREATE TABLE `book_db`.`users`(
    `idusers` INT(11) NOT NULL AUTO_INCREMENT,
    `userName` VARCHAR(15) NOT NULL,
    `password` VARCHAR(15) NOT NULL,
    `isAdmin` INT(1) NOT NULL DEFAULT '0',
    `gender` VARCHAR(6) NOT NULL,
    `birthDate` DATE NOT NULL,
    PRIMARY KEY(`idusers`)
) ENGINE = InnoDB; 

CREATE TABLE `book_db`.`writers`(
    `writerId` INT(11) NOT NULL AUTO_INCREMENT,
    `writerName` VARCHAR(15) NOT NULL,
    `gender` VARCHAR(6) NOT NULL,
    `writerBirthDay` DATE NOT NULL,
    PRIMARY KEY(`writerId`)
) ENGINE = InnoDB;

CREATE TABLE `book_db`.`books`(
    `bId` INT(11) NOT NULL AUTO_INCREMENT,
    `bName` VARCHAR(15) NOT NULL,
    `writerId` INT(11) NOT NULL,
    `bPublisher` VARCHAR(15) NOT NULL,
    `bPrice` DECIMAL(5, 2) NOT NULL,
    `categoryId` INT(11) NOT NULL,
    `bDate` DATETIME NOT NULL,
    PRIMARY KEY(`bId`),
    FOREIGN KEY(`writerId`) REFERENCES `writers`(`writerId`),
    FOREIGN KEY(`categoryId`) REFERENCES `categories`(`categoryId`)
) ENGINE = InnoDB; 