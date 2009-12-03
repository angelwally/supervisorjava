package supervisor.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface AdapterInterface extends Remote {

	public HashMap<String, HashMap<String, String>> polling() throws RemoteException;
	public void addHost(Host host) throws RemoteException;
}
