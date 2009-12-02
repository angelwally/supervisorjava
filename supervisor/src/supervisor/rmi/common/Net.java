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
		
		Sigar sigar = new Sigar();			
		System.out.println("** Network sur " + host.getName() + " **");			
		try {
			System.out.println(sigar.getTcp());
			String[] ifList = sigar.getNetInterfaceList();
			for (String intf : ifList)
				System.out.println(intf + " : " + sigar.getNetInterfaceStat(intf));
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}