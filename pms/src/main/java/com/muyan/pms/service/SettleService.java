package com.muyan.pms.service;

import com.muyan.pms.entity.dto.SettleDto;
import com.muyan.pms.entity.pms.Settle;

import java.util.List;

/**
 * @program: SettleService
 * @description:
 * @author: muyan
 * @date: 2023/04/23 08:32:41
 **/
public interface SettleService extends BaseService<Settle> {

    /**
     * 充值报表查询
     * @param userId
     * @return
     */
    List<SettleDto> report(String userId);
}
