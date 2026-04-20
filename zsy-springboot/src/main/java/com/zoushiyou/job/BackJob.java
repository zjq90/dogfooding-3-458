package com.zoushiyou.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackJob {
    
    private static final Logger logger = LoggerFactory.getLogger(BackJob.class);
    
    @Scheduled(cron = "0 * * * * ?")
    public void execute() {
        logger.info("定时任务执行中...");
    }
}
