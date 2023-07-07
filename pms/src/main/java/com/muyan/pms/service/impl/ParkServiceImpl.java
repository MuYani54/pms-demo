package com.muyan.pms.service.impl;

import com.muyan.pms.dao.ParkRepository;
import com.muyan.pms.entity.dto.ParkDto;
import com.muyan.pms.entity.enums.UseStatus;
import com.muyan.pms.entity.pms.Park;
import com.muyan.pms.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @program: ParkServiceImpl
 * @description:
 * @author: muyan
 * @date: 2023/04/20 19:05:12
 **/
@Service
public class ParkServiceImpl implements ParkService {

    @Autowired
    private ParkRepository parkRepository;

    @Override
    public void create(Park park) {
        park.setUseStatus(UseStatus.ENABLED);
        parkRepository.save(park);
    }

    @Override
    public Page<ParkDto> findAllDto(Pageable pageable) {
        return parkRepository.findAllDto(pageable);
    }

    @Override
    public Page<ParkDto> search(Long userId, Pageable pageable) {
        return parkRepository.search(userId,pageable);
    }

    @Override
    public ParkDto updateUseStatus(Park park) {
        parkRepository.updateUseStatus(park);
        return parkRepository.findOne(park.getId());
    }
}
