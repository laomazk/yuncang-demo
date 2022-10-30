package com.example.yuncangdemo.mapper;

import com.example.yuncangdemo.entity.BalanceChange;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:15
 */
@Mapper
public interface BalanceChangeMapper {
    @Insert("insert into balance_change(wallet_id,before_balance,after_balance,create_time,update_time) values (#{b.walletId},#{b.beforeBalance},#{b.afterBalance}" +
            ",#{b.createTime},#{b.updateTime})")
    void insert(@Param("b") BalanceChange balance);

    @Select("select * from balance_change wherer wallet_id = #{walletId} order by create_time desc")
    List<BalanceChange> listChangeOrderByCreateTime(@Param("walletId") Integer walletId);
}
