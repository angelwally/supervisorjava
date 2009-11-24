import java.util.*;
import org.apache.commons.configuration.*;

public class Parameters {
	
	static private Parameters parameters;
	
	private HashMap<String,Host> hosts;

	private Parameters(String path){
		hosts = new HashMap<String,Host>();
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
					Plugin plugin = Parameters.getPlugin(hc2.getString("[@name]"), host);
					if(plugin==null){
						System.out.println("Erreur. Le plugin "+hc2.getString("[@name]")+"n'existe pas.");
						continue;
					}
					Iterator it = hc2.getKeys();
					while (it.hasNext()){
						String key = (String) it.next();
						if(key.equals("[@name]"))
							continue;
						plugin.addParameter(key, hc2.getString(key));
					}
					host.addPlugin(plugin);
					
				}
				hosts.put(host.getName(), host);
			}
		} catch (ConfigurationException e){
			System.out.println(e);
		} catch (Throwable e){
			System.out.println(e);
		}
	}
	
	static public Proxy getProxy(String hostName){
		if(parameters.hosts.containsKey(hostName)){
			Host host = parameters.hosts.get(hostName);
			if(host.getIp().compareTo("localhost")==0){
				return new ProxyLocal(host);
			}
			else{
				return new ProxyRemote(host);
			}			
		}
		else
			return null;
	}
	
	static public Plugin getPlugin(String pluginName,Host host){
		if(pluginName.equals("ping"))
			return new Ping(host);
		else
			return null;
	}
	
	static public void setParameters(String path){
		if(parameters == null)
			parameters = new Parameters(path);
	}
	
	public void addHost(String nomHost,Host host){
		hosts.put(nomHost, host);
	}
	
}