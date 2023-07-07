package com.muyan.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muyan.pms.entity.enums.UseStatus;
import com.muyan.pms.entity.enums.DoorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: DoorDto
 * @description:
 * @author: muyan
 * @date: 2023/04/20 15:03:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoorDto implements Serializable{

    private Long id;

    private String username;

    private UseStatus useStatus;

    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    private Date expireDate;

    private DoorType doorType;
}
