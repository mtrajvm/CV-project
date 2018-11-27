package com.qa.CVManager.Constants;

public class MongoDBRepoQueries {
	public static final String SALES_QUERY_GET_TRAINEE_WITH_CVFILEFLAG_GREEN_OR_AMBER_IN_ANY_CV = "{ 'accountType' : ?0, '$or': [{'$or':[ {'cvFile1.fileFlag': ?1 }, {'cvFile1.fileFlag': ?2} ]},{ '$or':[ {'cvFile2.fileFlag': ?3 }, {'cvFile2.fileFlag': ?4} ]},{'$or':[ {'cvFile3.fileFlag': ?5 }, {'cvFile3.fileFlag': ?6} ]}] }";
}
