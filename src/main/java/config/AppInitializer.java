package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfigs.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfigs.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
