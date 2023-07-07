package com.muyan.pms.service.impl;

import com.muyan.pms.dao.CostSettingRepository;
import com.muyan.pms.dao.SettleRepository;
import com.muyan.pms.dao.UserRepository;
import com.muyan.pms.entity.dto.SettleDto;
import com.muyan.pms.entity.pms.CostSetting;
import com.muyan.pms.entity.pms.Settle;
import com.muyan.pms.exception.SystemErrorException;
import com.muyan.pms.service.SettleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: SettleServiceImpl
 * @description:
 * @author: muyan
 * @date: 2023/04/23 08:32:51
 **/
@Service
public class SettleServiceImpl implements SettleService {

    @Autowired
    private SettleRepository settleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CostSettingRepository costSettingRepository;

    @Override
    @Transactional
    public synchronized void create(Settle settle) {
        userRepository.findById(settle.getUser().getId()).ifPresent(user ->{
            CostSetting costSetting = costSettingRepository.findById(settle.getCostSetting().getId()).orElseThrow(() -> new SystemErrorException("费用不存在"));
            BigDecimal total = costSetting.getCostPrice().multiply(settle.getMultiple());
            settle.setTotalPrice(total);
            user.setBalance(user.getBalance().subtract(total));
            userRepository.save(user);
        });
        settle.setSettingDate(new Date());
        settleRepository.save(settle);
    }

    @Override
    public List<SettleDto> report(String userId) {
        Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        if(StringUtils.isBlank(userId) || userId.equals("null")){
            return costSettingRepository.findAllReportDto(sort);
        }else{
            return costSettingRepository.report(Long.parseLong(userId),sort);
        }
    }
}
