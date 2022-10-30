package com.example.yuncangdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName wallet
 */
@Data
public class Wallet implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 余额

     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}