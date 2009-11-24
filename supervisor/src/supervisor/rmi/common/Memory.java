package supervisor.rmi.common;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class Memory extends GlobalPlugin implements Plugin {

	public Memory(Host host) {
		this.name = "memory";
		this.host = host;
		//params.put("[@timeout]", "1000");
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

	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception {
		
		Sigar sigar = new Sigar();			

		Mem mem = sigar.getMem();	
		double memPourcent = mem.getFreePercent();
		double memPourcent2 = Math.floor(memPourcent*100)/100;
		System.out.println("** MEMOIRE sur " + host.getName() + " **");	
		System.out.println(sigar.getMem()+ ", "+ memPourcent2 +"% free");
		System.out.println(sigar.getSwap());		
		
		return true;
	}
}
