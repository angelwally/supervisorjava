package supervisor.rmi.common;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class CPU extends GlobalPlugin implements Plugin {

	public CPU(Host host) {
		this.name = "cpu";
		this.host = host;
	}

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	
	public boolean launchCommand(ArrayList<String> cmd) throws Exception {
		
		Sigar sigar = new Sigar();			
		
		CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
		for (CpuInfo cpuInfo : cpuInfoList) System.out.println(cpuInfo);
		
		
		System.out.println("** CPU sur " + host.getName() + " **");	
		CpuPerc[] cpuPercList = sigar.getCpuPercList();
		for (CpuPerc cpuPerc : cpuPercList) System.out.println(cpuPerc);
		System.out.println("ProcStat;: " + sigar.getProcStat());
		
		return true;
	}
}