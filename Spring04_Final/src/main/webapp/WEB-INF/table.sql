
-- 사용자(회원) 정보를 저장할 테이블
CREATE TABLE users(
    id VARCHAR2(100) PRIMARY KEY,
    pwd VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    profile VARCHAR2(100), --프로필 이미지 경로를 저장할 칼럼
    regdate DATE
);

-- 업로드된 파일의 정보를 저장할 테이블
CREATE TABLE board_file(
    num NUMBER PRIMARY KEY,
    writer VARCHAR2(100) NOT NULL,
    title VARCHAR2(100) NOT NULL,
    orgFileName VARCHAR2(100) NOT NULL, -- 원본 파일명
    saveFileName VARCHAR2(100) NOT NULL, -- 서버에 실제로 저장된 파일명
    fileSize NUMBER NOT NULL, -- 파일의 크기 
    regdate DATE
);

CREATE SEQUENCE board_file_seq; 

-- 게시글을 저장할 테이블 
CREATE TABLE board_cafe(
    num NUMBER PRIMARY KEY, --글번호
    writer VARCHAR2(100) NOT NULL, --작성자 (로그인된 아이디)
    title VARCHAR2(100) NOT NULL, --제목
    content CLOB, --글 내용
    viewCount NUMBER, -- 조회수
    regdate DATE --글 작성일
);
-- 게시글의 번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_seq; 


-- -------------------------------------

-- 댓글을 저장할 테이블
CREATE TABLE board_cafe_comment(
   num NUMBER PRIMARY KEY, --댓글의 글번호
   writer VARCHAR2(100), --댓글 작성자의 아이디
   content VARCHAR2(500), --댓글 내용
   target_id VARCHAR2(100), --댓글의 대상자 아이디
   ref_group NUMBER, -- 게시글 글 번호가 '3'이면 해당 게시글에 있는 댓글들 관리하기 위해 설정(게시글 3번의 모든 댓글 수 얻어낼때 사용) 
   comment_group NUMBER, --댓글의 그룹 번호
   deleted CHAR(3) DEFAULT 'no', --누군가 댓글 삭제를 하면 브라우저 상에서만 앙ㄴ보이게 하기(yes로 바꾸기) - > 실제로 삭제하면 테이블의 구조가 망가질 수 있기에 브라우저에서 안보이게 하기 
   regdate DATE
);

-- 댓글의 글번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_comment_seq;


-- -------------------------------------

-- 상품테이블
CREATE TABLE shop(
   num NUMBER PRIMARY KEY, --상품번호
   name VARCHAR2(30), --상품이름
   price NUMBER, --상품가격
   remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 
);

-- 고객 계좌 테이블
CREATE TABLE client_account(
   id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
   money NUMBER CHECK(money >= 0), -- 고객의 잔고 
   point NUMBER
);

-- 주문 테이블
CREATE TABLE client_order(
   num NUMBER PRIMARY KEY, -- 주문번호
   id VARCHAR2(30), -- 주문 고객의 아이디
   code NUMBER, -- 주문한 상품의 번호 
   addr VARCHAR2(50) -- 배송 주소
);

-- 주문 테이블에 사용할 시퀀스 
CREATE SEQUENCE client_order_seq;


-- sample 데이터
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', 1000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', 2000, 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', 3000, 5);


-- sample 계좌 데이터 
INSERT INTO client_account
(id, money,point)
VALUES('dd',10000,0);

-- 다시 실행하기
UPDATE client_account
SET money=10000, point = 0 
WHERE id ='kimgura';

UPDATE shop
SET remainCount=5;