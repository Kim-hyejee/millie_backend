-- Insert sample data
INSERT INTO users (id, email, password_hash, name, created_at, updated_at) VALUES 
(1,'user1@example.com', 'dummy_hash_123', 'Test User', NOW(), NOW());

INSERT INTO books (id,title, author, total_pages, isbn, created_at) VALUES 
(101,'The Great Gatsby', 'F. Scott Fitzgerald', 180, '978-0743273565', NOW()),
(102,'To Kill a Mockingbird', 'Harper Lee', 281, '978-0446310789', NOW());

INSERT INTO books (id, title, author, total_pages, isbn) VALUES 
(103,'어린 왕자', '앙투안 드 생텍쥐페리', 136, '9780156012195');

INSERT INTO reading_progress
(user_id, book_id, last_page, last_opened_at, last_summary_text, last_summary_length_opt)
VALUES
(
  1,  -- 예시: Alice (user_id=1)
  103,  -- 어린 왕자 (book_id=6)
  12, -- 현재까지 읽은 페이지
  NOW(),
  '화자는 어린 시절 보아뱀 그림으로 오해받으며 화가의 꿈을 접고 비행사가 된다. 
   사하라 사막에 불시착한 후 어린 왕자를 만나고, 왕자의 “양을 그려 달라”는 부탁을 
   여러 번 거절하다가 결국 상자 그림으로 만족을 얻으며 두 사람의 만남이 시작된다.',
  'NORMAL'  -- 대문자로 변경
)