package eci.ieti.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import eci.ieti.data.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String name); 

    

}