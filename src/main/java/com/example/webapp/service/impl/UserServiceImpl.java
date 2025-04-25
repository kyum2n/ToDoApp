package com.example.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webapp.entity.User;
import com.example.webapp.repository.UserMapper;
import com.example.webapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User findByUId(String uId) {
        return userMapper.selectByUId(uId);
    }

    @Override
    public void insertUser(User users) {
        userMapper.insertUser(users);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.selectByUMail(email);
    }
}
