package supervisor.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import supervisor.rmi.common.AdapterInterface;
import supervisor.rmi.common.Host;

public class Adapter extends UnicastRemoteObject implements AdapterInterface{

	private static final long serialVersionUID = 1L;
	private Host host;

	public Adapter() throws RemoteException{
	}

	public HashMap<String, HashMap<String, String>> polling() throws RemoteException{
		return host.polling();
		
	}

	public void addHost(Host host) throws RemoteException{
		this.host = host;
	}

	public static void main(String[] args) {

		try{		
			Registry r = LocateRegistry.getRegistry();
			Adapter adapter = new Adapter();
			r.bind("supervisor", adapter);
			/*Naming.rebind("rmi://localhost:1099/test", proxy);*/
			System.out.println("Serveur prêt");
		}
		catch(Exception e) {
			System.err.println("Erreur: " + e.getMessage());
			System.exit(0);
		}
	}
}
