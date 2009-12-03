package supervisor.rmi.client;

import java.util.HashMap;

public interface View {

	String getMessage(HashMap<String, String> hash);
	String replaceVar(HashMap<String,String> hash);
}
