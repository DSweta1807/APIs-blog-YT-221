package com.CWS.blog.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.CWS.blog.entity.User;

public  interface UserRepo extends CrudRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	//for putting data into database we create repository
	
		

}
