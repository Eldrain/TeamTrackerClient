import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server implements Virus {

    public Server() {}
    Scanner in = new Scanner(System.in);

    public boolean IsInCirClient(int[][] matrix,int x,int y)
    {
        boolean RightIndex=false;
        if(matrix[x][y]==0)
        {
            for (int i=x-1;i<x+2;i++)
                for (int j=y-1;j<y+2;j++)
                    if(matrix[i][j]==1)
                    {
                        System.out.println("IsInCirClient " + RightIndex);
                        RightIndex=true;
                    }
        }
        else
        {
            System.out.println("IsInCirClient " + RightIndex);
            RightIndex=false;
        }
        return RightIndex;
    }

    public boolean IsTrueIndexClient(int[][] matrix,int x,int y)
    {
        boolean RightIndex=false;
        if(matrix[x][y]==0)
        {
            if(matrix[1][1]==0)
            {
                System.out.println("matrix[1][1] = 0");
                if((x==1)&&(y==1))
                {
                    RightIndex=true;
                }
                else
                {
                    RightIndex=false;
                }
            }
            else
            {
                if(IsInCirClient(matrix,x,y))
                    RightIndex=true;
                else
                    RightIndex=false;
            }
            System.out.println("IsTrueIndexClient " + RightIndex);
        }
        else
        {
            System.out.println("IsTrueIndexClient " + RightIndex);
        }
        return RightIndex;
    }


    int[][] matrix=new int[12][12];
    int[][] error=new int[1][1];
    //matrix[10][10]=2;

    public int[][] sayHello(int x,int y,int n) {
        //matrix[10][10]=2;
        if(IsTrueIndexClient(matrix,x,y))
        {
            matrix[x][y]=1;
        }
        for (int i = 1; i < 11; i++)
        {
            for (int j = 1; j < 11; j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        if(n>2)
        {
            int m=0;
            while(m<3)
            {
                int u,v;
                do
                {
                    System.out.print("X = ");
                    u=in.nextInt();
                    System.out.print("Y = ");
                    v=in.nextInt();
                }
                while((u>10)||(u<1)||(v>10)||(v<1));
                matrix[u][v]=2;
                for (int i = 1; i < 11; i++)
                {
                    for (int j = 1; j < 11; j++)
                    {
                        System.out.print(matrix[i][j]);
                    }
                    System.out.println();
                }
                m++;
            }
        }
        return matrix;
    }

    public static void main(String args[]) {
        Timer t = new Timer(100, new MyClass());
        t.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        t.stop();
    }


}