import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class P2Server  {
    public static void main(String args[]) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL;
        try{
            System.out.println("Enter the RMIregistry port number:");
            portNum = (br.readLine()).trim();
            int RMIPortNum = Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            P2Impl exportedObj = new P2Impl();
            System.out.println("Enter id: ");
            int idServer = Integer.parseInt(br.readLine());
            registryURL = "rmi://localhost:" + portNum + "/p2/"+idServer;
            Naming.rebind(registryURL, exportedObj);
            /**/     System.out.println
/**/        ("P2 registered.  Registry currently contains:");
            /**/     // list names currently in the registry
            /**/     listRegistry(registryURL);
            System.out.println("P2 Server ready.");
        }// end try
        catch (Exception re) {
            System.out.println("Exception in P2Server.main: " + re);
        } // end catch
    } // end main

    // This method starts a RMI registry on the local host, if it
    // does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum)
            throws RemoteException{
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list( );  // This call will throw an exception
            // if the registry does not already exist
        }
        catch (RemoteException e) {
            // No valid registry at that port.
            /**/     System.out.println
/**/        ("RMI registry cannot be located at port "
        /**/        + RMIPortNum);
            Registry registry =
                    LocateRegistry.createRegistry(RMIPortNum);
            /**/        System.out.println(
                    /**/           "RMI registry created at port " + RMIPortNum);
        }
    } // end startRegistry

    // This method lists the names registered with a Registry object
    private static void listRegistry(String registryURL)
            throws RemoteException, MalformedURLException {
        System.out.println("Registry " + registryURL + " contains: ");
        String [ ] names = Naming.list(registryURL);
        for (int i=0; i < names.length; i++)
            System.out.println(names[i]);
    } //end listRegistry

} // end class
