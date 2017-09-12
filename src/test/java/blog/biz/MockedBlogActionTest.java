package blog.biz;

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
	public void testUpdateBlog() {
		try {
			Mockito.when(blog.getId()).thenReturn(1);
			Mockito.when(blogDao.getBlog(1)).thenReturn(oldBlog);
			blogActionService.updateBlog(blog);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

}