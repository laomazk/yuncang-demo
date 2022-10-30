package com.example.yuncangdemo.dto;

import com.example.yuncangdemo.entity.Order;
import lombok.Data;

import java.io.Serializable;

/**
 * @author laoma
 * @create 2022-10-30 11:31
 */
@Data
public class ActionDTO implements Serializable {

    /**
     * 1 消费 2 退款
     */
    private Integer action;

    private Integer userId;

    private Order order;
}
