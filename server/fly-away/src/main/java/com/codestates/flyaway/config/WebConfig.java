package com.codestates.flyaway.config;

import com.codestates.flyaway.web.login.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(false);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/record/**", "/board/**", "/members/**", "/logout")  // 미인증 사용자의 회원,게시판 기능을 모두 막고, 그 중 회원가입, 게시글 전체 목록만 허용
                .excludePathPatterns("/members/join", "/board/all");
    }
}
