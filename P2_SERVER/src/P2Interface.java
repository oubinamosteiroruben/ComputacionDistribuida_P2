
import java.rmi.*;
import java.util.List;

public interface P2Interface extends Remote {
    /**
     * This remote method returns the number of elements that are valid
     * @param n -the number of pairs to generate.
     * @return an int.
     */

    public int getM(int n) throws java.rmi.RemoteException;
}
