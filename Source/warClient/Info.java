//package war;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Info extends Remote {

    void setPlayer(boolean player) throws RemoteException;

    boolean getPlayer() throws RemoteException;

    void stepUp(int x, int y) throws RemoteException;

    int getX() throws RemoteException;

    int getY() throws RemoteException;
	
	void setElem(int num, int x, int y) throws RemoteException;
	
	int getElemX(int num) throws RemoteException;
	
	int getElemY(int num) throws RemoteException;
}
