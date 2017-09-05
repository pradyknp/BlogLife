package blog.api;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity
public class AuthToken {
    @Id	
	private String user;
    
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
