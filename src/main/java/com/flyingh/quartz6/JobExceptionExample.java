package com.flyingh.quartz6;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobExceptionExample {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		final Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		scheduler.scheduleJob(
				JobBuilder.newJob(BadJob1.class).withIdentity("job1", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger1", "group").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build());
		scheduler.scheduleJob(
				JobBuilder.newJob(BadJob2.class).withIdentity("job2", "group").build(),
				TriggerBuilder.newTrigger().withIdentity("trigger2", "group").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build());
		scheduler.start();
		Thread.sleep(60L * 1000);
		scheduler.shutdown(true);
	}
}
