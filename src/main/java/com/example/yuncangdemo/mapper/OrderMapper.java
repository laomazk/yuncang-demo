package com.example.yuncangdemo.mapper;

import com.example.yuncangdemo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author laoma
 * @create 2022-10-30 11:15
 */
@Mapper
public interface OrderMapper {
    @Insert("insert into `order`(balance,user_id,is_delete,create_time,update_time) values (#{order.balance},#{order.userId},0,#{order.createTime},#{order.updateTime})")
    void insert(@Param("order") Order order);

    @Update("update `order` set is_delete = 1 where id  = #{id}")
    void upddateDelete(@Param("id") Integer id);
}
