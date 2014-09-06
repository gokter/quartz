package com.flyingh.quartz5;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MisfireExample {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		final Date startTime = DateBuilder.nextGivenSecondDate(null, 15);
		scheduler.scheduleJob(
				JobBuilder.newJob(StatefulDumbJob.class).withIdentity("job1", "group").usingJobData(StatefulDumbJob.DELAY_MILLI_SECOND_LONG, 10000L)
				.build(),
				TriggerBuilder.newTrigger().withIdentity("trigger1", "group").startAt(startTime)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build());
		scheduler.scheduleJob(
				JobBuilder.newJob(StatefulDumbJob.class).withIdentity("job2", "group").usingJobData(StatefulDumbJob.DELAY_MILLI_SECOND_LONG, 10000L)
				.build(),
				TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group")
				.startAt(startTime)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()
						.withMisfireHandlingInstructionNowWithExistingCount()).build());
		scheduler.start();
		Thread.sleep(600L * 1000);
		scheduler.shutdown(true);
	}
}
