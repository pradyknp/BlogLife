package blog.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.User;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import blog.biz.BlogActionImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jwt.utils.JWTTokenNeeded;
import jwt.utils.KeyGenerator;
import jwt.utils.SimpleKeyGenerator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Path("")
public class BlogRootResource {
	private KeyGenerator keyGenerator = new SimpleKeyGenerator();
	
	@Context
	private UriInfo uriInfo;

	static BlogAction blogAction = new BlogActionImpl();

	@OPTIONS
	@Path("{path : .*}")
	public Response options() {
		return Response.ok("").header("Access-Control-Allow-Origin", "*")
				/*
				 * .header("Access-Control-Allow-Headers",
				 * "origin, content-type, accept, authorization")
				 * .header("Access-Control-Allow-Credentials", "true")
				 */
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600").build();
	}

	@GET
	@Path("/blog/{blogId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find(@PathParam("blogId") int id) {
		Blog blog = blogAction.view(id);
		return Response.ok().entity(blog).build();
	}

	@GET
	@Path("/blog/getAll")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll(@QueryParam("pageno") int pageno, @QueryParam("pagesize") int pagesize) {
		List<Blog> blog = blogAction.viewAll(pageno, pagesize);
		return Response.ok().entity(blog).build();
	}

	@GET
	@Path("/blog/blogCount")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response blogCount(@QueryParam("category") String category) {
		long blogCount = blogAction.totalCount(category);
		return Response.ok().entity(blogCount).build();
	}

	@POST
	@Path("/blog")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addBlog(Blog blog) {
		blogAction.post(blog);
		return Response.ok().entity(blog).header("location", "/blog/" + blog.getId()).build();
	}

	@POST
	@Path("/blog/updateBlog")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response updateBlog(Blog blog) {
		blogAction.updateBlog(blog);
		return Response.ok().entity(blog).header("location", "/blog/" + blog.getId()).build();
	}

	@POST
	@Path("/blog/postComment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addComment(Comment comment) {
		blogAction.postComment(comment);
		return Response.ok().entity(comment).build();
	}

	@GET
	@Path("/blog/category")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByCriteria(@QueryParam("search") String category, @QueryParam("pageno") int pageno,
			@QueryParam("pagesize") int pagesize) {
		List<Blog> blog = blogAction.findByCategory(category, pageno, pagesize);
		return Response.ok().entity(blog).build();
	}

	@GET
	@Path("/blog/user/{username}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByUser(@PathParam("username") String username, @QueryParam("pageno") int pageno,
			@QueryParam("pagesize") int pagesize) {
		List<Blog> blog = blogAction.findByUserName(username, pageno, pagesize);
		return Response.ok().entity(blog).build();
	}

	@GET
	@Path("/blog/title")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByTitle(@QueryParam("search") String search, @QueryParam("pageno") int pageno,
			@QueryParam("pagesize") int pagesize) {
		List<Blog> blog = blogAction.findByTitle(search, pageno, pagesize);
		return Response.ok().entity(blog).build();
	}

	@DELETE
	@Path("/blog/{blogId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response deleteBlog(@PathParam("blogId") int blogId) {
		blogAction.deleteBlog(blogId);
		return Response.ok().entity(blogId).build();
	}

	@GET
	@Path("/blog/getComments")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCommentforBlog(@QueryParam("blogId") int blogId) {
		List<Comment> comment = blogAction.gettheComments(blogId);
		return Response.ok().entity(comment).build();
	}

	@POST
	@Path("blog/{blogId}/like")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/unlike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addUnLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogUnLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/dislike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addDisLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogDisLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/undislike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addUnDisLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogUnDisLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("/createUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNewUser(User user) {
		blogAction.createUser(user);
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("/user")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllUsers() {
		List<User> user = blogAction.getAllUsers();
		return Response.ok().entity(user).build();
	}

	@GET
	@Path("/user/{user}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUser(@PathParam("user") String username) {
		User user = blogAction.getuserByUserName(username);
		return Response.ok().entity(user).build();
	}

	@POST
	@Path("/user/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response authenticateUser(User inputUser) {
		try {
			User user = blogAction.getuserByUserName(inputUser.getUsername());

			if (!user.getPwd().equals(inputUser.getPwd())) {
				return Response.status(UNAUTHORIZED).build();
			}
			// Issue a token for the user
			String jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuer(uriInfo.getAbsolutePath().toString())
					.setIssuedAt(new Date())
					.setExpiration(
							Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
					.signWith(SignatureAlgorithm.HS512, keyGenerator.generateKey()).compact();

			// Return the token on the response
			return Response.ok().header(AUTHORIZATION, "Bearer " + jwtToken).build();

		} catch (Exception e) {
			return Response.status(UNAUTHORIZED).build();
		}
	}

	@DELETE
	@Path("/user/{user}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteUser(@PathParam("user") String username) {
		blogAction.deleteUser(username);
		return Response.ok().build();
	}


}
