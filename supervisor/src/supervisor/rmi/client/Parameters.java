package supervisor.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import org.apache.commons.configuration.*;

import supervisor.rmi.common.CPU;
import supervisor.rmi.common.Host;
import supervisor.rmi.common.Memory;
import supervisor.rmi.common.Ping;
import supervisor.rmi.common.Plugin;
import supervisor.rmi.common.Proxy;
import supervisor.rmi.server.ProxyRemote;

public class Parameters {

	static private Parameters parameters;

	private ArrayList<Host> hosts;

	private Parameters(String path){
		hosts = new ArrayList<Host>();
		readXML(path);
	}

	/**
	 * Parse le fichier file
	 * @param file
	 */
	private void readXML(String file){

		System.out.println("* Lecture du fichier XML *");
		try {
			XMLConfiguration config = new XMLConfiguration("config.xml");
			List<HierarchicalConfiguration> fields = config.configurationsAt("host");
			for (HierarchicalConfiguration hc : fields) {
				Host host = new Host(hc.getString("[@name]"),hc.getString("[@ip]"));
				List<HierarchicalConfiguration> fields2= hc.configurationsAt("plugin");
				for (HierarchicalConfiguration hc2 : fields2){
					Plugin plugin = this.getPlugin(hc2.getString("[@name]"), host);
					if(plugin==null){
						System.out.println("Erreur. Le plugin "+hc2.getString("[@name]")+"n'existe pas.");
						continue;
					}
					Iterator it = hc2.getKeys();
					HashMap<String,String> params = new HashMap<String,String>();
					while (it.hasNext()){
						String key = (String) it.next();
						if(key.equals("[@name]"))
							continue;
						params.put(key, hc2.getString(key));
					}
					plugin.setParam(params);
					host.addPlugin(plugin);

				}
				hosts.add(host);
			}
		} catch (ConfigurationException e){
			System.out.println(e);
		}
		catch(RemoteException e){
			e.printStackTrace();
		}
		catch (Throwable e){
			System.out.println(e);
		}
	}

	/*static public Proxy getProxy(String hostName){
		if(parameters.hosts.containsKey(hostName)){
			Host host = parameters.hosts.get(hostName);
			if(host.getName().compareTo("localhost")==0){
				Proxy proxy = new ProxyLocal();
				proxy.addHost(host);
				return proxy;
			}
			else{
				try {
					Proxy proxy = (Proxy)Naming.lookup("rmi://localhost:1099");
					proxy.addHost(host);
					return proxy;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return null;
	}*/

	private Plugin getPlugin(String pluginName,Host host) throws RemoteException{
		if(pluginName.equals("ping"))
			return new Ping(host);
		else if(pluginName.endsWith("memory"))
			return new Memory(host);
		else if(pluginName.endsWith("cpu"))
			return new CPU(host);
		else
			return null;
	}

	static public Parameters setParameters(String path){
		if(parameters == null)
			parameters = new Parameters(path);
		return parameters;
	}
	
	public HashMap<String,String> polling() throws Exception{
		for(int i=0;i<hosts.size();i++){
			Host host = hosts.get(i);
			if(host.getName().compareTo("localhost")==0){
				Proxy proxy = new ProxyLocal();
				proxy.addHost(host);
				proxy.polling();
			}
			else{
				try {
					Proxy proxy = (Proxy)Naming.lookup("rmi://"+host.getIp()+":1099/myserver");
					proxy.addHost(host);
					proxy.polling();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		return null;
	}

}