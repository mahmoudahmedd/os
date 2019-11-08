package cli;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Device extends Thread
{
    static final int MINIMUM_TIME = 2;
    static final int MAXIMUM_TIME = 4;
    static final String logFile = "logs.txt";
    
    private String deviceName;
    private String deviceType;
    private Router router;
    
    public Device(String _deviceName, String _deviceType, Router _router) 
    {
        this.deviceName = _deviceName;
        this.deviceType = _deviceType;
        this.router     = _router;
    }
    
    public String getDeviceName()
    {
        return this.deviceName;
    }
    
    public String getDeviceType()
    {
        return this.deviceType;
    }
    
    private void writeLog(String _record)
    {
        try
        {
            File file = new File(logFile);
            FileWriter writer = new FileWriter(file, true);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            writer.write(_record + "[" + dateFormat.format(date) + "]" + "\r\n");
            writer.flush();
            writer.close();
        } 
        catch(Exception e) 
        {
            System.out.println("trouble opening file:" + logFile);
            System.exit(1);
        }
    }
    
    public void connect(int _connectedDeviceNumber)
    {
        Window.textArea.append("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Occupied." + Window.newline);
        System.out.println("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Occupied.");
        this.writeLog("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Occupied.");
                
        try 
        {
            Thread.sleep((int)(Math.random() * ((MAXIMUM_TIME - MINIMUM_TIME) + 1)) + MINIMUM_TIME * 1000);
        }
        catch(InterruptedException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void performOnlineActivity(int _connectedDeviceNumber)
    {
        Window.textArea.append("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Performs online activity." + Window.newline);
        System.out.println("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Performs online activity.");
        this.writeLog("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Performs online activity.");
        
        try 
        {
            Thread.sleep((int)(Math.random() * ((MAXIMUM_TIME - MINIMUM_TIME) + 1)) + MINIMUM_TIME * 1000);
        }
        catch(InterruptedException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void logout(int _connectedDeviceNumber)
    {
        Window.textArea.append("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Logged out." + Window.newline);
        System.out.println("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Logged out.");
        this.writeLog("- Connection " + _connectedDeviceNumber + ": " + this.deviceName + " Logged out.");
        
        router.releaseConnection(_connectedDeviceNumber);
    }
    
    public void run()
    {
        int connectedDeviceNumber = router.occupyConnection(this);
        
        this.connect(connectedDeviceNumber);
        this.performOnlineActivity(connectedDeviceNumber);
        this.logout(connectedDeviceNumber);
    }
     
}
