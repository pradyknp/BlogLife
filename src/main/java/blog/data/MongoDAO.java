package blog.data;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import blog.api.Blog;
import blog.api.Comment;
import blog.api.User;

public class MongoDAO implements IBlogViewDAO{
		MongoClient mongoClient = new MongoClient("localhost"); //27017
		Morphia morphia = new Morphia();
		String databaseName = "blogview";
		Datastore datastore = morphia.createDatastore(mongoClient, databaseName);
		BlogDAO dao = new BlogDAO(Blog.class, datastore);

		
//		BookDAO dao = new BookDAO(Book.class, datastore);
//
//		dao.save(new Book(2341, "mongo"));
//
//		System.out.println(dao.get(123.6));
//		System.out.println(dao.findByTitle("mongo"));
//		System.out.println(dao.find().asList());


	@Override
	public void post(Blog blog) {
		dao.save(blog);
	}

	@Override
	public Blog read(int id) {
		Blog blog = (Blog)dao.findByTitle(String.valueOf(id));
		return blog;
	}

	@Override
	public List<Blog> readAll() {
		BlogDAO dao = new BlogDAO(Blog.class, datastore);
		List<Blog> bloglist = dao.find().asList();
		return bloglist;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Blog> searchCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
