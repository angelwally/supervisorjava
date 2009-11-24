import java.util.ArrayList;


public class ProxyRemote implements Proxy {
	
	private Host host;

	public ProxyRemote(Host host){
		this.host = host;
	}

	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception{
		// TODO Auto-generated method stub
		if(cmd.size()==1)
			showHelp();
		else{
			if(!host.launchCommand(cmd))
				return false;
		}
		return true;
	}

	@Override
	public void showHelp() {
		// TODO Auto-generated method stub
		
	}
}
