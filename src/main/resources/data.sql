insert into users (uName, uId, uPwd, uMail)
values (
    '이규민', 'user1', '1234', 'aaa@naver.com'
);

insert into users (uName, uId, uPwd, uMail)
values (
    '류의정', 'user2', '5678', 'bbb@naver.com'
);

insert into users (uName, uId, uPwd, uMail)
values (
    '정광원', 'user3', '1357', 'ccc@naver.com'
);




insert into todos (uId, todo, detail, done, deadline)
values (
    'user1', '도서관 가기', '책 빌리기', true, current_timestamp
);

insert into todos (uId, todo, detail, done, deadline)
values (
    'user2', '쇼핑', '마트에서 식재료 구입하기', false, current_timestamp
);

insert into todos (uId, todo, detail, done, deadline)
values (
    'user3', '헬스장 가기', '운동하기', false, current_timestamp
);

