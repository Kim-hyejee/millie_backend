
-- Insert sample data
-- Users
INSERT INTO users (id, email, password_hash, name, created_at, updated_at) VALUES 
(1, 'user1@example.com', 'dummy_hash_123', '김독서', NOW(), NOW()),
(2, 'user2@example.com', 'dummy_hash_456', '이책사랑', NOW(), NOW()),
(3, 'user3@example.com', 'dummy_hash_789', '박지식', NOW(), NOW());

-- Books


-- Reading Progress (요약 정보 제거, 복합키 사용)
INSERT INTO reading_progress (user_id, book_id, last_page, last_opened_at, created_at, updated_at) VALUES
(1, 103, 55, NOW(), NOW(), NOW()),  -- 김독서가 어린 왕자를 55페이지까지 읽음
(1, 101, 45, NOW(), NOW(), NOW()),  -- 김독서가 The Great Gatsby를 45페이지까지 읽음
(1, 104, 120, NOW(), NOW(), NOW()), -- 김독서가 1984를 120페이지까지 읽음
(2, 102, 89, NOW(), NOW(), NOW()),  -- 이책사랑이 To Kill a Mockingbird를 89페이지까지 읽음
(2, 105, 156, NOW(), NOW(), NOW()); -- 이책사랑이 Pride and Prejudice를 156페이지까지 읽음

