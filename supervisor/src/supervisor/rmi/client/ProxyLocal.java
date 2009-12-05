package supervisor.rmi.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class ProxyLocal implements Proxy {

	private Host host;

	public ProxyLocal(){
	}

	@Override
	public void polling() throws RemoteException{
		HashMap<String,HashMap<String,String>> hash = host.polling();
		Iterator<String> it = hash.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			ArrayList<View> views = host.getViews(key);
			for(int i =0;i<views.size();i++){
				View view = views.get(i);
				String msg = view.getMessage(hash.get(key));
				if(!msg.equals(""))
					System.out.println(msg);
			}
		}

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
