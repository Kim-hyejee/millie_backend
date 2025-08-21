-- 즉시 ENUM 값 수정을 위한 SQL
-- 1. 현재 ENUM 값 확인
SELECT enumlabel FROM pg_enum WHERE enumtypid = (SELECT oid FROM pg_type WHERE typname = 'summary_length_opt') ORDER BY enumsortorder;

-- 2. 잘못된 ENUM 값 제거
DELETE FROM pg_enum WHERE enumtypid = (SELECT oid FROM pg_type WHERE typname = 'summary_length_opt') AND enumlabel = 'normal';

-- 3. 올바른 ENUM 값 추가
INSERT INTO pg_enum (enumtypid, enumlabel) VALUES ((SELECT oid FROM pg_type WHERE typname = 'summary_length_opt'), 'NORMAL');

-- 4. 수정된 ENUM 값 확인
SELECT enumlabel FROM pg_enum WHERE enumtypid = (SELECT oid FROM pg_type WHERE typname = 'summary_length_opt') ORDER BY enumsortorder;

-- 5. 기존 데이터의 ENUM 값도 수정
UPDATE reading_progress SET last_summary_length_opt = 'NORMAL' WHERE last_summary_length_opt = 'normal';
