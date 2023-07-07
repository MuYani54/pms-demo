package com.muyan.pms.dao;

import com.muyan.pms.entity.pms.Settle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: SettleRepository
 * @description:
 * @author: muyan
 * @date: 2023/04/23 08:31:49
 **/
@Repository
public interface SettleRepository extends JpaRepository<Settle, Long> {


}
