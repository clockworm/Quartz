package com.quartz.demo;

import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class mytask {
	int a = 0;

	@Scheduled(cron = "0/5 * *  * * ? ") // 每5秒执行一次
	public void myTest() {
		a += 1;
		System.out.println("进入测试1---->" + a+  "次数----->"   + 5+"s");
		System.out.println(this);
	}
	
	@Scheduled(cron = "0/10 * *  * * ? ") // 每5秒执行一次
	public void myTest2() {
		a += 1;
		System.out.println("进入测试2---->" + a+  "次数----->"   + 10+"s");
		System.out.println(this);
	}
	
	@Scheduled(cron = "0/50 * *  * * ? ") // 每5秒执行一次
	public void myTest3() {
		a += 1;
		System.out.println("进入测试3---->" + a+  "次数----->"   + 15+"s");
		System.out.println(this);
		JobKey jobKey = new JobKey("job1", "group1");
		try {
			QuartzDemo.scheduler.standby();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		System.out.println("重新开始");
	}
}