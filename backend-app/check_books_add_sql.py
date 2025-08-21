#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def check_sql_file(filename):
    """SQL 파일의 구조와 오류를 확인합니다."""
    print(f"=== {filename} 파일 검사 시작 ===")
    
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        print(f"총 라인 수: {len(lines)}")
        
        # INSERT 문 확인
        insert_lines = [i+1 for i, line in enumerate(lines) if line.strip().upper().startswith('INSERT')]
        print(f"INSERT 문 개수: {len(insert_lines)}")
        
        # 첫 번째 INSERT 문 확인
        if insert_lines:
            first_insert = insert_lines[0]
            print(f"\n첫 번째 INSERT 문 (라인 {first_insert}):")
            print(lines[first_insert-1].strip())
        
        # 마지막 INSERT 문 확인
        if insert_lines:
            last_insert = insert_lines[-1]
            print(f"\n마지막 INSERT 문 (라인 {last_insert}):")
            print(lines[last_insert-1].strip())
        
        # VALUES 형식 확인
        values_lines = [i+1 for i, line in enumerate(lines) if 'VALUES' in line.upper()]
        print(f"\nVALUES 키워드가 포함된 라인 수: {len(values_lines)}")
        
        # 괄호 개수 확인 (INSERT 문의 균형)
        open_parens = 0
        close_parens = 0
        for line in lines:
            open_parens += line.count('(')
            close_parens += line.count(')')
        
        print(f"열린 괄호 개수: {open_parens}")
        print(f"닫힌 괄호 개수: {close_parens}")
        
        if open_parens != close_parens:
            print("⚠️  경고: 괄호 개수가 맞지 않습니다!")
        
        # 세미콜론 개수 확인
        semicolons = sum(line.count(';') for line in lines)
        print(f"세미콜론 개수: {semicolons}")
        
        # INSERT 문과 세미콜론 개수 비교
        if len(insert_lines) != semicolons:
            print("⚠️  경고: INSERT 문과 세미콜론 개수가 맞지 않습니다!")
        
        # 빈 라인 확인
        empty_lines = [i+1 for i, line in enumerate(lines) if line.strip() == '']
        print(f"빈 라인 수: {len(empty_lines)}")
        
        # 특수 문자나 인코딩 문제 확인
        problematic_chars = []
        for i, line in enumerate(lines):
            try:
                line.encode('utf-8')
            except UnicodeEncodeError:
                problematic_chars.append(i+1)
        
        if problematic_chars:
            print(f"⚠️  인코딩 문제가 있는 라인: {problematic_chars[:10]}...")
        
        # 라인별 길이 확인
        long_lines = [i+1 for i, line in enumerate(lines) if len(line) > 1000]
        if long_lines:
            print(f"⚠️  매우 긴 라인 (>1000자): {long_lines[:5]}...")
        
        print(f"\n=== {filename} 파일 검사 완료 ===")
        
    except Exception as e:
        print(f"파일 읽기 오류: {e}")

if __name__ == "__main__":
    check_sql_file("books_add.sql")
