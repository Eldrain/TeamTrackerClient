/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package warClient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class World implements MouseListener, ActionListener {
    int actions, step, x, y;
    boolean player, win;
    Cell cells[][] = new Cell[10][10];
    Graphics gr;
    String infoObjName;
    Info info;

    public World(Graphics gr) throws RemoteException, NotBoundException, MalformedURLException {
        win = false;
        player = true;
        step = 1;
        actions = 3;
        this.gr = gr;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setXY(30*i+50, 30*j+100);
            }
        }

        infoObjName = "rmi://localhost/Info";
        try {
		  Registry registry = LocateRegistry.getRegistry(null, 1098);
		  Info server = (Info)registry.lookup("Info");
	 
		  Info stub = (Info)UnicastRemoteObject.exportObject(info, 0);
		  //server.register(stub);
	 
		} catch (Exception e) {
		  System.out.println ("Error occured: " + e.getMessage());
		  System.exit (1);
		}
        //info = (Info)Naming.lookup(infoObjName);
    }

    public void clickHandler(int x, int y) {
        if(!inBox(x, y) || win)
            return;

        if(player) {
            if(step == 1 && actions == 3)
                if(player && x != 0 && y != 9)
                    return;
                else {
                    cells[0][9].setInf(1);
					step++;
                    actions--;
                }
            if(makeStep(x, y)) {
                actions--;
                if(actions == 0) {
                    player = !player;
                    actions = 3;
					//info.setElem(3, 9, 0);
                    //info.setPlayer(false);
                }
                int buf = findVirus();
                if(buf == - 1) {
                    if (step != 1 && step != 2)
                        win = true;
                    return;
                }
                do {
                    win = !checkWin(buf / 10, buf % 10);
                    buf = findVirus();
                } while(buf != -1 && win);
                setBack(0);
            }
        } else {
            if(step == 2 && actions == 3)
                if(x != 9 && y != 0)
                    return;
                else {
                    cells[9][0].setInf(2);
                    actions--;
                    try {
                        info.stepUp(9, 0);
                        info.setElem(2 - actions, 9, 0);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            if(makeStep(x, y)) {
                actions--;
                try {
                    info.stepUp(x, y);
                    info.setElem(5 - actions, x, y);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                if(actions == 0) {
                    player = !player;
                    try{
                        info.setPlayer(true);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    actions = 3;
                    step++;
                }

                int buf = findVirus();
                if(buf == - 1) {
                    win = true;
                    return;
                }
                do {
                    win = !checkWin(buf / 10, buf % 10);
                    buf = findVirus();
                } while(buf != -1 && win);
                setBack(0);
            }
        }
        //paint(gr);
    }

    private boolean makeStep(int x, int y) {
        int nowPlayer = (player?1:2), enemy = (player?2:1);
        int numCell = cells[x][y].getInf();

        if(numCell == nowPlayer || numCell == 3 || numCell == 4)
            return false;

        numCell = findInEnv(x, y, nowPlayer);

        if(numCell == 0) {
            boolean havePath = findPathToVirus(x, y);
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++)
                    if(cells[i][j].getInf() == (nowPlayer + 4))
                        cells[i][j].setInf((nowPlayer + 2));

            if(havePath) {
                if(cells[x][y].getInf() == 0)
                    cells[x][y].setInf(nowPlayer);
                else if(cells[x][y].getInf() == enemy)
                    cells[x][y].setInf(nowPlayer + 2);
                return true;
            } else
                return false;
        } else {
            if(cells[x][y].getInf() == 0)
                cells[x][y].setInf(nowPlayer);
            else if(cells[x][y].getInf() == enemy)
                cells[x][y].setInf(nowPlayer + 2);
            return true;
        }
    }

    private boolean findPathToVirus(int x, int y) {
        int nowPlayer = player?1:2, enemy = player?2:1;
        int numCell = findInEnv(x, y, nowPlayer);

        if(numCell != 0)
            return true;
        else {
            numCell = findInEnv(x, y, nowPlayer + 2);

            while(numCell != 0) {
                int x1 = getCellXY(x, y, numCell), y1 = x1 % 10;
                x1 /= 10;
                cells[x1][y1].setInf(nowPlayer + 4);
                if(findPathToVirus(x1, y1))
                    return true;
                numCell = findInEnv(x, y, nowPlayer + 2);
            }
            return false;
        }
    }

    private int findInEnv(int x, int y, int num) {
        if(x != 0) {
            if(cells[x - 1][y].getInf() == num)
                return 8;
            if(y != 0) {
                if(cells[x - 1][y - 1].getInf() == num)
                    return 1;
                if(cells[x][y - 1].getInf() == num)
                    return 2;
            }
            if(y != 9) {
                if(cells[x - 1][y + 1].getInf() == num)
                    return 7;
                if(cells[x][y + 1].getInf() == num)
                    return 6;
            }
        }
        if(x != 9) {
            if(cells[x + 1][y].getInf() == num)
                return 4;
            if(y != 0) {
                if(cells[x + 1][y - 1].getInf() == num)
                    return 3;
                if(cells[x][y - 1].getInf() == num)
                    return 2;
            }
            if(y != 9) {
                if(cells[x + 1][y + 1].getInf() == num)
                    return 5;
                if(cells[x][y + 1].getInf() == num)
                    return 6;
            }
        }
        return 0;
    }

    private boolean checkWin(int x, int y) {
        int nowPlayer = player?1:2, enemy = player?2:1;
        int numCell = findInEnv(x, y, 0);

        if(numCell != 0)
            return true;
        else {
            numCell = findInEnv(x, y, nowPlayer);
            while(numCell != 0) {
                int x1 = getCellXY(x, y, numCell), y1 = x1 % 10;
                x1 /= 10;

                cells[x1][y1].setInf(7);
                if(checkWin(x1, y1))
                    return true;
                numCell = findInEnv(x, y, nowPlayer);
            }

            numCell = findInEnv(x, y, nowPlayer + 2);
            while(numCell != 0) {
                int x1 = getCellXY(x, y, numCell), y1 = x1 % 10;
                x1 /= 10;

                cells[x1][y1].setInf(8);
                if(checkWin(x1, y1))
                    return true;
                numCell = findInEnv(x, y, nowPlayer + 2);
            }
            return false;
        }
    }

    private int findVirus() {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if(cells[i][j].getInf() == (player?1:2))
                    return i * 10 + j;
        return -1;
    }

    private void setBack(int mode) {
        int check1 = 0, check2 = 0, set1 = 0, set2 = 0;
        switch(mode) {
            case 0:
                check1 = 7;
                check2 = 8;
                set1 = player?1:2;
                set2 = set1 + 2;
                break;
            case 1:
                check1 = 5;
                check2 = 6;
                set1 = player?1:2;
                set2 = set1 + 2;
                break;
        }

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
                if(cells[i][j].getInf() == check1)
                    cells[i][j].setInf(set1);
                else if(cells[i][j].getInf() == check2)
                    cells[i][j].setInf(set2);
    }

    private int getCellXY(int x, int y, int numCell) {
        int x1 = 0, y1 = 0;
        if(numCell > 4) {
            if(numCell == 8) {
                x1 = x - 1;
                y1 = y;
            } else {
                y1 = y + 1;
                switch(numCell) {
                    case 5:
                        x1 = x + 1;
                        break;
                    case 6:
                        x1 = x;
                        break;
                    case 7:
                        x1 = x - 1;
                        break;
                }
            }
        } else if(numCell == 4) {
            x1 = x + 1;
            y1 = y;
        }else {
            y1 = y - 1;
            switch(numCell) {
                case 3:
                    x1 = x + 1;
                    break;
                case 2:
                    x1 = x;
                    break;
                case 1:
                    x1 = x - 1;
                    break;
            }
        }
        return x1*10 + y1;
    }

    private boolean inBox(int x, int y) {
        if(x < 0 || x > 9 || y < 0 || y > 9)
            return false;
        else
            return true;
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(40, 30, 160, 25);
        g.setColor(Color.BLACK);
        g.drawString("Player: " + (player?1:2) + "| Steps: " + actions + (win?" win":""), 50, 50);

        for (int i = 0; i < 10; i++) {
            g.drawString("" + i, 30*i + 65, 90);
            g.drawString("" + i, 33, 30*i+120);
            for (int j = 0; j < 10; j++) {
                g.setColor(Color.WHITE);
                g.fillRect(30*i+50, 30*j+100, 29, 29);
                g.setColor(Color.BLACK);
                g.drawRect(30*i+50, 30*j+100, 30, 30);

                switch(cells[i][j].getInf()) {
                    case 1:
                        g.drawString("X",30*i+63, 30*j+120);
                        break;
                    case 2:
                        g.drawString("0",30*i+63, 30*j+120);
                        break;
                    case 3:
                        g.drawString("3",30*i+63, 30*j+120);
                        break;
                    case 4:
                        g.drawString("4",30*i+63, 30*j+120);
                        break;
                    case 7:
                        g.drawString("7",30*i+63, 30*j+120);
                        break;
                    case 8:
                        g.drawString("8",30*i+63, 30*j+120);
                        break;

                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!player)
            clickHandler((e.getX() - 50)/30, (e.getY() - 100)/30);
        paint(gr);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int x = info.getX();
            int y = info.getY();

            /*if(player != info.getPlayer()) {
				clickHandler(x, y);
				player = !player;
				actions = 3;
			}*/
            player = true;
            for(int i = 0; i < 6; i++) {
                if(i == 3)
                    player = false;
                x = info.getElemX(i);
                y = info.getElemY(i);
                clickHandler(x, y);
            }

            player = info.getPlayer();

            if(x != this.x || y != this.y) {
                this.x = x;
                this.y = y;
                //clickHandler(x, y);
            }
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        paint(gr);
    }
}