package supervisor.rmi.client;

import java.util.HashMap;

public class StreamView extends View {

	public StreamView(String string) {
		// TODO Auto-generated constructor stub
		this.message = string;
	}
	@Override
	public String getMessage(HashMap<String, String> hash) {
		// TODO Auto-generated method stub
		return replaceVar(hash,message);
	}
	
}
