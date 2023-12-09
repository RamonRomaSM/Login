package Login;

import java.util.ArrayList;
import java.util.Iterator;

public class DataBase {
	private ArrayList<User>users;
	
	
	public DataBase() {
		users=new ArrayList<User>();
	}
	
	public boolean contains(User user) {	
		if(users.contains(user)) {return true;}		
		return false;
	}
	public boolean logIn(User user) {	
		if(users.contains(user)) {		
			User act=users.get(users.indexOf(user));			
			if(act.fullCompare(user)) {				
				return true;				
			}
			return false;
		}		
		return false;
	}
	
	
	
	public void add(User user) {
		
		users.add(user);
		
	}
	
	
	
	
}
