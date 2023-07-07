package com.muyan.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: SettleDto
 * @description:
 * @author: muyan
 * @date: 2023/04/28 22:24:45
 **/
@Data
@AllArgsConstructor
public class SettleDto {

    private Long userId;

    private String username;

    private String realName;

    private String costName;

    private BigDecimal totalPrice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh", timezone = "GMT+8")
    private Date settingDate;

}
