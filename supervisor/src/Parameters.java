import java.util.HashMap;


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
		/*parser le fichier xml
		creer un objet hostparameter
		creer un objet plugin
		mettre chaque plugin dans un PluginParameter
		ajouter dans le plugin les differents parametres a l'aide de addParameter
		ajout du plugin dans hostparameter a l'aide de addplugin
		mettre chaque hostparameter a l'aide de addHost
		*/
		HostParameter host = new HostParameter("localhost","127.0.0.1");
		Plugin plugin = new Ping(host);
		plugin.addParameter("timeout", "2000");
		host.addPlugin(plugin);
		addHost("localhost",host);
		HostParameter host2 = new HostParameter("norace","10.2.0.222");
		Plugin plugin2 = new Ping(host2);
		plugin2.addParameter("timeout", "2000");
		host2.addPlugin(plugin2);
		addHost("norace",host2);
		
	}
	
	static public Proxy getProxy(String host){
		if(parameters.hosts.containsKey(host)){
			HostParameter hostParameter = parameters.hosts.get(host);
			if(hostParameter.getName().equals("localhost")){
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
