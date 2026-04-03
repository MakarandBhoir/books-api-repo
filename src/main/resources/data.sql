-- Create book table with id as primary key and identity column
CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    genre VARCHAR(255)
);



-- This file is used to initialize the database with sample data when the application starts.

INSERT INTO book (title, author, description, genre) VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'A novel set in the Roaring Twenties.', 'Fiction');
INSERT INTO book (title, author, description, genre) VALUES ('1984', 'George Orwell', 'A dystopian novel about totalitarianism.', 'Dystopian');
INSERT INTO book (title, author, description, genre) VALUES ('To Kill a Mockingbird', 'Harper Lee', 'A novel about racial injustice in the Deep South.', 'Fiction');
INSERT INTO book (title, author, description, genre) VALUES ('Pride and Prejudice', 'Jane Austen', 'A romantic novel that critiques the British landed gentry.', 'Romance');
INSERT INTO book (title, author, description, genre) VALUES ('The Catcher in the Rye', 'J.D. Salinger', 'A story about teenage angst and alienation.', 'Fiction');