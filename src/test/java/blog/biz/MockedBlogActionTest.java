package blog.biz;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import blog.api.Blog;
import blog.biz.BlogActionImpl;
import blog.data.BlogDAO;

/**
 * CURD operations for Blog
 * 
 * Create
 * Update
 * Read
 * Delete
 * 
 * @author mohakum2
 */
@RunWith(MockitoJUnitRunner.class)
public class MockedBlogActionTest {

	@InjectMocks
	private BlogActionImpl blogActionService;

	@Mock
	private BlogDAO blogDao;

	@Mock
	private Blog blog, oldBlog;

	@Test
	public void testCreateBlog() {
		try {
			Mockito.doNothing().when(blogDao).create(blog);
			Mockito.when(blog.getId()).thenReturn(1);
			Mockito.when(blog.getTitle()).thenReturn("New");
			Mockito.when(blog.getBody()).thenReturn("New Body");
			blogActionService.post(blog);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testReadBlog() {
		try {
			Mockito.when(blogDao.getBlog(1)).thenReturn(blog);
			blogActionService.view(1);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testUpdateBlog() {
		try {
			Mockito.when(blog.getId()).thenReturn(1);
			Mockito.when(blogDao.getBlog(1)).thenReturn(oldBlog);
			blogActionService.updateBlog(blog);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	
	@Test
	public void testDeleteBlog() {
		try {
			Mockito.when(blogDao.deleteBlogUsingID(1)).thenReturn("Success");
			Mockito.when(blogDao.getBlog(1)).thenReturn(blog);
			blogActionService.deleteBlog(1);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testReadBlogByCategory() {
		try {
			List<Blog> blogs = new ArrayList<Blog>();
			blogs.add(new Blog());
			Mockito.when(blogDao.getCount("Comedy")).thenReturn((long) 1);
			Mockito.when(blogDao.searchByCriteria("Comedy", 0, 1)).thenReturn(blogs);
			List<Blog> actualBlogs = blogActionService.findByCategory("Comedy", 1, 1);
			Assert.assertEquals(blogs, actualBlogs);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
}