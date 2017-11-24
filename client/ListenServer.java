package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

public class ListenServer extends Thread {
	private final int mPort;
	private Gson mGson;
	private DataOutputStream mOs;
	private DataInputStream mIs;
	
	public ListenServer(int port) {
		mPort = port;
		mGson = new Gson();
		mOs = null;
		mIs = null;
	}
	
	public void run() {
		try(Socket socket = new Socket("localhost", mPort)) {
			mOs = new DataOutputStream(socket.getOutputStream());
			mIs = new DataInputStream(socket.getInputStream());
			
			Coordinate coord = new Coordinate(0, 0);
			
			while(!socket.isClosed()) {
				String data = mIs.readUTF();
				coord = mGson.fromJson(data, Coordinate.class);
				
				System.out.println("x: " + coord.mX + "; y = " + coord.mY);
				
				/**
				 * Response to server
				 */
				mOs.writeUTF("Get = " + data);
				mOs.flush();
				System.out.println("Server send: " + data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
