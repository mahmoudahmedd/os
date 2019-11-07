package AssignmentPackage;

public class Router {
    public int maxConnections;
    //Semaphore s = new Semaphore (size);
    Router(int n){
        maxConnections = n;
    }

    Thread[] connections = new Thread[maxConnections]; //create an array of thread and its size is the max number of connections

}
