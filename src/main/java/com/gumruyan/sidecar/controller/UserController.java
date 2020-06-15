package com.gumruyan.sidecar.controller;

import com.gumruyan.sidecar.entity.User;
import com.gumruyan.sidecar.redisrepository.UserRedisRepository;
import com.gumruyan.sidecar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        log.info("Creating user ...");
        userService.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        log.info("getting all users");
        return userService.findAll();
    }

    @GetMapping("/{username}")
    @ResponseBody
    public User getAllUsers(@PathVariable String username){
        log.info("getting user by username");
        return userService.findOne(username);
    }


}
