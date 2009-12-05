package supervisor.rmi.common;

import java.io.IOException;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.net.telnet.TelnetClient;

import supervisor.rmi.client.Host;

public class Telnet extends GlobalPlugin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Telnet(Host host) throws RemoteException {
		super(host);
		this.name = "telnet";
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> polling() throws RemoteException {
		// TODO Auto-generated method stub
		HashMap<String,String> resultat = new HashMap<String, String>();
		TelnetClient telnet = new TelnetClient();
		try {
			telnet.setConnectTimeout(Integer.parseInt(inputParams.get("[@timeout]")));
			long start = System.currentTimeMillis();
			telnet.connect(inputParams.get("[@ipTo]"));
			long end = System.currentTimeMillis();
			telnet.disconnect();
			resultat.put("ping", end-start+"ms");
			resultat.put("reachable", "1");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			resultat.put("reachable", "0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resultat.put("reachable", "0");
		}
		catch(Exception e){
			System.out.println("Erreur de configuration !");
		}

		return resultat;
	}

}
