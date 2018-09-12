package api.ositel.MessageBody;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import api.ositel.classe.Result;

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class FileMessageBodyWriter implements MessageBodyWriter<Result> {

	@Override
	public long getSize(Result arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return type == Result.class;
	}

	@Override
	public void writeTo(Result result, Class<?> type, Type type1, Annotation[] antns, MediaType mt,
			MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		
		out.write(result.toString().getBytes());
	}

}
