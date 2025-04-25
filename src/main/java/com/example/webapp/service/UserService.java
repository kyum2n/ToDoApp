package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.User;

public interface UserService {
    // 모든 사용자를 조회
    List<User> findAllUsers();

    // 특정 uId의 사용자를 조회
    User findByUId(String uId); // -> SELECT * FROM todos WHERE id = ?

    // 사용자를 추가
    void insertUser(User users);

    // 구글로 로그인 시 이메일로 사용자 조회
    User findByEmail(String email);

    // 로그인 체크
    // User findByUIdAndPassword(String uId, String uPwd);

}
