package blog.biz;



import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.exception.BlogNotFoundException;
import blog.api.exception.InvalidBlogException;
import blog.data.BlogDAO;
import blog.data.InMemoryBlogDAO;

public class BlogActionTest{
	
//	private BlogAction toTest;
//	private Blog blog;
//	private BlogDAO dao = new InMemoryBlogDAO();
//	
//    @Before
//    public void setUp() throws Exception {
//    	toTest = new BlogActionImpl();
//    	blog = createMock(Blog.class);
//    //	dao = createMock(BlogDAO.class);
//		expect(blog.getId()).andStubReturn(24);
//		expect(blog.getBody()).andStubReturn("New Blog done");
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//        verifyAll();
//    }
//    
//	@Test(expected = InvalidBlogException.class)
//	public void testPostNullBlog() throws Exception {
//        replayAll();
//		toTest.post(null);
//	}
//	
//	@Test(expected = InvalidBlogException.class)
//	public void postInvalidBlogId() throws Exception {
//		expect(blog.getId()).andReturn(0);
//        replayAll();
//		toTest.post(blog);
//	}
//
//	@Test(expected = InvalidBlogException.class)
//	public void postInvalidBlogTitle() throws Exception {
//		expect(blog.getTitle()).andReturn("").anyTimes();
//        replayAll();
//		toTest.post(blog);
//	}
//	
//	@Test(expected = InvalidBlogException.class)
//	public void postInvalidBlogBigTitle() throws Exception {
//		expect(blog.getTitle()).andReturn("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssssssssssssssssss").anyTimes();
//        replayAll();
//		toTest.post(blog);
//	}
//	
//	@Test(expected = InvalidBlogException.class)
//	public void postInvalidBlogBody() throws Exception {
//		expect(blog.getBody()).andReturn("");
//		expect(blog.getTitle()).andReturn("mmmm").anyTimes();
//        replayAll();
//		toTest.post(blog);
//	}
//
//	@Test(expected = BlogNotFoundException.class)
//	public void postInvalidComment() throws Exception {
//        replayAll();
//		toTest.postComment(null);
//	}
	
}
