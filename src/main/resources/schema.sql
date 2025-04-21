
-- 유저 테이블 삭제 후 생성
DROP TABLE IF EXISTS todos;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    uName varchar(255) NOT NULL,
    uId varchar(255) NOT NULL PRIMARY KEY,
    uPwd varchar(255) NOT NULL,
    uMail varchar(255) NOT NULL
);

-- 할 일 테이블 삭제 후 생성

CREATE TABLE todos (
    id serial PRIMARY KEY, -- id(할일 ID)
    uId varchar(255) NOT NULL, -- u_id(닉네임)
    todo varchar(255) NOT NULL, -- to do(할일)
    detail text, -- detail(설명)
    done boolean DEFAULT false, -- check(체크)
    deadline timestamp, -- deadline(기간)
    createdAt timestamp without time zone, -- created_at(작성 일자)
    updatedAt timestamp without time zone, -- updated_at(업데이트 일자)
    FOREIGN KEY (uId) REFERENCES users (uId) -- 외래 키는 이렇게 따로 지정
);