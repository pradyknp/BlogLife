package blog.biz;



import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.User;
import blog.api.exception.BlogNotFoundException;
import blog.api.exception.CommentException;
import blog.api.exception.CommentNotFoundException;
import blog.api.exception.InvalidBlogException;
import blog.api.exception.InvalidUserException;
import blog.api.exception.UserException;
import blog.api.exception.UserNotFoundException;
import blog.data.BlogDAO;
import blog.data.CommentDAO;
import blog.data.IBlogViewDAO;
import blog.data.InMemoryBlogDAO;
import blog.data.MongoDAOImpl;
import blog.data.UserDAO;

import java.util.Comparator;
import java.util.List;

import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.List;;

public class BlogActionImpl implements BlogAction{
//	IBlogViewDAO dao = new InMemoryBlogDAO();
//	IBlogViewDAO dao = new MongoDAOImpl();
	
	MongoClient mongoClient = new MongoClient("localhost"); //27017
	Morphia morphia = new Morphia();
	String databaseName = "blogview";
	Datastore datastore = morphia.createDatastore(mongoClient, databaseName);
	
	
	BlogDAO blogDAO = new BlogDAO(Blog.class, datastore);
	CommentDAO commentDAO = new CommentDAO(Comment.class,datastore);
	UserDAO userDAO = new UserDAO(User.class,datastore);
	
	@Override
	public void post(Blog blog) {
		
		if(blog == null || blog.getId() < 1 || blog.getTitle() == null || blog.getTitle().trim().length()==0 || blog.getTitle().trim().length() > 64 || blog.getBody().trim().length() == 0)
			throw new InvalidBlogException();
		
		blogDAO.save(blog);
	}

	@Override
	public Blog updateBlog(Blog blog) {
		Blog blogOld= (Blog)blogDAO.get(blog.getId());
		
		if(blogOld == null)
			throw new BlogNotFoundException();
		
		blogDAO.deleteBlogUsingID(blogOld.getId());
		blogDAO.save(blog);
		
		return blog;
	}

	@Override
	public Blog view(int blogId) {
		
		Blog blog= (Blog)blogDAO.get(blogId);
		if(blog == null)
			throw new BlogNotFoundException();
		
		return blog;
	}
	
	@Override
	public void postComment(Comment comment) {
		if(comment == null)
			throw new BlogNotFoundException();
		
		commentDAO.save(comment);
		
	}
	
	@Override
	public List<Blog> viewAll() {
		List<Blog> blogs = blogDAO.find().asList();
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public void createUser(User user) {
		if(user == null || user.getPwd() == null || user.getMailid() == null || user.getUsername() == null)
			throw new InvalidUserException();
		
		userDAO.save(user);
	}
	
	@Override
	public List<Blog> findByCategory(String category) {
		List<Blog> blogs = blogDAO.searchByCriteria(category);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public List<Blog> findByUserName(String userName) {
		List<Blog> blogs =blogDAO.searchByUser(userName);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public List<Blog> findByTitle(String title) {
	List<Blog> blogs = blogDAO.searchByTitle(title);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public void deleteBlog(int blogId) {
		blogDAO.deleteBlogUsingID(blogId);
	}

	@Override
	public List<Comment> gettheComments(int BlogID) {
		 return commentDAO.getCommentsforBlog(BlogID);
	}

	@Override
	public void deleteComment(int commentID) {
		commentDAO.deleteCommentUsingID(commentID);
		
	}

	@Override
	public List<User> getAllUsers(){
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
