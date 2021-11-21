import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P2Impl extends UnicastRemoteObject implements P2Interface {

    public P2Impl() throws RemoteException{
        super();
    }

    public int getM(int n) throws RemoteException{
        int m = 0;
        // Lista de los pares que se van a generar aleatoriamente
        List<Par> list = new ArrayList<>();
        Double a,b;
        Random rand = new Random();
        // Generamos n pares de números random que se encuentren en el intervalo [-1,1]
        for(int i=0;i<n;i++){
            a = -1 + ( 1 - (-1) ) * rand.nextDouble();
            b = -1 + ( 1 - (-1) ) * rand.nextDouble();
            Par p = new Par(a,b);
            list.add(p);
        }
        // Cuenta cuantos pares generados verifican la desigualdad
        for(Par p: list){
            if(p.getA()*p.getA() + p.getB()*p.getB() <= 1){
                m++;
            }
        }
        return m;
    }
}
