package supervisor.rmi.client;

import java.rmi.RemoteException;
import java.util.*;
import org.apache.commons.configuration.*;

import supervisor.rmi.common.CPU;
import supervisor.rmi.common.FileSys;
import supervisor.rmi.common.Host;
import supervisor.rmi.common.Memory;
import supervisor.rmi.common.Ping;
import supervisor.rmi.common.Plugin;
import supervisor.rmi.common.Net;
import supervisor.rmi.common.Telnet;

public class Parameters {

	/**
	 * Parse le fichier file
	 * @param file
	 */
	public static ArrayList<Host> readXML(String file){
		ArrayList<Host> hosts = new ArrayList<Host>();
		System.out.println("* Lecture du fichier XML *");
		try {
			XMLConfiguration config = new XMLConfiguration("config.xml");
			List<HierarchicalConfiguration> fields = config.configurationsAt("host");
			for (HierarchicalConfiguration hc : fields) {
				Host host = new Host(hc.getString("[@name]"),hc.getString("[@ip]"),hc.getInt("[@refresh]"));
				List<HierarchicalConfiguration> fields2= hc.configurationsAt("plugin");
				for (HierarchicalConfiguration hc2 : fields2){
					Plugin plugin = getPlugin(hc2.getString("[@name]"), host);
					if(plugin==null){
						System.out.println("Erreur. Le plugin "+hc2.getString("[@name]")+"n'existe pas.");
						continue;
					}
					Iterator<String> it = hc2.getKeys();
					HashMap<String,String> params = new HashMap<String,String>();
					while (it.hasNext()){
						String key = it.next();
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
		return hosts;
	}
	
	private static Plugin getPlugin(String pluginName,Host host) throws RemoteException{
		if(pluginName.equals("ping"))
			return new Ping(host);
		else if(pluginName.endsWith("memory"))
			return new Memory(host);
		else if(pluginName.endsWith("cpu"))
			return new CPU(host);
		else if(pluginName.equals("filesystem"))
			return new FileSys(host);
		else if(pluginName.equals("net"))
			return new Net(host);
		else if(pluginName.equals("telnet"))
			return new Telnet(host);
		else
			return null;
	}

}