/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.payload;
import com.hcm.Database.CallDBQuery;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prasenjit
 */
public class invokeOkHttps {

    
    //test instance saas for REST API
    public static String getWebServices(){
       String saasUrl=CallDBQuery.getSetUpDtls("SAAS_URL");
       String url=saasUrl
               + "/hcmRestApi/resources/11.13.18.05/elementEntries";
       System.err.println("Post url==>"+url);
       return url;
    }
    
    //get TEST ucm wsdl
    public static String getUcmServices(){
       String saasUrl=CallDBQuery.getSetUpDtls("SAAS_URL");
       String url=saasUrl
               + "/idcws/GenericSoapPort?wsdl";
       System.err.println("Post UCM url==>"+url);
       return url;
    }
    //get TEST HDL wsdl
    public static String getHdlServices(){
       String saasUrl=CallDBQuery.getSetUpDtls("SAAS_URL");
       String url=saasUrl
               + "/hcmCommonDataLoader/HCMDataLoader?wsdl";
       System.err.println("Post HDL url==>"+url);
       return url;
    }
 
    
    //element entry post service
      public static List<String> callElementPostServices(String JsonData, String effectiveStartDate) throws IOException, JSONException{
       List<String> ls = new ArrayList<>();
       OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        
//        System.err.println("==>Json Data==>"+JsonData);   
        RequestBody body = RequestBody.create(mediaType, JsonData);
        Request request = new Request.Builder()
        .url(getWebServices())
        .method("POST", body)
        .addHeader("Authorization", "Basic UGFhUy5TZWxmIFNlcnZpY2VAdGF0d2Vlci5zYTpQQUFTVGF0d2VlckAyMDIw")
        .addHeader("Content-Type", "application/json")
//        .addHeader("Cookie", "ak_bmsc=CD6B4784001D63E9435080C69BEB9B65~000000000000000000000000000000~YAAQXNs4fcYbe+Z9AQAAMm0iHw4akOPVBo8lsymMXUqKZHG0oCR/x13o5aJltuwc/P5BBJ+lOZW08f39nBfaiA/lX2pV89C27DsIqlOI19voffu7RSpUUu49CGtGN2ySt4MASoZTaOstg+k3aZjr841befv8VH8j7IMoG9BKp3GiiVgmvRYB2z4XEbOgdD1Bfu2bIpJOq8R0he98Ckmi7H0jVtxEQcT8928AOm0LzrP64caT2Mh0NJAxj9h5ALMnUMUbqdZwJ9q2jOivxxb6kqRJR103OA5Yu6GzDgg3olM0j0+Y/WdcBvx9nroBcOK9npooQTNLQgoWPICnns6uXY+3DNNrLWOYA+a9Hnc4tHR9nWqCi0sZhnoMNQ7SPdDHuXNjsjgRSJcy")
//        .addHeader("Effective-Of", "RangeStartDate=2024-03-06")
        .addHeader("Effective-Of", "RangeStartDate="+effectiveStartDate)
        .build();
         Response response = client.newCall(request).execute();
         String jsonData = response.body().string();
         
         if(response.code()==201){
            JSONObject Jobject = new JSONObject(jsonData);
            Jobject.get("ElementEntryId");
//            System.err.println("WoOperationResourceId==>"+Jobject.get("WoOperationResourceId"));
            ls.add("SUCCESS");
            ls.add(Jobject.get("ElementEntryId").toString());
            return ls; 
         }else{
//            System.err.println("jsonData==>"+jsonData);
            ls.add("ERROR");
            ls.add(jsonData);
            return ls;
         }
//         System.err.println("response==>"+response);  
//         System.err.println("jsonData==>"+jsonData);   
    
   }
      //ucm upload of element entry
      public static List<String> uploadToContent(String JsonData) throws IOException, JSONException{
       List<String> ls = new ArrayList<>();
       OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/xml");
        
//        System.err.println("==>Json Data==>"+JsonData);   
        RequestBody body = RequestBody.create(mediaType, JsonData);
        Request request = new Request.Builder()
        .url(getUcmServices())
        .post(body)
        .addHeader("Authorization", "Basic UGFhUy5TZWxmIFNlcnZpY2VAdGF0d2Vlci5zYTpQQUFTVGF0d2VlckAyMDIw")
        .addHeader("Content-Type", "text/xml")
        .addHeader("cache-control", "no-cache")
        .addHeader("SOAPACTION", "GenericSoapOperation")
        .build();
         Response response = client.newCall(request).execute();
         String jsonOutData = response.body().string();
         System.out.println("Response Code : " + response.code());
         if(response.code()==200){
             if(jsonOutData.contains("Successfully checked in content item")){
                 System.out.println("Success");
                 ls.add("SUCCESS");
                 ls.add("Success");
             }else if(jsonOutData.contains("was not successfully checked in. The content item is not currently checked out.")){
                 System.out.println("Error already exist in UCM");
                 ls.add("ERROR");
                 ls.add("Already exist in UCM - was not successfully checked in. The content item is not currently checked out.");
             }else{
                 System.out.println("Error");
                 ls.add("ERROR");
                 ls.add("Kindly check in UCM");
             }             
            return ls; 
         }else{
            ls.add("ERROR");
            ls.add("UCM Error response code");
            System.out.println(" UCM Response Code error : ");
            return ls;
         }
    
   }
      
       //HDL upload of element entry
      public static List<String> uploadToHDL(String JsonData) throws IOException, JSONException{
       List<String> ls = new ArrayList<>();
       OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/xml");
        
//        System.err.println("==>Json Data==>"+JsonData);   
        RequestBody body = RequestBody.create(mediaType, JsonData);
        Request request = new Request.Builder()
        .url(getHdlServices())
        .post(body)
        .addHeader("Authorization", "Basic UGFhUy5TZWxmIFNlcnZpY2VAdGF0d2Vlci5zYTpQQUFTVGF0d2VlckAyMDIw")
        .addHeader("Content-Type", "text/xml")
        .addHeader("cache-control", "no-cache")
        .addHeader("SOAPACTION", "importAndLoadData")
        .build();
         Response response = client.newCall(request).execute();
         
         String jsonOutData = response.body().string();
         System.err.println("=======> response ==>"+response.toString());
         System.err.println("==>jsonOutData response output==>"+jsonOutData);
         
         System.out.println("Response Code HDL : " + response.code());
         if(response.code()==200){
            if(jsonOutData.contains("Check that the file was loaded successfully to the correct account. No file was found on the WebCenter Content server")){
                 System.out.println("Error");
                 ls.add("ERROR");
                 ls.add("Either already exist with same effective date or check additionally");
             }else{
                 System.out.println("SUCCESS");
                 ls.add("SUCCESS");
                 ls.add("Success");
             }
            return ls; 
         }else{
            System.err.println("HDL Error response code");
            ls.add("ERROR");
            ls.add("HDL Error response code");
            return ls;
         }    
   }
       
}
