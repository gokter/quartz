package com.flyingh.quartz1;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleExample {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		final JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job", "group").build();
		final Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").startAt(DateBuilder.evenMinuteDate(new Date())).build();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		Thread.sleep(90L * 1000);
		scheduler.shutdown(true);
	}
}
