package AssignmentPackage;

import java.util.Scanner;

public class Network {
    public static void main (String[] args) {
        int N = 0;
        System.out.println ("What is number of WI-FI Connections?");
        Scanner in = new Scanner (System.in);
        N = in.nextInt ();
        Router r = new Router (N);
        Semaphore s = new Semaphore ();
        Device d = new Device ();
        Scanner in2 = new Scanner (System.in);
        System.out.println ("What is number of devices Clients want to connect?");
        int TC = in2.nextInt ();
        Scanner in3 = new Scanner (System.in);
        String TCLines;
        for (int  i = 0 ; i < TC ; i++){
            TCLines = in.nextLine ();
            d.start ();
        }

    }
}
