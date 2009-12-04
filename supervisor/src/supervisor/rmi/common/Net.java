package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

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
		System.out.println("** Network sur " + host.getName() + " **");			
		try {
			
			String[] ifList = sigar.getNetInterfaceList();		
			int n = ifList.length;
			int i = 0;
			
			for (String intf : ifList){
				tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" " ;
				if (i==n-1) tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" " ;
				else tempString = tempString = tempString + intf + " : " + sigar.getNetInterfaceStat(intf)+" "  +"\n" ;
				i++;

			}		
			System.out.println(tempString);
			resultat.put("info",tempString);
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultat;
	}
}