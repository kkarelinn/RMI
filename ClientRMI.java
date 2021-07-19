package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRMI {
	
	static String SYMB;
	static StockServerInt myServer = null;
	static String quote;

	static OutputStream outbound;
	static BufferedReader inbound;

	private static void connectServer() {
		try {
			myServer = (StockServerInt)Naming.lookup("rmi://localhost:1099/QService");
			System.out.println("I have connected to the server");

		}
		catch(Exception e) {
			System.out.println("---Failed to connect the server---");
			System.exit(-1);
		}
	}
	
	private static void getSymbolPrice() {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));)
		{	
			System.out.println("Please, put the Stock symbol to get price");
			while(true) {
				SYMB = reader.readLine();
				if(SYMB.equals("q"))
				{	

					receivePrice("exit");
					System.out.println("Exit the programm");
					reader.close();
					System.exit(-1);
				}


				if(SYMB.equals(""))
					System.out.println("You no put any symbols");

				if(!SYMB.equals("")) {
					receivePrice(SYMB);
				}	

			}

		}
		catch (Exception e) {
			System.out.println("You have some problem with inputing symbols");
		}

	}

	private static void receivePrice(String sym) {

		try {

			String price = myServer.getQoute(sym);
			
			if (price.equals("exit")) {
				System.out.println("Server was Shat down");
				
			}

			if(price!=null) {
				System.out.println("The price of "+ sym+" - $"+price);
			}
			else {
				System.out.println("Invalid symbolsList. Please, use one of these: "+ myServer.getListSymbols().toString());
			}


		} catch (RemoteException e) {
			
			e.printStackTrace();
		}

	}

	
	public static void main(String[] args) {
		connectServer();
		getSymbolPrice();
		

		
	}

}
