package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.db.DataAccessLayer;

@Path("/")
public class SandeepRESTService {
	@POST
	@Path("/session")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crunchifyREST(@QueryParam("username") String username, @QueryParam("password") String password) {
		StringBuilder crunchifyBuilder = new StringBuilder();
		System.out.println("Data Received: " + crunchifyBuilder.toString());
		try{
			String sessionId = DataAccessLayer.authenticate(username, password);
			// return HTTP response 200 in case of success
			return Response.status(201).entity("{\"token_id\":\""+sessionId+"\"}").build();
		}catch(Exception e){
			return Response.status(401).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
		}
	}
 
	/*@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(@QueryParam("username") String username, @QueryParam("password") String password) {
		String result = "CrunchifyRESTService Successfully started123.." + username + " and password - " + password;
 
		System.out.println("Data Received: " + result);
		
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}*/
 
}
