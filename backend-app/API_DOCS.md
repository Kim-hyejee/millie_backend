# API 문서

## 도서 API

### 1. 전체 도서 목록 조회
- **URL**: `GET /api/books`
- **설명**: 모든 도서 목록을 조회합니다.
- **응답**: 도서 목록 배열

### 2. 특정 도서 조회
- **URL**: `GET /api/books/{bookId}`
- **설명**: 특정 도서의 상세 정보를 조회합니다.
- **응답**: 도서 상세 정보

### 3. 독서 진행률 조회
- **URL**: `GET /api/books/{bookId}/progress`
- **설명**: 특정 도서의 독서 진행률을 조회합니다. (userId=1 사용)
- **응답**: 독서 진행률 정보

### 4. 최신 요약 조회
- **URL**: `GET /api/books/{bookId}/summary/latest`
- **설명**: 특정 도서의 최신 요약을 조회합니다. (userId=1 사용)
- **응답**: 요약 텍스트

## 내 서재 API

### 1. 서재에 책 추가
- **URL**: `POST /api/library/add/{bookId}`
- **설명**: 내 서재에 책을 추가합니다. 이미 있으면 아무것도 하지 않습니다 (멱등성).
- **응답**: `"서재에 추가되었습니다."`

### 2. 서재에서 책 제거
- **URL**: `DELETE /api/library/remove/{bookId}`
- **설명**: 내 서재에서 책을 제거합니다.
- **응답**: `"서재에서 제거되었습니다."`

### 3. 내 서재 목록 조회
- **URL**: `GET /api/library/my-books`
- **설명**: 내 서재에 있는 책 목록을 조회합니다.
- **응답**: 내 서재 도서 목록

### 4. 서재 포함 여부 확인
- **URL**: `GET /api/library/check/{bookId}`
- **설명**: 특정 책이 내 서재에 있는지 확인합니다.
- **응답**: `true` (포함됨) 또는 `false` (포함되지 않음)

### 5. 내 서재 책 개수 조회
- **URL**: `GET /api/library/count`
- **설명**: 내 서재에 있는 책의 총 개수를 조회합니다.
- **응답**: 책 개수 (숫자)

## 내 서재 구현 원리

### 핵심 아이디어
- `reading_progress` 테이블이 "내 서재에 담김"의 최소 증거가 됩니다
- `last_page=0`은 "아직 읽지 않았지만 서재에 담아둔 상태"를 의미합니다

### 동작 방식
1. **담기**: `reading_progress`에 `(user_id, book_id, last_page=0)` 행 생성
2. **목록 조회**: `reading_progress`와 `books` 테이블 조인으로 서재 목록 생성
3. **삭제**: 해당 `reading_progress` 행 삭제
4. **멱등성**: 같은 책을 여러 번 담아도 중복되지 않음

### 데이터 흐름
```
사용자 → 책 담기 → reading_progress 생성 (last_page=0)
사용자 → 내 서재 조회 → reading_progress + books 조인
사용자 → 책 제거 → reading_progress 삭제
```

## Postman 테스트 방법

### 1. 도서 목록 조회
1. `GET` 요청을 `http://localhost:8080/api/books`로 설정
2. Send

### 2. 내 서재에 책 추가
1. `POST` 요청을 `http://localhost:8080/api/library/add/{bookId}`로 설정
2. Send

### 3. 내 서재 목록 조회
1. `GET` 요청을 `http://localhost:8080/api/library/my-books`로 설정
2. Send

### 4. 서재에서 책 제거
1. `DELETE` 요청을 `http://localhost:8080/api/library/remove/{bookId}`로 설정
2. Send

## 주의사항

- 모든 API는 `http://localhost:8080`을 기본 URL로 사용합니다.
- 현재는 `userId=1`을 하드코딩하여 사용합니다.
- 인증이 필요하지 않습니다.
- 내 서재 기능은 `reading_progress` 테이블을 활용하여 구현됩니다.
