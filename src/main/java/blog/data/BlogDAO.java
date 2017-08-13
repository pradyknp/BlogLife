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

	public List<Blog> findByTitle(String title) {
		Query<Blog> query = createQuery().field("title").contains(title).field("isbn").lessThan(1000);
		return query.asList();
	}

}
