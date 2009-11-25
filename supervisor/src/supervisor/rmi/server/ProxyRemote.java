package supervisor.rmi.server;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.rmi.*;
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
		return host.polling();
	}

	@Override
	public void addHost(Host host) {
		this.host = host;
	}

	public static void main(String[] args) {

		try {
			ProxyRemote proxy = new ProxyRemote();
			Naming.rebind("rmi://localhost:1099/test", proxy);
			System.out.println("Serveur prêt");

		}

		catch(Exception e) {
			System.err.println("Erreur: " + e.getMessage());
		}


	}
}
