
import java.rmi.*;
import java.util.List;

public interface P2Interface extends Remote{
    public int getM(int n) throws java.rmi.RemoteException;
}
