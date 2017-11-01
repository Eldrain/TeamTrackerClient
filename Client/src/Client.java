import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host,1098);
            Virus stub = (Virus) registry.lookup("Virus");
			int n=0;
			while(true)
			{
				int x,y;
				do
				{				
					System.out.print("X = ");
					x=in.nextInt();
					System.out.print("Y = ");
					y=in.nextInt();
				}
				while((x>10)||(x<1)||(y>10)||(y<1));
				/*if(n==0)
				{*/
					int[][] response = stub.sayHello(x,y,n);
					if((response[1][1]!=0)&&(response[x][y]!=0))
					{
						n++;
						for (int i = 1; i < 11; i++) 
						{
							for (int j = 1; j < 11; j++) 
							{
								System.out.print(response[i][j]);
							}
						System.out.println();
						}
					}
				//}
			}
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}