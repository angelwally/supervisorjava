package supervisor.rmi.common;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

import supervisor.rmi.client.Host;

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

			CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
			
			for (int i=0;i<cpuInfoList.length;i++){
				CpuInfo cpuInfo = cpuInfoList[i];
				resultat.put("cpu"+i,cpuInfo.toString());
			}

			//System.out.println("** CPU sur " + host.getName() + " **");

			CpuPerc[] cpuPercList = sigar.getCpuPercList();
			for(int i=0;i<cpuPercList.length;i++){
				CpuPerc cpuPerc = cpuPercList[i];	
				resultat.put("cpu"+i+"_user",cpuPerc.getUser()*100+"");
				resultat.put("cpu"+i+"_system",cpuPerc.getSys()*100+"");
				resultat.put("cpu"+i+"_nice",cpuPerc.getNice()*100+"");
				resultat.put("cpu"+i+"_wait",cpuPerc.getWait()*100+"");
				resultat.put("cpu"+i+"_idle",cpuPerc.getIdle()*100+"");
			}
			

			
			//System.out.println("ProcStat;: " + sigar.getProcStat());
			ProcStat tempProcStat = sigar.getProcStat();
			resultat.put("procIdle",tempProcStat.getIdle()+"");	
			resultat.put("procZombie",tempProcStat.getZombie()+"");	
			resultat.put("procStopped",tempProcStat.getStopped()+"");	
			resultat.put("procSleeping",tempProcStat.getSleeping()+"");
			resultat.put("procThreads",tempProcStat.getThreads()+"");
			resultat.put("procRunning",tempProcStat.getRunning()+"");
			resultat.put("procTotal",tempProcStat.getTotal()+"");
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return resultat;
	}
}