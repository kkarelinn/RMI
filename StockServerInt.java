package socket;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StockServerInt extends Remote {
	public String getQoute(String symbol) throws RemoteException;
	public List<String> getListSymbols() throws RemoteException;
	

}
