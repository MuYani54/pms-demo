package com.muyan.pms.service.impl;

import com.muyan.pms.dao.RepairsRepository;
import com.muyan.pms.dao.UserRepository;
import com.muyan.pms.entity.dto.RepairsDto;
import com.muyan.pms.entity.dto.RepairsReportDto;
import com.muyan.pms.entity.enums.RepairsStatus;
import com.muyan.pms.entity.pms.Repairs;
import com.muyan.pms.entity.vo.RepairVo;
import com.muyan.pms.service.RepairsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: RepairsServiceImpl
 * @description:
 * @author: muyan
 * @date: 2023/04/22 12:09:25
 **/
@Service
public class RepairsServiceImpl implements RepairsService {

    @Autowired
    private RepairsRepository repairsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(Repairs repairs) {
        repairs.setRepairsStatus(RepairsStatus.NOT);
        repairs.setRepairsDate(new Date());
        repairs.setRepairsBillNo("BX"+System.currentTimeMillis());
        repairsRepository.save(repairs);
    }

    @Override
    public Page<RepairsDto> findAllDto(Pageable pageable) {
        return repairsRepository.findAllDto(pageable);
    }

    @Override
    public void startDispatch(RepairVo repairVo) {
        repairsRepository.findById(repairVo.getId()).ifPresent(detail ->{
            detail.setRepairsStatus(RepairsStatus.ING);
            detail.setRepairsPrice(repairVo.getRepairsPrice());
            repairsRepository.save(detail);
        });
    }

    @Override
    @Transactional
    public void endDispatch(RepairVo repairVo) {
        repairsRepository.findById(repairVo.getId()).ifPresent(detail ->{
            detail.setRepairsStatus(RepairsStatus.YES);
            detail.setFinishDate(new Date());
            userRepository.findById(detail.getUser().getId()).ifPresent(user ->{
                user.setBalance(user.getBalance().subtract(detail.getRepairsPrice()));
                userRepository.save(user);
            });
            repairsRepository.save(detail);
        });
    }

    @Override
    public Page<RepairsDto> search(Long userId, Pageable pageable) {
        return repairsRepository.search(userId,pageable);
    }

    @Override
    public List<RepairsReportDto> report(String userId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        if(StringUtils.isBlank(userId)||userId.equals("null")){
            return repairsRepository.findAllReportDto(sort);
        }else {
            return repairsRepository.report(Long.parseLong(userId),sort);
        }
    }
}
