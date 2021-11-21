import java.rmi.Remote;
import java.rmi.RemoteException;

public interface P2Interface extends Remote{
    public int getM(int n) throws RemoteException;
}
