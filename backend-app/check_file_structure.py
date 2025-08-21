#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def check_file_structure(filename):
    """파일의 실제 구조를 확인합니다."""
    print(f"=== {filename} 파일 구조 확인 ===")
    
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            # 처음 20줄 확인
            print("처음 20줄:")
            for i in range(20):
                line = f.readline().strip()
                if line:
                    print(f"{i+1:2d}: {line}")
                else:
                    print(f"{i+1:2d}: [빈 줄]")
            
            # 파일 끝 부분 확인
            f.seek(0, 2)  # 파일 끝으로 이동
            file_size = f.tell()
            
            # 끝에서 1000바이트 앞으로 이동
            f.seek(max(0, file_size - 1000))
            end_lines = f.readlines()
            
            print(f"\n마지막 {len(end_lines)}줄:")
            for i, line in enumerate(end_lines):
                line = line.strip()
                if line:
                    print(f"끝-{len(end_lines)-i:2d}: {line}")
                else:
                    print(f"끝-{len(end_lines)-i:2d}: [빈 줄]")
        
        print(f"\n=== {filename} 파일 구조 확인 완료 ===")
        
    except Exception as e:
        print(f"파일 읽기 오류: {e}")

if __name__ == "__main__":
    check_file_structure("books_add.sql")
