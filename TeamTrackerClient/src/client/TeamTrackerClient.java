package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TeamTrackerClient {

	public static void main(String[] args) {
		
		try(Socket socket = new Socket("localhost", 2222)) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
			//int key = Integer.parseInt(rd.readLine());
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			DataInputStream is = new DataInputStream(socket.getInputStream());
			
			while(!socket.isClosed()) {
				String data = is.readUTF();
				System.out.println(data);
				//System.out.println("Client sent message " + key + " to server.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
