package supervisor.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import supervisor.rmi.common.AdapterInterface;
import supervisor.rmi.common.Plugin;

public class Adapter extends UnicastRemoteObject implements AdapterInterface{

	private static final long serialVersionUID = 1L;
	private ArrayList<Plugin> plugins;

	public Adapter() throws RemoteException{
	}

	public HashMap<String, HashMap<String, String>> polling() throws RemoteException{
		HashMap<String,HashMap<String,String>> resultat = new HashMap<String, HashMap<String, String>>();
		for(int i=0;i<plugins.size();i++){
			Plugin plugin = plugins.get(i);
			resultat.put(plugin.getName(),plugin.polling());
		}
		return resultat;
		
	}

	public void addPlugins(ArrayList<Plugin> plugins) throws RemoteException{
		this.plugins = plugins;
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
