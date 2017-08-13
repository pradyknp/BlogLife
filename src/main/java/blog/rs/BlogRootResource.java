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

@Path("/blog")
public class BlogRootResource {
	static BlogAction blogAction = new BlogActionImpl();

	@GET
	@Path("/{blogId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find(@PathParam("blogId") int id) {
		Blog blog = blogAction.view(id);
		return Response.ok().entity(blog).build();
	}

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		List<Blog> blog = blogAction.viewAll();
		return Response.ok().entity(blog).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response add(Blog blog) {
		blogAction.post(blog);
		return Response.ok().entity(blog).header("location", "/blog/" + blog.getId()).build();
	}

	@POST
	@Path("/postComment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addComment(Comment comment) {
		try {
			blogAction.postComment(comment);
			return Response.ok().entity(comment).build();
		} catch (InvalidBlogException ibe) {
			return Response.status(405).build();
		} catch (DuplicateBlogException dbe) {
			return Response.status(406).build();
		} catch (BlogException le) {
			return Response.status(500).build();
		}
	}

	@POST
	@Path("/createUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNewUser(User user) {
		try {
			blogAction.createUser(user);
			return Response.ok().entity(user).build();
		} catch (InvalidUserException ibe) {
			return Response.status(405).build();
		} catch (UserAlreadyExistsException dbe) {
			return Response.status(406).build();
		} catch (UserException le) {
			return Response.status(500).build();
		}
	}

	@GET
	@Path("/category")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByCriteria(@QueryParam("search") String search) {
		try {
			List<Blog> blog = blogAction.findByCategory(search);
			return Response.ok().entity(blog).build();
		} catch (BlogNotFoundException bnfe) {
			return Response.status(404).build();
		} catch (BlogException le) {
			return Response.status(500).build();
		}
	}
}
