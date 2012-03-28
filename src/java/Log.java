
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.xml.*;
import org.w3c.dom.*;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author northpole
 */
 @Path("/logs/{app}/{logname}")
public class Log {

    
    
    @GET
    @Produces("application/xml")
    public String getLog(@PathParam("logname") String name, @PathParam("app") String app){
        int ch;
       
            int lcount = 0;
            
            File logfile=new File("logs/"+app+"/"+name);
            FileInputStream fin = null;
            StringBuilder strContent = new StringBuilder("");
          try {   
                    fin = new FileInputStream(logfile);
                    while ((ch = fin.read()) != -1)
                    {
                        strContent.append(ch);
                        lcount++;
                                }
                    fin.close();
            
           
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } 
    XmlCreator x=new XmlCreator();
    x.addNormalElement(name);
    x.addTextNode(strContent.toString(), name);
    String log=x.toString();
    
    System.out.println(log);
  
    return log;   }
    
    
    
}
