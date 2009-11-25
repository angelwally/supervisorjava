package supervisor.rmi.common;
import java.util.ArrayList;
import java.util.HashMap;


public interface Plugin {
	
	ArrayList<String> getParamNameList() throws Exception;
	void setParam(HashMap<String,String> lstParam) throws Exception;
	HashMap<String,String> polling() throws Exception;
	String getName();
}

