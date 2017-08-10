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
	void postComment(Comment comment);
	
	List<Blog> viewAll();
	List<Blog> findByCategory(String category);
	void createUser(User user) throws UserAlreadyExistsException,InvalidUserException,UserException;
	
//	void delete(int blogId);
//	List<Blog> findByUserName(String userName);
	
//	List<Blog> search(String searchText);
}
