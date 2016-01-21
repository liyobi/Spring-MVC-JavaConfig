package com.hello.config.web;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import com.hello.config.mvc.SpringMvcConfig;

/**
 * web.xml
 *
 */
public class Initializer implements WebApplicationInitializer
{

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        this.addDispatcherServlet(servletContext);
        this.addUtf8CharacterEncodingFilter(servletContext);
    }
 
    /**
     * Dispatcher Servlet 
     * @param servletContext
     */
    private void addDispatcherServlet(ServletContext servletContext)
    {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //profile example
        //applicationContext.getEnvironment().addActiveProfile("production");
        applicationContext.register(SpringMvcConfig.class);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter("dispatchOptionsRequest", "true"); // CORS 를 위해서 option request 도 받아들인다.
    }
 
    /**
     * UTF-8 encoding
     * @param servletContext
     */
    private void addUtf8CharacterEncodingFilter(ServletContext servletContext)
    {
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }
}