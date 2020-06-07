package com.songbo.filecoincloud.utils;

import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName TimeUtil
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/20 上午12:04
 **/
@Data
public class TimeUtil {

    private AtomicLong nowLong;
    private static volatile TimeUtil timeUtil;

    public TimeUtil() {
        this.nowLong = new AtomicLong(System.currentTimeMillis());
        scheduled();
    }

    private void scheduled() {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "time now");
                        thread.setDaemon(true);
                        return thread;
                    }
                }
        );
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                nowLong.set(System.currentTimeMillis());
            }
        }, 1, 1, TimeUnit.MICROSECONDS);
    }

    public static TimeUtil getInstance() {
        if (timeUtil == null) {
            synchronized (TimeUtil.class) {
                timeUtil = new TimeUtil();
            }
        }
        return timeUtil;
    }

    public static void main(String[] args) throws InterruptedException {
        /*String b = "asdadada1lla";
        char[] s = b.toCharArray();
        int a = s[s.length-1];
        System.out.println(a);*/
        for (int i = 0; i < 100; i++) {
            System.out.println(TimeUtil.getInstance().getNowLong());
            Thread.sleep(1000);
        }
    }
}
