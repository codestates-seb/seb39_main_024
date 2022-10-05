package com.codestates.flyaway.config;

import com.codestates.flyaway.web.login.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "PATCH", "GET", "DELETE", "OPTIONS")
                .exposedHeaders("Set-Cookie")
                .exposedHeaders("Cookie")
                .exposedHeaders("memberId")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/record/**", "/board/**", "/members/**", "/logout")  // 미인증 사용자의 회원,게시판 기능을 모두 막고, 그 중 회원가입, 게시글 전체 목록만 허용
                .excludePathPatterns("/members/join", "/board/all");
    }
}
