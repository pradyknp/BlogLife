package blog.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BlogExceptionMapper implements ExceptionMapper<BlogException>{

	@Override
	public Response toResponse(BlogException blogExp) {
		 if (blogExp instanceof BlogNotFoundException){
			 return Response.status(404).build();
		 }
		 else if (blogExp instanceof InvalidBlogException){
			 return Response.status(405).build();
		 }
		 else if (blogExp instanceof DuplicateBlogException){
			 return Response.status(406).build();
		 }
		 else if (blogExp instanceof CommentNotFoundException){
			 return Response.status(404).build();
		 }
		 else if (blogExp instanceof InvalidCommentException){
			 return Response.status(406).build();
		 }
		 else if (blogExp instanceof CommentException){
			 return Response.status(500).build();
		 }
		 else if (blogExp instanceof UserAlreadyExistsException){
			 return Response.status(400).build();
		 }
		 else if (blogExp instanceof UserNotFoundException){
			 return Response.status(404).build();
		 }
		 else if (blogExp instanceof UserException){
			 return Response.status(500).build();
		 }
		 else if (blogExp instanceof InvalidCommentException){
			 return Response.status(405).build();
		 }
		 else if (blogExp instanceof CommentNotFoundException ){
			 return Response.status(404).build();
		 }
		 else if (blogExp instanceof CommentException){
			 return Response.status(500).build();
		 }
		 else
			 return Response.status(500).build();
	}
}

