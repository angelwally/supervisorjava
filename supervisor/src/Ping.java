import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;


public class Ping extends GlobalPlugin implements Plugin{

	public Ping(HostParameter host){
		this.name = "ping";
		this.host = host;
		params.put("timeout", "1000");
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
		HashMap<String,String> response = new HashMap<String,String>();
		if(java.net.InetAddress.getLocalHost().isReachable(Integer.parseInt(params.get("timeout")))){
			response.put("1", "Ping OK");
		}
		else{
			response.put("1", "Ping OK");
		}

		return response;
	}

	@Override
	public void setParam(HashMap<String, String> lstParam) throws Exception {
		// TODO Auto-generated method stub
		this.params = lstParam;

	}
	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception{
		// TODO Auto-generated method stub
		try {
			InetAddress address = InetAddress.getByName(host.getIp());
			System.out.println("Nom: " + address.getHostName());
			System.out.println("Addrese: " + address.getHostAddress());
			System.out.print("Ping: ");
			if(address.isReachable(Integer.parseInt(params.get("timeout"))))
				System.out.println("OK");
			else
				System.out.println("Impossible");
		}
		catch (UnknownHostException e) {
			System.err.println("Impossible de résoudre l'hôte "+host.getName());
		}
		catch (IOException e) {
			System.err.println("Impossible d'atteindre "+host.getName());
		}

		return true;
	}

}
