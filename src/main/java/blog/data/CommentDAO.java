
package blog.data;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import blog.api.Blog;
import blog.api.Comment;

public class CommentDAO extends BasicDAO<Comment, Integer> {

	public CommentDAO(Class<Comment> entityClass, Datastore ds) {
		super(entityClass, ds);
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Comment> getCommentsforBlog(int blogId){
		Query<Comment> query = createQuery().filter("blogId =", blogId);
		return query.asList();
	}
	
	public void deleteCommentUsingID(int commentID){
		deleteById(commentID);
	}
}