package com.qa.CVManager.Persistence.Respository;

import org.springframework.data.repository.CrudRepository;

import com.qa.CVManager.Persistence.Domain.User;

public interface UserRepository extends CrudRepository<User, String>{
}
