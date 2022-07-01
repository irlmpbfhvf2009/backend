package com.lwdevelop.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.lwdevelop.backend.service.MemberUserDetailsService;


@Configuration
@EnableWebSecurity	// 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 启用方法级别的权限认证
public class SecurityConfig {

/*     @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } */

    @Bean
    PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                .antMatchers("/").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/admin/login").permitAll()
                .anyRequest().authenticated())
                .userDetailsService(memberUserDetailsService)
                /* .addFilterBefore(JwtAuthFilter (), UsernamePasswordAuthenticationFilter.class) */
                .build();
    }
/*     @Bean
    JwtAuthFilter  JwtAuthFilter () {
        return new JwtAuthFilter ();
    } */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}