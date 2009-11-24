package supervisor.rmi.client;
import java.util.ArrayList;
import java.rmi.*;

import supervisor.rmi.common.Host;
import supervisor.rmi.common.Plugin;

public class ProxyRemote implements Proxy {

	private Host host;

	public ProxyRemote(Host host){
		this.host = host;
	}

	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception{

		try {

			Plugin plugin = (Plugin)Naming.lookup("rmi://localhost:1099");

			plugin.launchCommand(cmd);
		}
		catch(Exception e) {
			System.err.println("Erreur: " + e.getMessage());
		}
		

		return true;
	}

	@Override
	public void showHelp() {
		// TODO Auto-generated method stub

	}
}
