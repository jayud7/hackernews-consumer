package com.jd.qp.storyapp;

import com.jd.qp.storyapp.service.HackerNewsConsumerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class HackerNewsConsumerServiceTest {
    @Bean
    @Primary
    public HackerNewsConsumerService productService() {
        return Mockito.mock(HackerNewsConsumerService.class);
    }
}