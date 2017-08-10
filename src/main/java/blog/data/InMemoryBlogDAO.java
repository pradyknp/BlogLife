package blog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import blog.api.Blog;
import blog.api.Comment;
import blog.api.User;

import java.util.List;



public class InMemoryBlogDAO implements BlogDAO {

//	Map<Integer, Blog> stock = new HashMap<>();
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("trialblog");
	EntityManager stock = factory.createEntityManager();
	

	@Override
	public void post(Blog blog) {
		
		if(stock.getTransaction().isActive())
			stock.getTransaction().rollback();
		
		stock.getTransaction().begin();
		stock.persist(blog);
		stock.getTransaction().commit();
	}

	@Override
	public Blog read(int id) {
		return stock.find(Blog.class,id);
	}
	
	@Override
	public List<Blog> readAll() {
		if(stock.getTransaction().isActive())
			stock.getTransaction().rollback();
		
		stock.getTransaction().begin();
		return stock.createQuery( "from Blog", Blog.class ).getResultList();
	}
	
	@Override
	public void addComment(Comment comment) {
		
		if(stock.getTransaction().isActive())
			stock.getTransaction().rollback();
		
		stock.getTransaction().begin();
		stock.persist(comment);
		stock.getTransaction().commit();
	}
	
	@Override
	public void addUser(User user){
		if(stock.getTransaction().isActive())
			stock.getTransaction().rollback();
		
		stock.getTransaction().begin();
		stock.persist(user);
		stock.getTransaction().commit();
	}
	
	@Override
	public List<Blog> searchCriteria(String criteria){
		if(stock.getTransaction().isActive())
			stock.getTransaction().rollback();
		
		stock.getTransaction().begin();
		return stock.createQuery( "from Blog where category=\"Entertainment\"", Blog.class ).getResultList();
	}
}
