# 📚 밀리 독서 프론트엔드

`books` 테이블의 `image_url` 컬럼을 사용하여 책 표지 이미지를 표시하는 웹 애플리케이션입니다.

## 🚀 기능

- **책 목록 표시**: 그리드 형태로 책들을 카드 형태로 표시
- **이미지 표시**: `image_url`에서 책 표지 이미지 로드
- **이미지 에러 처리**: 이미지 로드 실패 시 기본 아이콘 표시
- **검색 기능**: 제목과 저자로 책 검색
- **책 상세 정보**: 책 카드 클릭 시 상세 정보 모달 표시
- **반응형 디자인**: 모바일과 데스크톱 모두 지원

## 📁 파일 구조

```
frontend/
├── index.html          # 메인 HTML 파일
├── styles.css          # CSS 스타일
├── script.js           # JavaScript 로직
└── README.md           # 이 파일
```

## 🎯 주요 특징

### 1. 이미지 표시
- `book.image_url`이 있으면 해당 URL에서 이미지 로드
- 이미지가 없거나 로드 실패 시 📚 아이콘과 "이미지 없음" 메시지 표시
- 이미지 로딩 중 스피너 표시

### 2. 이미지 에러 처리
```javascript
onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';"
```
- 이미지 로드 실패 시 자동으로 기본 이미지로 대체

### 3. 반응형 디자인
- 데스크톱: 3-4열 그리드
- 태블릿: 2-3열 그리드  
- 모바일: 1열 그리드

## 🛠️ 사용법

### 1. 로컬에서 실행
```bash
# frontend 디렉토리로 이동
cd frontend

# 웹 서버 실행 (Python 3)
python -m http.server 3000

# 또는 Node.js가 설치되어 있다면
npx serve .
```

### 2. 브라우저에서 접속
```
http://localhost:3000
```

## 🔧 백엔드 연동

현재는 더미 데이터를 사용하고 있습니다. 실제 백엔드 API와 연동하려면:

### 1. API 호출 함수 수정
```javascript
// script.js에서 더미 데이터 대신 API 호출
async function fetchBooks() {
    try {
        const response = await fetch('/api/books');
        const books = await response.json();
        renderBooks(books);
    } catch (error) {
        console.error('책 목록을 불러오는데 실패했습니다:', error);
    }
}
```

### 2. CORS 설정 확인
백엔드에서 `http://localhost:3000`에서의 요청을 허용해야 합니다.

## 📱 반응형 브레이크포인트

- **데스크톱**: 1200px 이상
- **태블릿**: 768px - 1199px
- **모바일**: 767px 이하

## 🎨 스타일 특징

- **그라데이션 배경**: 보라색 계열의 모던한 디자인
- **카드 호버 효과**: 마우스 오버 시 카드가 위로 올라가는 애니메이션
- **부드러운 전환**: 모든 상호작용에 0.3초 트랜지션 적용
- **그림자 효과**: 깊이감을 주는 박스 섀도우

## 🐛 문제 해결

### 이미지가 표시되지 않는 경우
1. `image_url`이 올바른지 확인
2. CORS 정책 확인
3. 이미지 URL이 유효한지 확인

### 스타일이 적용되지 않는 경우
1. CSS 파일 경로 확인
2. 브라우저 캐시 삭제
3. 개발자 도구에서 오류 확인

## 📝 향후 개선 사항

- [ ] 이미지 지연 로딩 (Lazy Loading)
- [ ] 이미지 압축 및 최적화
- [ ] 무한 스크롤
- [ ] 필터링 기능 (장르, 페이지 수 등)
- [ ] 즐겨찾기 기능
- [ ] 읽기 진행률 표시

## 🤝 기여하기

버그 리포트나 기능 제안은 이슈로 등록해주세요!
