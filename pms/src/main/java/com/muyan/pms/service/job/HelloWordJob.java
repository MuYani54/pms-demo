package com.muyan.pms.service.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @program: HelloWordJob
 * @description:
 * @author: muyan
 * @date: 2023/06/13 11:40:16
 **/
@Slf4j
public class HelloWordJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context){
        log.info("say Hello {}",context);
    }
}
