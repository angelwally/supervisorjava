package supervisor.rmi.client;

import java.util.HashMap;
import java.util.StringTokenizer;

public class StreamView implements View {

	String message;
	public StreamView(String string) {
		// TODO Auto-generated constructor stub
		this.message = string;
	}
	@Override
	public String getMessage(HashMap<String, String> hash) {
		// TODO Auto-generated method stub
		return replaceVar(hash);
	}
	@Override
	public String replaceVar(HashMap<String,String> hash){
		StringTokenizer token = new StringTokenizer(message);
		String response="";
		while(token.hasMoreTokens()){
			String s = token.nextToken();
			if(s.charAt(0)=='@'){
				if(hash.containsKey(s.substring(1)))
					response+=hash.get(s.substring(1));
				else
					response+="erreur";
			}
			else{
				response+=s;
			}
		}
		return response;
	}
}
