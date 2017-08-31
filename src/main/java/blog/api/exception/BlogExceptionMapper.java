package blog.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BlogExceptionMapper implements ExceptionMapper<BlogException> {

	@Override
	public Response toResponse(BlogException blogExp) {
		Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
		if (blogExp instanceof BlogNotFoundException) {
			httpStatus = Response.Status.NOT_FOUND;
		} else if (blogExp instanceof InvalidBlogException) {
			httpStatus = Response.Status.NOT_ACCEPTABLE;
		} else if (blogExp instanceof DuplicateBlogException) {
			httpStatus = Response.Status.CONFLICT;
		} else if (blogExp instanceof CommentNotFoundException) {
			httpStatus = Response.Status.NOT_FOUND;
		} else if (blogExp instanceof InvalidCommentException) {
			httpStatus = Response.Status.NOT_ACCEPTABLE;
		} else if (blogExp instanceof UserAlreadyExistsException) {
			httpStatus = Response.Status.CONFLICT;
		} else if (blogExp instanceof UserNotFoundException) {
			httpStatus = Response.Status.NOT_FOUND;
		} else
			httpStatus = Response.Status.NOT_ACCEPTABLE;

		return Response.status(httpStatus).entity(blogExp.getMessage()).build();
	}
}
