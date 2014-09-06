package com.flyingh.quartz3;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getJobDetail().getKey() + " executed at " + String.format("%1$tF %1$tT", Calendar.getInstance()));
	}

}
