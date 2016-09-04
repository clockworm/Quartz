package com.quartz.demo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

public class JobDemo implements Job {
	int count = 1;
	private static ThreadOpen threadOpen = new ThreadOpen();

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		JobKey jobKey = new JobKey("job1", "group1");
		if (threadOpen.openOrclose() % 20 == 0) {
			try {
				QuartzDemo.scheduler.pauseAll();//停止触发器
				System.out.println("停止任务");	
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		} 

		Map<String, Object> wrappedMap = jobExecutionContext.getJobDetail().getJobDataMap().getWrappedMap();
		Set<Entry<String, Object>> entrySet = wrappedMap.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			System.out.print(entry.getKey() + "," + entry.getValue());
		}
		System.out.println(this);
	}

}
