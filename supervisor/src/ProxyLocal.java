import java.util.ArrayList;


public class ProxyLocal implements Proxy {
	
	private HostParameter hostParameter;

	public ProxyLocal(HostParameter hostParameter){
		this.hostParameter = hostParameter;
	}
	@Override
	public boolean launchCommand(ArrayList<String> cmd) throws Exception{
		// TODO Auto-generated method stub
		if(cmd.size()==1)
			showHelp();
		else{
			if(!hostParameter.launchCommand(cmd))
				return false;
		}
		return true;
		
	}
	@Override
	public void showHelp() {
		// TODO Auto-generated method stub
		System.out.println("L'aide c'est cool!");
		
	}

}
