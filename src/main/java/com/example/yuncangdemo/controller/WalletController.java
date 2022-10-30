package com.example.yuncangdemo.controller;

import com.example.yuncangdemo.dto.ActionDTO;
import com.example.yuncangdemo.entity.BalanceChange;
import com.example.yuncangdemo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author laoma
 * @create 2022-10-28 15:44
 */
@RestController
@RequestMapping("wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    /**
     * 查询用户钱包余额
     */
    @GetMapping("/query_balance_by_user_id")
    public BigDecimal queryBalanceByUserId(@RequestParam Integer userId){
        return walletService.queryBalanceByUserId(userId);
    }

    /**
     * 用户消费 100 元
     */
    @PostMapping("consume")
    public Boolean consume(@RequestBody ActionDTO actionDTO){
        // 消费
        walletService.consume(actionDTO);
        return true;
    }

    /**
     * 用户退款 20 元
     */
    @PostMapping("refund")
    public Boolean refund(@RequestBody ActionDTO actionDTO){
        walletService.refund(actionDTO);
        return true;
    }


    /**
     * 查询用户余额变动接口
     */
    @GetMapping("listChange")
    public List<BalanceChange> listChange(@RequestParam Integer userId){

        return walletService.listChange(userId);
    }
}
