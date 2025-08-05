CREATE DATABASE IF NOT EXISTS socialscape;
USE socialscape;

-- DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
	id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    password char(68) NOT NULL,
    role ENUM('USER', 'ADMIN', 'ORGANIZER') NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_details(
	id INT NOT NULL AUTO_INCREMENT,
    interests JSON,
    user_id INT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

-- DROP TABLE IF EXISTS events;

CREATE TABLE IF NOT EXISTS events(
	id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    location VARCHAR(255) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'PENDING'	,
    created_at DATETIME DEFAULT current_timestamp,
    
    PRIMARY KEY(id)
    
) ENGINE=InnoDB AUTO_INCREMENT=1000;

-- add a column in events
ALTER TABLE events
ADD COLUMN category ENUM('Business', 'Food & Drink', 'Health', 'Music', 'Charity & Causes', 'Community', 'Family & Education', 'Fashion', 'Film & Media', 'Home & Lifestyle', 'Science & Tech', 'Sports & Fitness', 'Travel & Outdoor');

ALTER TABLE events
ADD COLUMN image VARCHAR(512);

-- Booking table for many-to-many relationship between users and events
-- DROP TABLE IF EXISTS bookings;

CREATE TABLE IF NOT EXISTS bookings(
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    booked_at DATETIME DEFAULT current_timestamp,
    
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(event_id) REFERENCES events(id)
) ENGINE=InnoDB AUTO_INCREMENT=2000;

-- DROP TABLE IF EXISTS tags;

CREATE TABLE IF NOT EXISTS tags(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    
    PRIMARY KEY(id)
);

-- events_tags table for many-to-many relationship between events and tags
-- DROP TABLE IF EXISTS enents_tags;

CREATE TABLE IF NOT EXISTS event_tags(
	event_id INT NOT NULL,
    tags_id INT NOT NULL,
    
    PRIMARY KEY(event_id, tags_id),
    FOREIGN KEY(event_id) REFERENCES events(id),
    FOREIGN KEY(tags_id) REFERENCES tags(id)
);
