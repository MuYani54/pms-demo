package com.muyan.pms.service;

import com.muyan.pms.entity.dto.ParkDto;
import com.muyan.pms.entity.pms.Park;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: ParkService
 * @description:
 * @author: muyan
 * @date: 2023/04/19 08:45:01
 **/
public interface ParkService extends BaseService<Park> {

    Page<ParkDto> findAllDto(Pageable pageable);

    Page<ParkDto> search(Long userId,Pageable pageable);

    ParkDto updateUseStatus(Park park);
}
