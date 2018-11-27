package com.qa.CVManager.Persistence.Respository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.CVManager.Persistence.Domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUserName(String userName);
	Iterable<User> findByAccountType(String accountType);
}
