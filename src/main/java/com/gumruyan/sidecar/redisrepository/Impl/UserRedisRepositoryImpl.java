package com.gumruyan.sidecar.redisrepository.Impl;

import com.gumruyan.sidecar.entity.User;
import com.gumruyan.sidecar.redisrepository.UserRedisRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRedisRepositoryImpl implements UserRedisRepository {

    private final RedisTemplate redisTemplate;

    public UserRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(User user) {
        redisTemplate.opsForValue().set("user:" + user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return (User) redisTemplate.opsForValue().get("user:"+username);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) redisTemplate.opsForValue().get("user:");
    }

}
