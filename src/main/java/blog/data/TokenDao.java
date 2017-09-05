package blog.data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import blog.api.AuthToken;

public class TokenDao extends BasicDAO<AuthToken, String> {

	public TokenDao(Class<AuthToken> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
}
