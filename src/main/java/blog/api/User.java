package blog.api;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import java.util.List;

@Entity
public class User {
	
	private String pwd;
	private String mailid;
	
	@Id
	private String username;
	
	private String tagLine;
	

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mailid == null) ? 0 : mailid.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((tagLine == null) ? 0 : tagLine.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (mailid == null) {
			if (other.mailid != null)
				return false;
		} else if (!mailid.equals(other.mailid))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (tagLine == null) {
			if (other.tagLine != null)
				return false;
		} else if (!tagLine.equals(other.tagLine))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
