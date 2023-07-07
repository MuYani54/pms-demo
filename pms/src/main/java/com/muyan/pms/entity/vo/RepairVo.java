package com.muyan.pms.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: RepairVo
 * @description:
 * @author: muyan
 * @date: 2023/04/22 22:40:35
 **/
@Data
public class RepairVo {

    @NotNull(message = "id 不能为空")
    private Long id;

    private BigDecimal RepairsPrice;

    private String repairsBillNo;
}
