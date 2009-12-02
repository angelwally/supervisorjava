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
		try {
			Sigar sigar = new Sigar();			

			CpuInfo[] cpuInfoList;

			cpuInfoList = sigar.getCpuInfoList();
			for (CpuInfo cpuInfo : cpuInfoList) System.out.println(cpuInfo);


			System.out.println("** CPU sur " + host.getName() + " **");	
			CpuPerc[] cpuPercList = sigar.getCpuPercList();
			for (CpuPerc cpuPerc : cpuPercList) System.out.println(cpuPerc);
			System.out.println("ProcStat;: " + sigar.getProcStat());
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}
}