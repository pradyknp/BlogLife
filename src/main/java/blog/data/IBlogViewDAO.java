package blog.data;

import blog.api.Blog;
import blog.api.Comment;
import java.util.List;

import blog.api.User;;

public interface IBlogViewDAO {
	
	public void post(Blog blog);

	public Blog read(int id);
	
	List<Blog> readAll();

	public void addComment(Comment comment);
	
	public void addUser(User user);
	
	List<Blog> searchCriteria(String criteria);
	
	List<Blog> searchByUsername(String userName);
	
	List<Blog> searchByTitle(String title);
	
	public void deleteBlogByID(int id);
	
	public List<Comment> getComments(int BlogID);
	
	public void deletComment(int CommentID);
	
	public Blog updateBlog(Blog blog);
	
	List<Blog> getAllUsers();
}
