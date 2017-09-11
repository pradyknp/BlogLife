package blog.api;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mongodb.morphia.annotations.Id;

import blog.api.exception.BlogNotFoundException;
import blog.api.exception.InvalidBlogException;
import blog.api.exception.UserAlreadyExistsException;
import blog.api.exception.UserNotFoundException;
import blog.biz.BlogActionImpl;

public class BlogActionTest {
	private BlogActionImpl toTest = new BlogActionImpl();
	
	private Blog getTestBlog(int id, String title, String body, String username, String createdDate, String modifiedDate, String category) {
		Blog resultBlog = new Blog();
		resultBlog.setId(id);
		resultBlog.setTitle(title);
		resultBlog.setBody(body);
		resultBlog.setUsername(username);
		resultBlog.setCreatedDate(createdDate);
		resultBlog.setModifiedDate(modifiedDate);
		resultBlog.setCategory(category);
		return resultBlog;
	}
	
	private User getTestUser(String username, String mailid, String pwd, String tagLine) {
		User resultUser = new User();
		resultUser.setUsername(username);
		resultUser.setMailid(mailid);
		resultUser.setPwd(pwd);
		resultUser.setTagLine(tagLine);
		return resultUser;
	}
	
	private Comment getTestComment(int id, String title, String body, String username, String createdDate, String modifiedDate) {
		Comment resultComment = new Comment();
		resultComment.setBlogId(id);
		resultComment.setBody(body);
		resultComment.setusername(username);
		resultComment.setCreatedDate(createdDate);
		resultComment.setModifiedDate(modifiedDate);
		return resultComment;
	}
	/**
	 * Method to test get and post
	 * @throws Exception
	 */
	@Test
	public void testGetPost() throws Exception {
		Blog testBlog = getTestBlog(12, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		toTest.post(testBlog);
		assertEquals(toTest.view(12), testBlog);
		toTest.deleteBlog(12);

	}
	
	/**
	 * Method to test update post
	 * @throws Exception
	 */
	@Test
	public void testUpdatePost() throws Exception {
		Blog testBlog = getTestBlog(12, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		toTest.post(testBlog);
		testBlog.setBody("Testing update blogs");
		assertFalse(toTest.view(12).equals(testBlog));
		toTest.updateBlog(testBlog);
		assertEquals(toTest.view(12), testBlog);
		toTest.deleteBlog(12);

	}
	
	/**
	 * Method to test null post
	 * @throws Exception
	 */
	@Test(expected=BlogNotFoundException.class)
	public void testViewInvalidBlog() throws Exception {
		toTest.view(0xffff);
	}
	
	/**
	 * Method to test null post
	 * @throws Exception
	 */
	@Test(expected=InvalidBlogException.class)
	public void testNullPost() throws Exception {
		toTest.post(null);
	}
	
	/**
	 * Method to test update post
	 * @throws Exception
	 */
	@Test(expected=BlogNotFoundException.class)
	public void testUpdateNullBlog() throws Exception {
		Blog testBlog = getTestBlog(0x2345, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		toTest.updateBlog(testBlog);
	}
	
	/**
	 * Method to test all posts
	 * @throws Exception
	 */
	@Test
	public void testAllPost() throws Exception {
		List<Blog> blogs = toTest.viewAll(0, 5);
		for (Blog blog : blogs) {
			toTest.deleteBlog(blog.getId());
		}
		Blog testBlog1 = getTestBlog(1, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		Blog testBlog2 = getTestBlog(2, "testTitle2", "Testing all gets", "mohakum2", "14sept2017", "16sept2017", "Entertainment");
		toTest.post(testBlog1);
		toTest.post(testBlog2);
		assertEquals(2, toTest.viewAll(0, 0).size());
		assertEquals(1, toTest.totalCount("Entertainment"));

	}
	
	/**
	 * Method to test Create user
	 * @throws Exception
	 */
	@Test
	public void testCreateUser() throws Exception {
		User testUser = getTestUser("mohakum2", "mohakum2@cisco.com", "cisco", "Life goes as it tends to");
		toTest.deleteUser(testUser.getUsername());
		toTest.createUser(testUser);
		assertEquals(testUser, toTest.getuserByUserName(testUser.getUsername()));
		toTest.deleteUser(testUser.getUsername());
	}
	
	/**
	 * Method to test Create user
	 * @throws Exception
	 */
	@Test(expected= UserAlreadyExistsException.class)
	public void testUserExists() throws Exception {
		User testUser = getTestUser("mohakum2", "mohakum2@cisco.com", "cisco", "Life goes as it tends to");
		toTest.createUser(testUser);
		toTest.createUser(testUser);
		assertEquals(testUser, toTest.getuserByUserName(testUser.getUsername()));
		toTest.deleteUser(testUser.getUsername());
	}
	
	/**
	 * Method to test invalid user
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundException.class)
	public void testInvalidUser() throws Exception {
		toTest.getuserByUserName(null);
	}
	
	/**
	 * Method to test deletion and counting of users
	 * @throws Exception
	 */
	@Test
	public void testDeleteCountUser() throws Exception {
		int existingUsers = toTest.getAllUsers().size();
		User testUser = getTestUser("mohakum212", "mohakum2@cisco.com", "cisco", "Life goes as it tends to");
		toTest.deleteUser(testUser.getUsername());
		assertEquals(existingUsers, toTest.getAllUsers().size());
	}
	
	/**
	 * Method to test get, count, post and delete comments
	 * @throws Exception
	 */
	@Test
	public void testCommentOperations() throws Exception {
		Blog testBlog = getTestBlog(12, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		toTest.post(testBlog);
		assertEquals(toTest.view(12), testBlog);
		Comment comment = getTestComment(12, "Comment Title", "good one", "guna", "15sept2017", "15sept2017");
		toTest.postComment(comment);
		List<Comment> resultComment = toTest.gettheComments(12);
		assertEquals(1, resultComment.size());
		toTest.deleteComment(resultComment.get(0).getId());
		assertEquals(0, toTest.gettheComments(12).size());
		toTest.deleteBlog(12);

	}
	
	/**
	 * Method to test search
	 * @throws Exception
	 */
	@Test
	public void testSearch() throws Exception {
		Blog testBlog1 = getTestBlog(1, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		Blog testBlog2 = getTestBlog(2, "testTitle2", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Entertainment");
		toTest.post(testBlog1);
		toTest.post(testBlog2);
		List<Blog> resultBlogs = toTest.findByCategory("Comedy", 1, 5);
		assertEquals(1, resultBlogs.size());
		resultBlogs = toTest.findByTitle("testTitle2", 1, 1);
		assertEquals(1, resultBlogs.size());
		resultBlogs = toTest.findByUserName("mohakum2", 1, 2);
		assertEquals(2, resultBlogs.size());
		toTest.deleteBlog(1);
		toTest.deleteBlog(2);
	}
	
	/**
	 * Method to test search
	 * @throws Exception
	 */
	@Test
	public void testBlogsLikeDislike() throws Exception {
		Blog testBlog1 = getTestBlog(1, "testTitle1", "Testing get and post blogs", "mohakum2", "14sept2017", "15sept2017", "Comedy");
		toTest.post(testBlog1);
		toTest.blogLike(1);
		toTest.blogDisLike(1);
		List<Blog> resultBlogs = toTest.findByCategory("Comedy", 1, 5);
		assertEquals(1, resultBlogs.size());
		assertEquals(1, resultBlogs.get(0).getLikes());
		assertEquals(1, resultBlogs.get(0).getDislikes());
		
		toTest.blogUnLike(1);
		toTest.blogUnDisLike(1);
		resultBlogs = toTest.findByCategory("Comedy", 1, 5);
		assertEquals(0, resultBlogs.get(0).getLikes());
		assertEquals(0, resultBlogs.get(0).getDislikes());
		
		toTest.deleteBlog(1);
	}
}
