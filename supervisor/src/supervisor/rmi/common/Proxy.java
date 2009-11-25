package supervisor.rmi.common;
import java.util.HashMap;


public interface Proxy {
	public void addHost(Host host);
	public HashMap<String,String> polling() throws Exception;

}
