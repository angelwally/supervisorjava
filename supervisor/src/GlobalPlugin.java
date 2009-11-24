import java.util.ArrayList;
import java.util.HashMap;


public class GlobalPlugin {
	
	protected String name;
	protected HashMap<String,String> params = new HashMap<String,String>();
	protected Host host;
	
	
	public void addParameter(String key,String value){
		params.put(key, value);
	}
	
	public String getName(){
		return name;
	}

}
