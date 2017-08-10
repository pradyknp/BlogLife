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

public class BlogTest {
	private Blog toTest = new Blog();
	
	@Test
	public void testGetSetBlogId() throws Exception {
		int blogId = 1234;
		toTest.setId(blogId);
		assertEquals(toTest.getId(), blogId);

	}

	@Test
	public void testGetSetBlogTitle() throws Exception {
		String title = "Blogging Starts";
		toTest.setTitle(title);
		assertEquals(toTest.getTitle(), title);

	}
	
	@Test
	public void testGetSetBlogBody() throws Exception {
		String body = "Happy blogging";
		toTest.setBody(body);
		assertEquals(toTest.getBody(), body);

	}
	
	@Test
	public void testGetSetCreatedDate() throws Exception {
		Date date = new Date(0);
		toTest.setCreatedDate(date);
		assertEquals(toTest.getCreatedDate(), date);

	}
	
	@Test
	public void testGetSetModifiedDate() throws Exception {
		Date date = new Date(10);
		toTest.setModifiedDate(date);
		assertEquals(toTest.getModifiedDate(), date);

	}
	
	@Test
	public void testGetSetBlogUserName() throws Exception {
		String userName = "Mohan";
		toTest.setUsername(userName);
		assertEquals(toTest.getUsername(), userName);

	}
	
	@Test
	public void testGetSetBlogCategory() throws Exception {
		String category = "Sports";
		toTest.setCategory(category);
		assertEquals(toTest.getCategory(), category);

	}
	
}
