package com.flyingh.quartz4;

import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {
	public static final String EXECUTION_COUNT = "execution_count";
	public static final String FAVORITE_COLOR = "favorite_color";
	private int ownCount;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int executeCount = jobDataMap.getInt(EXECUTION_COUNT);
		System.out.printf("%s executed at %tF %<tT with %s favourite color, %d counts,%d own count%n", context.getJobDetail().getKey(),
				Calendar.getInstance(), jobDataMap.getString(FAVORITE_COLOR), executeCount, ++ownCount);
		jobDataMap.put(EXECUTION_COUNT, ++executeCount);
	}
}
