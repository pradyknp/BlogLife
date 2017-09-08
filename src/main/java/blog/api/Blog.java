package blog.api;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Blog {
	
	@Id
	private int id;
	private String title;
	private String body;
	
	private String username;
	
	private String createdDate;
	private String modifiedDate;
	private String modifiedTime;
	
	private String category;
	private int likes = 0;
	private int dislikes = 0;
	private int commentCount=0;
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(boolean increment) {
		if(increment)
			this.commentCount++;
		else
			this.commentCount--;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes() {
		this.likes++;
	}
	
	public void setUnLikes() {
		this.likes--;
	}
	
	public int getDislikes() {
		return dislikes;
	}
	
	public void setDislikes() {
		this.dislikes++;
	}
	
	public void setUnDislikes() {
		this.dislikes--;
	}
	
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
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
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
