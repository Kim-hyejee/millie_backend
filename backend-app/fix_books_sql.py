#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import json
import re

def clean_text(text):
    """텍스트를 정리합니다."""
    if not text:
        return ""
    
    # HTML 태그 제거
    text = re.sub(r'<[^>]+>', '', text)
    # 특수 문자 처리
    text = text.replace("'", "''")  # SQL에서 작은따옴표 이스케이프
    text = text.replace('"', '""')  # SQL에서 큰따옴표 이스케이프
    # 줄바꿈과 탭 제거
    text = text.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ')
    # 연속된 공백을 하나로
    text = re.sub(r'\s+', ' ', text)
    return text.strip()

def generate_correct_sql():
    """books.json에서 올바른 SQL을 생성합니다."""
    print("=== 올바른 SQL 생성 시작 ===")
    
    try:
        # books.json 읽기
        with open('books.json', 'r', encoding='utf-8') as f:
            books_data = json.load(f)
        
        print(f"총 {len(books_data)}개의 책 데이터를 읽었습니다.")
        
        # SQL 파일 생성
        with open('books_fixed.sql', 'w', encoding='utf-8') as f:
            f.write("-- Fixed books data from books.json\n")
            f.write("-- Generated with proper SQL syntax\n\n")
            
            # ID 시작 번호 (기존 data.sql의 books ID 다음부터)
            start_id = 108  # 기존 data.sql에는 101-107까지 있음
            
            for i, book in enumerate(books_data):
                book_id = start_id + i
                
                # 데이터 정리
                title = clean_text(book.get('title', ''))
                author = clean_text(book.get('author', ''))
                isbn = clean_text(book.get('isbn', ''))
                
                # 기본값 설정
                if not title:
                    title = f"Unknown Title {book_id}"
                if not author:
                    author = "Unknown Author"
                if not isbn:
                    isbn = f"ISBN_{book_id}"
                
                # total_pages는 기본값 100으로 설정
                total_pages = 100
                
                # SQL INSERT 문 생성
                sql = f"INSERT INTO books (id, title, author, total_pages, isbn, created_at) VALUES ({book_id}, '{title}', '{author}', {total_pages}, '{isbn}', NOW());\n"
                
                f.write(sql)
                
                # 진행상황 표시
                if (i + 1) % 1000 == 0:
                    print(f"진행률: {i + 1}/{len(books_data)} ({((i + 1) / len(books_data) * 100):.1f}%)")
        
        print(f"\n✅ 올바른 SQL이 'books_fixed.sql' 파일로 생성되었습니다.")
        print(f"   - 총 {len(books_data)}개의 INSERT 문")
        print(f"   - ID 범위: {start_id} ~ {start_id + len(books_data) - 1}")
        
    except FileNotFoundError:
        print("❌ books.json 파일을 찾을 수 없습니다.")
    except json.JSONDecodeError as e:
        print(f"❌ JSON 파싱 오류: {e}")
    except Exception as e:
        print(f"❌ 오류 발생: {e}")

if __name__ == "__main__":
    generate_correct_sql()
