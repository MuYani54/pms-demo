package com.muyan.pms.service;

import com.muyan.pms.entity.pms.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @program: HouseService
 * @description:
 * @author: muyan
 * @date: 2023/04/09 13:37:37
 **/
public interface HouseService  extends  BaseService<House> {

    Page<House> findHouseByUserId(Long id,Pageable pageable);

}
