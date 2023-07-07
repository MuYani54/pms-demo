package com.muyan.pms.dao;

import com.muyan.pms.entity.dto.AccountDetailDto;
import com.muyan.pms.entity.pms.AccountDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: AccountDetailRepository
 * @description:
 * @author: muyan
 * @date: 2023/04/21 22:07:56
 **/
@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Long>{

    @Query("select new com.muyan.pms.entity.dto.AccountDetailDto(user.id,user.username,user.realName,user.phone,acc.money,acc.rechargeTime,acc.rechargeType) " +
            "from AccountDetail acc left join User user on user.id=acc.user")
    List<AccountDetailDto> findAllDto(Sort sort);


    @Query("select new com.muyan.pms.entity.dto.AccountDetailDto(user.id,user.username,user.realName,user.phone,acc.money,acc.rechargeTime,acc.rechargeType) " +
            "from AccountDetail acc left join User user on user.id=acc.user where user.id= :userId")
    List<AccountDetailDto> search(@Param("userId") Long userId, Sort sort);

}
