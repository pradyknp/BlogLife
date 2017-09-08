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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.google.gson.Gson;

import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.User;
import blog.api.AuthToken;
import blog.biz.BlogActionImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Path("")
public class BlogRootResource {
	static BlogAction blogAction = new BlogActionImpl();

	/*@OPTIONS
	@Path("{path : .*}")
	public Response options() {
		return Response.ok("").header("Access-Control-Allow-Origin", "*")
				
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				 .header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600").build();
	}*/

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
	public Response blogCount(@QueryParam("category") String category,@QueryParam("username") String username) {
		long blogCount = blogAction.totalCount(category,username);
		return Response.ok().entity(blogCount).build();
	}

	@POST
	@Path("/blog")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addBlog(String inputBlog) {
		Gson g = new Gson();
		Blog blog = g.fromJson(inputBlog, Blog.class);
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
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addComment(String inputcomment) {
		Gson g = new Gson();
		Comment comment = g.fromJson(inputcomment, Comment.class);
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

		
	@DELETE
	@Path("/comment/{commentId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response deleteComment(@PathParam("commentId") int commentId) {
		blogAction.deleteComment(commentId);
		return Response.ok().entity(commentId).build();
	}
	
	
	@POST
	@Path("blog/{blogId}/like")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/unlike")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addUnLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogUnLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/dislike")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addDisLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogDisLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("blog/{blogId}/undislike")
	@Produces({ MediaType.APPLICATION_JSON })
	@JWTTokenNeeded
	public Response addUnDisLike(@PathParam("blogId") int blogId) {
		Blog blog = blogAction.blogUnDisLike(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("/createUser")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNewUser(String inputUser) {
		Gson g = new Gson();
		User user = g.fromJson(inputUser, User.class);
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
	@Path("/login")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({ MediaType.TEXT_PLAIN })
	public Response loginUser(String inputUser) throws IllegalArgumentException, UnsupportedEncodingException {
		Gson g = new Gson();
		User user = g.fromJson(inputUser,User.class);				
		if (blogAction.authenticateUser(user)) {
			String result = "{\"token\":\"Bearer " + blogAction.issueAndStoreToken(user.getUsername()) + "\"}";
			return Response.ok().entity(result).build();
		} else {
			return Response.status(401).entity("Authentication Failed.").build();
		}
	}

	@GET
	@Path("/user/verifyToken")
	public Response verifyJWTToken(@QueryParam("token") String token)
			throws IllegalArgumentException, UnsupportedEncodingException {
		if (blog.biz.AuthUtil.verifyToken(token)) {
			return Response.ok().build();
		}
		return Response.status(401).entity("Verification failed.").build();
	}

	@POST
	@Path("/user/logout")
	@JWTTokenNeeded
	@Consumes(MediaType.TEXT_PLAIN)
	public Response logoutUser(String inputUser) {
		Gson g = new Gson();
		AuthToken token = g.fromJson(inputUser,AuthToken.class);	
		blogAction.deleteTokenByUser(token);
		return Response.ok().build();
	}

	@DELETE
	@Path("/user/{user}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteUser(@PathParam("user") String username) {
		blogAction.deleteUser(username);
		return Response.ok().build();
	}


}
