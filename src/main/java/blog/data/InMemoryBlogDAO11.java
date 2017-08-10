//package blog.data;
//import java.util.HashMap;
//import java.util.Map;
//
//import blog.api.Blog;
//import blog.api.Comment;
//
//
//
//public class InMemoryBlogDAO11 implements BlogDAO {
//
//	Map<Integer, Blog> stock = new HashMap<>();
//	Map<Integer,Comment> commentMap = new HashMap<>();
//
//	@Override
//	public void post(Blog blog) {
//		stock.put(blog.getId(), blog);
//	}
//
//	@Override
//	public Blog read(int id) {
//		return stock.get(id);
//	}
//	
//	@Override
//	public void addComment(Comment comment) {
//		commentMap.put(comment.getId(), comment);
//	}
//
//}
