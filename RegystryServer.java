package socket;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RegystryServer {

	public static void main(String[] args) {

		
		try {
			StockServerImpl ssi = new StockServerImpl();
			Naming.rebind("rmi://localhost:1099/QService", ssi);
			System.out.println("QServer is ready...");
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
