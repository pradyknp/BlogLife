package blog.api;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.junit.Test;

public class CommentTest {
	private Comment toTest = new Comment();
	
	@Test
	public void testGetSetCommentId() throws Exception {
		int blogId = 1234;
		toTest.setId(blogId);
		assertEquals(toTest.getId(), blogId);

	}
	
	@Test
	public void testGetSetCommentBody() throws Exception {
		String body = "Happy blogging";
		toTest.setBody(body);
		assertEquals(toTest.getBody(), body);

	}
	
	@Test
	public void testGetSetUserName() throws Exception {
		String userName = "Mohan";
		toTest.setusername(userName);
		assertEquals(toTest.getusername(), userName);

	}
	
	@Test
	public void testGetSetCreatedDate() throws Exception {
		Date date = new Date(0);
		toTest.setCreatedDate(date.toString());
		assertEquals(toTest.getCreatedDate(), date.toString());

	}
	
	@Test
	public void testGetSetModifiedDate() throws Exception {
		Date date = new Date(10);
		toTest.setModifiedDate(date.toString());
		assertEquals(toTest.getModifiedDate(), date.toString());

	}
	
	@Test
	public void testGetSetBlogId() throws Exception {
		int blogId = 1234;
		toTest.setBlogId(blogId);
		assertEquals(toTest.getBlogId(), blogId);

	}
}
