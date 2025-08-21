-- ========================================
-- DROP TABLES (테이블 삭제)
-- ========================================
-- 외래키 제약조건 때문에 삭제 순서가 중요합니다

-- 1. 외래키를 참조하는 테이블들을 먼저 삭제
DROP TABLE IF EXISTS summary_feedbacks CASCADE;
DROP TABLE IF EXISTS reading_progress CASCADE;

-- 2. 기본 테이블들을 삭제
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- 3. ENUM 타입 삭제
DROP TYPE IF EXISTS summary_length_opt CASCADE;

-- Create enum type for summary length options
CREATE TYPE summary_length_opt AS ENUM ('short', 'normal', 'deep');

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(200) NOT NULL,
    name VARCHAR(100) NOT NULL,
    timezone VARCHAR(50) NOT NULL DEFAULT 'Asia/Seoul',
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Create books table
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    total_pages INTEGER NOT NULL,
    isbn VARCHAR(20),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Create reading_progress table
CREATE TABLE reading_progress (
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    last_page INTEGER NOT NULL,
    last_opened_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_summary_text TEXT,
    last_summary_length_opt summary_length_opt,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Create summary_feedbacks table
CREATE TABLE summary_feedbacks (
    id BIGSERIAL PRIMARY KEY,
    progress_user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    is_helpful BOOLEAN NOT NULL,
    comment TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    FOREIGN KEY (progress_user_id, book_id) REFERENCES reading_progress(user_id, book_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE(user_id, book_id)
);


