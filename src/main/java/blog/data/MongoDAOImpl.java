package blog.data;

import java.util.List;

import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import blog.api.Blog;
import blog.api.Comment;
import blog.api.User;

public class MongoDAOImpl implements IBlogViewDAO{
		MongoClient mongoClient = new MongoClient("localhost"); //27017
		Morphia morphia = new Morphia();
		String databaseName = "blogview";
		Datastore datastore = morphia.createDatastore(mongoClient, databaseName);
		
		
		BlogDAO blogDAO = new BlogDAO(Blog.class, datastore);
		CommentDAO commentDAO = new CommentDAO(Comment.class,datastore);
		UserDAO userDAO = new UserDAO(User.class,datastore);

	@Override
	public void post(Blog blog) {
		blogDAO.save(blog);
	}

	@Override
	public Blog read(int id) {
		return (Blog)blogDAO.get(id);
	}

	@Override
	public List<Blog> readAll() {
		return blogDAO.find().asList();
	}

	@Override
	public void addComment(Comment comment) {
		commentDAO.save(comment);
	}

	@Override
	public void addUser(User user) {
		userDAO.save(user);
	}

	@Override
	public List<Blog> searchCriteria(String category) {
		return blogDAO.searchByCriteria(category);
	}

	@Override
	public List<Blog> searchByUsername(String userName) {
		return blogDAO.searchByUser(userName);
	}

	@Override
	public List<Blog> searchByTitle(String title) {
		return blogDAO.searchByTitle(title);
}

	@Override
	public void deleteBlogByID (int id) {
		blogDAO.deleteBlogUsingID(id);
		
	}

	@Override
	public List<Comment> getComments(int BlogID) {
		return commentDAO.getCommentsforBlog(BlogID);
	}
}
