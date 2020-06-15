package com.gumruyan.sidecar.redisrepository;

import com.gumruyan.sidecar.entity.User;

import java.util.List;

public interface UserRedisRepository {

    User findByUsername(String username);
    List<User> findAll();
    void save(User user);




}
