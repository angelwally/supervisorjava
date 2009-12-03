package supervisor.rmi.client;

import java.rmi.RemoteException;
import java.util.HashMap;

import supervisor.rmi.common.Host;


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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				polling();
				Thread.sleep(host.getRefresh());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
