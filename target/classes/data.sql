-- Create book table with id as primary key and identity column
CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT
);



-- This file is used to initialize the database with sample data when the application starts.

INSERT INTO book (title, author, description) VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'A novel set in the Roaring Twenties.');
INSERT INTO book (title, author, description) VALUES ('1984', 'George Orwell', 'A dystopian novel about totalitarianism.');
INSERT INTO book (title, author, description) VALUES ('To Kill a Mockingbird', 'Harper Lee', 'A novel about racial injustice in the Deep South.');
INSERT INTO book (title, author, description) VALUES ('Pride and Prejudice', 'Jane Austen', 'A romantic novel that critiques the British landed gentry.');
INSERT INTO book (title, author, description) VALUES ('The Catcher in the Rye', 'J.D. Salinger', 'A story about teenage angst and alienation.');