-- Summaries (페이지별 요약 정보)
INSERT INTO summaries (book_id, last_page, summary_text, length_option, created_at) VALUES
-- 어린 왕자 요약들
(102, 12, '화자는 어린 시절 보아뱀 그림으로 오해받으며 화가의 꿈을 접고 비행사가 된다. 
   사하라 사막에 불시착한 후 어린 왕자를 만나고, 왕자의 “양을 그려 달라”는 부탁을 
   여러 번 거절하다가 결국 상자 그림으로 만족을 얻으며 두 사람의 만남이 시작된다.', 'SHORT', NOW()),
(102, 55, '화자는 어린 시절 보아뱀이 코끼리를 삼킨 그림을 그렸으나, 어른들이 이해하지 못해 화가의 꿈을 포기하고 비행사가 된다. 그러던 중 사하라 사막에 불시착하게 되고, 그곳에서 신비롭고 진지한 아이인 어린 왕자를 만난다. 어린 왕자는 “양을 그려 달라”고 부탁하며 화자와 친구가 된다.

대화를 나누며 어린 왕자가 작은 별(소행성 B612)에서 왔음을 알게 된다. 그 별에는 바오밥나무 같은 위험한 싹이 있어 매일 뽑아야 하고, 의자만 옮기면 여러 번 해질녘을 볼 수 있을 만큼 작다. 어린 왕자는 해질녘을 사랑하며, 그만큼 외롭고 쓸쓸한 마음을 드러낸다.

그는 특히 자신의 별에 핀 단 하나뿐인 꽃을 소중히 여긴다. 그 꽃은 까다롭고 허영심도 있지만, 동시에 연약한 존재였다. 어린 왕자는 꽃을 사랑했지만 그 마음을 제대로 표현하지 못했고, 꽃의 모순된 말들에 상처받아 결국 별을 떠나기로 결심한다. 꽃은 떠나는 순간에야 진심을 털어놓으며 어린 왕자를 사랑했다고 고백하지만, 이미 그는 철새들의 무리를 따라 여행길에 오른다.

별을 떠나기 전, 어린 왕자는 화산들을 청소하고 바오밥 씨앗을 뽑으며 자신의 별을 정리한다. 마지막으로 꽃에게 작별 인사를 나누는데, 꽃은 더 이상 유리 덮개도, 바람막이도 필요 없다며 자존심을 지킨 채 눈물을 감춘다. 어린 왕자는 아쉬움을 품고 떠난다.

그가 처음 방문한 별은 왕이 사는 별이다. 왕은 절대권력을 자처하지만, 실제로는 명령을 현실에 맞게 내리는 ‘겉만 위엄 있는 인물’이다. 어린 왕자는 왕의 권위가 허망하다는 것을 느끼고 곧 그곳을 떠난다.', 'NORMAL', NOW()),

-- The Great Gatsby 요약들
(101, 45, '1922년 뉴욕 롱아일랜드에서 닉 캐러웨이는 이웃인 제이 게츠비의 화려한 파티에 초대된다. 게츠비는 신비로운 인물로, 닉은 그의 과거와 부의 원천에 대해 궁금해한다. 닉의 사촌 데이지와 그녀의 남편 톰과도 만나게 되는데, 이들 사이에는 복잡한 관계가 얽혀있다.', 'SHORT', NOW()),
(101, 180, '게츠비의 과거가 밝혀지고, 데이지와의 사랑이 재점화되지만, 톰의 질투와 사회적 편견으로 인해 비극이 발생한다. 게츠비는 데이지를 위해 모든 것을 바치지만, 결국 총을 맞아 죽고 만다. 닉은 이 경험을 통해 미국의 꿈과 환멸, 그리고 인간의 허영심과 탐욕을 깨닫게 된다.', 'DEEP', NOW()),

-- 1984 요약들
(104, 120, '1984년 런던에서 빅 브라더가 감시하는 전체주의 사회를 배경으로 한다. 주인공 윈스턴 스미스는 진실부에서 일하며 과거 기록을 조작한다. 그는 줄리아와 사랑에 빠지지만, 사상경찰에게 발각되어 고문을 받게 된다.', 'NORMAL', NOW()),
(104, 328, '윈스턴은 고문을 통해 모든 것을 부정하게 되고, 결국 빅 브라더를 사랑하게 된다. 그는 자신의 기억과 사랑을 배신하고, 완전히 체제에 굴복한다. 이 작품은 전체주의의 위험성과 인간의 자유의지에 대한 경고를 담고 있다.', 'DEEP', NOW());

-- Summary Feedbacks (올바른 reading_progress 참조)
INSERT INTO summary_feedbacks (progress_user_id, book_id, user_id, rating, is_helpful, comment, created_at) VALUES
(1, 103, 1, 5, true, '정말 도움이 되는 요약이었어요!', NOW()),
(1, 103, 2, 4, true, '핵심 내용을 잘 정리했네요.', NOW()),
(2, 102, 1, 5, true, '이해하기 쉽게 설명되어 있어요.', NOW()),
(2, 105, 3, 4, true, '좋은 요약입니다.', NOW());

INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(101, 'To Kill a Mockingbird', 'https://search.pstatic.net/sunny/?src=https%3A%2F%2Fcontents.kyobobook.co.kr%2Fsih%2Ffit-in%2F458x0%2Fpdt%2F9780061980268.jpg&type=a340','Harper Lee', 281, '978-0446310789', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES
(102, '어린 왕자', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNTAxMzFfMTI2%2FMDAxNzM4MzM1NDA1OTIw.bCahc7cAdOuL8pp_aquW19J4oyNUQWSg3Y46nedCzQog.239r2jY8m9Ou4TN0yACWsYeHfKhYr_nzGVRUmUi1EBUg.JPEG%2FXL_%25284%2529.jpg&type=sc960_832','앙투안 드 생텍쥐페리', 136, '9780156012195', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(103, 'The Great Gatsby', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMjhfMTEw%2FMDAxNzA5MTA1NjkwNDE0.3fHPuIrYIcITQi5anmdEnae3gCOM02taKwWOqSmG63og.1jzdLLFK6WeAaRSXRXZFXcNKnZC5ydnvSg3x5R5NxDYg.PNG%2F20240228_161844.png&type=a340', 'F. Scott Fitzgerald', 180, '978-0743273565', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(104, '1984', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA2MTBfMjEx%2FMDAxNzE3OTUxODQ4MzAw.GNt8fefeTUFPF8lVdXp4QSe9LvASebadv8-jNAKePTgg.0aDlYbdHha2ZT3sgldpdJuCBBbJ1M7v8lI4gRS1-jf8g.JPEG%2FIMG_7805.jpg&type=a340', 'George Orwell', 328, '978-0451524935', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(105, 'Pride and Prejudice', 'https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDEyMjhfMjMx%2FMDAxNjA5MTM0Nzg3OTMx.NAXrIt5nxegTVGsXpxv2rKDA3d5WeYfyJf792z4V5Tkg.LsVTf5y2SGZcIuFoPQI_h7CsTk4yr0W5VNtWNJwax9Qg.JPEG.rosemsyo%2FIMG_2613.jpg&type=sc960_832','Jane Austen', 432, '978-0141439518', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(106, '너에게 목소리를 보낼게 - &lt;달빛천사&gt; 성우 이용신의 첫 번째 에세이', 'https://image.aladin.co.kr/product/28415/8/cover/k652835115_1.jpg','이용신 (지은이)', 100, '9791156759270', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(107, '일기에도 거짓말을 쓰는 사람 - 99년생 시인의 자의식 과잉 에세이', 'https://image.aladin.co.kr/product/28414/66/cover/k202835114_1.jpg','차도하 (지은이)', 100, '9791168120877', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(108, '본격 한중일 세계사 12 - 임오군란과 통킹 위기', 'https://image.aladin.co.kr/product/28414/47/cover/k402835113_1.jpg','굽시니스트 (지은이)', 100, '9791168120839', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(109, '즉시 기분을 바꿔드립니다 - 신기하게 마음이 편해지는 응급 처방전', 'https://image.aladin.co.kr/product/28414/30/cover/k892835112_1.jpg','올리비아 레메스 (지은이), 김잔디 (옮긴이)', 100, '9791168120846', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(110, '오늘도 리추얼 : 음악, 나에게 선물하는 시간', 'https://image.aladin.co.kr/product/28413/80/cover/k202835112_1.jpg','정혜윤 (지은이)', 100, '9791168120747', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(111, '지리산 1 - 김은희 대본집', 'https://image.aladin.co.kr/product/28413/61/cover/k612835111_1.jpg','김은희 (지은이)', 100, '9791168030183', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(112, '달러구트 꿈 백화점 (100만부 기념 합본호 기프트 에디션) - 주문하신 꿈은 매진입니다', 'https://image.aladin.co.kr/product/28413/58/cover/k542835111_1.jpg','이미예 (지은이)', 100, '9791165344252', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(113, '그린 스완 - 회복과 재생을 촉진하는 새로운 경제', 'https://image.aladin.co.kr/product/28413/47/cover/8984054208_1.jpg','존 엘킹턴 (지은이), 정윤미 (옮긴이)', 100, '9788984054202', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(114, '마음이 허기질 때 어린이책에서 꺼내 먹은 것들 - 나를 채운 열일곱 가지 맛', 'https://image.aladin.co.kr/product/28413/47/cover/8958207531-1.jpg','김단비 (지은이)', 100, '9788958207535', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(115, '돌리버드 Dollybird : Fairy Tale', 'https://image.aladin.co.kr/product/28412/99/cover/k482835017_1.jpg','호비재팬 편집부 (지은이), 정유미 (옮긴이)', 100, '9791188726936', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(116, '광고학교에서 배운 101가지 - 101 Things I Learned(r) in Advertising School', 'https://image.aladin.co.kr/product/28412/94/cover/897297014x_1.jpg','트레이시 애링턴, 매튜 프레더릭 (지은이), 김경영 (옮긴이)', 100, '9788972970149', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(117, '화가들의 정원 (리미티드 에디션) - 명화를 탄생시킨 비밀의 공간', 'https://image.aladin.co.kr/product/28412/89/cover/8946422017_1.jpg','재키 베넷 (지은이), 김다은 (옮긴이)', 100, '9788946422018', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(118, '개미 5년, 세후 55억', 'https://image.aladin.co.kr/product/28412/81/cover/k102835016_1.jpg','성현우(스위트레이더) (지은이)', 100, '9791197001932', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(119, '보통의 것이 좋아 - 나만의 보폭으로 걷기, 작고 소중한 행복을 놓치지 말기', 'https://image.aladin.co.kr/product/28412/78/cover/k712835015_1.jpg','반지수 (지은이)', 100, '9791168120587', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(120, '신의 비밀, 징조 - 그 징조는 어떤 미래를 알려주는가?', 'https://image.aladin.co.kr/product/28412/77/cover/k752835015_1.jpg','김승호 (지은이)', 100, '9791165344351', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(130, '사이 떡볶이', 'https://image.aladin.co.kr/product/28412/76/cover/k492835015_1.jpg','소연 (지은이), 원유미 (그림)', 100, '9791187903956', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(131, 'COSMOS 우주에 깃든 예술', 'https://image.aladin.co.kr/product/28410/86/cover/k392835013_1.jpg','로베르타 J. M. 올슨, 제이 M. 파사쇼프 (지은이), 곽영직 (옮긴이)', 100, '9791159713781', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(132, '2022 대한민국이 열광할 시니어 트렌드 - 새로운 소비권력 5070의 취향과 욕망에서 찾은 비즈니스 인사이트', 'https://image.aladin.co.kr/product/28410/78/cover/k052835013_1.jpg','고려대학교 고령사회연구센터 (지은이)', 100, '9791162542507', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(133, '천 번의 죽음이 내게 알려준 것들 - 호스피스 의사가 전하는 삶과 죽음에 관한 이야기', 'https://image.aladin.co.kr/product/28410/67/cover/k852835012_1.jpg','김여환 (지은이)', 100, '9791191347586', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(134, '과학이 재밌어지는 아주 친절한 과학책', 'https://image.aladin.co.kr/product/28410/56/cover/k502835012_1.jpg','이재화 (지은이), 사마키 다케오 (옮긴이), 류성철 (감수)', 100, '9791159713828', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(135, '이 약 한번 잡숴 봐! - 식민지 약 광고와 신체정치', 'https://image.aladin.co.kr/product/28410/15/cover/k562835011_1.jpg','최규진 (지은이)', 100, '9791192085043', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(136, '밤이여 오라 - 제9회 제주 4·3평화문학상 수상작', 'https://image.aladin.co.kr/product/28409/99/cover/k372835011_1.jpg','이성아 (지은이)', 100, '9791167371058', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(137, '권력의 미래 - 소프트 파워 리더십은 어떻게 세상을 바꾸는가？', 'https://image.aladin.co.kr/product/28409/67/cover/k792835010_1.jpg','조지프 나이 (지은이), 윤영호 (옮긴이)', 100, '9788984078130', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(138, '라흐 뒤 프루콩 드 네주 말하자면 눈송이의 예술', 'https://image.aladin.co.kr/product/28409/49/cover/8937409135_1.jpg','박정대 (지은이)', 100, '9788937409134', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(139, '오늘은 비건 샌드위치 - 채식 초보자를 위한 맛있고 건강하고 만들기 쉬운 비건 레시피 60', 'https://image.aladin.co.kr/product/28409/43/cover/896952486x_1.jpg','박소현 (지은이)', 100, '9788969524867', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(140, '매거진 G 4호 다시 시작할 수 있을까?', 'https://image.aladin.co.kr/img/noimg_b.gif','안수향, 휘리, 김연덕, 오지은, 최석현, 김산하, 박정현, 마민지, 조효원, 김승일, 백승주, 박한선, 김대식, 김경일, 우동현, 홍종원, 서진영, 허휘수, 김혜연, 이정화 (지은이)', 100, '9788934974901', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(141, '전라디언의 굴레 - 지역과 계급이라는 이중차별, 누구나 알지만 아무도 모르는 호남의 이야기', 'https://image.aladin.co.kr/product/28409/12/cover/k872835019_1.jpg','조귀동 (지은이)', 100, '9791190955454', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(142, '느낌표', 'https://image.aladin.co.kr/product/28409/12/cover/k892835019_1.jpg','에이미 크루즈 로젠탈 (지은이), 탐 리히텐헬드 (그림), 용희진 (옮긴이)', 100, '9791165732080', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(143, '산책 소설', 'https://image.aladin.co.kr/product/28408/46/cover/k322835917_1.jpg','오은경 (지은이)', 100, '9791167900753', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(144, '나만큼 널 사랑할 인간은 없을 것 같아 - 마음껏 사랑해도 그 이상을 돌려주는 멍냥이들에게', 'https://image.aladin.co.kr/product/28408/26/cover/k652835916_1.jpg','백세희, 전아론 (지은이)', 100, '9791191623017', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(145, '내일은 발명왕 35 - 소리의 발명 대결', 'https://image.aladin.co.kr/product/28408/12/cover/k312835916_1.jpg','곰돌이 co. (지은이), 홍종현 (그림), 박완규, 황성재 (감수)', 100, '9791164136964', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(146, '아버지는 변명하지 않는다, 다만 사라질 뿐 - 아버지를 인터뷰하다', 'https://image.aladin.co.kr/product/28408/10/cover/8997870580_2.jpg','김경희 (지은이)', 100, '9788997870585', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(147, '한 게으른 시인의 이야기', 'https://image.aladin.co.kr/product/28407/99/cover/k162835916_2.jpg','최승자 (지은이)', 100, '9791191859133', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(148, '비폭력의 힘 - 윤리학-정치학 잇기', 'https://image.aladin.co.kr/product/28407/99/cover/8954683851_1.jpg','주디스 버틀러 (지은이), 김정아 (옮긴이)', 100, '9788954683852', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(149, '아무것도 하지 않는 법', 'https://image.aladin.co.kr/product/28407/86/cover/k772835915_1.jpg','제니 오델 (지은이), 김하현 (옮긴이)', 100, '9791197559679', NOW());
INSERT INTO books (id, title, image_url, author, total_pages, isbn, created_at) VALUES 
(150, '계획이 실패가 되지 않게 - 반드시 결과를 내는 탁월한 실행의 기술', 'https://image.aladin.co.kr/product/28407/81/cover/k662835915_1.jpg','이소연 (지은이)', 100, '9791130678221', NOW());