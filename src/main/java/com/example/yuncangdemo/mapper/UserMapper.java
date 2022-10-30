package com.example.yuncangdemo.mapper;

import com.example.yuncangdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:15
 */
@Mapper
public interface UserMapper {
    @Select("select * from user ")
    List<User> list();

    @Select("select wallet_id from user where id = #{userId}")
    Integer getWalletById(@Param("userId") Integer userId);
}
