package AssignmentPackage;

public class Router {
    int size;
    Semaphore s = new Semaphore (size);
    Router(int n){
        this.size = n;
    }
    
}
