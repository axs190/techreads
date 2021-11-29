CREATE TABLE IF NOT EXISTS books (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR (255),
    author VARCHAR (255),
    rating VARCHAR (255),
    cover VARCHAR (255)
    ) engine=InnoDB;