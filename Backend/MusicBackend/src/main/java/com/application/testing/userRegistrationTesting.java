package com.application.testing;




import com.application.people.User;
import com.application.people.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userRegistrationTesting {

    @Autowired
    private UserTestingService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void registerNewUserTest(){
        User user = new User("test", "test@test.com", "password", 1);
        Mockito.when(repository.save(user)).thenReturn(user);
        assertEquals("Saved", service.addUser(user));
    }

    @Test
    public void getAllUsersTest(){
        Mockito.when(repository.findAll()).thenReturn(Stream.of
                (new User("test", "test@test.com", "password", 1),
                        new User("test1", "test1@test.com", "password", 1)).collect(Collectors.toList()));
        assertEquals(2, service.getAllUsers().size());
    }

    @Test
    public void getUserByUsernameTest(){
        User user = new User("test", "test@test.com", "password", 1);
        String username = "test";
        Mockito.when(repository.findByUsername(username)).thenReturn(user);
        assertEquals(user, service.getUserByUsername(username));
    }

    @Test
    public void deleteUserTest() {
        User user = new User("test", "test@test.com", "password", 1);
        service.deleteUser(user);
        verify(repository, times(1)).delete(user);
    }






}

