
import javax.ws.rs.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author northpole
 */
@Path("/logs")
public class LogStore {
 
    /*Returns A list of hyperlinks to the  logs of all applications of the "type" type 
     * (e.g.if type is error it will return registered apps  error.log) 
     * 
     *and reads the last lines lines*/
    @GET 
    @Produces("application/xml")
    public String getLogs(@QueryParam("type") @DefaultValue("error") String type,
                          @QueryParam("lines") @DefaultValue("20") int lines  ){
        String x = null; 
        
        x="Trolololololololo aouaouaoaoaouoau foobar";
        
        return x;};
    
    
    
Authenticator myAuth = new Authenticator() 
{
    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("german", "german".toCharArray());
    }
};

    @POST
    @PUT
    public void newApp(@QueryParam("name") String name){System.out.print(name);}
    
   
    
}
