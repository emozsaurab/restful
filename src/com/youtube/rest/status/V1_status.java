package com.youtube.rest.status;

import java.sql.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.youtube.util.DbUtil;

 

 
@Path("/v1/status")
public class V1_status {
	
	private static final String api_version = "00.01.00"; //version of the api
 @GET	
 @Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		
		return "RestFUL webservices";
		
	}
 
 @Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
 
 
 @Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		Integer myId=null;
		String myName= null;
		String returnString = null;
		Connection conn = null;
		
		try {
			
			conn =   DbUtil.getConnection(); //calls the method defined in the Oracle308tube package
			
			//simple sql query to return the date/time
			query = conn.prepareStatement("select * from rest");
			ResultSet rs = query.executeQuery();
			
			//loops through the results and save it into myString
			while (rs.next()) {
				// /*Debug*/ System.out.println(rs.getString("DATETIME"));
				myId=rs.getInt("id");
				myName = rs.getString("name");
			}
			
			query.close(); //close connection
			
			returnString = "<p>Database Status</p> " +
				"<p>id " + myId + " name "+myName+ "</p>";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * The finally cause will always run. Even if the the method get a error.
		 * You want to make sure the connection to the database is closed.
		 */
		finally {
			//if (conn != null) conn.close();
		}
		
		return returnString; 
	}
}
