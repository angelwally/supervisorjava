package supervisor.rmi.client;

import java.rmi.RemoteException;
import java.util.HashMap;

import supervisor.rmi.common.Host;
import supervisor.rmi.common.Proxy;


public class ProxyLocal implements Proxy {

	private Host host;

	public ProxyLocal(){
	}

	public HashMap<String,HashMap<String,String>> polling() throws RemoteException{
		// TODO Auto-generated method stub
		return host.polling();		
	}

	@Override
	public void addHost(Host host) {
		this.host = host;

	}

}
