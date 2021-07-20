package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ClientRMI {
	
		
	static String SYMB;
	static StockServerInt myServer = null;
	static String quote;

	static OutputStream outbound=null;
	static BufferedReader inbound=null;

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
					System.exit(0);
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

			if (sym.equals("exit")) {
				 myServer.getQoute(sym);
				System.out.println("Server was Shut down");;
				
			}
			else {
			String price = myServer.getQoute(sym);
			
			if(price!=null ) {
				
				System.out.println("The price of "+ sym+" - $"+price);
			}
			else if (!sym.equals("exit")){
				System.out.println("Invalid symbolsList. Please, use one of these: "+ myServer.getListSymbols().toString());
			}
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
