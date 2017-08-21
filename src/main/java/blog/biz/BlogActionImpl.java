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
import blog.data.IBlogViewDAO;
import blog.data.InMemoryBlogDAO;
import blog.data.MongoDAOImpl;

import java.util.List;;

public class BlogActionImpl implements BlogAction{
//	IBlogViewDAO dao = new InMemoryBlogDAO();
	IBlogViewDAO dao = new MongoDAOImpl();
	
	@Override
	public void post(Blog blog) {
		
		if(blog == null || blog.getId() < 1 || blog.getTitle() == null || blog.getTitle().trim().length()==0 || blog.getTitle().trim().length() > 64 || blog.getBody().trim().length() == 0)
			throw new InvalidBlogException();
		
		dao.post(blog);
	}

	@Override
	public Blog updateBlog(Blog blog) {
		Blog blogOld= dao.read(blog.getId());
		
		if(blogOld == null)
			throw new BlogNotFoundException();
		
		dao.deleteBlogByID(blogOld.getId());
		dao.post(blog);
		
		return blog;
	}

	@Override
	public Blog view(int blogId) {
		
		Blog blog= dao.read(blogId);
		if(blog == null)
			throw new BlogNotFoundException();
		
		return blog;
	}
	
	@Override
	public void postComment(Comment comment) {
		if(comment == null)
			throw new BlogNotFoundException();
		
		dao.addComment(comment);
		
	}
	
	@Override
	public List<Blog> viewAll() {
		List<Blog> blogs = dao.readAll();
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public void createUser(User user) {
		if(user == null || user.getPwd() == null || user.getMailid() == null || user.getUsername() == null)
			throw new InvalidUserException();
		
		dao.addUser(user);
	}
	
	@Override
	public List<Blog> findByCategory(String category) {
		List<Blog> blogs = dao.searchCriteria(category);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public List<Blog> findByUserName(String userName) {
		List<Blog> blogs = dao.searchByUsername(userName);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public List<Blog> findByTitle(String title) {
	List<Blog> blogs = dao.searchByTitle(title);
		
		if(blogs == null)
			throw new BlogNotFoundException();
		
		return blogs;
	}

	@Override
	public void deleteBlog(int blogId) {
		dao.deleteBlogByID(blogId);
	}

	@Override
	public List<Comment> gettheComments(int BlogID) {
		 return dao.getComments(BlogID);
	}

	@Override
	public void deleteComment(int commentID) {
		dao.deletComment(commentID);
		
	}
	
	
}
