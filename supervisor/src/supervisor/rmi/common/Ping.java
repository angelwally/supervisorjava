package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;


public class Ping extends GlobalPlugin implements Plugin{

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
		array.add("timeout");
		return array;
	}

	@Override
	public HashMap<String, String> polling() throws Exception {
		// TODO Auto-generated method stub
		try {
			InetAddress address = InetAddress.getByName(inputParams.get("[@ipTo]"));
			System.out.println("Nom: " + address.getHostName());
			System.out.println("Addrese: " + address.getHostAddress());
			System.out.print("Ping: ");
			if(address.isReachable(Integer.parseInt(inputParams.get("[@timeout]"))))
				System.out.println("OK");
			else
				System.out.println("Impossible");
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

		return null;
	}

}
