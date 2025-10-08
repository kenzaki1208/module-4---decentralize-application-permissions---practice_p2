package org.example.demospringsecurityjsonwebtoken.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.example.demospringsecurityjsonwebtoken")
public class AppConfiguration implements WebMvcConfigurer {
}