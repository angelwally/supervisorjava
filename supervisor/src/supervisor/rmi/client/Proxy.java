package supervisor.rmi.client;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import supervisor.rmi.common.Host;


public interface Proxy extends Runnable{
	public void addHost(Host host) throws RemoteException;
	public HashMap<String,HashMap<String,String>> polling() throws RemoteException;

}
