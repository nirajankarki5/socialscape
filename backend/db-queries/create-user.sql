DROP USER if exists 'socialscape'@'localhost';

CREATE USER 'socialscape'@'localhost' IDENTIFIED BY 'socialscape';

GRANT ALL PRIVILEGES ON *.* TO 'socialscape'@'localhost';