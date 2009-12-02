package supervisor.rmi.common;

import java.io.IOException;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.net.telnet.TelnetClient;

public class Telnet extends GlobalPlugin {

	public Telnet(Host host) throws RemoteException {
		super(host);
		this.name = "telnet";
		this.inputParams.put("[@ipTo]","localhost");
		this.inputParams.put("[@timeout]", "1000");
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
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			resultat.put("ping","échec");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resultat.put("ping","échec");
		}
		catch(Exception e){
			System.out.println("Erreur de configuration !");
		}

		return resultat;
	}

}
