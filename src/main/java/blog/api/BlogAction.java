package blog.api;


import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import blog.api.exception.BlogException;
import blog.api.exception.BlogNotFoundException;
import blog.api.exception.DuplicateBlogException;
import blog.api.exception.InvalidBlogException;
import blog.api.exception.InvalidUserException;
import blog.api.exception.UserAlreadyExistsException;
import blog.api.exception.UserException;

import java.util.List;
import blog.api.User;

public interface BlogAction {
	void post(Blog blog) throws DuplicateBlogException,InvalidBlogException, BlogException;
	
	Blog update(Blog blog) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	Blog view(int blogId) throws DuplicateBlogException,InvalidBlogException,BlogException;
	
	void postComment(Comment comment) ;
	
	List<Blog> viewAll() throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Blog> findByCategory(String category) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	void createUser(User user) throws UserAlreadyExistsException,InvalidUserException,UserException;
	
	void deleteBlog(int blogId) throws BlogNotFoundException,BlogException;
	
	List<Blog> findByUserName(String userName) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Blog> findByTitle(String title) throws BlogNotFoundException,InvalidBlogException,BlogException;
	
	List<Comment> gettheComments(int BlogID);
}
