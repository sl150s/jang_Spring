
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
