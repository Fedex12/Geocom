package app;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@ImportResource({"classpath:/META-INF/portal/context.xml"})
@EnableJpaRepositories(basePackages = {
        "*datasource*"
})
@EntityScan( basePackages = {"*recipe*"} )
public class Application extends SpringBootServletInitializer {
			
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	@ConditionalOnProperty(name = "server.ssl.enabled")
	public EmbeddedServletContainerFactory servletContainer() {
		
	  TomcatEmbeddedServletContainerFactory tomcat =
	      new TomcatEmbeddedServletContainerFactory() {

	        @Override
	        protected void postProcessContext(Context context) {
	          SecurityConstraint securityConstraint = new SecurityConstraint();
	          securityConstraint.setUserConstraint("CONFIDENTIAL");
	          SecurityCollection collection = new SecurityCollection();
	          collection.addPattern("/*");
	          securityConstraint.addCollection(collection);
	          context.addConstraint(securityConstraint);
	        }
	      };
	  tomcat.addAdditionalTomcatConnectors(createHttpConnector());
	  return tomcat;
	}

	@Value("${server.port2}")
	private int serverPortHttp;

	@Value("${server.port}")
	private int serverPortHttps;

	private Connector createHttpConnector() {
	  Connector connector =
	      new Connector("org.apache.coyote.http11.Http11NioProtocol");
	  connector.setScheme("http");
	  connector.setSecure(false);
	  connector.setPort(serverPortHttp);
	  connector.setRedirectPort(serverPortHttps);
	  return connector;
	}
	
}