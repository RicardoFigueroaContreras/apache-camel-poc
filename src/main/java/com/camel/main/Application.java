package com.camel.main;

import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude={CamelAutoConfiguration.class})
@ComponentScan(basePackages = "com.camel")
@ImportResource({"classpath:/META-INF/spring/camel-context.xml"})
public class Application extends SpringBootServletInitializer{

    private static final String RESTLET_URL_MAPPING = "/rs/*";
    private static final String RESTLET_SERVLET_NAME = "RestletServlet";

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public ServletRegistrationBean servletRegistrationBean1() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new SpringServerServlet(), RESTLET_URL_MAPPING);
        registration.setName(RESTLET_SERVLET_NAME);
        registration.addInitParameter("org.restlet.component", "RestletComponent");
        return registration;
    }
}
