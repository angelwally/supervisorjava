package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;


public class Ping extends GlobalPlugin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ping(Host host) throws RemoteException{
		super(host);
		this.name = "ping";
		inputParams.put("[@timeout]", "1000");
		inputParams.put("[@ipTo]", "127.0.0.1");
	}
	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> array = new ArrayList<String>();
		array.add("name");
		array.add("address");
		array.add("ping");
		return array;
	}

	@Override
	public HashMap<String, String> polling() throws RemoteException {
		// TODO Auto-generated method stub
		HashMap<String,String> resultat = new HashMap<String, String>();

		try {
			InetAddress address = InetAddress.getByName(inputParams.get("[@ipTo]"));
			resultat.put("name", address.getHostName());
			resultat.put("address", address.getHostAddress());

			long start = System.currentTimeMillis();
			if(address.isReachable(Integer.parseInt(inputParams.get("[@timeout]")))){
				long end = System.currentTimeMillis();
				resultat.put("ping", end-start+"");
			}
			else
				resultat.put("ping", "impossible");
		}
		catch (UnknownHostException e) {
			System.err.println("Impossible de résoudre l'hôte ");
		}
		catch (IOException e) {
			System.err.println("Impossible d'atteindre ");
		}
		catch(NullPointerException e){
			System.err.println("Erreur de syntaxe.");
			return null;
		}

		return resultat;
	}

}
