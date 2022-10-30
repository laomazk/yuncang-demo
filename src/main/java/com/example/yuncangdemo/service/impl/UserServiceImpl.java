package com.example.yuncangdemo.service.impl;

import com.example.yuncangdemo.entity.User;
import com.example.yuncangdemo.mapper.UserMapper;
import com.example.yuncangdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
