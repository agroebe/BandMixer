package com.application.testing;

import com.application.people.User;
import com.application.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTestingService{

    @Autowired
    private UserRepository repository;

    public String addUser(User user){
        if(repository.findByUsername(user.getName()) != null){
            return "username is already taken";
        }else if(repository.findByEmail(user.getEmail())!= null){
            return "email is already taken";
        }else{
            repository.save(user);
            return "Saved";
        }
    }

    public void deleteUser(User user){
        repository.delete(user);
    }

    public List<User> getAllUsers(){
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    public User getUserByUsername(String username){
       return repository.findByUsername(username);
    }


}
