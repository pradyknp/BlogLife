package blog.api;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Comment {
	@Id
	private int id;
	
	private String body;
	private String username;
	private String createdDate;
	private String modifiedDate;
	private int blogId;
//	private List<String> replies;
	
	private int likes = 0;
	private int dislikes = 0;
	
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
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
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
//	public List<String> getReplies() {
//		return replies;
//	}
//	public void setReplies(List<String> replies) {
//		this.replies = replies;
//	}


}
