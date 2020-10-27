package com.application.people;

//This will be auto implemented by Spring into a bean called userRepository
//CRUD means create, read, update, delete

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByid(Long id);
}
