package AssignmentPackage;

import java.util.Vector;

public class Device extends Thread {
    Vector name = new Vector ();
    Vector type = new Vector ();


    Semaphore s = new Semaphore ();

    public void run(){
        System.out.println (name);
        System.out.println (type);

    }
}
