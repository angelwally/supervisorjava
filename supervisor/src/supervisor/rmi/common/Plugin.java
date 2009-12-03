package supervisor.rmi.common;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import supervisor.rmi.client.View;


public interface Plugin extends Serializable,Remote {
	
	ArrayList<String> getParamNameList() throws Exception;
	void setParam(HashMap<String,String> lstParam) throws Exception;
	HashMap<String,String> polling() throws RemoteException;
	String getName() throws RemoteException;
}

