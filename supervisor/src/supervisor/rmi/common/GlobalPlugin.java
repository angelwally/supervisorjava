package supervisor.rmi.common;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import supervisor.rmi.client.View;


public abstract class GlobalPlugin  extends UnicastRemoteObject implements Serializable,Plugin{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected HashMap<String,String> inputParams = new HashMap<String,String>();
	protected Host host;
	
	public GlobalPlugin(Host host) throws RemoteException{
		this.host = host;
	}
	
	public void setParam(HashMap<String,String> lstParam){
		this.inputParams = lstParam;
	}
	
	public void addView(View view){
		
	}
	
	public String getName(){
		return name;
	}
}
