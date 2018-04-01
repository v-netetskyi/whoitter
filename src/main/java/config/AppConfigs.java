package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repo.InMemoryPostRepository;
import repo.PostRepository;

@Configuration
public class AppConfigs {

    @Bean
    public PostRepository inmemoryPostRepository(){
        return new InMemoryPostRepository();
    }
}
