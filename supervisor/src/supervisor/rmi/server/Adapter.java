package supervisor.rmi.server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;

import supervisor.rmi.common.Plugin;

public class Adapter extends UnicastRemoteObject implements Plugin {

	public Adapter() throws RemoteException {
		super(); 
	}

	public static void main(String[] args) {
		try {
			Adapter adapter = new Adapter();
			Naming.rebind("rmi://localhost:1099", adapter);
			System.out.println("Serveur prêt");
		}
		catch(Exception e) {
			System.err.println("Erreur: " + e.getMessage());
		}
	}

	@Override
	public void addParameter(String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, String> polling() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setParam(HashMap<String, String> lstParam) throws Exception {
		// TODO Auto-generated method stub

	}

}
