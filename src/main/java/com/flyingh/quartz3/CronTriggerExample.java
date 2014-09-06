package com.flyingh.quartz3;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerExample {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger1", "group").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger2", "group").withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger3", "group").withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger4", "group").withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 17-23 * * ?"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger5", "group").withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger6", "group").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI"))
				.build());
		scheduler.scheduleJob(JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger7", "group").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN"))
				.build());

		scheduler.start();
		Thread.sleep(300L * 1000);
		scheduler.shutdown();

	}
}
