import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class MiHilo extends Thread {
    // Variables locales
    private String hostName;
    private int id;
    private Num m;
    private int N;
    private String portNum;

    public MiHilo(String name, String hostName, int id, int N, Num m, String portNum){
        super(name);
        this.hostName = hostName;
        this.id = id;
        this.N= N;
        this.m = m;
        this.portNum = portNum;
    }
    public void run(){
        //Código que ejecutará el hilo
        try {
            String registryURL =
                    "rmi://" + hostName+ ":" + portNum + "/p2/"+this.id;
            // find the remote object and cast it to an interface object
            P2Interface h = (P2Interface) Naming.lookup(registryURL);

            this.m.setNum(h.getM(N));

        } catch (Exception e) {
            System.out.println("Exception in P2Client t"+this.id+": " + e);
        }
    }
}


