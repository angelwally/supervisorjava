import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.*;

import javax.naming.ConfigurationException;


public class Parameters {
	
	static private Parameters parameters;
	
	private HashMap<String,HostParameter> hosts;

	private Parameters(String path){
		hosts = new HashMap<String,HostParameter>();
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
				HostParameter host = new HostParameter(hc.getString("[@name]"),hc.getString("[@ip]"));
				List<HierarchicalConfiguration> fields2= hc.configurationsAt("plugin");
				for (HierarchicalConfiguration hc2 : fields2){
					Plugin plugin = new Ping(host);
					Iterator it = hc2.getKeys();
					while (it.hasNext()){
						String S = (String) it.next();
						plugin.addParameter(S, hc2.getString("[@"+S+"]"));
					}
					
				}
			}
		} catch (ConfigurationException e){
			System.out.println(e);
		} catch (Throwable e){
			System.out.println(e);
		}
	}
	
	static public Proxy getProxy(String host){
		if(parameters.hosts.containsKey(host)){
			HostParameter hostParameter = parameters.hosts.get(host);
			if(hostParameter.getIp().compareTo("localhost")==0){
				return new ProxyLocal(hostParameter);
			}
			else{
				return new ProxyRemote(hostParameter);
			}			
		}
		else
			return null;
	}
	
	static public void setParameters(String path){
		if(parameters == null)
			parameters = new Parameters(path);
	}
	
	public void addHost(String nomHost,HostParameter host){
		hosts.put(nomHost, host);
	}
	
}