-- Insert sample data
INSERT INTO users (email, password_hash, name, created_at, updated_at) VALUES 
('user1@example.com', 'dummy_hash_123', 'Test User', NOW(), NOW());

INSERT INTO books (title, author, total_pages, isbn, created_at) VALUES 
('The Great Gatsby', 'F. Scott Fitzgerald', 180, '978-0743273565', NOW()),
('To Kill a Mockingbird', 'Harper Lee', 281, '978-0446310789', NOW());
