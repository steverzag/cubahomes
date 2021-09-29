package cubahomes.components;

import java.security.Principal;

public class StompUser implements Principal{

	private String name;
	
	public StompUser(String name) {
		this.name=name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

}
