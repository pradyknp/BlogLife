package blog.data;

import org.bson.Document;

import com.glarimy.cmad.essentials.production.demo.api.Book;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDAO implements DAO {

	@SuppressWarnings("resource")
	@Override
	public Book read(int pk) {
		MongoClient client = new MongoClient("localhost");
		MongoDatabase db = client.getDatabase("cmad");
		Document document = db.getCollection("books", Document.class).find(new Document("isbn", pk)).first();
		return new Book(pk, document.getString("title"));
	}

}
