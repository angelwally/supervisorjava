package supervisor.rmi.client;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import supervisor.rmi.common.Host;
import supervisor.rmi.common.Proxy;

public class Supervisor {

	private static ArrayList<Host> hosts;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hosts = Parameters.readXML("config.xml");
		System.setSecurityManager(new RMISecurityManager());

		while(true){
			for(int i=0;i<hosts.size();i++){
				try {
					Host host = hosts.get(i);
					Proxy proxy;
					if(host.getName().compareTo("localhost")==0){
						proxy = new ProxyLocal();			
					}
					else{
						proxy = (Proxy)Naming.lookup("rmi://"+host.getIp()+":1099/supervisor");
					}
					proxy.addHost(host);
					proxy.polling();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}


