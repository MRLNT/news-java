package id.fazzbca.news.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry){
        CorsRegistration corsRegistration = registry.addMapping("/**");
        corsRegistration.allowedMethods("*");
        // tambahkan url end point client disini
        corsRegistration.allowedOriginPatterns("*");
    }
}
