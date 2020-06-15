package com.gumruyan.sidecar.service;

import com.gumruyan.sidecar.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails findByUsername(String username);
    User findOne(String username);
    List<User> findAll();
    void save(User user);
}
