package supervisor.rmi.common;
import java.util.ArrayList;
import java.util.HashMap;


public class Host {
	
	private String name;
	private String ip;
	private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

	public Host(String name,String ip){
		this.ip = ip;
		this.name = name;
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
	
	public HashMap<String,String> polling() throws Exception{
		for(int i=0;i<plugins.size();i++){
			Plugin plugin = plugins.get(i);
			return plugin.polling();
		}
		return null;
	}
}
