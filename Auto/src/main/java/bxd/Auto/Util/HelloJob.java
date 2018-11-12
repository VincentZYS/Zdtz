/**
 * 
 */
package bxd.Auto.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import bxd.Auto.Dao.BuyDAO;
import bxd.Auto.Dao.PlanDAO;
import bxd.Auto.Model.Plan;

/**
 * @author a
 *
 */
public class HelloJob implements Job {
	
	
	
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// 打印当前的执行时间，格式为2017-01-01 00:00:00
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("定时投注任务，执行时间: " + sdf.format(date));
		String time = sdf.format(date).substring(0, 10);
		
		BuyDAO bd=new BuyDAO();
		Connection con = Jsoup.connect("https://1111hj8.com/mobile/v3/betInfo.do");
	}

}
