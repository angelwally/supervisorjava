package supervisor.rmi.common;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hyperic.sigar.*;

public class FileSys extends GlobalPlugin {

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
	public HashMap<String, String> polling() throws RemoteException {
		
		Sigar sigar = new Sigar();			
		HashMap<String,String> resultat = new HashMap<String, String>();
		String tempString = "";
		
		FileSystem[] list;
		try {
			list = sigar.getFileSystemList();
			int n = list.length;
			int i = 0;
			
			/*for (int i=0; i<=n; i++) {
				tempString = tempString + list[i].getDirName()+" " ;
				//tempString = tempString + list[i].getTypeName()+" " ;
				//if (i==n) tempString = tempString + list[i].getOptions()+" " ;
				//else tempString = tempString + list[i].getOptions()+" " +"\n" ;				
			}		*/		
			
			for (FileSystem fs : list) {
				tempString = tempString + fs.getDirName()+" " ;
				tempString = tempString + fs.getTypeName()+" " ;
				if (i==n-1) tempString = tempString + list[i].getOptions()+" " ;
				else tempString = tempString + list[i].getOptions()+" " +"\n" ;
				i++;
			}	
			resultat.put("info",tempString);
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return resultat;
	}

}