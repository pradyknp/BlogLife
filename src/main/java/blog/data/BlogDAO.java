package blog.data;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import blog.api.Blog;

public class BlogDAO extends BasicDAO<Blog, Integer> {

	public BlogDAO(Class<Blog> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	public List<Blog> searchByTitle(String title,int startIndex,int lastIndex) {
		Query<Blog> query = createQuery().field("title").containsIgnoreCase(title);
		return query.asList().subList(startIndex,lastIndex);
	}
	
	public List<Blog> searchByCriteria(String category,int startIndex,int lastIndex) {
		Query<Blog> query = createQuery().field("category").containsIgnoreCase(category);
		return query.asList().subList(startIndex,lastIndex);
	}
	
	public List<Blog> searchByUser(String user,int startIndex,int lastIndex) {
		Query<Blog> query = createQuery().field("username").containsIgnoreCase(user);
		return query.asList().subList(startIndex,lastIndex);
	}
	
	public void deleteBlogUsingID(int id){
		deleteById(id);
	}
	
	public Blog updateBlog(Blog blog){
		return null;
	}
}
