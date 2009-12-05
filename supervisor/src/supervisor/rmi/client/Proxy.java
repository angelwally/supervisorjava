package supervisor.rmi.client;
import java.rmi.RemoteException;


public interface Proxy extends Runnable{
	public void addHost(Host host) throws RemoteException;
	public void polling() throws RemoteException;

}
