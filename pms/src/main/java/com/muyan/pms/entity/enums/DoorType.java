package com.muyan.pms.entity.enums;

import lombok.Getter;

/**
 * @program: DoorType
 * @description: 开门方式
 * @author: muyan
 * @date: 2023/04/18 22:27:29
 **/
@Getter
public enum DoorType {

    QR("二维码"),
    NFC("门禁卡");

    private String name;

    DoorType(String name){
        this.name=name;
    }

}
