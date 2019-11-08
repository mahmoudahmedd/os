package cli;

public class Semaphore 
{
    protected int value = 0;

    protected Semaphore() 
    {
        this.value = 0;
    }

    protected Semaphore(int _initial) 
    {
        this.value = _initial;
    }

    public synchronized void P(Device _device) 
    {
        this.value-- ;
        if(this.value < 0)
        {
            try 
            { 
                Window.textArea.append("- " + _device.getDeviceName() + "(" + _device.getDeviceType() + ") arrived and waiting." + Window.newline);
                System.out.println("- " + _device.getDeviceName() + "(" + _device.getDeviceType() + ") arrived and waiting.");
                wait();
            }
            catch(Exception e) 
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            Window.textArea.append("- " + _device.getDeviceName() + "(" + _device.getDeviceType() + ") arrived." + Window.newline);
            System.out.println("- " + _device.getDeviceName() + "(" + _device.getDeviceType() + ") arrived.");
        }
    }

    public synchronized void V() 
    {
        this.value++;
        if(this.value <= 0)
        {
            notify();
        }
    }
}
