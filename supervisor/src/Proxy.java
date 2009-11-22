import java.util.ArrayList;


public interface Proxy {
	
	public boolean launchCommand(ArrayList<String> cmd) throws Exception;
	public void showHelp();

}
