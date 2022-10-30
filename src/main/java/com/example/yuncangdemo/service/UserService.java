package com.example.yuncangdemo.service;

import com.example.yuncangdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:15
 */

public interface UserService {
    List<User> list();
}
