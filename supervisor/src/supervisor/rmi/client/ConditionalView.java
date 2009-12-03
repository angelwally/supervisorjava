package supervisor.rmi.client;

import java.util.HashMap;
import java.util.StringTokenizer;

public class ConditionalView implements View{

	String condition;
	String message;

	public ConditionalView(String condition,String message) {
		// TODO Auto-generated constructor stub
		this.condition = condition;
		this.message = message;
	}

	@Override
	public String getMessage(HashMap<String,String> hash) {
		// TODO Auto-generated method stub
		if(isTrue(hash)){
			return replaceVar(hash);
		}
		return "";
		

	}

	public boolean isTrue(HashMap<String,String> hash){
		StringTokenizer token = new StringTokenizer(condition);
		int valueOfData = 0;
		int operator = 0;
		boolean result = false;
		int i=0;
		while(token.hasMoreTokens()){
			String s = token.nextToken();
			switch(i){
			case 0:
				if(hash.containsKey(s.substring(1))&&i==0){
					valueOfData = Integer.parseInt(hash.get(s.substring(1)));				
				}
				else
					return false;
				break;
			case 1:
				if(s.equals(">"))
					operator = 0;
				else if(s.equals("<"))
					operator = 1;
				else if(s.equals("=="))
					operator = 2;
				else
					return false;
				break;
			case 2:
				int value = Integer.parseInt(s);
				switch(operator){
				case 0:
					result = valueOfData>value;
					break;
				case 1:
					result = valueOfData<value;
					break;
				case 2:
					result = valueOfData==value;
					break;
				}
				return result;
					
			}
			i++;
		}
		return false;
	}
	
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
