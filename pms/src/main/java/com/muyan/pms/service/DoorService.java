package com.muyan.pms.service;

import com.muyan.pms.entity.dto.DoorDto;
import com.muyan.pms.entity.pms.Door;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: DoorService
 * @description:
 * @author: muyan
 * @date: 2023/04/19 08:44:10
 **/
public interface DoorService extends BaseService<Door> {

    /**
     * 更新门禁状态
     * @param door
     * @return
     */
    DoorDto updateDoorStatus(Door door);

    /**
     * 查询全部
     * @param pageable
     * @return
     */
    Page<DoorDto> findAllDto(Pageable pageable);

    /**
     * 查询用户的门禁
     * @param id
     * @param pageable
     * @return
     */
    Page<DoorDto> search(Long id,Pageable pageable);
}
