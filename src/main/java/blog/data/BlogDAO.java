package blog.data;

import blog.api.Blog;
import blog.api.Comment;
import java.util.List;
import blog.api.User;;

public interface BlogDAO {
	
	public void post(Blog blog);

	public Blog read(int id);
	
	List<Blog> readAll();

	public void addComment(Comment comment);
	
	public void addUser(User user);
	
	List<Blog> searchCriteria(String criteria);
}
