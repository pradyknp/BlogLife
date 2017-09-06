package blog.biz;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import blog.api.BlogAction;
import blog.api.AuthToken;

public class AuthUtil {

	private static final String ISSUER = "blogLife";
	private static final String SECRET = "blogLife-secret";

	public static String issueToken(String userName) {
		Algorithm algorithmHS;
		try {
			algorithmHS = Algorithm.HMAC256(SECRET);
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		Date currentTime = Calendar.getInstance().getTime();
		Date expiryTime = Calendar.getInstance().getTime();
		int timeOutSec = 15 * 60 * 1000;
		expiryTime.setTime(currentTime.getTime() + timeOutSec);
		String token = JWT.create().withIssuer(ISSUER).withIssuedAt(currentTime).withExpiresAt(expiryTime)
				.withClaim("user", userName).sign(algorithmHS);
		return token;
	}

	public static boolean verifyToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).acceptIssuedAt(0).acceptExpiresAt(0)
					.build();
			DecodedJWT decodedToken = verifier.verify(token);
			Claim userNameClaim = decodedToken.getClaim("user");
			if (userNameClaim != null && userNameClaim.asString() != null) {
				String userName = userNameClaim.asString();
				AuthToken tokenFromDb = getTokenFromDb(userName);
				if (tokenFromDb!=null && tokenFromDb.getToken().equals(token)) {
					return true;
				}
			}
		} catch (IllegalArgumentException | UnsupportedEncodingException | JWTVerificationException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	private static AuthToken getTokenFromDb(String userName) {
		BlogAction blogAction = new BlogActionImpl();
		return blogAction.getTokenDAO().get(userName);
	}
}
