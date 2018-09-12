package api.ositel;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class MainApp {
	
	private final static int port = 8080;
	private final static String host="http://localhost/";

	public static void main(String[] args) {
		URI baseUri = UriBuilder.fromUri(host).port(port).build();
		ResourceConfig config = new ResourceConfig(OsitelExcelFileWebService.class);
		config.packages("api.ositel");
		JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.err.println("Server started !");
	}

}
