package supervisor.rmi.client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.net.MalformedURLException;
import java.rmi.*;

import supervisor.rmi.common.AdapterInterface;

public class ProxyRemote implements Proxy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdapterInterface adapter;
	private int refresh = 2000;
	private Host host;

	public ProxyRemote() throws RemoteException{

	}

	@Override
	public void polling() throws RemoteException{
		HashMap<String,HashMap<String,String>> hash = adapter.polling();
		Iterator<String> it = hash.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			ArrayList<View> views = host.getViews(key);
			for(int i =0;i<views.size();i++){
				View view = views.get(i);
				System.out.println(view.getMessage(hash.get(key)));
			}
		}

	}

	@Override
	public void addHost(Host host) throws RemoteException{
		try {
			this.host = host;
			adapter = (AdapterInterface)Naming.lookup("rmi://"+host.getIp()+":1099/supervisor");
			adapter.addPlugins(host.getPlugins());
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
