package blog.api;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Blog {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String body;
	
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	@JoinColumn(name="user_id")
//	private User user;
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	private Date createdDate;
	private Date modifiedDate;
	private String modifiedTime;
	
	private String category;
	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
////	@JoinColumn
//	private List<Comment> comments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	

//	public List<Comment> getComments() {
//		return comments;
//	}
//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}
	
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}

}
