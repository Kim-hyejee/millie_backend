#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def analyze_sql_structure(filename):
    """SQL 파일의 구조를 자세히 분석합니다."""
    print(f"=== {filename} 파일 상세 분석 ===")
    
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        print(f"총 라인 수: {len(lines)}")
        
        # INSERT 문 패턴 분석
        insert_patterns = []
        current_insert = None
        
        for i, line in enumerate(lines):
            line = line.strip()
            if line.upper().startswith('INSERT INTO'):
                if current_insert:
                    insert_patterns.append(current_insert)
                current_insert = {
                    'start_line': i + 1,
                    'insert_line': line,
                    'values_count': 0,
                    'parens_count': 0,
                    'semicolon': False
                }
            elif current_insert and 'VALUES' in line.upper():
                current_insert['values_line'] = line
            elif current_insert and line.startswith('('):
                current_insert['parens_count'] += 1
            elif current_insert and line.endswith(');'):
                current_insert['parens_count'] += 1
                current_insert['semicolon'] = True
                current_insert['end_line'] = i + 1
                current_insert = None
        
        if current_insert:
            insert_patterns.append(current_insert)
        
        print(f"INSERT 문 패턴 수: {len(insert_patterns)}")
        
        # 문제가 있는 INSERT 문 찾기
        problematic_inserts = []
        for pattern in insert_patterns:
            if not pattern.get('semicolon', False):
                problematic_inserts.append(pattern)
            elif pattern.get('parens_count', 0) % 2 != 0:
                problematic_inserts.append(pattern)
        
        print(f"문제가 있는 INSERT 문 수: {len(problematic_inserts)}")
        
        if problematic_inserts:
            print("\n문제가 있는 INSERT 문들:")
            for i, pattern in enumerate(problematic_inserts[:5]):  # 처음 5개만 표시
                print(f"  {i+1}. 라인 {pattern['start_line']}-{pattern.get('end_line', 'N/A')}")
                print(f"     INSERT: {pattern['insert_line']}")
                print(f"     VALUES: {pattern.get('values_line', 'N/A')}")
                print(f"     괄호 수: {pattern.get('parens_count', 0)}")
                print(f"     세미콜론: {pattern.get('semicolon', False)}")
                print()
        
        # 라인별 괄호 개수 확인
        print("라인별 괄호 개수 분석:")
        line_parens = []
        for i, line in enumerate(lines):
            open_count = line.count('(')
            close_count = line.count(')')
            if open_count > 0 or close_count > 0:
                line_parens.append((i+1, open_count, close_count))
        
        # 괄호가 많은 라인들 확인
        high_paren_lines = [lp for lp in line_parens if lp[1] > 100 or lp[2] > 100]
        if high_paren_lines:
            print(f"괄호가 많은 라인들 (>100):")
            for line_num, open_count, close_count in high_paren_lines[:10]:
                print(f"  라인 {line_num}: 열린 괄호 {open_count}, 닫힌 괄호 {close_count}")
        
        # VALUES 라인 분석
        values_lines = []
        for i, line in enumerate(lines):
            if 'VALUES' in line.upper():
                values_lines.append((i+1, line.strip()))
        
        print(f"\nVALUES 라인 분석:")
        print(f"총 VALUES 라인 수: {len(values_lines)}")
        
        # VALUES 라인에서 괄호 개수 확인
        values_parens = []
        for line_num, line in values_lines:
            open_count = line.count('(')
            close_count = line.count(')')
            values_parens.append((line_num, open_count, close_count))
        
        # 괄호가 많은 VALUES 라인들
        high_values_parens = [vp for vp in values_parens if vp[1] > 50 or vp[2] > 50]
        if high_values_parens:
            print(f"괄호가 많은 VALUES 라인들 (>50):")
            for line_num, open_count, close_count in high_values_parens[:5]:
                print(f"  라인 {line_num}: 열린 괄호 {open_count}, 닫힌 괄호 {close_count}")
        
        print(f"\n=== {filename} 파일 상세 분석 완료 ===")
        
    except Exception as e:
        print(f"파일 분석 오류: {e}")

if __name__ == "__main__":
    analyze_sql_structure("books_add.sql")
