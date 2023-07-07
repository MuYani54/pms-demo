package com.muyan.pms.service;

import com.muyan.pms.entity.dto.AccountDetailDto;
import com.muyan.pms.entity.dto.AccountDto;
import com.muyan.pms.entity.pms.AccountDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: AccountService
 * @description: 账户接口
 * @author: muyan
 * @date: 2023/04/21 21:52:50
 **/
public interface AccountService {

    /**
     * 查询全部账户
     * @param pageable
     * @return
     */
    Page<AccountDto> findAll(Pageable pageable);

    /**
     * 根据用户id 查询用户账户
     * @param userId
     * @return
     */
    Page<AccountDto> search(Long userId,Pageable pageable);

    /**
     * 账户充值
     * @param accountDetail
     */
    void payment(AccountDetail accountDetail);

    /**
     * 充值报表查询
     * @param userId
     * @return
     */
    List<AccountDetailDto> report(String userId);
}
