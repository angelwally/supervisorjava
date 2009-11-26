package supervisor.rmi.server;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import supervisor.rmi.common.Host;
import supervisor.rmi.common.Plugin;
import supervisor.rmi.common.Proxy;

public class ProxyRemote extends UnicastRemoteObject implements Proxy {

	private Host host;

	public ProxyRemote() throws RemoteException{
	}

	@Override
	public HashMap<String,String> polling() throws Exception{
		System.out.println("Polling en cours...");
		return host.polling();
		
	}

	@Override
	public void addHost(Host host) throws RemoteException{
		this.host = host;
	}

	public static void main(String[] args) {

		try {
			Registry r = LocateRegistry.getRegistry();
			ProxyRemote proxy = new ProxyRemote();
			r.bind("myserver", proxy);
			/*Naming.rebind("rmi://localhost:1099/test", proxy);*/
			System.out.println("Serveur prêt");
		}
		catch(Exception e) {
			System.err.println("Erreur: " + e.getMessage());
		}
	}
}
