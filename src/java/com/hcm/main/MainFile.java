/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.main;

import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;

/**
 *
 * @author Prasenjit
 */
public class MainFile {
 
    public static void main(String[] args) throws JSONException, IOException, Exception {
        
       
        
    }
    
    
    public static String getPostElementEntryPayload(HashMap<String, String> details){
       System.err.println("Entered Sample Payload==>"); 
      String json=
                "{\r\n\""
              + "PersonId\": \""+details.get("PersonId").toString()+"\","
              + "\r\n\"ElementTypeId\":\""+details.get("ElementTypeId").toString()+"\","
              + "\r\n\"AssignmentId\":\""+details.get("AssignmentId").toString()+"\","
              + "\r\n\"EntryType\":\""+details.get("EntryType").toString()+"\","
              + "\r\n\"CreatorType\": \""+details.get("CreatorType").toString()+"\","
              + "\r\n\"elementEntryValues\":"
              + "\r\n[{"
              + "\r\n\"InputValueId\": \""+details.get("InputValueId").toString()+"\","
              + "\r\n\"ScreenEntryValue\": \""+details.get("ScreenEntryValue").toString()+"\""
              + "\r\n}]}";
      System.err.println("Sample Payload==>"+json);
      return json;
    }
    //get payload for ucm element entry creation 
      public String getUcmElementEntryPayload(HashMap<String, String> details) {
        System.err.println("Entered Sample Payload==>");
//          String ucmSoapPayload = null;
        String ucmSoapPayload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ucm=\"http://www.oracle.com/UCM\">\n"
                + "   <soapenv:Header/>\n"
//                + "      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n"
//                + "         <wsu:Timestamp wsu:Id=\"TS-8B960622638231FB7F15394482101538\">\n"
//                + "            <wsu:Created>" + payloadUtility.getCreatedDate() + "</wsu:Created>\n"
//                + "            <wsu:Expires>" + payloadUtility.getExpiredDate() + "</wsu:Expires>\n"
//                + "         </wsu:Timestamp>\n"
//                + "         <wsse:UsernameToken wsu:Id=\"UsernameToken-7A5DA39F515F1BF2A415269962607085\">\n"
//                + "            <wsse:Username>" + getUserName() + "</wsse:Username>\n"
//                + "            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">" + getPassword() + "</wsse:Password>\n"
//                + "            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">" + getEncoded() + "</wsse:Nonce>\n"
//                + "            <wsu:Created>" + payloadUtility.getCreatedDate() + "</wsu:Created>\n"
//                + "         </wsse:UsernameToken>\n"
//                + "      </wsse:Security>\n"
//                + "   </soapenv:Header>\n"
                + "   <soapenv:Body>\n"
                + "      <ucm:GenericRequest webKey=\"cs\">\n"
                + "      <ucm:Service IdcService=\"CHECKIN_UNIVERSAL\">\n"
                + "         <ucm:Document>\n"
                + "            <ucm:Field name=\"dDocTitle\">" + details.get("DocTitle").toString() + "</ucm:Field>\n"
                + "            <ucm:Field name=\"dDocAuthor\">" + details.get("DocAuthor").toString() + "</ucm:Field>\n"
                + "            <ucm:Field name=\"dSecurityGroup\">" + details.get("SecurityGroup").toString() + "</ucm:Field>\n"
                + "            <ucm:Field name=\"dDocAccount\">" + details.get("DocAccount").toString() + "</ucm:Field>\n"
                + "            <ucm:Field name=\"dDocName\">" + details.get("DocTitle").toString() + "</ucm:Field>\n"
                + "            <ucm:Field name=\"dDocType\">" + details.get("DocType").toString() + "</ucm:Field>\n"
                + "            <ucm:File name=\"primaryFile\" href=\"" + details.get("DocTitle").toString() +".zip"+ "\">\n"
                + "            <ucm:Contents>" + details.get("BaseSixtyFourFile").toString() + "</ucm:Contents>\n"
                + "            </ucm:File>\n"
                + "            </ucm:Document>\n"
                + "            </ucm:Service>\n"
                + "            </ucm:GenericRequest>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        return ucmSoapPayload;
    }
    //get payload for HDL element entry creation 
      public String getHdlElementEntryPayload(String contenetId) {
        System.err.println("Entered Sample Payload==>");
//          String hdlSoapPayload = null;
        String hdlSoapPayload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://xmlns.oracle.com/apps/hcm/common/dataLoader/core/dataLoaderIntegrationService/types/\">\n"
                + "   <soapenv:Header/>\n"
////                + "      <wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n"
////                + "         <wsu:Timestamp wsu:Id=\"TS-8B960622638231FB7F15394482101538\">\n"
////                + "            <wsu:Created>" + payloadUtility.getCreatedDate() + "</wsu:Created>\n"
////                + "            <wsu:Expires>" + payloadUtility.getExpiredDate() + "</wsu:Expires>\n"
////                + "         </wsu:Timestamp>\n"
////                + "         <wsse:UsernameToken wsu:Id=\"UsernameToken-7A5DA39F515F1BF2A415269962607085\">\n"
////                + "            <wsse:Username>" + getUserName() + "</wsse:Username>\n"
////                + "            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">" + getPassword() + "</wsse:Password>\n"
////                + "            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">" + getEncoded() + "</wsse:Nonce>\n"
////                + "            <wsu:Created>" + payloadUtility.getCreatedDate() + "</wsu:Created>\n"
////                + "         </wsse:UsernameToken>\n"
////                + "      </wsse:Security>\n"
////                + "   </soapenv:Header>\n"
                + "   <soapenv:Body>\n"
                + "      <typ:importAndLoadData>\n"
                + "            <typ:ContentId>" + contenetId + "</typ:ContentId>\n"
//                + "            <typ:Parameters>" + "" + "</typ:Parameters>\n"
                + "   </typ:importAndLoadData>\n"
                + "   </soapenv:Body>\n"
                + "</soapenv:Envelope>";

        return hdlSoapPayload;
    }
    
}
