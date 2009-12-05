package supervisor.rmi.client;

import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ConditionalView extends View{

	String condition;
	String messageFalse = "";

	public ConditionalView(String condition,String message) {
		// TODO Auto-generated constructor stub
		this.condition = condition;
		this.message = message;
	}
	
	public ConditionalView(String condition,String messageTrue,String messageFalse){
		this(condition,messageTrue);
		this.messageFalse = messageFalse;
	}

	@Override
	public String getMessage(HashMap<String,String> hash) {
		// TODO Auto-generated method stub
		if(isTrue(hash)){
			return replaceVar(hash,message);
		}
		else
			return replaceVar(hash,messageFalse);
		
	}

	public boolean isTrue(HashMap<String,String> hash){
		String s = replaceVar(hash,condition);
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByMimeType("text/javascript");
		try {
			boolean result = (Boolean) engine.eval(s);
			return result;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
