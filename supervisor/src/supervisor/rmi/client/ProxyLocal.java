package supervisor.rmi.client;
import java.util.ArrayList;

import supervisor.rmi.common.Host;


public class ProxyLocal implements Proxy {
	
	private Host host;

	public ProxyLocal(Host host){
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
		System.out.println("L'aide c'est cool!");
		
	}

}
