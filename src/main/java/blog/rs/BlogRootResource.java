package blog.rs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import blog.api.Blog;
import blog.api.BlogAction;
import blog.api.Comment;
import blog.api.User;
import blog.api.exception.BlogException;
import blog.api.exception.BlogNotFoundException;
import blog.api.exception.DuplicateBlogException;
import blog.api.exception.InvalidBlogException;
import blog.api.exception.InvalidUserException;
import blog.api.exception.UserAlreadyExistsException;
import blog.api.exception.UserException;
import blog.biz.BlogActionImpl;
import java.util.List;

@Path("")
public class BlogRootResource {
	static BlogAction blogAction = new BlogActionImpl();

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
	public Response findAll(@QueryParam("pageno") int pageno,@QueryParam("pagesize") int pagesize) {
		List<Blog> blog = blogAction.viewAll(pageno,pagesize);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("/blog")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addBlog(Blog blog) {
		blogAction.post(blog);
		return Response.ok().entity(blog).header("location", "/blog/" + blog.getId()).build();
	}

	@POST
	@Path("/blog/updateBlog")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
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

	@POST
	@Path("/createUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNewUser(User user) {
			blogAction.createUser(user);
			return Response.ok().entity(user).build();
	}

	@GET
	@Path("/blog/category/{category}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByCriteria(@PathParam("category") String category,@QueryParam("pageno") int pageno,@QueryParam("pagesize") int pagesize) {
			List<Blog> blog = blogAction.findByCategory(category,pageno,pagesize);
			return Response.ok().entity(blog).build();
	}
	
	@GET
	@Path("/blog/user/{username}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByUser(@PathParam("username") String username,@QueryParam("pageno") int pageno,@QueryParam("pagesize") int pagesize) {
			List<Blog> blog = blogAction.findByUserName(username,pageno,pagesize);
			return Response.ok().entity(blog).build();
	}
	
	@GET
	@Path("/blog/title")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByTitle(@QueryParam("search") String search,@QueryParam("pageno") int pageno,@QueryParam("pagesize") int pagesize) {
			List<Blog> blog = blogAction.findByTitle(search,pageno,pagesize);
			return Response.ok().entity(blog).build();
	}
	
	@GET
	@Path("/blog/delete/{blogId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteBlog(@PathParam("blogId") int blogId) {
		blogAction.deleteBlog(blogId);
		return Response.ok().build();
	}
	
	@GET
	@Path("/blog/getComments")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCommentforBlog(@QueryParam("blogId") int blogId) {
		List<Comment> comment= blogAction.gettheComments(blogId);
		return Response.ok().entity(comment).build();
	}
	
	@GET
	@Path("/users/getAll")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllUsers() {
		List<User> user= blogAction.getAllUsers();
		return Response.ok().entity(user).build();
	}
	
	@GET
	@Path("/user/{user}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUser(@PathParam("user") String username) {
		User user= blogAction.getuserByUserName(username);
		return Response.ok().entity(user).build();
	}
	
	@GET
	@Path("/user/delete/{user}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deletetUser(@PathParam("user") String username) {
		blogAction.deleteUser(username);
		return Response.ok().build();
	}
	
	@POST
	@Path("blog/{blogId}/like")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addLike(@PathParam("blogId") int blogId) {
			Blog blog = blogAction.blogLike(blogId);
			return Response.ok().entity(blog).build();
	}
	
	@POST
	@Path("blog/{blogId}/unlike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addUnLike(@PathParam("blogId") int blogId) {
			Blog blog = blogAction.blogUnLike(blogId);
			return Response.ok().entity(blog).build();
	}
	
	@POST
	@Path("blog/{blogId}/dislike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addDisLike(@PathParam("blogId") int blogId) {
			Blog blog = blogAction.blogDisLike(blogId);
			return Response.ok().entity(blog).build();
	}
	
	@POST
	@Path("blog/{blogId}/undislike")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addUnDisLike(@PathParam("blogId") int blogId) {
			Blog blog = blogAction.blogUnDisLike(blogId);
			return Response.ok().entity(blog).build();
	}
}
