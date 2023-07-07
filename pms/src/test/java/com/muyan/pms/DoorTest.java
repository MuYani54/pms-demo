package com.muyan.pms;

import com.muyan.pms.dao.DoorRepository;
import com.muyan.pms.entity.dto.DoorDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: DoorTest
 * @description:
 * @author: muyan
 * @date: 2023/04/20 15:11:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DoorTest {

    @Autowired
    private DoorRepository doorRepository;

    @Test
    public void findOne(){
        DoorDto oneById = doorRepository.findOneById(1L);
        System.out.println(oneById);
    }
}
