# 简单思路  
- 查询用户钱包余额
  
1. 根据用户id查询即可
- 用户消费100元的接口

1. 生成订单
2. 查询原来的余额
3. 消费
4. 记录余额变动
- 用户退款20元接口

1. 查出原来的余额
2. 将原来创建的订单置为软删
3. 退款
4. 记录余额变动
- 查询用户钱包金额变动明细的接口
1. 将钱包id的余额变动查出来按照创建时间或者主键id倒序即可

# 表设计
- 用户表
  
```sql
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `wallet_id` int DEFAULT NULL COMMENT '钱包id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

- 订单表
```sql
CREATE TABLE `order` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
`balance` decimal(20,0) DEFAULT NULL COMMENT '金额',
`user_id` int DEFAULT NULL COMMENT '用户id',
`is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

- 钱包表
```sql
CREATE TABLE `wallet` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
`balance` decimal(20,0) DEFAULT NULL COMMENT '余额\r\n',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```

- 余额变动表
```sql
CREATE TABLE `balance_change` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wallet_id` int DEFAULT NULL COMMENT '钱包ID',
  `before_balance` decimal(20,0) DEFAULT NULL COMMENT '变动前余额',
  `after_balance` decimal(20,0) DEFAULT NULL COMMENT '变动后余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
```




# api 接口实现详见 WalletController
# 延伸阅读
因为做的比较匆忙，可能有写地方设计的不是很合理。

