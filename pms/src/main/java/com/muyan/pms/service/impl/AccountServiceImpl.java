package com.muyan.pms.service.impl;

import com.muyan.pms.dao.AccountDetailRepository;
import com.muyan.pms.dao.UserRepository;
import com.muyan.pms.entity.dto.AccountDetailDto;
import com.muyan.pms.entity.dto.AccountDto;
import com.muyan.pms.entity.pms.AccountDetail;
import com.muyan.pms.entity.security.User;
import com.muyan.pms.exception.SystemErrorException;
import com.muyan.pms.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @program: AccountServiceImpl
 * @description:
 * @author: muyan
 * @date: 2023/04/21 21:53:23
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        return userRepository.findAccountAll(pageable);
    }

    @Override
    public Page<AccountDto> search(Long userId,Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SystemErrorException("用户不存在"));
        return userRepository.findAccountByUserId(user.getUsername(),pageable);
    }

    @Override
    @Transactional
    public synchronized void payment(AccountDetail accountDetail) {
        userRepository.findById(accountDetail.getUser().getId()).ifPresent(detail->{
            detail.setBalance(detail.getBalance().add(accountDetail.getMoney()));
            userRepository.save(detail);
        });
        //设置充值时间
        accountDetail.setRechargeTime(new Date());
        accountDetailRepository.save(accountDetail);
    }

    @Override
    public List<AccountDetailDto> report(String userId) {
        Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        if(StringUtils.isBlank(userId) || userId.equals("null")){
            return accountDetailRepository.findAllDto(sort);
        }else{
            return accountDetailRepository.search(Long.parseLong(userId),sort);
        }
    }
}
