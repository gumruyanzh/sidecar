package com.gumruyan.sidecar.service.impl;

import com.gumruyan.sidecar.entity.User;
import com.gumruyan.sidecar.redisrepository.UserRedisRepository;
import com.gumruyan.sidecar.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRedisRepository redisRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl(redisRepository);

    @Test
    public void testFindByUsername() {
        User user = new User("testUsername","testPassword");

        String username = "testUsername";
        Mockito.when(redisRepository.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userService.findByUsername(username);

        assertEquals(username, userDetails.getUsername());

    }

}