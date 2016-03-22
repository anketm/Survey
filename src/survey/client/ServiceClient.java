/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey.client;

import com.google.gson.Gson;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import survey.beans.RestRequest;
import survey.beans.RestResponse;

/**
 *
 * @author anket
 */
public class ServiceClient {
    final static Logger logger = Logger.getLogger(ServiceClient.class);
    public RestResponse callService(RestRequest request){
        logger.info("RestResponse Called");
        RestResponse res = null;
        try{
        ClientConfig config = new ClientConfig();
	Client client = ClientBuilder.newClient(config);

	System.out.println("Request : " + request.toString());
	    
        WebTarget target = client.target(getBaseURI());
        Invocation.Builder invocationBuilder =  target.request(MediaType.APPLICATION_JSON);
        String response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON)).readEntity(String.class);
        Gson gson = new Gson();
        res = gson.fromJson(response, RestResponse.class);
        
        }catch(Exception e)
        {
          e.getMessage();
        }
        logger.info("RestResponse Ends");
        return res;
    }
    
    private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/BackingRestService/rest/RestBackingService/insertEmployee").build();
    }
    
}
