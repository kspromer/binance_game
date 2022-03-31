package io.renren.modules.task;

import io.renren.modules.binancegame.service.KlinesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/22 22:46
 */
@Component
@Slf4j
public class SettlementTask {

    @Autowired
    private KlinesService klinesService;

    @Scheduled(cron="2/30 * * * * ?") //每分钟执行一次
    public void settlement() {
        log.info("task = {}",System.currentTimeMillis());
        klinesService.settlement();
    }

    @Scheduled(cron="0 0/5 * * * ?") //每分钟执行一次
    public void currentIssueNoCacheRefresh() {
        log.info("currentIssueNoCacheRefresh = {}",System.currentTimeMillis());
        klinesService.currentIssueNoCacheRefresh();
    }

}
