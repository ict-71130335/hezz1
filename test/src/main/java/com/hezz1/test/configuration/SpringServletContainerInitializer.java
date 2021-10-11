package com.hezz1.test.configuration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.rmi.server.ServerCloneException;

public class SpringServletContainerInitializer extends SpringContextConfig {
    public void onStartup(ServletContext servletContext)  throws ServerCloneException{
        AnnotationConfigWebApplicationContext context= new AnnotationConfigWebApplicationContext();
        context.scan("com.hezz1.springmvc");
        DispatcherServlet dispatcherServlet= new DispatcherServlet();
        ServletRegistration.Dynamic app=servletContext.addServlet("app",dispatcherServlet);
        app.setLoadOnStartup(1);
        app.addMapping("/");
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("utf-8"));
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }

}
