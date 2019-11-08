package cli;

import java.util.Vector;

public class Router 
{
    public Vector<String> devices;
    public Boolean[]      availableConnections;
    public Semaphore      semaphore;
    public int            size;
    
    public Router(int _size, Vector<String> _devices) 
    {
        this.devices              = _devices;
        this.semaphore            = new Semaphore(_size);
        this.availableConnections = new Boolean[_size];
        this.size                 = _size; 
        
        for(int i = 0; i < _size; i++) 
        {
            availableConnections[i] = true;
        }
    }
    
    public int occupyConnection(Device _device) 
    {
        semaphore.P(_device);
        
        int connectedDeviceNumber = 0;
        
        for(int i = 0; i < availableConnections.length; i++) 
        {
            if(availableConnections[i]) 
            {
                connectedDeviceNumber = i + 1;
                availableConnections[i] = false;
                break;
            }
        }
        
        return connectedDeviceNumber;
    }

    public void releaseConnection(int _connectedDeviceNumber) 
    {
        availableConnections[_connectedDeviceNumber - 1] = true;
        semaphore.V();
    }
    
    public void run() 
    {
        
        for(int i = 0;i < devices.size();i++) 
        {
            String deviceName = devices.get(i).split(" ")[0];
            String deviceType = devices.get(i).split(" ")[1];
            
            (new Thread(new Device(deviceName, deviceType, this))).start();
        }
    }
    
}
