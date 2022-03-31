package io.renren.modules.task;

import io.renren.modules.binancegame.service.AccountRechargeAddressService;
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
    @Autowired
    private AccountRechargeAddressService accountRechargeAddressService;

    /**
     * 30s执行一次
     */
    @Scheduled(cron="2/30 * * * * ?") //每分钟执行一次
    public void settlement() {
        log.info("task = {}",System.currentTimeMillis());
        klinesService.settlement();
    }

    /**
     * 5分钟执行一次
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public void currentIssueNoCacheRefresh() {
        log.info("currentIssueNoCacheRefresh = {}",System.currentTimeMillis());
        klinesService.currentIssueNoCacheRefresh();
    }

    /**
     * 充值扫描
     */
    @Scheduled(cron="0 0/1 * * * ?")
    public void rechargeTask() {
        log.info("rechargeTask = {}",System.currentTimeMillis());
        accountRechargeAddressService.rechargeTask();
    }

}
