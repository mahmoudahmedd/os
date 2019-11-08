package cli;
import java.util.*; 

public class Network 
{
    public static void main(String[] args) 
    {
        Window window = new Window();
         
        Scanner scannerInt     = new Scanner(System.in);
        Scanner scannerString  = new Scanner(System.in);
        Vector<String> devices = new Vector<>();
        
        int N  = 0;
        int TC = 0;
        
        System.out.println("What is number of WI-FI Connections?");
        N = scannerInt.nextInt();
        
        System.out.println("What is number of devices Clients want to connect?");
        TC = scannerInt.nextInt();
       
        String TCLines;
        Scanner scannear = new Scanner(System.in);
        
        for (int  i = 0 ; i < TC ; i++)
        {
            TCLines = scannerString.nextLine();
            devices.add(TCLines);
        }

        Router router = new Router(N, devices);
        router.run();
        
        scannerInt.close();
        scannerString.close();
    }
}
