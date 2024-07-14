/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.Database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
/**
 *
 * @author Prasenjit
 */
public class CallDBQuery {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement ps;
    
    /**
     * Method to initialize DB connection
     * @throws SQLException
     * @throws NamingException
     * @throws ClassNotFoundException 
     */
    

    public static void dbInitialization() throws SQLException, NamingException, ClassNotFoundException {
        connection = DBConnection.getConnectionDS("ORADBSPRD01_TATWEERDB");//PROD
      //connection = DBConnection.getConnectionDS("ORADBSDEV_TATWEERDB");    //Dev datasource
      //connection = DBConnection.getDBConnection(); //LOCAL
    }

    /**
     * Method to destroy DB connection
     * @throws SQLException 
     */
    public static void dbDestroy() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    
    public static Statement getStatement() throws SQLException{
        return connection.createStatement();
    }
    
    //
    public static HashMap<String, String> getTransferElementEntry(){
            HashMap<String, String> map = new HashMap<>();        
        try {
            String primaryKeyId="0";
            int iVal = 0;
            //******************************            
            String SQL=
                "SELECT \n" +
                "PERSON_ID, PERSON_NO, ASSIGNMENT_ID, ASSIGNMENT_NO, ELEMENT_TYPE_ID, ENTRY_TYPE, "
            +   "CREATOR_TYPE, INPUT_VALUE_ID, SCREEN_ENTRY_VALUE, ELEMENT_NAME, INPUT_VALUE_NAME,REST_METHOD_TYPE,EFFECTIVE_START_DATE, "
            +   "REQUEST_ID\n" +
                "FROM \n" +
                "THC_EES_ELEMENT_TRANSFER_V\n" +
                "WHERE ROWNUM=1";
                    
//            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    primaryKeyId=resultSet.getString("PERSON_ID")==null?"0":resultSet.getString("PERSON_ID").toString();
//                    System.out.println("primaryKeyId==>"+primaryKeyId);
                    if(primaryKeyId.equalsIgnoreCase("0")){
                        map.put("PersonId"                 , resultSet.getString("PERSON_ID"));
                    }else{
                        map.put("PersonId"                 , resultSet.getString("PERSON_ID"));
                        map.put("PersonNo"                 , resultSet.getString("PERSON_NO"));
                        map.put("AssignmentId"             , resultSet.getString("ASSIGNMENT_ID"));
                        map.put("AssignmentNo"             , resultSet.getString("ASSIGNMENT_NO"));
                        map.put("ElementTypeId"            , resultSet.getString("ELEMENT_TYPE_ID"));
                        map.put("EntryType"                , resultSet.getString("ENTRY_TYPE"));
                        map.put("CreatorType"              , resultSet.getString("CREATOR_TYPE"));
                        map.put("InputValueId"             , resultSet.getString("INPUT_VALUE_ID"));
                        map.put("ScreenEntryValue"         , resultSet.getString("SCREEN_ENTRY_VALUE"));
                        map.put("ElementName"              , resultSet.getString("ELEMENT_NAME"));
                        map.put("InputValueName"           , resultSet.getString("INPUT_VALUE_NAME"));
                        map.put("RestMethodType"           , resultSet.getString("REST_METHOD_TYPE"));
                        map.put("EffectiveStartDate"       , resultSet.getString("EFFECTIVE_START_DATE"));
                        map.put("RequestId"                , resultSet.getString("REQUEST_ID"));
                                       
                    }
                }
            }else{
                  map.put("PersonId", "0");
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return map;
        } catch (SQLException ex) {
            map.put("PersonId", "0");
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            map.put("PersonId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            map.put("PersonId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return map; 
    }
    //get element entry details for ucm upload and hdl upload
    public static HashMap<String, String> getTransferElementEntryForUcm(){
            HashMap<String, String> map = new HashMap<>();        
        try {
            String primaryKeyId="0";
            int iVal = 0;
            //******************************            
            String SQL=
                "SELECT \n" +
                "PERSON_NO, DOC_TITLE, DOC_AUTHOR, SECURITY_GROUP, DOC_ACCOUNT, DOC_TYPE, BASE_SIXTYFOUR_FILE, "
            +   "REQUEST_ID, ELEMENT_NAME\n" +
                "FROM \n" +
                "THC_EES_ELEMENT_TRANSFER_UCM_V\n" +
                "WHERE ROWNUM=1";
                    
//            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    primaryKeyId=resultSet.getString("REQUEST_ID")==null?"0":resultSet.getString("REQUEST_ID").toString();
//                    System.out.println("primaryKeyId==>"+primaryKeyId);
                    if(primaryKeyId.equalsIgnoreCase("0")){
                        map.put("RequestId"                 , resultSet.getString("REQUEST_ID"));
                    }else{
                        map.put("RequestId"                 , resultSet.getString("REQUEST_ID"));
                        map.put("PersonNo"                  , resultSet.getString("PERSON_NO"));
                        map.put("DocTitle"                  , resultSet.getString("DOC_TITLE"));
                        map.put("DocAuthor"                 , resultSet.getString("DOC_AUTHOR"));
                        map.put("SecurityGroup"             , resultSet.getString("SECURITY_GROUP"));
                        map.put("DocAccount"                , resultSet.getString("DOC_ACCOUNT"));
                        map.put("DocType"                   , resultSet.getString("DOC_TYPE"));
                        map.put("BaseSixtyFourFile"         , resultSet.getString("BASE_SIXTYFOUR_FILE"));
                        map.put("ElementName"               , resultSet.getString("ELEMENT_NAME"));
                                       
                    }
                }
            }else{
                  map.put("RequestId", "0");
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return map;
        } catch (SQLException ex) {
            map.put("RequestId", "0");
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            map.put("RequestId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            map.put("RequestId", "0");    
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return map; 
    }
                        
        public static String updateStatus(
                                        String p_UCM_TYPE, 
                                        String p_UCM_MESSAGE,
                                        String p_HDL_TYPE, 
                                        String p_HDL_MESSAGE,
                                        String p_REQ_NO,
                                        String p_ELEMENT_NAME
                                        ) throws SQLException, NamingException, ClassNotFoundException{
        String result="N";
        dbInitialization();
        String pkgName="THC_EES_UPDATE_ELEMENT_STATUS";
            CallableStatement cst= connection.prepareCall("{call "+pkgName+"(?,?,?,?,?,?,?)}");
        cst.setString(1, p_UCM_TYPE);
        cst.setString(2, p_UCM_MESSAGE);
        cst.setString(3, p_HDL_TYPE);
        cst.setString(4, p_HDL_MESSAGE);
        cst.setString(5, p_REQ_NO);
        cst.setString(6, p_ELEMENT_NAME);
        cst.registerOutParameter(7, Types.VARCHAR);
        cst.execute();
        result=cst.getString(7);
        dbDestroy();
        System.err.println("Pkg result==>"+result);
        return result;
        
    }    
        
         //get schedule time set
    public static String getSetUpDtls(String columnName){
        System.out.println("inside setUpdtls==>");
            String data="0";
        try {
            
            //******************************            
            String SQL=
                "SELECT \n" +
                columnName +
                " FROM \n" +
                " THC_EES_SETUP \n" +
                " WHERE ROWNUM=1";
                    
            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    data=resultSet.getString(columnName)==null?"0":resultSet.getString(columnName);
//                    System.out.println("timeSet==>"+timeSet);          
                }
            }else{
                  data="0";
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return data; 
    }

    //get schedule time set
    public static String getCronExp(String timeSpan,String timeInterval){
        System.out.println("inside setUpdtls==>");
            String data = null;
            String timeUnit = null;
            String timeDuration = null;
        try {
            
            //******************************            
            String SQL=
                "SELECT \n" +
               timeSpan + ", " + timeInterval + "\n" +
                " FROM \n" +
                " THC_EES_SETUP \n" +
                " WHERE ROWNUM=1";
                    
            System.out.println("SQL==>"+SQL);
            
            dbInitialization();
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
//            System.out.println("resultSet==>"+resultSet);
            if(resultSet!=null){
                while (resultSet.next()){
                    timeUnit=resultSet.getString(timeSpan)==null?"0":resultSet.getString(timeSpan);
                    timeDuration=resultSet.getString(timeInterval)==null?"0":resultSet.getString(timeInterval);
                    
                    switch(timeUnit.toUpperCase()){
                        
                        case "SECOND":
                            data = String.format("0/%s * * 1/1 * ? *", timeDuration);
                            break;
                        case "MINUTE":
                            data = String.format("0 0/%s * 1/1 * ? *", timeDuration);
                            break;
                        case "HOUR":
                            data = String.format("0 0 */%s 1/1 * ? *", timeDuration);
                            break;
                        default:
                            data = "0 0/1 * 1/1 * ? *";//Every 1 min
                        
                    }
                   System.out.println("data==="+data); 
                }
            }else{ 
                  data="0";
//                System.out.println("=EE==>");
            }
            dbDestroy();            
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CallDBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       return data; 
    }
    
    public static void updateSetupStatus(String stop) throws SQLException, NamingException, ClassNotFoundException{
        //String p_boolean = stop.toString();
        dbInitialization();
        String pkgName="THC_EES_SETUP_STATUS";
        CallableStatement cst= connection.prepareCall("{call "+pkgName+"(?)}");
        cst.setString(1, stop);
        cst.execute();
        dbDestroy();
    }
}
