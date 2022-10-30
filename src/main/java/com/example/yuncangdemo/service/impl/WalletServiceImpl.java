package com.example.yuncangdemo.service.impl;

import com.example.yuncangdemo.dto.ActionDTO;
import com.example.yuncangdemo.entity.BalanceChange;
import com.example.yuncangdemo.entity.Order;
import com.example.yuncangdemo.entity.Wallet;
import com.example.yuncangdemo.mapper.BalanceChangeMapper;
import com.example.yuncangdemo.mapper.OrderMapper;
import com.example.yuncangdemo.mapper.UserMapper;
import com.example.yuncangdemo.mapper.WalletMapper;
import com.example.yuncangdemo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author laoma
 * @create 2022-10-30 11:23
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private BalanceChangeMapper balanceChangeMapper;

    @Override
    public BigDecimal queryBalanceByUserId(Integer userId) {
        return walletMapper.queryBalanceByUserId(userId);
    }

    @Transactional
    @Override
    public void consume(ActionDTO actionDTO) {
        // 生成订单
        Order order = actionDTO.getOrder();
        // todo 订单造假数据
        order.setBalance(BigDecimal.TEN);
        order.setUserId(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        BigDecimal balance = order.getBalance();
        Integer walletId = userMapper.getWalletById(order.getUserId());
        // 查出原来的余额
        Wallet w = walletMapper.getByWalletId(walletId);
        BigDecimal before = w.getBalance();
        if(before.compareTo(balance) < 0){
            throw new RuntimeException("余额不足");
        }
        orderMapper.insert(order);
        // 消费
        walletMapper.consume(walletId, balance);
        // 记录余额变动
        BalanceChange balanceChange = new BalanceChange();
        balanceChange.setBeforeBalance(before);
        balanceChange.setAfterBalance(before.subtract(balance));
        balanceChange.setWalletId(walletId);
        balanceChange.setUpdateTime(new Date());
        balanceChange.setCreateTime(new Date());
        balanceChangeMapper.insert(balanceChange);
    }

    @Transactional
    @Override
    public void refund(ActionDTO actionDTO) {
        Order order = actionDTO.getOrder();
        BigDecimal balance = order.getBalance();
        Integer walletId = userMapper.getWalletById(order.getUserId());
        // 查出原来的余额
        Wallet w = walletMapper.getByWalletId(walletId);
        BigDecimal before = w.getBalance();
        // 把订单置为软删
        Integer id = order.getId();
        //  todo id 置为假数据
        id = 5;
        orderMapper.upddateDelete(id);
        // 退款
        walletMapper.refund(walletId,balance);
        // 记录余额变动
        BalanceChange balanceChange = new BalanceChange();
        balanceChange.setBeforeBalance(before);
        balanceChange.setAfterBalance(before.add(balance));
        balanceChange.setWalletId(walletId);
        balanceChange.setUpdateTime(new Date());
        balanceChange.setCreateTime(new Date());
        balanceChangeMapper.insert(balanceChange);
    }

    @Override
    public List<BalanceChange> listChange(Integer userId) {
        // 查出用户的钱包id
        Integer walletId = userMapper.getWalletById(userId);
        // 余额变动表中查钱包id 按照 create_time 倒叙
        return balanceChangeMapper.listChangeOrderByCreateTime(walletId);

    }
}
