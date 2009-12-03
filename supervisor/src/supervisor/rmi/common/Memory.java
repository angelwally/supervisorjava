package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class Memory extends GlobalPlugin{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Memory(Host host) throws RemoteException{
		super(host);
		this.name = "memory";
		//params.put("[@timeout]", "1000");
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
		
		Mem mem;
		try {
			mem = sigar.getMem();
			double memPourcent = mem.getFreePercent();
			double memPourcent2 = Math.floor(memPourcent*100)/100;

			resultat.put("mem",sigar.getMem()+"");
			resultat.put("memFree", memPourcent2+"");
			resultat.put("swap", sigar.getSwap()+"");		
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		
		return resultat;
	}
}
