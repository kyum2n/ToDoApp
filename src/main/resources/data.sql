insert into users (uName, uId, uPwd, uMail) values ('이규민', 'user1', '1234', 'aaa@naver.com');
insert into users (uName, uId, uPwd, uMail) values ('류의정', 'user2', '5678', 'bbb@naver.com');
insert into users (uName, uId, uPwd, uMail) values ('정광원', 'user3', '1357', 'ccc@naver.com');



-- 위치 표시용 latitude, longitude 추가
-- 위치용 데이터 추가

insert into todos (uId, todo, detail, done, deadline, latitude, longitude) values ('user1', '도서관 가기', '책 빌리기', true, current_timestamp, 37.579617, 126.977041);
insert into todos (uId, todo, detail, done, deadline, latitude, longitude) values ('user2', '쇼핑', '마트에서 식재료 구입하기', false, current_timestamp, 37.565784, 126.989489);
insert into todos (uId, todo, detail, done, deadline, latitude, longitude) values ('user3', '헬스장 가기', '운동하기', false, current_timestamp, 37.501274, 127.039585);


