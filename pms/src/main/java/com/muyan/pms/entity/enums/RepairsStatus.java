package com.muyan.pms.entity.enums;

import lombok.AllArgsConstructor;

/**
 * @program: RepairsStatus
 * @description:
 * @author: muyan
 * @date: 2023/04/22 12:01:04
 **/
@AllArgsConstructor
public enum RepairsStatus {

     NOT("未完成"),
     ING("进行中"),
     YES("已完成");

    private String name;

}
