package com.muyan.pms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: AccountDto
 * @description:
 * @author: muyan
 * @date: 2023/04/21 21:55:41
 **/
@Data
@AllArgsConstructor
public class AccountDto {

    private Long userId;

    private String username;

    private String realName;

    private BigDecimal balance;
}
