
package blog.data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import blog.api.User;

public class UserDAO extends BasicDAO<User, String> {

	public UserDAO(Class<User> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
}