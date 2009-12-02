package supervisor.rmi.common;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class FileSys extends GlobalPlugin implements Plugin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileSys(Host host) throws RemoteException{
		super(host);
		this.name = "filesystem";
	}
	// TODO Auto-generated constructor stub

	@Override
	public ArrayList<String> getParamNameList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> polling() throws Exception {
		Sigar sigar = new Sigar();			

		FileSystem[] list = sigar.getFileSystemList();
		System.out.println("** File System sur " + host.getName() + " **");	
		for (FileSystem fs : list) {
			System.out.println(fs.getDirName());
			System.out.println(fs.getTypeName());
			System.out.println(fs.getOptions());
			/*try{
				FileSystemUsage usage =	sigar.getFileSystemUsage(fs.getDirName());
				System.out.println(usage);	
			} catch (SigarException e){
				System.out.println(e.getMessage());
			}*/
		}

		return null;
	}

}