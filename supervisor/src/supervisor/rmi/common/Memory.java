package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

import supervisor.rmi.client.Host;

public class Memory extends GlobalPlugin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Memory(Host host) throws RemoteException{
		super(host);
		this.name = "memory";
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> polling() throws RemoteException {
		
		Sigar sigar = new Sigar();			
		HashMap<String,String> resultat = new HashMap<String, String>();
		
		
		try {
			Mem mem;
			mem = sigar.getMem();
			double memFreePercent = Math.floor(mem.getFreePercent()*100)/100;
			double memUsedPercent = Math.floor(mem.getUsedPercent()*100)/100;
			resultat.put("memFreePercent",memFreePercent+"");
			resultat.put("memFree", mem.getFree()+"");
			resultat.put("memUsedPercent", memUsedPercent+"");
			resultat.put("memUsed", mem.getUsed()+"");
			resultat.put("memTotal", mem.getTotal()+"");

			resultat.put("mem",mem+"");
			Swap swap = sigar.getSwap();
			resultat.put("swap", swap+"");	
			resultat.put("swapFree", swap.getFree()+"");
			resultat.put("swapUsed", swap.getUsed()+"");
			resultat.put("swapTotal",swap.getTotal()+"");
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		
		return resultat;
	}
}
