package com.example.MySQLExperiment;

//This will be auto implemented by Spring into a bean called userRepository
//CRUD means create, read, update, delete

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
