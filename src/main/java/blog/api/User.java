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
	
////	public int getId() {
////		return id;
////	}
////	public void setId(int id) {
////		this.id = id;
////	}
//	
//	public List<Blog> getBlog() {
//		return blog;
//	}
//	public void setBlog(List<Blog> blog) {
//		this.blog = blog;
//	}
}
