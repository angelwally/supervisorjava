package supervisor.rmi.client;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.rmi.*;

import supervisor.rmi.common.AdapterInterface;
import supervisor.rmi.common.Host;

public class ProxyRemote implements Proxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdapterInterface adapter;
	private int refresh = 2000;

	public ProxyRemote() throws RemoteException{

	}

	@Override
	public HashMap<String, HashMap<String, String>> polling() throws RemoteException{
		return adapter.polling();

	}

	@Override
	public void addHost(Host host) throws RemoteException{
		try {
			adapter = (AdapterInterface)Naming.lookup("rmi://"+host.getIp()+":1099/supervisor");
			adapter.addHost(host);
			refresh = host.getRefresh();
		} catch (MalformedURLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				polling();
				Thread.sleep(refresh);

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
