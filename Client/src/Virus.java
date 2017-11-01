import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Virus extends Remote {
    int[][] sayHello(int x,int y,int n) throws RemoteException;
}