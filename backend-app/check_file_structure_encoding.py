#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def check_file_structure_encoding(filename):
    """다양한 인코딩으로 파일을 읽어봅니다."""
    print(f"=== {filename} 파일 인코딩 확인 ===")
    
    encodings = ['utf-8', 'cp949', 'euc-kr', 'latin-1', 'iso-8859-1']
    
    for encoding in encodings:
        try:
            print(f"\n{encoding} 인코딩으로 시도:")
            with open(filename, 'r', encoding=encoding) as f:
                # 처음 10줄만 확인
                for i in range(10):
                    line = f.readline().strip()
                    if line:
                        print(f"{i+1:2d}: {line[:100]}...")  # 100자만 표시
                    else:
                        print(f"{i+1:2d}: [빈 줄]")
                break  # 성공하면 루프 종료
        except UnicodeDecodeError as e:
            print(f"  {encoding}: 실패 - {e}")
        except Exception as e:
            print(f"  {encoding}: 기타 오류 - {e}")
    
    print(f"\n=== {filename} 파일 인코딩 확인 완료 ===")

if __name__ == "__main__":
    check_file_structure_encoding("books_add.sql")
