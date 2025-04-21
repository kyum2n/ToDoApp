package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.User;

@Mapper
public interface UserMapper {
    // 회원 목록 조회
    List<User> selectAll();

    User selectByUId(@Param("uId") String uId);

    void insertUser(User users);
}
