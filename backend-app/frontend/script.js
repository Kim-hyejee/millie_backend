// 더미 데이터 (실제로는 백엔드 API에서 가져올 데이터)
const dummyBooks = [
    {
        id: 1,
        title: "곤란할 땐, 옆집 언니 - 명랑하고 호쾌한 마흔여섯 인생론",
        author: "남수혜 (지은이)",
        total_pages: 100,
        isbn: "9791191998023",
        image_url: "https://image.aladin.co.kr/product/28388/74/cover/k432835810_1.jpg"
    },
    {
        id: 2,
        title: "어린 왕자",
        author: "앙투안 드 생텍쥐페리",
        total_pages: 136,
        isbn: "9780156012195",
        image_url: "https://image.aladin.co.kr/product/103/74/cover/8952782358_1.jpg"
    },
    {
        id: 3,
        title: "The Great Gatsby",
        author: "F. Scott Fitzgerald",
        total_pages: 180,
        isbn: "978-0743273565",
        image_url: "https://image.aladin.co.kr/product/103/74/cover/8952782358_2.jpg"
    },
    {
        id: 4,
        title: "1984",
        author: "George Orwell",
        total_pages: 328,
        isbn: "978-0451524935",
        image_url: null // 이미지가 없는 경우 테스트
    },
    {
        id: 5,
        title: "Pride and Prejudice",
        author: "Jane Austen",
        total_pages: 432,
        isbn: "978-0141439518",
        image_url: "https://image.aladin.co.kr/product/103/74/cover/8952782358_3.jpg"
    }
];

// DOM 요소들
const booksGrid = document.getElementById('booksGrid');
const searchInput = document.getElementById('searchInput');
const searchBtn = document.getElementById('searchBtn');
const loading = document.getElementById('loading');

// 책 카드 생성 함수
function createBookCard(book) {
    const bookCard = document.createElement('div');
    bookCard.className = 'book-card';
    bookCard.dataset.bookId = book.id;
    
    // 이미지 부분
    let imageHtml = '';
    if (book.image_url) {
        imageHtml = `
            <img 
                src="${book.image_url}" 
                alt="${book.title} 표지" 
                class="book-cover"
                onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';"
            />
            <div class="no-image-placeholder" style="display: none;">
                <span>📚</span>
                <p>이미지를 불러올 수 없습니다</p>
            </div>
        `;
    } else {
        imageHtml = `
            <div class="no-image-placeholder">
                <span>📚</span>
                <p>이미지 없음</p>
            </div>
        `;
    }
    
    // 책 정보 부분
    const bookInfo = `
        <div class="book-info">
            <h3 class="book-title">${book.title}</h3>
            <p class="book-author">${book.author}</p>
            <p class="book-pages">${book.total_pages}페이지</p>
        </div>
    `;
    
    bookCard.innerHTML = imageHtml + bookInfo;
    
    // 클릭 이벤트 추가
    bookCard.addEventListener('click', () => {
        showBookDetail(book);
    });
    
    return bookCard;
}

// 책 상세 정보 표시
function showBookDetail(book) {
    const detailHtml = `
        <div class="book-detail-overlay" id="bookDetailOverlay">
            <div class="book-detail-modal">
                <button class="close-btn" onclick="closeBookDetail()">&times;</button>
                <div class="book-detail-content">
                    <div class="book-detail-image">
                        ${book.image_url ? 
                            `<img src="${book.image_url}" alt="${book.title} 표지" class="detail-cover">` : 
                            `<div class="no-image-placeholder"><span>📚</span></div>`
                        }
                    </div>
                    <div class="book-detail-info">
                        <h2>${book.title}</h2>
                        <p class="detail-author">저자: ${book.author}</p>
                        <p class="detail-pages">페이지: ${book.total_pages}페이지</p>
                        <p class="detail-isbn">ISBN: ${book.isbn}</p>
                        <div class="detail-actions">
                            <button class="action-btn primary">내 서재에 추가</button>
                            <button class="action-btn secondary">읽기 시작</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
    
    document.body.insertAdjacentHTML('beforeend', detailHtml);
    
    // ESC 키로 닫기
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            closeBookDetail();
        }
    });
}

// 책 상세 정보 닫기
function closeBookDetail() {
    const overlay = document.getElementById('bookDetailOverlay');
    if (overlay) {
        overlay.remove();
    }
}

// 책 목록 렌더링
function renderBooks(books) {
    booksGrid.innerHTML = '';
    books.forEach(book => {
        const bookCard = createBookCard(book);
        booksGrid.appendChild(bookCard);
    });
}

// 검색 기능
function searchBooks(query) {
    if (!query.trim()) {
        renderBooks(dummyBooks);
        return;
    }
    
    const filteredBooks = dummyBooks.filter(book => 
        book.title.toLowerCase().includes(query.toLowerCase()) ||
        book.author.toLowerCase().includes(query.toLowerCase())
    );
    
    renderBooks(filteredBooks);
}

// 검색 이벤트 리스너
searchBtn.addEventListener('click', () => {
    searchBooks(searchInput.value);
});

searchInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        searchBooks(searchInput.value);
    }
});

// 실시간 검색 (입력할 때마다)
searchInput.addEventListener('input', (e) => {
    searchBooks(e.target.value);
});

// 초기 책 목록 표시
function initializeApp() {
    // 로딩 표시
    loading.style.display = 'block';
    
    // 실제 API 호출을 시뮬레이션
    setTimeout(() => {
        loading.style.display = 'none';
        renderBooks(dummyBooks);
    }, 1000);
}

// 앱 초기화
document.addEventListener('DOMContentLoaded', initializeApp);

// 이미지 로딩 상태 관리
function handleImageLoad(img) {
    img.classList.add('loaded');
}

function handleImageError(img) {
    img.style.display = 'none';
    const placeholder = img.nextElementSibling;
    if (placeholder && placeholder.classList.contains('no-image-placeholder')) {
        placeholder.style.display = 'flex';
    }
}

// 전역 이미지 이벤트 핸들러
document.addEventListener('load', function(e) {
    if (e.target.tagName === 'IMG') {
        handleImageLoad(e.target);
    }
}, true);

document.addEventListener('error', function(e) {
    if (e.target.tagName === 'IMG') {
        handleImageError(e.target);
    }
}, true);
