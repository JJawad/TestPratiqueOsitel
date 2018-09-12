package api.ositel.MessageBody;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

@Provider
public class JSONObjectMessageBodyReader implements MessageBodyReader<JSONObject> {
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == JSONObject.class && mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public JSONObject readFrom(Class<JSONObject> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        try { 
            // Using Apache Commons IO:
            String body = IOUtils.toString(entityStream, "UTF-8");
            return new JSONObject(body);
        } catch(JSONException e) {
            throw new BadRequestException("Invalid JSON", e);
        }
    }
}