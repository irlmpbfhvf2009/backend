package com.lwdevelop.backend;

import org.springframework.context.annotation.Bean;

public class LocalSessionFactoryBean {
    @Bean(name="entityManagerFactory")
public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    return sessionFactory;
} 
}
