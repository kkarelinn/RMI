package socket;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StockServerImpl extends UnicastRemoteObject implements StockServerInt {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String price = null;
	Registry registry;
	private ArrayList<String>listSymbols = new ArrayList<>();
	
	protected StockServerImpl() throws RemoteException {
		super();
		registry = LocateRegistry.createRegistry(1200);
		//define some stock Symbols code
		listSymbols.add("AMZN");
		listSymbols.add("MSFT");
		listSymbols.add("AAPL");
		listSymbols.add("YHOO");
		listSymbols.add("BRNT");

		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getQoute(String symbol) throws RemoteException {
		 if(symbol.equals("exit")) {
			try {
				UnicastRemoteObject.unexportObject(this, true);
				registry.unbind("rmi://localhost:1200/QService");
				
				System.exit(-1);
							
		   } catch (Exception e) {
			 e.printStackTrace();
			}	 
		 }
		
		if(listSymbols.indexOf(symbol.toUpperCase())!=-1)
			 price= (new Double(Math.random()*100)).toString();
		return price;
	}

	@Override
	public List<String> getListSymbols() throws RemoteException {
		
		return listSymbols;
	
	}

}
