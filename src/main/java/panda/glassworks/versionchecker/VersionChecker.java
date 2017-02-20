package panda.glassworks.versionchecker;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import org.apache.commons.io.IOUtils;

import panda.glassworks.GlassWorks;

/**
 * Thanks, Jabelar!
 * @author jabelar
 */
public class VersionChecker implements Runnable
{
    private static boolean isLatestVersion = false;
    private static String latestVersion = "";

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() 
    {
        InputStream in = null;
        try 
        {
            in = new URL("https://raw.githubusercontent.com/cleverpanda/GlassWorks/master/update.json").openStream();
        } 
        catch 
        (MalformedURLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try 
        {
            latestVersion = IOUtils.readLines(in).get(0);
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        finally 
        {
            IOUtils.closeQuietly(in);
        }
        isLatestVersion = GlassWorks.VERSION.equals(latestVersion);
        if(isLatestVersion){
        	 System.out.println("Your version of GlassWorks is up to date!");
        }else{
        	 System.out.println("Your version of GlassWorks is OUT OF DATE! Current version is "+latestVersion);
        }
       
    }
    
    public boolean isLatestVersion()
    {
     return isLatestVersion;
    }
    
    public String getLatestVersion()
    {
     return latestVersion;
    }
   
}