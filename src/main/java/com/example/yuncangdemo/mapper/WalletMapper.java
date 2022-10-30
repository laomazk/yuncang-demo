package com.example.yuncangdemo.mapper;

import com.example.yuncangdemo.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @author laoma
 * @create 2022-10-30 11:14
 */
@Mapper
public interface WalletMapper {

    @Select("select w.balance from wallet w left join user u on w.id = u.wallet_id where u.id = #{userId}")
    BigDecimal queryBalanceByUserId(@Param("userId") Integer userId);

    @Update("update wallet set balance = balance - #{balance} where id = #{walletId}")
    void consume(@Param("walletId") Integer walletId, @Param("balance") BigDecimal balance);

    @Select("select * from wallet")
    Wallet getByWalletId(Integer walletId);

    @Update("update wallet set balance = balance + #{balance} where id = #{walletId}")
    void refund(@Param("walletId") Integer walletId, @Param("balance") BigDecimal balance);
}
