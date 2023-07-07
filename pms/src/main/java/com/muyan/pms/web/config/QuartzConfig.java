package com.muyan.pms.web.config;

import com.muyan.pms.service.job.HelloWordJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: QuartzConfig
 * @description:
 * @author: muyan
 * @date: 2023/06/13 11:13:47
 **/
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail helloJobDetail(){
        return JobBuilder.newJob(HelloWordJob.class)
                .withIdentity("myJob","myGroup")
                .usingJobData("","")
                .storeDurably()
                .build();
    }

    //@Bean
    public Trigger helloJobTrigger(){
        return TriggerBuilder.newTrigger().forJob(helloJobDetail())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("1/5 * * * * ?"))
                .build();
    }

}
