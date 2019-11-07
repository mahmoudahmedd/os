package AssignmentPackage;

import java.util.Scanner;
import java.util.Vector;

public class Network {
    public static void main (String[] args) {
        Device d = new Device ();
        Vector<String> v = new Vector<> (); //will contain all devices that need to connect to the router
        int N = 0;
        System.out.println ("What is number of WI-FI Connections?");
        Scanner in = new Scanner (System.in);
        N = in.nextInt ();
        Router r = new Router (N); //send N (number of wifi connections) to router constructor
        Scanner in2 = new Scanner (System.in);
        System.out.println ("What is number of devices Clients want to connect?");
        int TC = in2.nextInt ();
        Scanner in3 = new Scanner (System.in);
        String TCLines;
        for (int  i = 0 ; i < TC ; i++){
            TCLines = in3.nextLine ();
            String s[] = TCLines.split(" ");
            d.name.add (s[0]);
            d.type.add (s[1]);
            s = null;
        }
        d.start ();

    }
}
