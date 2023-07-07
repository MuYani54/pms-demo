package com.muyan.pms.entity.enums;

import lombok.AllArgsConstructor;

/**
 * @program: RechargeType
 * @description:
 * @author: muyan
 * @date: 2023/04/21 16:49:20
 **/
@AllArgsConstructor
public enum RechargeType {
    WECHART("微信支付"),
    ALIPAY("支付宝"),
    CASH("现金"),
    CARD("银行卡");

    private String name;

}
