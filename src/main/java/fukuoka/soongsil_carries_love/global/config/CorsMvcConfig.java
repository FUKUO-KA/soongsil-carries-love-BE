package fukuoka.soongsil_carries_love.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .allowedOrigins("*") // 모든 출처 허용
                .allowedMethods("*") // 모든 HTTP 메서드 허용
                .allowedHeaders("*"); // 모든 헤더 허용
    }
}
