
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE tenders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tender_id VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL
);

CREATE TABLE bids (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tender_id VARCHAR(255),
    user_id INT,
    amount DOUBLE,
    FOREIGN KEY (tender_id) REFERENCES tenders(tender_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
