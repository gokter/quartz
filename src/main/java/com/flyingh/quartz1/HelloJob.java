package com.flyingh.quartz1;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello world!!!" + String.format("%1$tF %1$tT", Calendar.getInstance()));
	}

}
