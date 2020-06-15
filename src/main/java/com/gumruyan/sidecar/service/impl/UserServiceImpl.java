package com.gumruyan.sidecar.service.impl;

import com.gumruyan.sidecar.model.UserAuthModel;
import com.gumruyan.sidecar.entity.User;
import com.gumruyan.sidecar.redisrepository.UserRedisRepository;
import com.gumruyan.sidecar.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRedisRepository userRedisRepository;

    public UserServiceImpl(UserRedisRepository userRedisRepository) {
        this.userRedisRepository = userRedisRepository;
    }

    @Override
    public List<User> findAll() {
        return userRedisRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRedisRepository.save(user);
    }

    @Override
    public UserDetails findByUsername(String username) {
        UserAuthModel userAuthModel = new UserAuthModel();
        User user = userRedisRepository.findByUsername(username);

        userAuthModel.setUsername(user.getUsername());
        userAuthModel.setPassword(user.getPassword());

        return userAuthModel;
    }

    @Override
    public User findOne(String username) {
        return userRedisRepository.findByUsername(username);
    }
}
