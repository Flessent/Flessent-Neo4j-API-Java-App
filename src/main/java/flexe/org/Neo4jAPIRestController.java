package flexe.org;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Neo4jAPIRestController {

	@Autowired
	private Neo4jAPIService neo4jAPIService;
	


	
	

	@PostMapping(path = "/executeQuery" , produces =  MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Object>   execute(@RequestBody @Valid FlexeNeo4jDTO neo4jDto) throws Exception{
		
		String  api=neo4jAPIService.execute(neo4jAPIService.convertDtoToEntity(neo4jDto));
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(api);  
		System.out.println("URL"+neo4jDto.getHost());
				
		return new ResponseEntity<Object>(json, HttpStatus.OK);

	 }
	
}
