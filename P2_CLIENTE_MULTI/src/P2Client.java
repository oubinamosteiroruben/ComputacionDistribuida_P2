import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

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
            List<Thread> listThreads = new ArrayList<>();
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            hostName = br.readLine();
            System.out.println("Enter number of threads: ");
            int threadsNum = Integer.parseInt(br.readLine());
            System.out.println("Enter the RMIregistry port number:");
            String portNum = null;
            portNum = br.readLine();
            System.out.println("Value of n: ");
            int N = Integer.parseInt(br.readLine());
            int NAux = N;
            // Creamos una lista de valores en los cuales irá almacenando cada hilo su valor de m
            List<Num> mList = new ArrayList<>();
            // Creamos threadsNum hilos

            for(int t = 0; t<threadsNum;t++){
                Num m = new Num(0);
                mList.add(m);
                int n = (int) Math.ceil((double)N/(threadsNum-t));
                N-=n;
                // Creamos hilo y lo arrancamos
                Thread th = new MiHilo("HILO " + t, hostName, t, n,m, portNum);
                listThreads.add(th);
                th.start();
            }
            // Esperamos a que finalicen todos los hilos
            for(Thread t: listThreads){
                t.join();
            }

            int mSum = 0;

            // Calcula el valor total de m
            for(Num m: mList){
                mSum += m.getNum();
            }

            // Calcula PI
            double pi = 4*(((double)mSum)/((double) NAux));
            System.out.println("VALOR PI: " + pi);
        } // end try
        catch (Exception e) {
            System.out.println("Exception in P2Client: " + e);
        }
    } //end main
}//end class


