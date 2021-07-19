package socket;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StockServerImpl extends UnicastRemoteObject implements StockServerInt {


	
	private String price = null;
	private ArrayList<String>listSymbols = new ArrayList<>();
	
	protected StockServerImpl() throws RemoteException {
		super();
		LocateRegistry.createRegistry(1099);
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
			System.exit(-1);
			 return "exit";
			 
			 
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
