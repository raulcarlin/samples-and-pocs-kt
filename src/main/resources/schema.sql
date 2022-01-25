CREATE TABLE sample (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    number DECIMAL,
    add_at TIMESTAMP
);

CREATE TABLE other (
    id INT AUTO_INCREMENT PRIMARY KEY,
    other_name VARCHAR(20),
    other_number DECIMAL,
    other_date TIMESTAMP
);