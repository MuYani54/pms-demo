package com.muyan.pms.service;

import com.muyan.pms.entity.dto.RepairsDto;
import com.muyan.pms.entity.dto.RepairsReportDto;
import com.muyan.pms.entity.pms.Repairs;
import com.muyan.pms.entity.vo.RepairVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: RepairsService
 * @description:
 * @author: muyan
 * @date: 2023/04/22 12:09:05
 **/
public interface RepairsService extends BaseService<Repairs> {

    Page<RepairsDto> findAllDto(Pageable pageable);

    /**
     * 开始派单
     * @param repairVo
     */
    void startDispatch(RepairVo repairVo);

    /**
     * 结束订单
     * @param repairVo
     */
    void endDispatch(RepairVo repairVo);

    /**
     * 订单中心搜索
     * @param userId
     * @param pageable
     * @return
     */
    Page<RepairsDto> search(Long userId, Pageable pageable);

    /**
     * 报表查询
     * @param userId
     * @return
     */
    List<RepairsReportDto> report(String userId);
}
