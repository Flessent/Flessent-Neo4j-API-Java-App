package flexe.org;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.util.Arrays;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

public class Neo4jServerApplication {
    //public static String SERVER_ROOT_URI = "http://localhost:7474/db/data/transaction/commit";

	public static void main(String[] args)  throws Exception{
		SpringApplication.run(Neo4jServerApplication.class, args);
		
		
		
		
		
		
		/*
		 String user="neo4j";
	        String password ="malika";
	        String credentials =user+":"+password;
	        WebResource webResource = Client.create()
	                .resource( SERVER_ROOT_URI );
	        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
	        builder.type(MediaType.APPLICATION_JSON_TYPE);
	        String query="MATCH (m:Movie) RETURN m.title LIMIT 1";
	        String payload = "{\"statements\" : [ {\"statement\" : \"" +query + "\"} ]}";
	        JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(payload);
	        Client client = Client.create();
	        client.addFilter(new LoggingFilter(System.out));
	         webResource = client.resource(SERVER_ROOT_URI);

	        ClientResponse   clientResponse=  webResource.accept(MediaType.APPLICATION_JSON)
	                .type(MediaType.APPLICATION_JSON)
	                .header("Authorization","Basic "+Base64.getEncoder().encodeToString(credentials.getBytes()))
	                .entity(json).
	                      post(ClientResponse.class,json);
	        clientResponse.close();
	       System.out.println(clientResponse.toString());
		*/
	}
	
	 @Bean
     public ModelMapper modelMapper() {
         final ModelMapper mapper = new ModelMapper();
         Provider<UUID> uuidProvider = new AbstractProvider<UUID>() {
             @Override
             public UUID get() {
                 return UUID.randomUUID();
             }
         }; 

         final Converter<String, UUID> uuidConverter = new AbstractConverter<>() {
             @Override
             protected UUID convert(final String source) {
                 return UUID.fromString(source);
             }
         };
         mapper.createTypeMap(String.class, UUID.class);
         mapper.addConverter(uuidConverter);
         mapper.getTypeMap(String.class, UUID.class).setProvider(uuidProvider);


         return mapper;
     }
}
