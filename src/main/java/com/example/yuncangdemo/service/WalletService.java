package com.example.yuncangdemo.service;

import com.example.yuncangdemo.dto.ActionDTO;
import com.example.yuncangdemo.entity.BalanceChange;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:14
 */

public interface WalletService {
    BigDecimal queryBalanceByUserId(Integer userId);

    void consume(ActionDTO actionDTO);

    void refund(ActionDTO actionDTO);

    List<BalanceChange> listChange(Integer userId);
}
