package supervisor.rmi.client;

import java.util.HashMap;
import java.util.StringTokenizer;

public class StreamView extends View {

	public StreamView(String string) {
		// TODO Auto-generated constructor stub
		this.message = string;
	}
	@Override
	public String getMessage(HashMap<String, String> hash) {
		// TODO Auto-generated method stub
		return replaceVar(hash);
	}
	
}
