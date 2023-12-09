package Login;

public class User {
	private String name;
	private String passw;
	public User(String name, String passw) {
		super();
		this.name = name;
		this.passw = passw;
	}
	public String getName() {
		return name;
	}
	public String getPassw() {
		return passw;
	}
	@Override
	public boolean equals(Object obj) {
		
		try {
			User act=(User) obj;
			if(act.name.equals(this.name)) {return true;}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean fullCompare(User u) {
		if(u.passw.equals(this.passw)) {return true;}
		return false;
	}

}
