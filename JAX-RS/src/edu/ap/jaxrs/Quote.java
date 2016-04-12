package edu.ap.jaxrs;

import java.io.*;
import javax.ws.rs.*;
import javax.json.*;
//kan de jar file niet geinclude krijgen
//import redis.clients.jedis.Jedis;

@Path("/quotes")
public class Quote {

	/*public void connect(){
		Jedis jedis = new Jedis("localhost");
	}*/
	
	static final String FILE = "C:\\Users\\Arno\\eclipse\\workspace\\JAX-RS-Examen\\Quotes.json";
	
	@GET
	@Produces({"text/html"})
	public String getQuotesHTML() {
		String htmlString = "<html><body>";
		try {
			JsonReader reader = Json.createReader(new StringReader(getQuotesJSON()));
			JsonObject rootObj = reader.readObject();
			JsonArray array = rootObj.getJsonArray("quotes");
			
			
			for(int i = 0 ; i < array.size(); i++) {
				JsonObject obj = array.getJsonObject(i);
				htmlString += "<b>Author : " + obj.getString("author") + "</b><br>";
				htmlString += "Quote : " + obj.getString("quote") + "<br>";
				htmlString += "<br><br>";
			}
		}
		finally{
		}
		return htmlString;
	}
		
		@GET
		@Consumes({"application/json"})
		@Produces({"application/json"})
		public String getQuotesJSON() {
			String jsonString = "";
			try {
				InputStream fis = new FileInputStream(FILE);
		        JsonReader reader = Json.createReader(fis);
		        JsonObject obj = reader.readObject();
		        reader.close();
		        fis.close();
		        
		        jsonString = obj.toString();
			} 
			catch (Exception ex) {
				jsonString = ex.getMessage();
			}
			
			return jsonString;
		}
		
		@POST
		@Produces({"text/html"})
		public String postGetQuotesByAuthor(@QueryParam("author") String author){
			String jsonString = "";
			String htmlString = "";
			try {
				InputStream fis = new FileInputStream(FILE);
		        JsonReader reader = Json.createReader(fis);
		        JsonObject obj = reader.readObject();
		        reader.close();
		        fis.close();
		        
		        jsonString = obj.toString();
		        
		        JsonReader reader2 = Json.createReader(new StringReader(jsonString));
				JsonObject rootObj = reader2.readObject();
				JsonArray array = rootObj.getJsonArray("products");
				
				
				for(int i = 0 ; i < array.size(); i++) {
					JsonObject obj2 = array.getJsonObject(i);
					if(obj2.getString("author").equals(author)){
						htmlString += "<b>Author : " + obj2.getString("author") + "</b><br>";
						htmlString += "Quote : " + obj2.getString("quote") + "<br>";
						htmlString += "<br><br>";
					}
					
				}
			} 
			catch (Exception ex) {
				jsonString = ex.getMessage();
			}
			
			return htmlString;
		}
}
