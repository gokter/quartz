package com.flyingh.quartz6;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BadJob2 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.printf("%s executed at %tF %<tT%n", context.getJobDetail().getKey(), Calendar.getInstance());
		try {
			final int a = 0 / 0;
			System.out.println(a);
		} catch (final Exception e) {
			final JobExecutionException exception = new JobExecutionException(e);
			exception.setUnscheduleAllTriggers(true);
			throw exception;
		}
	}

}
