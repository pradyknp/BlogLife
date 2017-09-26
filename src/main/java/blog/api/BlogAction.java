package blog.api;

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
import blog.data.TokenDao;

import java.util.List;
import blog.api.User;

public interface BlogAction {
	void post(Blog blog) throws DuplicateBlogException,InvalidBlogException, BlogException;
	
	Blog updateBlog(Blog blog) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog view(int blogId) throws DuplicateBlogException,InvalidBlogException,BlogException;
	
	List<Blog> viewAll(int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Blog> findByCategory(String category,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Blog> findByUserName(String userName,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Blog> findByTitle(String title,int pageno,int pagesize) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog blogLike(int blogId) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog blogUnLike(int blogId) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog blogDisLike(int blogId) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog blogUnDisLike(int blogId) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	String deleteBlog(int blogId) throws BlogNotFoundException,BlogException;
	
	void postComment(Comment comment) throws InvalidCommentException,CommentException;
	
	List<Comment> gettheComments(int BlogID) throws CommentNotFoundException,CommentException;
	
	void deleteComment(int commentID) throws CommentNotFoundException,CommentException;
	
	void createUser(User user) throws UserAlreadyExistsException,InvalidUserException,UserException;
	
	List<User> getAllUsers() throws UserNotFoundException,UserException;
	
	User getuserByUserName(String username) throws UserNotFoundException,UserException;
	
	void deleteUser(String username) throws UserNotFoundException,UserException;
	
	long totalCount(String category,String username) throws BlogNotFoundException,BlogException;

	TokenDao getTokenDAO();
	
	boolean deleteTokenByUser(AuthToken username);
	
	String issueAndStoreToken(String userName);

	boolean authenticateUser(User user) throws UserNotFoundException, UserException;
	
	long totalCountBytitle(String title)  throws BlogNotFoundException,BlogException;
}
