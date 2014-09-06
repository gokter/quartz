package com.flyingh.quartz4;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobStateExample {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		final Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
		final JobDetail jobDetail1 = JobBuilder.newJob(ColorJob.class).withIdentity("job1", "group").build();
		jobDetail1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
		jobDetail1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);
		scheduler.scheduleJob(
				jobDetail1,
				TriggerBuilder.newTrigger().withIdentity("trigger1", "group").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build());

		final JobDetail jobDetail2 = JobBuilder.newJob(ColorJob.class).withIdentity("job2", "group").build();
		jobDetail2.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Red");
		jobDetail2.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);
		scheduler.scheduleJob(
				jobDetail2,
				TriggerBuilder.newTrigger().withIdentity("trigger2", "group").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build());

		scheduler.start();
		Thread.sleep(60L * 1000);
		scheduler.shutdown(true);
		System.out.println(scheduler.getMetaData().getNumberOfJobsExecuted() + " jobs executeds");
	}
}
