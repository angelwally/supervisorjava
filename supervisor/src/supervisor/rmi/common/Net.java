package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

import supervisor.rmi.client.Host;

public class Net extends GlobalPlugin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Net(Host host) throws RemoteException{
		super(host);
		this.name = "net";
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> polling() throws RemoteException {
	
		HashMap<String,String> resultat = new HashMap<String, String>();
		String tempString = "";
		
		Sigar sigar = new Sigar();			
		
		try {
			
			String[] ifList = sigar.getNetInterfaceList();		
			int n = ifList.length;
			int i = 0;
			
			for (String intf : ifList){
				NetInterfaceStat stat = sigar.getNetInterfaceStat(intf);
				resultat.put(intf, stat.toString());
				resultat.put(intf+"_txErrors", stat.getTxErrors()+"");
				resultat.put(intf+"_rxErrors", stat.getRxErrors()+"");
				tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" " ;
				if (i==n-1) tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" " ;
				else tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" "  +"\n" ;
				i++;

			}		
			resultat.put("info",tempString);
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultat;
	}
}