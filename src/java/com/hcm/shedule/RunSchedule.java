/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.shedule;

import com.hcm.Database.CallDBQuery;
import com.hcm.process.Schedule;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



/**
 *
 * @author Prasenjit
 * http://localhost:8080/elementEntryServices/runapps
 * http://localhost:8080/elementEntryServices/runapps?stop=true
 */
public class RunSchedule {
    
//    public static void main(String[] args) throws SchedulerException{
//        JobDetail jd= JobBuilder.newJob(Schedule.class).build();
//        
//        Trigger tr=TriggerBuilder.newTrigger().withIdentity("CronTrigger")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(02)
//                .repeatForever()).build();
//        
//        Scheduler sc=StdSchedulerFactory.getDefaultScheduler();
//        
//        sc.start();
//        sc.scheduleJob(jd, tr);
//        
//    }
   
    
    public void runScheduler(Boolean stop) throws SchedulerException, SQLException, NamingException, ClassNotFoundException{
        System.out.println("boolean data: "+stop);
        Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
        if(stop){
           CallDBQuery.updateSetupStatus("TRUE");
           System.out.println("scheduler stopped");
           sc.shutdown();
        }else{
            CallDBQuery.updateSetupStatus("FALSE");
            //String timeSet=CallDBQuery.getSetUpDtls("TIME_SET");
            String timeSet = CallDBQuery.getCronExp("TIME_UNIT", "TIME_INTERVAL");
            System.out.println("cron genrated: "+timeSet);
            JobDetail jd = JobBuilder.newJob(Schedule.class).build();
            Trigger tr=TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(timeSet)) // Every 1 min
 //             .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * 1/1 * ? *")) // Every 1 hour
//              .inTimeZone(TimeZone.getTimeZone("Asia/Calcutta"))
//              .withSchedule(SimpleScheduleBuilder.simpleSchedule()s.withIntervalInSeconds(02).repeatForever())
                .build();   
            sc.start();
            sc.scheduleJob(jd, tr);
        }
    }
}
