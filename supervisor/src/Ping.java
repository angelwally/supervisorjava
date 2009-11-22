import java.util.ArrayList;
import java.net.InetAddress;
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
		if(InetAddress.getByName(host.getIp()).isReachable(Integer.parseInt(params.get("timeout")))){
			System.out.println("PING OK");
		}
		else{
			System.out.println("Impossible de joindre l'hôte distant.");
		}
		return true;
	}

}
