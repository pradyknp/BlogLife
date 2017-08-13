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
		 if (blogExp instanceof InvalidBlogException){
			 return Response.status(405).build();
		 }
		 if (blogExp instanceof DuplicateBlogException){
			 return Response.status(406).build();
		 }
		 return Response.status(500).build();
	}
}

