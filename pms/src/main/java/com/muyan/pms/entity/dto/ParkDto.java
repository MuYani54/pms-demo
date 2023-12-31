package com.muyan.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muyan.pms.entity.enums.ParkType;
import com.muyan.pms.entity.enums.UseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @program: ParkDto
 * @description:
 * @author: muyan
 * @date: 2023/04/20 23:16:21
 **/
@Data
@AllArgsConstructor
public class ParkDto {

    private Long id;

    private String username;

    private UseStatus useStatus;

    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    private Date expireDate;

    private ParkType parkType;

    private String position;
}
