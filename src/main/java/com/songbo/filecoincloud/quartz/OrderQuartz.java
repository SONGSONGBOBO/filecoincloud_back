package com.songbo.filecoincloud.quartz;


import com.songbo.filecoincloud.service.IFccOrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName AuctionQuartz
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/16 下午1:30
 **/
@Slf4j
public class OrderQuartz implements Job {

    @Autowired
    private IFccOrderService orderService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        orderService.refreshOrders();
    }
}
