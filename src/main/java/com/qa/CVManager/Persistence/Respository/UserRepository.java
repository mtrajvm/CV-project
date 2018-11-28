package com.qa.CVManager.Persistence.Respository;
import com.qa.CVManager.Constants.MongoDBRepoQueries;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.qa.CVManager.Persistence.Domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUserName(String userName);
	Iterable<User> findByAccountType(String accountType);
	
	@Query(MongoDBRepoQueries.SALES_QUERY_GET_TRAINEE_WITH_CVFILEFLAG_GREEN_OR_AMBER_IN_ANY_CV)
	Iterable<User> findByAccountTypeAndCVFileFlag(String accountType, 
													String fileFlag1, String fileFlag2,
													String fileFlag3, String fileFlag4,
													String fileFlag5, String fileFlag6);
}
