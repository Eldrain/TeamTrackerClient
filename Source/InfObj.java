//package war;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InfObj extends UnicastRemoteObject implements Info {
    boolean player, updC, updS;
    int x, y;
	int xy[];
	
    public InfObj() throws RemoteException {
        super();
		xy = new int[12];
		for(int i = 0; i < 12; i++)
			xy[i] = -1;
		player = true;
        updC = false;
        updS = false;
        x = 0;
        y = 9;
    }

    @Override
    public void setPlayer(boolean player) throws RemoteException {
        this.player = player;
    }

    @Override
    public boolean getPlayer() throws RemoteException {
        return player;
    }

    @Override
    public void stepUp(int x, int y) throws RemoteException {
        this.x = x;
        this.y = y;
        updC = false;
        updS = false;
    }

    @Override
    public int getX() throws RemoteException {
        return x;
    }

    public int getY() throws RemoteException {
        return y;
    }
	
	public void setElem(int num, int x, int y) throws RemoteException {
		xy[num * 2] = x;
		xy[num * 2 + 1] = y;
	}
	
	public int getElemX(int num) throws RemoteException {
		return xy[num * 2];
	}
	
	public int getElemY(int num) throws RemoteException {
		return xy[num * 2 + 1];
	}
}
