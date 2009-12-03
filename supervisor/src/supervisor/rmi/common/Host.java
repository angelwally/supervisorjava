package supervisor.rmi.common;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


public class Host implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String ip;
	private int refresh = 2000;
	private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

	public Host(String name,String ip,int refresh){
		this.ip = ip;
		this.name = name;
		this.refresh = refresh;
	}
	
	public String getIp(){
		return ip;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean addPlugin(Plugin plugin){
		return plugins.add(plugin);
	}
	
	/*private Plugin getPlugin(String pluginName){
		for(int i=0;i<plugins.size();i++){
			Plugin plugin = plugins.get(i);
			if(plugin.getName().equals(pluginName))
				return plugin;
		}
		return null;
	}*/
	
	public HashMap<String,HashMap<String,String>> polling() throws RemoteException{
		HashMap<String,HashMap<String,String>> resultat = new HashMap<String, HashMap<String, String>>();
		for(int i=0;i<plugins.size();i++){
			Plugin plugin = plugins.get(i);
			resultat.put(plugin.getName(),plugin.polling());
		}
		return resultat;
	}
	
	public int getRefresh(){
		return refresh;
	}
}
