
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author northpole
 */
 @Path("/logs/{app}")
    public class AppLogs{
    
    @GET
    @Produces("application/xml")
    public String getLogs(@PathParam("app") String app){
        String x = null;
        XmlCreator res=new XmlCreator();
        res.addRootElement(app);
        String files;
        File folder = new File("/logs/"+app);
        File[] listOfFiles = folder.listFiles(); 
  
        for (int i = 0; i < listOfFiles.length; i++) 
        {
 
            if (listOfFiles[i].isFile()) 
            {
                files = listOfFiles[i].getName();
               res.addTextNode(files, app);                 
            }
        }
        x=res.toString();
        return x;
    }
        
    
    @PUT
    @Consumes("application/xml")
    /*how the hell do I access the request's data?*/
    public void newLog(@QueryParam("name") String name){
    File logfile=new File(name);
        try {
            logfile.createNewFile();
            
            
        } catch (IOException ex) {
            Logger.getLogger(AppLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    }