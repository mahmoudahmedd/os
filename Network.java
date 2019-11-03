package AssignmentPackage;

import java.util.Scanner;

public class Network {
    public static void main (String[] args) {
        Router r = new Router ();
        Semaphore s = new Semaphore ();
        int N = 0;
        Scanner in = new Scanner (System.in);
        System.out.println ("What is number of WI-FI Connections?");
        N = in.nextInt ();
        Scanner in2 = new Scanner (System.in);
        System.out.println ("What is number of devices Clients want to connect?");
        int TC = in2.nextInt ();
        Scanner in3 = new Scanner (System.in);
        String TCLines;
        for (int  i = 0 ; i < TC ; i++){
            TCLines = in.nextLine ();
            s.start ();
        }

    }
}
