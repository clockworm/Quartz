package com.quartz.demo;

import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo implements ServletContextListener {
	public static Scheduler scheduler = null;
	public static Trigger trigger = null;
	public static JobDetail job = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/* 注册定时任务 */
		try {
			// 获取Scheduler实例
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			// 具体任务和参数
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("name", "zhang");
			jobDataMap.put("age", 17);
			jobDataMap.put("time", new Date());
			job = JobBuilder.newJob(JobDemo.class).withIdentity("job1", "group1").usingJobData(jobDataMap).build();

			// 触发时间点
			// SimpleScheduleBuilder simpleScheduleBuilder =
			// SimpleScheduleBuilder.simpleSchedule()
			// .withIntervalInSeconds(5).repeatForever();
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
			trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
					.withSchedule(cronScheduleBuilder).build();

			// 交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException se) {
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		/* 注销定时任务 */
		try {
			// 关闭Scheduler
			scheduler.shutdown();
		} catch (SchedulerException se) {
		}
	}
}
