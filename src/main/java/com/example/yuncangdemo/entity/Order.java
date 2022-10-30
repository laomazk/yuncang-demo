package com.example.yuncangdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName order
 */
@Data
public class Order implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 金额
     */
    private BigDecimal balance;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除时间
     */
    private Date updateTime;


}