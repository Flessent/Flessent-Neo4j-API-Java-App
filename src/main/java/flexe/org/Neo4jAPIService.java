package flexe.org;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;

@CrossOrigin(origins = "http://localhost:4200")
//@Transactional
@Service
public class Neo4jAPIService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FlexeNeo4jDTO convertEntityToDto(Neo4jAPI neo4jAPI){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    FlexeNeo4jDTO neo4jDTO = new FlexeNeo4jDTO();
	    neo4jDTO = modelMapper.map(neo4jAPI, FlexeNeo4jDTO.class);

	    return neo4jDTO;
	}

	public Neo4jAPI convertDtoToEntity(FlexeNeo4jDTO neo4jDTO){
	    modelMapper.getConfiguration()
	            .setMatchingStrategy(MatchingStrategies.LOOSE);
	    Neo4jAPI neo4jAPI = new Neo4jAPI();
	    neo4jAPI = modelMapper.map(neo4jDTO, Neo4jAPI.class);

	    return neo4jAPI;
	}
	
	
	
	
	public String execute(Neo4jAPI neo4jApi){
		Client client = Client.create();
	    String result="";
		try {
	    	
	    	
	    
         
	    	
	    	
	    	
		    String credentials =neo4jApi.getUsername()+":"+neo4jApi.getPassword();

	    	MediaType[] m = new MediaType[]{MediaType.APPLICATION_JSON, 
                    MediaType.ALL, MediaType.TEXT_XML};
	        WebResource webResource = Client.create()
	                .resource(neo4jApi.getHost());
	        WebResource.Builder builder = webResource.accept("application/json");
	        builder.type("application/json");
	        String payload = "{\"statements\" : [ {\"statement\" : \"" +neo4jApi.getQuery() + "\"} ]}";
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(payload);
	        
	        client.addFilter(new LoggingFilter(System.out));
	        webResource = client.resource(neo4jApi.getHost());

	        ClientResponse clientResponse=  webResource.accept("application/json")
	                .type("application/json")
	                .header("Authorization","Basic "+ Base64.getEncoder().encodeToString(credentials.getBytes()))
	                .entity(json).
	                post(ClientResponse.class,json);       

	        clientResponse.close();
	       // neo4jApi.setResponse(clientResponse);

	        InputStream output = clientResponse.getEntityInputStream();

	        //clientResponse.close();
	         result = IOUtils.toString(output, StandardCharsets.UTF_8);
	        if (neo4jApi.getStatus() != 200) {
	            throw new RuntimeException("Error during the runtime : HTTP error code : "+ neo4jApi.getStatus());

	        } else {
	        	//neo4jApi.setResponse(clientResponse.getEntity(String.class));
	        	

	        }
	    } catch (Exception e){
			System.err.println("error"+e.getMessage());
	    }
		System.err.println("Get me back "+result);

	    return result;
	}
	
}
