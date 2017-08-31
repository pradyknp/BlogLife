package blog.biz;

import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.User;
import blog.api.exception.BlogException;
import blog.api.exception.BlogNotFoundException;
import blog.api.exception.CommentException;
import blog.api.exception.CommentNotFoundException;
import blog.api.exception.DuplicateBlogException;
import blog.api.exception.InvalidBlogException;
import blog.api.exception.InvalidCommentException;
import blog.api.exception.InvalidUserException;
import blog.api.exception.UserAlreadyExistsException;
import blog.api.exception.UserException;
import blog.api.exception.UserNotFoundException;
import blog.data.BlogDAO;
import blog.data.CommentDAO;
import blog.data.UserDAO;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class BlogActionImpl implements BlogAction {
	// IBlogViewDAO dao = new InMemoryBlogDAO();
	// IBlogViewDAO dao = new MongoDAOImpl();

	MongoClient mongoClient = new MongoClient("localhost"); // 27017
	Morphia morphia = new Morphia();
	String databaseName = "blogview";
	Datastore datastore = morphia.createDatastore(mongoClient, databaseName);

	BlogDAO blogDAO = new BlogDAO(Blog.class, datastore);
	CommentDAO commentDAO = new CommentDAO(Comment.class, datastore);
	UserDAO userDAO = new UserDAO(User.class, datastore);
	protected int startIndex = 0;
	protected int lastIndex = 10;

	@Override
	public void post(Blog blog) throws DuplicateBlogException,InvalidBlogException, BlogException {

		if (blog == null || blog.getId() < 1 || blog.getTitle() == null || blog.getTitle().trim().length() == 0
				|| blog.getTitle().trim().length() > 64 || blog.getBody().trim().length() == 0)
			throw new InvalidBlogException();

		blogDAO.save(blog);
	}

	@Override
	public Blog updateBlog(Blog blog) throws BlogNotFoundException,InvalidBlogException,BlogException {
		Blog blogOld = (Blog) blogDAO.get(blog.getId());

		if (blogOld == null)
			throw new BlogNotFoundException();

		blogDAO.deleteBlogUsingID(blogOld.getId());
		blogDAO.save(blog);

		return blog;
	}

	@Override
	public Blog view(int blogId) throws DuplicateBlogException,InvalidBlogException,BlogException {

		Blog blog = (Blog) blogDAO.get(blogId);
		if (blog == null)
			throw new BlogNotFoundException();

		return blog;
	}

	@Override
	public List<Blog> viewAll(int pageno, int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException{

		if (pageno != 0 && pagesize != 0) {
			startIndex = (pageno - 1) * 5;
			lastIndex = startIndex + pagesize;
		}

		List<Blog> blogs = blogDAO.find().asList().subList(startIndex, lastIndex);

		if (blogs == null)
			throw new BlogNotFoundException();

		return blogs;
	}

	@Override
	public List<Blog> findByCategory(String category,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException {
		
		if (pageno != 0 && pagesize != 0) {
			startIndex = (pageno - 1) * 5;
			lastIndex = startIndex + pagesize;
		}
		
		List<Blog> blogs = blogDAO.searchByCriteria(category,startIndex, lastIndex);

		if (blogs == null)
			throw new BlogNotFoundException();

		return blogs;
	}

	@Override
	public List<Blog> findByUserName(String userName,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException {
		if (pageno != 0 && pagesize != 0) {
			startIndex = (pageno - 1) * 5;
			lastIndex = startIndex + pagesize;
		}
		
		List<Blog> blogs = blogDAO.searchByUser(userName,startIndex, lastIndex);

		if (blogs == null)
			throw new BlogNotFoundException();

		return blogs;
	}

	@Override
	public List<Blog> findByTitle(String title,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException {
		if (pageno != 0 && pagesize != 0) {
			startIndex = (pageno - 1) * 5;
			lastIndex = startIndex + pagesize;
		}
		
		List<Blog> blogs = blogDAO.searchByTitle(title,startIndex, lastIndex);

		if (blogs == null)
			throw new BlogNotFoundException();

		return blogs;
	}
	
	@Override
	public Blog blogLike(int blogId) throws BlogNotFoundException, InvalidBlogException, BlogException {

		Blog blog = (Blog) blogDAO.get(blogId);
		if (blog == null)
			throw new BlogNotFoundException();
		
		blogDAO.deleteBlogUsingID(blog.getId());
		blog.setLikes();
		blogDAO.save(blog);

		return blog;
		
	}
	
	@Override
	public Blog blogUnLike(int blogId) throws BlogNotFoundException, InvalidBlogException, BlogException {
		// TODO Auto-generated method stub
		Blog blog = (Blog) blogDAO.get(blogId);
		if (blog == null)
			throw new BlogNotFoundException();
		
		blogDAO.deleteBlogUsingID(blog.getId());
		blog.setUnLikes();
		blogDAO.save(blog);

		return blog;
	}

	
	@Override
	public Blog blogDisLike(int blogId) throws BlogNotFoundException, InvalidBlogException, BlogException {
		Blog blog = (Blog) blogDAO.get(blogId);
		if (blog == null)
			throw new BlogNotFoundException();
		
		blogDAO.deleteBlogUsingID(blog.getId());
		blog.setDislikes();
		blogDAO.save(blog);

		return blog;
	}
	
	@Override
	public Blog blogUnDisLike(int blogId) throws BlogNotFoundException, InvalidBlogException, BlogException {
		Blog blog = (Blog) blogDAO.get(blogId);
		
		if (blog == null)
			throw new BlogNotFoundException();
		
		blogDAO.deleteBlogUsingID(blog.getId());
		blog.setUnDislikes();
		blogDAO.save(blog);

		return blog;
	}
	
	@Override
	public String deleteBlog(int blogId) throws BlogNotFoundException,BlogException {
		return blogDAO.deleteBlogUsingID(blogId);
	}
	
	@Override
	public void postComment(Comment comment) throws InvalidCommentException,CommentException {
		if (comment == null)
			throw new BlogNotFoundException();

		commentDAO.save(comment);

	}
	
	@Override
	public List<Comment> gettheComments(int BlogID) throws CommentNotFoundException,CommentException {
		return commentDAO.getCommentsforBlog(BlogID);
	}

	@Override
	public void deleteComment(int commentID) throws CommentNotFoundException,CommentException {
		commentDAO.deleteCommentUsingID(commentID);

	}

	@Override
	public void createUser(User user) throws UserAlreadyExistsException,InvalidUserException,UserException {
		
		if (user == null || user.getPwd() == null || user.getMailid() == null || user.getUsername() == null)
			throw new InvalidUserException();
		
		User userExists = (User) userDAO.get(user.getUsername());

		if (userExists != null)
			throw new UserAlreadyExistsException();

		userDAO.save(user);
	}

	@Override
	public List<User> getAllUsers() throws UserNotFoundException,UserException {
		// TODO Auto-generated method stub
		List<User> users = userDAO.find().asList();
		return users;
	}

	@Override
	public User getuserByUserName(String username) throws UserNotFoundException,UserException {
		User user = (User) userDAO.get(username);
		
		if (user == null)
			throw new UserNotFoundException("User cannot be found");

		return user;
	}

	@Override
	public void deleteUser(String username) throws UserNotFoundException,UserException {
		userDAO.deleteById(username);
	}

	@Override
	public long totalCount(String category) throws BlogNotFoundException, BlogException {
		
		if(category.equals("getAll"))
			return blogDAO.find().count();
		else
			return blogDAO.getCount(category);
	}

}
