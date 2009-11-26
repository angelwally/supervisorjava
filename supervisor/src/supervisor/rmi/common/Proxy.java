package supervisor.rmi.common;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


public interface Proxy extends Remote{
	public void addHost(Host host) throws RemoteException;
	public HashMap<String,String> polling() throws Exception;

}
