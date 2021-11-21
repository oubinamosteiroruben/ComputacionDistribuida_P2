import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a distributed
 * object of class Hello, which implements the remote interface
 * HelloInterface.
 * @author M. L. Liu
 */

public class P2Client {

    public static void main(String args[]) {
        try {
            int RMIPort;
            String hostName;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            hostName = br.readLine();
            System.out.println("Enter the RMIregistry port number:");
            String portNum = br.readLine();
            RMIPort = Integer.parseInt(portNum);
System.out.println("Enter the id:");
            String id = br.readLine();
            String registryURL =
                    "rmi://" + hostName+ ":" + portNum + "/p2/"+id;
            // find the remote object and cast it to an interface object
            P2Interface h =
                    (P2Interface) Naming.lookup(registryURL);
            System.out.println("Lookup completed " );
            // invoke the remote method

            System.out.println("Value of n: ");
            int n = Integer.parseInt(br.readLine());

            //int n = 20000;
            int m = h.getM(n);
            System.out.println("N: " + n + " M: " + m);

            // Calculate PI
            double pi = 4*(((double)m)/(double) n);
            System.out.println("VALOR PI: " + pi);
        } // end try
        catch (Exception e) {
            System.out.println("Exception in P2Client: " + e);
        }
    } //end main
}//end class
