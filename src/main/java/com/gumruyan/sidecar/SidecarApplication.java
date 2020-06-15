package com.gumruyan.sidecar;

import com.gumruyan.sidecar.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@EnableCaching
@SpringBootApplication
@ComponentScan("com.gumruyan")
public class SidecarApplication {


    public static void main(String[] args) {
        SpringApplication.run(SidecarApplication.class, args);
    }

}

@Component
class Runner {
    @Autowired
    private RedisTemplate redisTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void setDataIntoRedis(){
        redisTemplate.opsForValue().set("user:test", new User("test","test"));
    }

}
