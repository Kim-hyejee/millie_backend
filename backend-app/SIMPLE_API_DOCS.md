# 📚 밀리 독서 백엔드 API 문서 (간단 버전)

## 🎯 Postman과 연동하여 테스트할 수 있는 간단한 API들

### 🔐 회원가입 API

#### 1. 회원가입
**POST** `http://localhost:8080/api/auth/signup`

**Headers:**
```
Content-Type: application/json
```

**Body (raw JSON):**
```json
{
    "email": "test@example.com",
    "password": "password123",
    "name": "테스트유저",
    "timezone": "Asia/Seoul"
}
```

**응답 예시:**
```json
{
    "success": true,
    "message": "회원가입이 완료되었습니다.",
    "userId": 4,
    "email": "test@example.com",
    "name": "테스트유저"
}
```

#### 2. 이메일 중복 확인
**GET** `http://localhost:8080/api/auth/check-email?email=test@example.com`

**응답:**
- `true`: 이메일 사용 가능
- `false`: 이메일 이미 사용 중

#### 3. 서버 상태 확인
**GET** `http://localhost:8080/api/auth/health`

**응답:** `OK`

### 📖 책 관련 API

#### 1. 전체 책 목록 조회
**GET** `http://localhost:8080/api/books`

**응답 예시:**
```json
[
    {
        "id": 101,
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "totalPages": 180,
        "isbn": "978-0743273565",
        "imageUrl": "https://example.com/image.jpg",
        "createdAt": "2024-01-01T00:00:00Z"
    }
]
```

#### 2. 특정 책 정보 조회
**GET** `http://localhost:8080/api/books/{bookId}`

예: `http://localhost:8080/api/books/101`

#### 3. 책 읽기 진행률 조회
**GET** `http://localhost:8080/api/books/{bookId}/progress`

#### 4. 최신 요약 조회
**GET** `http://localhost:8080/api/books/{bookId}/summary/latest`

### 📚 서재 관련 API

#### 1. 내 서재에 책 추가
**POST** `http://localhost:8080/api/library/add/{bookId}`

예: `http://localhost:8080/api/library/add/101`

**응답:** `"서재에 추가되었습니다."`

#### 2. 내 서재에서 책 제거
**DELETE** `http://localhost:8080/api/library/remove/{bookId}`

예: `http://localhost:8080/api/library/remove/101`

**응답:** `"서재에서 제거되었습니다."`

#### 3. 내 서재 목록 조회
**GET** `http://localhost:8080/api/library/my-books`

#### 4. 서재 포함 여부 확인
**GET** `http://localhost:8080/api/library/check/{bookId}`

**응답:** `true` 또는 `false`

## 🧪 Postman에서 테스트하는 방법

### 1. Postman 설정
1. **새 Collection 생성**: "밀리 독서 API 테스트"
2. **Base URL 설정**: `http://localhost:8080`

### 2. 회원가입 테스트
1. **새 Request 생성**
2. **Method**: POST
3. **URL**: `{{baseUrl}}/api/auth/signup`
4. **Headers**: `Content-Type: application/json`
5. **Body**: raw JSON으로 위의 회원가입 데이터 입력
6. **Send** 클릭

### 3. 책 목록 조회 테스트
1. **새 Request 생성**
2. **Method**: GET
3. **URL**: `{{baseUrl}}/api/books`
4. **Send** 클릭

### 4. 서재에 책 추가 테스트
1. **새 Request 생성**
2. **Method**: POST
3. **URL**: `{{baseUrl}}/api/library/add/101`
4. **Send** 클릭

## 📝 주의사항

1. **보안 없음**: 현재는 인증이나 암호화 없이 모든 API에 접근 가능
2. **비밀번호**: 평문으로 저장됨 (실제 서비스에서는 사용하지 마세요)
3. **사용자 ID**: 임시로 userId=1을 사용
4. **데이터베이스**: PostgreSQL이 실행 중이어야 함

## 🚀 테스트 순서

1. **서버 실행**: Spring Boot 애플리케이션 시작
2. **데이터베이스 확인**: PostgreSQL 연결 상태 확인
3. **회원가입**: 새로운 사용자 등록
4. **책 목록 조회**: 등록된 책들 확인
5. **서재 기능**: 책 추가/제거/목록 조회 테스트

## 🔧 문제 해결

- **404 에러**: URL 경로 확인
- **500 에러**: 서버 로그 확인
- **연결 실패**: 포트 8080이 사용 가능한지 확인
- **데이터베이스 오류**: PostgreSQL 서비스 상태 확인

이제 Postman에서 간단하게 API를 테스트할 수 있습니다! 🎉
