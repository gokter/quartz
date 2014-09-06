package com.flyingh.quartz5;

import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {

	public static final String DELAY_MILLI_SECOND_LONG = "delay_milli_second";
	public static final String EXECUTION_COUNT = "execution_count";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getJobDetail().getKey() + " executed at " + String.format("%1$tF %1$tT", Calendar.getInstance()));
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int executionCount = jobDataMap.containsKey(EXECUTION_COUNT) ? jobDataMap.getInt(EXECUTION_COUNT) : 0;
		jobDataMap.put(EXECUTION_COUNT, ++executionCount);
		final long delay = jobDataMap.containsKey(DELAY_MILLI_SECOND_LONG) ? jobDataMap.getLong(DELAY_MILLI_SECOND_LONG) : 5000L;
		try {
			Thread.sleep(delay);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s executed %d times.%n", context.getJobDetail().getKey(), executionCount);
	}
}
