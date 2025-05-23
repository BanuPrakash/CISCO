package com.cisco.datarest.cfg;

import com.cisco.datarest.entity.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
       cors.addMapping("/products").allowedOrigins("http://domain2");

        ExposureConfiguration exposureConfiguration = config.getExposureConfiguration();
        exposureConfiguration.forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> {
                    return httpMethods.disable(HttpMethod.DELETE);
                });
    }
}
