/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.process;

import com.hcm.Database.CallDBQuery;
import com.hcm.main.MainFile;
import com.hcm.payload.invokeOkHttps;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.json.JSONException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Prasenjit
 */
public class Schedule implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
//        System.err.println("===>RUN");

        String status = CallDBQuery.getSetUpDtls("STATUS");
        if(status.equalsIgnoreCase("START")){
            HashMap<String, String> details=CallDBQuery.getTransferElementEntryForUcm();
            String requestId=details.get("RequestId")==null?"0":details.get("RequestId").toString();
            System.err.println("==>RequestId==>"+requestId);
            ArrayList ucmList = new ArrayList(); 
            ArrayList hdlList = new ArrayList();
            if(requestId.equalsIgnoreCase("0")){
                System.err.println("==>requestId<==");
            }else{
                System.err.println("RequestId==>"+details.get("RequestId"));
                System.err.println("PersonNo==>"+details.get("PersonNo"));
                System.err.println("DocTitle==>"+details.get("DocTitle")); 
                System.err.println("DocAuthor==>"+details.get("DocAuthor"));
                System.err.println("SecurityGroup==>"+details.get("SecurityGroup"));
                System.err.println("DocAccount==>"+details.get("DocAccount"));
                System.err.println("DocType==>"+details.get("DocType"));
                System.err.println("BaseSixtyFourFile==>"+details.get("BaseSixtyFourFile"));
                System.err.println("ElementName==>"+details.get("ElementName"));
                       MainFile mainFile = new MainFile();               
                    try {
                               requestId        =details.get("RequestId")==null?"0":details.get("RequestId").toString();                           
                        String assignmentId     =details.get("PersonNo")==null?"0":details.get("PersonNo").toString();
                        String docTitle         =details.get("DocTitle")==null?"0":details.get("DocTitle").toString();
                        String elementName      =details.get("ElementName")==null?"0":details.get("ElementName").toString();
                        String responseMsg="0";
                        //HCM UCM process
                        String ucmSoapPayload=mainFile.getUcmElementEntryPayload(details); 
                        System.err.println("UCM Payload ==>"+ucmSoapPayload);
                        ucmList=(ArrayList) invokeOkHttps.uploadToContent(ucmSoapPayload);
                        //1st UCM process response
                        if(ucmList.get(0).toString().equalsIgnoreCase("SUCCESS")){
                            System.err.println("SUCCESS UCM==>");
                            responseMsg=ucmList.get(1).toString();
                            System.err.println("===UCM responseMsg==>"+responseMsg);
                            //HCM HDL process
                            String hdlSoapPayload=mainFile.getHdlElementEntryPayload(docTitle);
                            System.err.println("HDL Payload ==>"+hdlSoapPayload);
                            hdlList=(ArrayList) invokeOkHttps.uploadToHDL(hdlSoapPayload);
                            //2nd HDL process response
                            if(hdlList.get(0).toString().equalsIgnoreCase("SUCCESS")){
                                System.err.println("SUCCESS HDL==>");
                                responseMsg=hdlList.get(1).toString();
                            System.err.println("===HDL responseId==>"+responseMsg);
                            }else{
                            String message=hdlList.get(0).toString();
                            System.err.println("Error==>"+message);
                            }
                        }else{
                            hdlList.add("ERROR");
                            hdlList.add("HDL process not executed as UCM is error");
                            String message=ucmList.get(0).toString();
                            System.err.println("Error==>"+message);
                        }

                        //==========procedure call=========
                        CallDBQuery.updateStatus(ucmList.get(0).toString(), ucmList.get(1).toString(),hdlList.get(0).toString(),hdlList.get(1).toString(), requestId,elementName);
                    } catch (SQLException ex) {
                        Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NamingException ex) {
                        Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    catch (IOException ex) {
                        Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    catch (JSONException ex) {
                        Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                    }         

            }
        
        }else{
            System.out.println("scheduler is Stopped");
        }
        
    }

    
    
}
