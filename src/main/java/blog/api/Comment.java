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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blogId;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
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
		Comment other = (Comment) obj;
		if (blogId != other.blogId)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (id != other.id)
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
