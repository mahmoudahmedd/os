package AssignmentPackage;

public class Semaphore {

    protected int value = 0 ; //value like a counter

    protected Semaphore() {
        value = 0;
    }

    protected Semaphore(int initial) {
        value = initial ;
    }
    // if the value -ve therefore there is a waiting devices ,
    // if +ve therefore there is an available connection (fe mkan le device ye2dar yod5ol y3mel connect 3latool mn 8er waiting),
    // if zero therefore there is no waiting devices and all connections occupied (mfesh waiting bs law device tany geh hay wait)
    public synchronized void P() {
        value-- ;
        if (value < 0)
            try { wait();}
        catch(  InterruptedException e ) { }
    }

    public synchronized void V() {
        value++ ;
        if (value <= 0){
            notify() ;
        }
    }
}

