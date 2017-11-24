import client.ListenServer;

public class TeamTrackerClient {

	public static void main(String[] args) {
		
		ListenServer client = new ListenServer(2222);
		try {
			client.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
