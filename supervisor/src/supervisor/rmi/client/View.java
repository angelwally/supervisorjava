package supervisor.rmi.client;

import java.util.HashMap;
import java.util.StringTokenizer;

public abstract class View {

	abstract String getMessage(HashMap<String, String> hash);
	
	String message;
	
	public String replaceVar(HashMap<String,String> hash,String string){
		StringTokenizer token = new StringTokenizer(string);
		String response="";
		while(token.hasMoreTokens()){
			String s = token.nextToken();
			if(s.charAt(0)=='@'){
				if(hash.containsKey(s.substring(1)))
					response+=hash.get(s.substring(1))+ " ";
				else
					response+="erreur ";
			}
			else if(s.equals("and"))
				response+="&& ";
			else if(s.equals("or"))
				response+="|| ";
			else{
				response+=s+" ";
			}
		}
		return response;
	}
}
