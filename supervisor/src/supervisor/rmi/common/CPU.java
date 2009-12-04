package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class CPU extends GlobalPlugin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CPU(Host host) throws RemoteException{
		super(host);
		this.name = "cpu";
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> polling() throws RemoteException {

		HashMap<String,String> resultat = new HashMap<String, String>();
		
		try {
			Sigar sigar = new Sigar();			

			CpuInfo[] cpuInfoList;

			cpuInfoList = sigar.getCpuInfoList();
			for (CpuInfo cpuInfo : cpuInfoList) System.out.println(cpuInfo);

			//System.out.println("** CPU sur " + host.getName() + " **");

			CpuPerc[] cpuPercList = sigar.getCpuPercList();
			CpuPerc cpuPerc = cpuPercList[0];	
			resultat.put("user",cpuPerc.getUser()*100+"");
			resultat.put("system",cpuPerc.getSys()*100+"");
			resultat.put("idle",cpuPerc.getIdle()*100+"");

			
			//System.out.println("ProcStat;: " + sigar.getProcStat());
			ProcStat tempProcStat = sigar.getProcStat();
			resultat.put("procIdle",tempProcStat.getIdle()+"");	
			resultat.put("procSleeping",tempProcStat.getSleeping()+"");
			resultat.put("procRunning",tempProcStat.getRunning()+"");
			resultat.put("procTotal",tempProcStat.getTotal()+"");
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return resultat;
	}
}