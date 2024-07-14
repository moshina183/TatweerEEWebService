/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hcm.restapi;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Prasenjit
 */
@Path("resourcecreation")
public class ResourcesCreation {
    
    // http://localhost:8080/woServices/webresources/resourcecreation/v1?resourceId=2

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InvoiceCreation
     */
    public ResourcesCreation() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
//        throw new UnsupportedOperationException();
        return null;
    }
    
    
 
        public String responseToRest(String result) throws JSONException {
            JSONObject json = new JSONObject();
            json.put("result", result);
            return json.toString();
        }
    
    
}
