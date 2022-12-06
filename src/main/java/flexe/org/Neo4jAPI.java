package flexe.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Neo4jAPI extends Exception {
    private String query;
    private String username;
    private String password;
   // private String hostname;
  private Object response;
  private String host;

private int status;
private String error;


public Neo4jAPI(String URI,String username, String password,String query){
    this.host=host;
	this.password=password;
    this.query=query;
    this.username=username;
    
}
//    neo4jAPI.query="MATCH (m:Movie) RETURN m.title LIMIT 1";




}
